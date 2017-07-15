package perso.renaud.com.myfirstrxapp.presentation.post_detail.presenter;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;
import perso.renaud.com.myfirstrxapp.data.repository.base_class.Repository;
import perso.renaud.com.myfirstrxapp.presentation.post_detail.contracts.PostDetailContract;
import perso.renaud.com.myfirstrxapp.presentation.post_detail.model.PostDetailData;

/**
 * Created by renaudfavier on 15/07/2017.
 */

public class PostDetailPresenter implements PostDetailContract.Presenter {

    final int id;
    final PostDetailContract.View view;
    final Repository<JSPost> jsPostRepository;

    public PostDetailPresenter(int id, PostDetailContract.View view, Repository<JSPost> jsPostRepository) {
        this.id = id;
        this.view = view;
        this.jsPostRepository = jsPostRepository;
    }

    @Override public void resume() {
        jsPostRepository.get(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(a)
                .subscribeWith(view.getRefreshDataObserver());
    }

    @Override public void pause() {

    }

    @Override public void destroy() {

    }

    Function<JSPost, SingleSource<PostDetailData>> a = new Function<JSPost, SingleSource<PostDetailData>>() {

        @Override public SingleSource<PostDetailData> apply(JSPost jsPost) throws Exception {

            PostDetailData data = new PostDetailData();
            data.body = jsPost.body;
            data.title = jsPost.title;

            return Single.just(data);
        }
    };
}
