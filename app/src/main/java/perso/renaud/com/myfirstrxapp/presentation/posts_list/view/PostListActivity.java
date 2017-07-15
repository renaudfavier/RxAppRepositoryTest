package perso.renaud.com.myfirstrxapp.presentation.posts_list.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.TextView;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.List;
import perso.renaud.com.myfirstrxapp.R;
import perso.renaud.com.myfirstrxapp.ancestors.IBasePresenter;
import perso.renaud.com.myfirstrxapp.ancestors.PresentedActivity;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSUser;
import perso.renaud.com.myfirstrxapp.data.repository.base_class.Repository;
import perso.renaud.com.myfirstrxapp.data.repository.post.GenerifiedPostRepositoryImpl;
import perso.renaud.com.myfirstrxapp.data.repository.user.UserRepositoryImpl;
import perso.renaud.com.myfirstrxapp.network.Api;
import perso.renaud.com.myfirstrxapp.presentation.post_detail.view.PostDetailActivity;
import perso.renaud.com.myfirstrxapp.presentation.posts_list.contracts.FlowController;
import perso.renaud.com.myfirstrxapp.presentation.posts_list.contracts.PostListContract;
import perso.renaud.com.myfirstrxapp.presentation.posts_list.model.PostListItemData;
import perso.renaud.com.myfirstrxapp.presentation.posts_list.presenter.PostListPresenter;

public class PostListActivity extends PresentedActivity implements PostListContract.View, FlowController {

    private final String TAG = "PostListActivity";
    CompositeDisposable disposables = new CompositeDisposable();
    private RecyclerView recyclerView;
    private PostRecyclerAdapter adapter;

    TextView counterTextView;
    Button refreshButton;
    int i = 0;

    PostListContract.Presenter presenter;

    @Override public void routeToPostDetail(int postId) {
        startActivity(PostDetailActivity.newInstance(this, postId));
    }

    @Override public IBasePresenter getPresenter() {
        return presenter;
    }

    SingleObserver<List<PostListItemData>> refreshListObserver = null;

    @Override public SingleObserver<List<PostListItemData>> getRefreshListObserver() {

        if (refreshListObserver == null) {
            refreshListObserver = new SingleObserver<List<PostListItemData>>() {
                @Override public void onSubscribe(Disposable d) {

                }

                @Override public void onSuccess(List<PostListItemData> posts) {
                    adapter.updateData(posts);
                }

                @Override public void onError(Throwable e) {

                }
            };
        }

        return refreshListObserver;
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PostRecyclerAdapter(this, LayoutInflater.from(this));
        recyclerView.setAdapter(adapter);

        refreshButton = (Button) findViewById(R.id.refresh);
        counterTextView = (TextView) findViewById(R.id.counter);

        final Api.JsonPlaceholderInterface jsonPlaceHolder = Api.getInstance().jsonPlaceholder;

        //final PostRepository postRepository = PostRepositoryImpl.getInstance(jsonPlaceHolder);
        final Repository<JSPost> postRepository = GenerifiedPostRepositoryImpl.getInstance(jsonPlaceHolder);
        final Repository<JSUser> userRepository = UserRepositoryImpl.getInstance(jsonPlaceHolder);

        presenter = new PostListPresenter(this, this, postRepository, userRepository);


        //refreshButton.setOnClickListener(new View.OnClickListener() {
        //    @Override public void onClick(View v) {
        //        postRepository.getAll()
        //                .subscribeOn(Schedulers.io())
        //                .observeOn(AndroidSchedulers.mainThread())
        //                .subscribeWith(new SingleObserver<List<JSPost>>() {
        //                    @Override public void onSubscribe(Disposable d) {
        //
        //                    }
        //
        //                    @Override public void onSuccess(List<JSPost> posts) {
        //                        i++;
        //                        counterTextView.setText("" + i);
        //
        //                        //adapter.updateData(posts);
        //                    }
        //
        //                    @Override public void onError(Throwable e) {
        //
        //                    }
        //                });
        //    }
        //});
    }



    @Override protected void onStop() {
        super.onStop();
        disposables.dispose();
    }
}
