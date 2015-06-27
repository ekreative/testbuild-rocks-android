package rocks.testbuild.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.rightutils.rightutils.utils.CacheUtils;

import rocks.testbuild.R;
import rocks.testbuild.entities.Cache;
import rocks.testbuild.utils.SystemUtils;

public class SplashActivity extends AppCompatActivity {
	private int splashDelay = 2000;
	private boolean doInit = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);

		if(savedInstanceState == null) {
			this.doInit();
		} else {
			this.doInit = savedInstanceState.getBoolean(Boolean.class.getName(), true);
			this.splashDelay = savedInstanceState.getInt(Integer.class.getName(), this.splashDelay);
			if(this.doInit) {
				this.doInit();
			}
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(Boolean.class.getName(), this.doInit);
		outState.putInt(Integer.class.getName(), this.splashDelay);
	}

	private void doInit() {
		this.doInit = false;
		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				SystemUtils.getCache(SplashActivity.this, new CacheUtils.CallBack<Cache>() {
					@Override
					public boolean run(Cache cache) {
						if (cache != null && cache.getUser() != null) {
							Intent intent = new Intent(SplashActivity.this, MainActivity.class);
							startActivity(intent);
							SplashActivity.this.finish();
						} else {
							Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
							startActivity(intent);
							SplashActivity.this.finish();
						}
						return false;
					}
				});
			}
		}, (long)this.splashDelay);
	}

}
