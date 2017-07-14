package perso.renaud.com.myfirstrxapp.presentation.post_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import perso.renaud.com.myfirstrxapp.R;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;
import perso.renaud.com.myfirstrxapp.data.repository.post.GenerifiedPostRepositoryImpl;
import perso.renaud.com.myfirstrxapp.network.Api;
import perso.renaud.com.myfirstrxapp.presentation.posts_list.viewholders.PostViewHolder;

public class PostDetailActivity extends AppCompatActivity {

    private static final String EXTRA_POST_ID = "EXTRA_POST_ID";

    public static Intent newInstant(Context ctx, int id) {
        Intent intent = new Intent(ctx, PostDetailActivity.class);
        intent.putExtra(EXTRA_POST_ID, id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        final int postId = getIntent().getExtras().getInt(EXTRA_POST_ID);
        final PostViewHolder holder = new PostViewHolder(findViewById(R.id.item));

        final Api.JsonPlaceholderInterface jsonPlaceHolder = Api.getInstance().jsonPlaceholder;

//        final PostRepositoryImpl postRepository = PostRepositoryImpl.getInstance(jsonPlaceHolder);
        final GenerifiedPostRepositoryImpl postRepository = GenerifiedPostRepositoryImpl.getInstance(new Api.StandardRest<JSPost>() {
            @Override
            public Observable<List<JSPost>> getAll() {
                return jsonPlaceHolder.posts();
            }

            @Override
            public Observable<JSPost> get(long id) {
                return jsonPlaceHolder.post(id);
            }
        });

        postRepository.get(postId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new SingleObserver<JSPost>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(JSPost post) {

                holder.postId.setText(String.valueOf(post.id));
                holder.userId.setText(String.valueOf(post.userId));
                holder.title.setText(post.title);
                holder.body.setText(post.body);

            }

            @Override
            public void onError(Throwable e) {

            }
        });


    }
}
