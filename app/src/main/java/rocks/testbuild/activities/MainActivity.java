package rocks.testbuild.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.loaders.BaseLoader;
import com.rightutils.rightutils.loaders.LoaderListener;

import rocks.testbuild.R;
import rocks.testbuild.adapters.ProjectAdapter;
import rocks.testbuild.entities.Project;
import rocks.testbuild.loaders.GetProjectsLoader;
import rocks.testbuild.utils.Constants;
import rocks.testbuild.utils.SystemUtils;

public class MainActivity extends AppCompatActivity implements ProjectAdapter.ActionCallback {
	private final String TAG = MainActivity.class.getSimpleName();
	private RecyclerView recyclerView;
	private RecyclerView.LayoutManager layoutManager;
	private RightList<Project> projects;
	private ProjectAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
		recyclerView.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(MainActivity.this);
		recyclerView.setLayoutManager(layoutManager);

		final GetProjectsLoader loader = new GetProjectsLoader(MainActivity.this, Constants.LOADER_ID_GET_PROJECTS);
		loader.setLoaderListener(new LoaderListener<Boolean>() {
			@Override
			public void onLoadFinished(FragmentActivity fragmentActivity, Fragment fragment, Boolean aBoolean, BaseLoader<Boolean> baseLoader) {
				if (aBoolean) {
					projects = loader.getProjects();
					adapter = new ProjectAdapter(MainActivity.this, projects, MainActivity.this);
					recyclerView.setAdapter(adapter);
				} else {
					SystemUtils.handleError(MainActivity.this, loader.getError());
				}
			}

			@Override
			public void onCancelLoad() {
				loader.cancelLoad();
			}
		});
		loader.execute();
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.menu_main, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//
//		//noinspection SimplifiableIfStatement
//		if (id == R.id.action_settings) {
//			return true;
//		}
//
//		return super.onOptionsItemSelected(item);
//	}

	@Override
	public void onSelect(Project project) {
		Intent intent = new Intent(MainActivity.this, ProjectActivity.class);
		intent.putExtra(Project.class.getSimpleName(), project);
		startActivity(intent);
	}
}
