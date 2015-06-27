package rocks.testbuild.loaders;

import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.rightutils.rightutils.collections.RightList;
import com.rightutils.rightutils.loaders.BaseLoader;
import com.rightutils.rightutils.utils.CacheUtils;

import ch.boye.httpclientandroidlib.HttpResponse;
import ch.boye.httpclientandroidlib.HttpStatus;
import ch.boye.httpclientandroidlib.util.EntityUtils;
import rocks.testbuild.R;
import rocks.testbuild.entities.Cache;
import rocks.testbuild.entities.Project;
import rocks.testbuild.entities.ResponseError;
import rocks.testbuild.network.RightRequest;
import rocks.testbuild.utils.Constants;
import rocks.testbuild.utils.SystemUtils;

import static com.rightutils.rightutils.utils.RightUtils.isOnline;

/**
 * Created by nnet on 4/29/15.
 */
public class GetProjectsLoader extends BaseLoader<Boolean> {
	private final static String TAG = GetProjectsLoader.class.getSimpleName();

	private Cache cache;

	private ResponseError error;
	private RightList<Project> projects;

	public GetProjectsLoader(FragmentActivity fragmentActivity, int loaderId) {
		super(fragmentActivity, loaderId);
	}

	@Override
	public Boolean loadInBackground() {
		SystemUtils.getCache(getContext(), new CacheUtils.CallBack<Cache>() {
			@Override
			public boolean run(Cache cache) {
				GetProjectsLoader.this.cache = cache;
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
//			Header header = new BasicHeader("apikey", cache.getUser().getApiKey());

			Uri.Builder builder = Uri.parse(Constants.GET_PROJECTS_URL).buildUpon();

//			response = request.getHttpResponse(builder.toString(), header);
			response = request.getHttpResponse(builder.toString());
			int status = response.getStatusLine().getStatusCode();
			Log.i(TAG, "status code: " + String.valueOf(status));
			if (status == HttpStatus.SC_OK) {
				String result = EntityUtils.toString(response.getEntity());
				Log.i(TAG, result);
				DataContent dataContent = SystemUtils.MAPPER.readValue(result, DataContent.class);
				projects = dataContent.projects;
				return true;
			} else {
				error = SystemUtils.MAPPER.readValue(response.getEntity().getContent(), ResponseError.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public RightList<Project> getProjects() {
		return projects;
	}

	public ResponseError getError() {
		return error;
	}

	private static class DataContent {
		public RightList<Project> projects;
	}

}
