package rocks.testbuild.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rightutils.rightutils.collections.RightList;

import rocks.testbuild.R;
import rocks.testbuild.entities.Build;

/**
 * Created by nnet on 6/27/15.
 */
public class BuildAdapter extends RecyclerView.Adapter<BuildAdapter.ViewHolder> {
	private static final String TAG = BuildAdapter.class.getSimpleName();
	private final Context context;
	private RightList<Build> builds;

	public BuildAdapter(Context context, RightList<Build> builds) {
		this.context = context;
		this.builds = builds;
	}

	@Override
	public BuildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_build, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		final Build build = (Build) builds.get(position);
		holder.name.setText(build.getBuild());
//		holder.date.setText(Constants.FEEDBACK_DATE_FORMAT.format(build.get());
		holder.date.setText(build.getPlist());
	}

	@Override
	public int getItemCount() {
		return builds.size();
	}

	public Context getContext() {
		return context;
	}

	public RightList<Build> getProjects() {
		return builds;
	}

	public class ViewHolder extends RecyclerView.ViewHolder{
		TextView name, date;

		public ViewHolder(View itemView) {
			super(itemView);
			name = (TextView) itemView.findViewById(R.id.txt_name);
			date = (TextView) itemView.findViewById(R.id.txt_date);
		}
	}
}
