package perso.renaud.com.myfirstrxapp.presentation.post_detail.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import perso.renaud.com.myfirstrxapp.R;
import perso.renaud.com.myfirstrxapp.ancestors.IBasePresenter;
import perso.renaud.com.myfirstrxapp.ancestors.PresentedActivity;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;
import perso.renaud.com.myfirstrxapp.data.repository.base_class.Repository;
import perso.renaud.com.myfirstrxapp.data.repository.GenerifiedPostRepositoryImpl;
import perso.renaud.com.myfirstrxapp.network.Api;
import perso.renaud.com.myfirstrxapp.presentation.post_detail.contracts.PostDetailContract;
import perso.renaud.com.myfirstrxapp.presentation.post_detail.model.PostDetailData;
import perso.renaud.com.myfirstrxapp.presentation.post_detail.presenter.PostDetailPresenter;

public class PostDetailActivity extends PresentedActivity implements PostDetailContract.View {

    private static final String EXTRA_POST_ID = "EXTRA_POST_ID";

    public static Intent newInstance(Context ctx, int id) {
        Intent intent = new Intent(ctx, PostDetailActivity.class);
        intent.putExtra(EXTRA_POST_ID, id);
        return intent;
    }

    PostDetailContract.Presenter presenter;

    @Override public IBasePresenter getPresenter() {
        return presenter;
    }

    SingleObserver<PostDetailData> refreshDetailsObserver;

    @Override public SingleObserver<PostDetailData> getRefreshDataObserver() {
        if (refreshDetailsObserver == null) {
            refreshDetailsObserver = new SingleObserver<PostDetailData>() {
                @Override public void onSubscribe(Disposable d) {

                }

                @Override public void onSuccess(PostDetailData value) {
                    titleTextView.setText(value.title);
                    bodyTextView.setText(value.body);
                }

                @Override public void onError(Throwable e) {

                }
            };
        }

        return refreshDetailsObserver;
    }

    TextView titleTextView;
    TextView bodyTextView;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);

        final int postId = getIntent().getExtras().getInt(EXTRA_POST_ID);

        titleTextView = (TextView) findViewById(R.id.title);
        bodyTextView = (TextView) findViewById(R.id.body);

        final Api.JsonPlaceholderInterface jsonPlaceHolder = Api.getInstance().jsonPlaceholder;

        final Repository<JSPost> postRepository = GenerifiedPostRepositoryImpl.getInstance(jsonPlaceHolder);

        presenter = new PostDetailPresenter(postId, this, postRepository);
    }
}
