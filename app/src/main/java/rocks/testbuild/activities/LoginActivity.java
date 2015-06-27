package rocks.testbuild.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.rightutils.rightutils.loaders.BaseLoader;
import com.rightutils.rightutils.loaders.LoaderListener;
import com.rightutils.rightutils.utils.CacheUtils;

import rocks.testbuild.R;
import rocks.testbuild.entities.Cache;
import rocks.testbuild.loaders.LogInLoader;
import rocks.testbuild.utils.Constants;
import rocks.testbuild.utils.SystemUtils;

public class LoginActivity extends AppCompatActivity {
	private final String TAG = LoginActivity.class.getSimpleName();
	private TextInputLayout usernameTextInputLayout, passwordTextInputLayout;
	private EditText fUsername, fPassword;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		usernameTextInputLayout = (TextInputLayout) findViewById(R.id.til_username);
		passwordTextInputLayout = (TextInputLayout) findViewById(R.id.til_password);

		fUsername = (EditText) findViewById(R.id.f_username);
		fPassword = (EditText) findViewById(R.id.f_password);
	}

	public void logIn(View someView) {
		if (isValid()) {
			final LogInLoader loader = new LogInLoader(fUsername.getText().toString(), fPassword.getText().toString(), LoginActivity.this, Constants.LOADER_ID_LOG_IN);
			loader.setLoaderListener(new LoaderListener<Boolean>() {
				@Override
				public void onLoadFinished(FragmentActivity fragmentActivity, Fragment fragment, Boolean aBoolean, BaseLoader<Boolean> baseLoader) {
					if (aBoolean) {
						SystemUtils.getCache(LoginActivity.this, new CacheUtils.CallBack<Cache>() {
							@Override
							public boolean run(Cache cache) {
								cache.setUser(loader.getUser());
								startMainActivity();
								return true;
							}
						});
					} else {
						SystemUtils.handleError(LoginActivity.this, loader.getError());
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

	private void startMainActivity() {
		Intent intent = new Intent(LoginActivity.this, MainActivity.class);
		startActivity(intent);
		LoginActivity.this.finish();
	}

	private boolean isValid() {
		return SystemUtils.isEditTextFilled(LoginActivity.this, fUsername, usernameTextInputLayout, R.string.username_empty) &
				SystemUtils.isEditTextFilled(LoginActivity.this, fPassword, passwordTextInputLayout, R.string.password_empty);
	}

}
