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
import rocks.testbuild.entities.Project;
import rocks.testbuild.utils.CircleTransform;

/**
 * Created by nnet on 6/27/15.
 */
public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ViewHolder> {
	private static final String TAG = ProjectAdapter.class.getSimpleName();
	private final Context context;
	private RightList<Project> projects;
	private final ActionCallback callback;

	public ProjectAdapter(Context context, RightList<Project> projects, ActionCallback callback) {
		this.context = context;
		this.projects = projects;
		this.callback = callback;
	}

	@Override
	public ProjectAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_project, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		final Project project = (Project) projects.get(position);
		holder.name.setText(project.getName());
		holder.comment.setText(project.getDescription());
		Picasso.with(getContext())
//				TODO
				.load("http://lorempixel.com/57/57/cats/")
				.transform(new CircleTransform())
				.placeholder(R.drawable.ph_list) // optional
				.error(R.drawable.ph_list)         // optional
				.into(holder.icon);
		holder.itemView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (callback != null) {
					callback.onSelect(project);
				}
			}
		});
	}

	@Override
	public int getItemCount() {
		return projects.size();
	}

	public interface ActionCallback {
		void onSelect(Project project);
	}

	public Context getContext() {
		return context;
	}

	public RightList<Project> getProjects() {
		return projects;
	}

	public class ViewHolder extends RecyclerView.ViewHolder{
		TextView name, comment;
		ImageView icon;
		View itemView;

		public ViewHolder(View itemView) {
			super(itemView);
			this.itemView = itemView;
			name = (TextView) itemView.findViewById(R.id.txt_name);
			comment = (TextView) itemView.findViewById(R.id.txt_comment);
			icon = (ImageView) itemView.findViewById(R.id.img_icon);
		}
	}
}
