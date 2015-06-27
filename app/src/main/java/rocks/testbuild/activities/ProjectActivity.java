package rocks.testbuild.activities;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.loaders.BaseLoader;
import com.rightutils.rightutils.loaders.LoaderListener;
import com.squareup.picasso.Picasso;

import java.io.File;

import rocks.testbuild.R;
import rocks.testbuild.adapters.BuildAdapter;
import rocks.testbuild.entities.Build;
import rocks.testbuild.entities.Project;
import rocks.testbuild.loaders.GetProjectBuildsLoader;
import rocks.testbuild.utils.Constants;
import rocks.testbuild.utils.SystemUtils;

/**
 * Created by nnet on 6/27/15.
 */
public class ProjectActivity extends AppCompatActivity implements BuildAdapter.ActionCallback {
	private CoordinatorLayout rootLayout;
	private RecyclerView recyclerView;
	private LinearLayoutManager layoutManager;
	private DownloadManager downloadManager =null;
	private long lastDownload = -1L;
	private RightList<Build> builds;
	private BuildAdapter adapter;
	private Build lastBuild;
	private Project project;
	private ImageView imgHeader;
	private CollapsingToolbarLayout collapsingToolbarLayout;
	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		initToolbar();

		rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
		imgHeader = (ImageView) findViewById(R.id.img_header);

		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(ProjectActivity.this);
		recyclerView.setLayoutManager(layoutManager);

		collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);

		if (getIntent().hasExtra(Project.class.getSimpleName())) {
			project = getIntent().getExtras().getParcelable(Project.class.getSimpleName());
			collapsingToolbarLayout.setTitle(project.getName());

			downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
			registerReceiver(onComplete,
					new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
//			registerReceiver(onNotificationClick,
//					new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED));

			final GetProjectBuildsLoader loader = new GetProjectBuildsLoader(project.getId(), ProjectActivity.this, Constants.LOADER_ID_GET_PROJECT_BUILDS);
			loader.setLoaderListener(new LoaderListener<Boolean>() {
				@Override
				public void onLoadFinished(FragmentActivity fragmentActivity, Fragment fragment, Boolean aBoolean, BaseLoader<Boolean> baseLoader) {
					if (aBoolean) {
						builds = loader.getBuilds();
						if (builds != null && !builds.isEmpty()) {
							lastBuild = builds.getLast();
							fillInProjectData(lastBuild);
							adapter = new BuildAdapter(ProjectActivity.this, builds, ProjectActivity.this);
							recyclerView.setAdapter(adapter);
						}
					} else {
						SystemUtils.handleError(ProjectActivity.this, loader.getError());
					}
				}

				@Override
				public void onCancelLoad() {
					loader.cancelLoad();
				}
			});
			loader.execute();
		}
	}

	private void initToolbar() {
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
	}

	public void installLatestBuild(View someView) {
		if (lastBuild == null) {
			Snackbar.make(rootLayout, "Try later...", Snackbar.LENGTH_LONG)
					.show();
		} else {
			// does something very interesting
			removeInstalledApk(lastBuild);
		}
	}

	private void removeInstalledApk(Build build) {
		Intent intent;
		PackageManager manager = getPackageManager();
		try {
			intent = manager.getLaunchIntentForPackage(build.getBundleId());
			if (intent == null)
				throw new PackageManager.NameNotFoundException();
			intent = new Intent(Intent.ACTION_DELETE);
			intent.setData(Uri.parse("package:" + build.getBundleId()));
			startActivityForResult(intent, Constants.REQUEST_CODE_REMOVED_INSTALLED_APK);
		} catch (PackageManager.NameNotFoundException e) {
			downloadLatestApk(lastBuild);
		}
	}

	private void downloadLatestApk(Build build) {
		Uri uri=Uri.parse(build.getUrl());

		Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
				.mkdirs();

		lastDownload=
				downloadManager.enqueue(new DownloadManager.Request(uri)
						.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
								DownloadManager.Request.NETWORK_MOBILE)
						.setAllowedOverRoaming(false)
						.setTitle(build.getBuild())
						.setDescription(build.getVersion())
						.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
								build.getBuildUrl()));

		Snackbar.make(rootLayout, "Downloading latest build...", Snackbar.LENGTH_SHORT)
				.setAction("Undo", new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						downloadManager.remove(lastDownload);
					}
				})
				.show();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case Constants.REQUEST_CODE_REMOVED_INSTALLED_APK:
				if (resultCode == RESULT_OK) {
					Snackbar.make(rootLayout, "Apk was removed successfully!", Snackbar.LENGTH_SHORT)
							.show();
				}

				downloadLatestApk(lastBuild);
				break;
		}
	}

	BroadcastReceiver onComplete=new BroadcastReceiver() {
		public void onReceive(Context ctxt, Intent intent) {
			Toast.makeText(ctxt, "Wow! Downloaded successfully", Toast.LENGTH_SHORT).show();
			Intent installationIntent = new Intent(Intent.ACTION_VIEW);
			installationIntent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator+ lastBuild.getBuildUrl())), "application/vnd.android.package-archive");
			installationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(installationIntent);
		}
	};

//	BroadcastReceiver onNotificationClick=new BroadcastReceiver() {
//		public void onReceive(Context ctxt, Intent intent) {
//			Toast.makeText(ctxt, "Ummmm...hi!", Toast.LENGTH_SHORT).show();
//		}
//	};

	private void fillInProjectData(Build build) {
		Picasso.with(ProjectActivity.this)
				.load(build.getPicture())
				.placeholder(R.drawable.ph_project) // optional
				.error(R.drawable.ph_project)         // optional
				.into(imgHeader);
	}

	@Override
	public void onDownload(Build build) {
		removeInstalledApk(build);
	}
}
