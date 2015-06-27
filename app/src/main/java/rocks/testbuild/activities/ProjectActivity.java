package rocks.testbuild.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import rocks.testbuild.R;

/**
 * Created by nnet on 6/27/15.
 */
public class ProjectActivity extends AppCompatActivity {
	private CoordinatorLayout rootLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		rootLayout = (CoordinatorLayout) findViewById(R.id.rootLayout);
	}

	public void installLatestBuild(View someView) {
		// does something very interesting
		Snackbar.make(rootLayout, "Hello. I am Snackbar!", Snackbar.LENGTH_SHORT)
				.setAction("Undo", new View.OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				})
				.show();
	}
}
