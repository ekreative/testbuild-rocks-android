package rocks.testbuild.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import rocks.testbuild.R;

public class LoginActivity extends AppCompatActivity {
	private final String TAG = LoginActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
	}

}
