package rocks.testbuild.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import rocks.testbuild.R;

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
	protected void onResume() {
		super.onResume();
		View decorView = getWindow().getDecorView();
		decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
				| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
				| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
				| View.SYSTEM_UI_FLAG_FULLSCREEN
				| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
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
				Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
				startActivity(intent);
				SplashActivity.this.finish();
			}
		}, (long)this.splashDelay);
	}

}
