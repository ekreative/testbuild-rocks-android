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
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.loaders.BaseLoader;
import com.rightutils.rightutils.loaders.LoaderListener;

import rocks.testbuild.R;
import rocks.testbuild.adapters.BuildAdapter;
import rocks.testbuild.entities.Build;
import rocks.testbuild.loaders.GetProjectBuildsLoader;
import rocks.testbuild.utils.Constants;
import rocks.testbuild.utils.SystemUtils;

/**
 * Created by nnet on 6/27/15.
 */
public class ProjectActivity extends AppCompatActivity {
	private CoordinatorLayout rootLayout;
	private RecyclerView recyclerView;
	private LinearLayoutManager layoutManager;
	private DownloadManager mgr=null;
	private long lastDownload=-1L;
	private RightList<Build> builds;
	private BuildAdapter adapter;
	private Build lastBuild;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);

		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(ProjectActivity.this);
		recyclerView.setLayoutManager(layoutManager);

		mgr=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
		registerReceiver(onComplete,
				new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
		registerReceiver(onNotificationClick,
				new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED));

		final GetProjectBuildsLoader loader = new GetProjectBuildsLoader(314, ProjectActivity.this, Constants.LOADER_ID_GET_PROJECT_BUILDS);
		loader.setLoaderListener(new LoaderListener<Boolean>() {
			@Override
			public void onLoadFinished(FragmentActivity fragmentActivity, Fragment fragment, Boolean aBoolean, BaseLoader<Boolean> baseLoader) {
				if (aBoolean) {
					builds = loader.getBuilds();
					lastBuild = builds.getLast();
					adapter = new BuildAdapter(ProjectActivity.this, builds);
					recyclerView.setAdapter(adapter);
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

	public void installLatestBuild(View someView) {
		// does something very interesting
		removeInstalledApk("com.inthecheesefactory.lab.designlibrary");
	}

	private void removeInstalledApk(String bundleId) {
		Intent intent;
		PackageManager manager = getPackageManager();
		try {
			intent = manager.getLaunchIntentForPackage(bundleId);
			if (intent == null)
				throw new PackageManager.NameNotFoundException();
			intent = new Intent(Intent.ACTION_DELETE);
			intent.setData(Uri.parse("package:" + bundleId));
			startActivityForResult(intent, Constants.REQUEST_CODE_REMOVED_INSTALLED_APK);
		} catch (PackageManager.NameNotFoundException e) {
			downloadLatestApk(lastBuild);
		}
	}

	private void downloadLatestApk(Build build) {
		Uri uri=Uri.parse(build.getUrl());
		int start = build.getUrl().lastIndexOf("/") + 1;
		String fileName = build.getUrl().substring(start);

		Environment
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
				.mkdirs();

		lastDownload=
				mgr.enqueue(new DownloadManager.Request(uri)
						.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI |
								DownloadManager.Request.NETWORK_MOBILE)
						.setAllowedOverRoaming(false)
						.setTitle(build.getBuild())
						.setDescription(build.getVersion())
						.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,
								fileName));

		Snackbar.make(rootLayout, "Downloading latest build...", Snackbar.LENGTH_SHORT)
				.setAction("Undo", new View.OnClickListener() {
					@Override
					public void onClick(View v) {

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
		}
	};

	BroadcastReceiver onNotificationClick=new BroadcastReceiver() {
		public void onReceive(Context ctxt, Intent intent) {
			Toast.makeText(ctxt, "Ummmm...hi!", Toast.LENGTH_SHORT).show();
		}
	};
}
