package rocks.testbuild.application;

import android.app.Application;

import rocks.testbuild.db.DBUtils;

/**
 * Created by nnet on 6/27/15.
 */
public class TestBuildApp extends Application {
	private static final String TAG = TestBuildApp.class.getSimpleName();
	public static DBUtils dbUtils;

	@Override
	public void onCreate() {
		super.onCreate();
		dbUtils = DBUtils.newInstance(getApplicationContext(), "apps-v2.sqlite", 1);
	}
}
