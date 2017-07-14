package perso.renaud.com.myfirstrxapp.presentation.posts_list.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import perso.renaud.com.myfirstrxapp.R;

/**
 * Created by renaud on 19/03/17.
 */

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView userId;
    public TextView postId;
    public TextView title;
    public TextView body;

    public View container;

    public PostViewHolder(View itemView) {
        super(itemView);

        container = itemView;
        userId = (TextView) itemView.findViewById(R.id.userId);
        postId = (TextView) itemView.findViewById(R.id.postId);
        title = (TextView) itemView.findViewById(R.id.title);
        body = (TextView) itemView.findViewById(R.id.body);

    }
}
