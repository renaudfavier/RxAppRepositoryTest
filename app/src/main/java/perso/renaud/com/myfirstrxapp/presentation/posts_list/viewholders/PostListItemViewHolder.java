package perso.renaud.com.myfirstrxapp.presentation.posts_list.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import perso.renaud.com.myfirstrxapp.R;

/**
 * Created by renaud on 19/03/17.
 */

public class PostListItemViewHolder extends RecyclerView.ViewHolder {

    public TextView userName;
    public TextView title;

    public View container;

    public PostListItemViewHolder(View itemView) {
        super(itemView);

        container = itemView;
        userName = (TextView) itemView.findViewById(R.id.userName);
        title = (TextView) itemView.findViewById(R.id.title);
    }
}
