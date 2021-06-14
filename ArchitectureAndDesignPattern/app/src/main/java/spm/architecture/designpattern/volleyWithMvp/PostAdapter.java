package spm.architecture.designpattern.volleyWithMvp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import spm.architecture.designpattern.R;
import spm.architecture.designpattern.volleyWithMvp.models.Post;
import spm.architecture.designpattern.volleyWithMvp.models.Posts;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

	private List<Post> posts;
	private int rowLayout;

	public PostAdapter(Posts posts, int rowLayout) {
		this.posts = posts.getPosts();
		this.rowLayout = rowLayout;
	}

	@Override
	public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
		return new PostViewHolder(view);
	}

	@Override
	public void onBindViewHolder(PostViewHolder holder, final int position) {
		holder.name.setText(posts.get(position).getName());
		holder.title.setText(posts.get(position).getTitle());
		holder.category.setText(posts.get(position).getCategory());
		holder.url.setText(posts.get(position).getUrl());
	}

	@Override
	public int getItemCount() {
		return posts.size();
	}

	static class PostViewHolder extends RecyclerView.ViewHolder {
		TextView name;
		TextView title;
		TextView category;
		TextView url;

		PostViewHolder(View v) {
			super(v);
			name = (TextView) v.findViewById(R.id.name);
			title = (TextView) v.findViewById(R.id.title);
			category = (TextView) v.findViewById(R.id.category);
			url = (TextView) v.findViewById(R.id.url);
		}
	}
}