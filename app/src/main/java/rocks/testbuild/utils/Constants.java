package rocks.testbuild.utils;

import java.text.SimpleDateFormat;

/**
 * Created by nnet on 6/27/15.
 */
public class Constants {
	public static final SimpleDateFormat REDMINE_SERVER_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"); //2015-03-25T00:00:00+0000
	public static final SimpleDateFormat SERVER_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'"); //2015-03-25T00:00:00+0000
	public static final SimpleDateFormat PROJECT_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy"); //Mon 03/10/2014 - 13:45

	private static final String BASE_URL = "http://testbuild.rocks/";
	private static final String BASE_API_URL = BASE_URL + "api/";

	public static final String REGISTER_DEVICE_URL = "";

	public static final String LOG_IN_URL = BASE_URL + "login";

	public static final String GET_PROJECTS_URL = BASE_API_URL       + "projects.json";
	public static final String GET_PROJECT_SINGLE_BUILD_URL = BASE_API_URL + "builds/%d.json";
	public static final String GET_PROJECT_BUILDS_URL = BASE_API_URL + "builds/%d/android";

	public final static int LOADER_ID_LOG_IN = 1;
	public final static int LOADER_ID_GET_PROJECTS = 2;
	public final static int LOADER_ID_GET_PROJECT_BUILDS = 3;

	public final static int REQUEST_CODE_REMOVED_INSTALLED_APK = 10001;
}
