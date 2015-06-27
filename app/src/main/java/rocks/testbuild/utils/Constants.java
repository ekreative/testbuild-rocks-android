package rocks.testbuild.utils;

import java.text.SimpleDateFormat;

/**
 * Created by nnet on 6/27/15.
 */
public class Constants {
	public static final SimpleDateFormat SERVER_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ"); //2015-03-25T00:00:00+0000

	private static final String BASE_URL = "http://local.testbuild.rocks/app_dev.php/api/";

	public static final String REGISTER_DEVICE_URL = "";

	public static final String GET_PROJECTS_URL = BASE_URL       + "projects";
	public static final String GET_PROJECT_BUILDS_URL = BASE_URL + "builds/%d";

	public static int LOADER_ID_GET_PROJECTS = 1;
}
