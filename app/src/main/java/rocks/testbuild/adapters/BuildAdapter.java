package rocks.testbuild.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rightutils.rightutils.collections.RightList;
import com.squareup.picasso.Picasso;

import rocks.testbuild.R;
import rocks.testbuild.entities.Build;
import rocks.testbuild.utils.CircleTransform;

/**
 * Created by nnet on 6/27/15.
 */
public class BuildAdapter extends RecyclerView.Adapter<BuildAdapter.ViewHolder> {
	private static final String TAG = BuildAdapter.class.getSimpleName();
	private final Context context;
	private RightList<Build> builds;
	private final ActionCallback callback;

	public BuildAdapter(Context context, RightList<Build> builds, BuildAdapter.ActionCallback callback) {
		this.context = context;
		this.builds = builds;
		this.callback = callback;
	}

	@Override
	public BuildAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_build, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		final Build build = (Build) builds.get(position);
		holder.name.setText(build.getName());
//		holder.date.setText(Constants.FEEDBACK_DATE_FORMAT.format(build.get());
		holder.date.setText(build.getCreatedName());
		holder.comment.setText(build.getComment());
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (callback != null) {
					callback.onDownload(build);
				}
			}
		});
		Picasso.with(getContext())
//				TODO
				.load(build.getPicture())
				.transform(new CircleTransform())
				.placeholder(R.drawable.ph_list) // optional
				.error(R.drawable.ph_list)         // optional
				.into(holder.icon);
	}

	@Override
	public int getItemCount() {
		return builds.size();
	}

	public interface ActionCallback {
		void onDownload(Build build);
	}

	public Context getContext() {
		return context;
	}

	public RightList<Build> getProjects() {
		return builds;
	}

	public class ViewHolder extends RecyclerView.ViewHolder{
		TextView name, date, comment;
		View itemView;
		ImageView icon;

		public ViewHolder(View itemView) {
			super(itemView);
			this.itemView = itemView;
			this.name = (TextView) itemView.findViewById(R.id.txt_name);
			this.date = (TextView) itemView.findViewById(R.id.txt_date);
			this.comment = (TextView) itemView.findViewById(R.id.txt_comment);
			this.icon = (ImageView) itemView.findViewById(R.id.img_icon);
		}
	}
}
