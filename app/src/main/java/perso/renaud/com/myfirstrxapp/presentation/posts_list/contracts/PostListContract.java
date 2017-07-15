package perso.renaud.com.myfirstrxapp.presentation.posts_list.contracts;

import io.reactivex.SingleObserver;
import java.util.List;
import perso.renaud.com.myfirstrxapp.ancestors.IBasePresenter;
import perso.renaud.com.myfirstrxapp.ancestors.IBaseView;
import perso.renaud.com.myfirstrxapp.presentation.post_detail.model.PostDetailData;
import perso.renaud.com.myfirstrxapp.presentation.posts_list.model.PostListItemData;

/**
 * Created by renaudfavier on 15/07/2017.
 */

public interface PostListContract {

    interface View extends IBaseView {

        SingleObserver<List<PostListItemData>> getRefreshListObserver();
    }

    interface Presenter extends IBasePresenter {

        void click(int id);
    }
}
