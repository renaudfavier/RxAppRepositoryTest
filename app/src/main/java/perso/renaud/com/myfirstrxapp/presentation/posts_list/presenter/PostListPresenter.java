package perso.renaud.com.myfirstrxapp.presentation.posts_list.presenter;

import android.util.Log;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSUser;
import perso.renaud.com.myfirstrxapp.data.repository.base_class.Repository;
import perso.renaud.com.myfirstrxapp.presentation.posts_list.contracts.FlowController;
import perso.renaud.com.myfirstrxapp.presentation.posts_list.contracts.PostListContract;
import perso.renaud.com.myfirstrxapp.presentation.posts_list.model.PostListItemData;

/**
 * Created by renaudfavier on 15/07/2017.
 */

public class PostListPresenter implements PostListContract.Presenter {

    public static final String TAG = "PostListPresenter";

    final PostListContract.View view;
    final FlowController flowController;
    final Repository<JSPost> postRepository;
    final Repository<JSUser> userRepository;

    public PostListPresenter(PostListContract.View view, FlowController flowController,
            Repository<JSPost> postRepository, Repository<JSUser> userRepository) {
        this.view = view;
        this.flowController = flowController;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override public void resume() {

        Single.zip(postRepository.getAll().subscribeOn(Schedulers.io()),
                userRepository.getAll().subscribeOn(Schedulers.io()), mapToPostDeailData())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(view.getRefreshListObserver());
    }

    @Override public void click(int id) {
        flowController.routeToPostDetail(id);
    }

    @Override public void pause() {

    }

    @Override public void destroy() {

    }

    private BiFunction<List<JSPost>, List<JSUser>, List<PostListItemData>> mapToPostDeailData() {
        return new BiFunction<List<JSPost>, List<JSUser>, List<PostListItemData>>() {

            @Override public List<PostListItemData> apply(List<JSPost> jsPosts, List<JSUser> jsUsers) throws Exception {

                Log.i(TAG, "mapping, jsPosts.size() : " + jsPosts.size() + " jsUsers.size() : " + jsUsers.size());

                List<PostListItemData> retour = new ArrayList<PostListItemData>();

                for (JSPost post : jsPosts) {
                    PostListItemData data = new PostListItemData();
                    data.title = post.title;
                    data.id = post.id;
                    for (JSUser user : jsUsers) {

                        Log.i("ren", "user.id : " + user.id + "   post.userId : " + post.userId);

                        if (user.id == post.userId) {
                            data.userName = user.name;
                            Log.e(TAG, user.toLog());
                            break;
                        }
                    }
                    retour.add(data);
                }

                return retour;
            }
        };
    }
}
