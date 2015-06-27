package rocks.testbuild.loaders;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.rightutils.rightutils.loaders.BaseLoader;
import com.rightutils.rightutils.utils.CacheUtils;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import ch.boye.httpclientandroidlib.HttpResponse;
import ch.boye.httpclientandroidlib.HttpStatus;
import ch.boye.httpclientandroidlib.util.EntityUtils;
import rocks.testbuild.R;
import rocks.testbuild.entities.Cache;
import rocks.testbuild.entities.ResponseError;
import rocks.testbuild.entities.User;
import rocks.testbuild.network.RightRequest;
import rocks.testbuild.utils.Constants;
import rocks.testbuild.utils.SystemUtils;

import static com.rightutils.rightutils.utils.RightUtils.isOnline;

/**
 * Created by nnet on 4/29/15.
 */
public class LogInLoader extends BaseLoader<Boolean> {
	private final static String TAG = LogInLoader.class.getSimpleName();
	private final String username, password;

	private Cache cache;

	private ResponseError error;
	private User user;

	public LogInLoader(String username, String password, FragmentActivity fragmentActivity, int loaderId) {
		super(fragmentActivity, loaderId);
		this.username = username;
		this.password = password;
	}

	@Override
	public Boolean loadInBackground() {
		SystemUtils.getCache(getContext(), new CacheUtils.CallBack<Cache>() {
			@Override
			public boolean run(Cache cache) {
				LogInLoader.this.cache = cache;
				return false;
			}
		});
		HttpResponse response = null;
		try {
			if (!isOnline(getContext())) {
				error = new ResponseError(getContext().getString(R.string.no_internet_connection));
				return false;
			}
			RightRequest request = new RightRequest();

			Uri.Builder builder = Uri.parse(Constants.LOG_IN_URL).buildUpon();

			response = request.postHttpResponse(builder.toString(), getJson());
			int status = response.getStatusLine().getStatusCode();
			Log.i(TAG, "status code: " + String.valueOf(status));
			if (status == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(response.getEntity());
				ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
				objectMapper.setDateFormat(Constants.REDMINE_SERVER_DATE_FORMAT);
				DataContent dataContent = objectMapper.readValue(result, DataContent.class);
				user = dataContent.user;
				Log.i(TAG, "APIkey: " + user.getApiKey());
				return true;
			} else {
				error = SystemUtils.MAPPER.readValue(response.getEntity().getContent(), ResponseError.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private String getJson() {
		JSONObject jsonObject = new JSONObject();
		try {
			JSONObject login = new JSONObject();
			login.accumulate("username", username);
			login.accumulate("password", password);
			jsonObject.accumulate("login", login);
			return jsonObject.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	public User getUser() {
		return user;
	}

	public ResponseError getError() {
		return error;
	}

	private static class DataContent {
		public User user;
	}

}
