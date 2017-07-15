package perso.renaud.com.myfirstrxapp.presentation.posts_list.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import perso.renaud.com.myfirstrxapp.R;
import perso.renaud.com.myfirstrxapp.presentation.posts_list.contracts.FlowController;
import perso.renaud.com.myfirstrxapp.presentation.posts_list.model.PostListItemData;
import perso.renaud.com.myfirstrxapp.presentation.posts_list.viewholders.PostListItemViewHolder;

/**
 * Created by renaudfavier on 15/07/2017.
 */
public class PostRecyclerAdapter extends RecyclerView.Adapter<PostListItemViewHolder> {

    private FlowController flowController;
    List<PostListItemData> dataset = new ArrayList<>();

    final LayoutInflater inflater;

    public PostRecyclerAdapter(FlowController flowController, LayoutInflater inflater) {
        this.flowController = flowController;
        this.inflater = inflater;
    }

    public void updateData(List<PostListItemData> posts) {
        dataset = posts;
        notifyDataSetChanged();
    }

    @Override public PostListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PostListItemViewHolder(inflater.inflate(R.layout.listitem_post, parent, false));
    }

    @Override public void onBindViewHolder(PostListItemViewHolder holder, int position) {
        final PostListItemData post = dataset.get(position);
        //Log.i("renaud", "post == null ? => " + (post == null) + "\n" + post.toLog());
        holder.userName.setText(post.userName);
        holder.title.setText(post.title);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                flowController.routeToPostDetail(post.id);
            }
        });
    }

    @Override public int getItemCount() {
        return dataset.size();
    }
}
