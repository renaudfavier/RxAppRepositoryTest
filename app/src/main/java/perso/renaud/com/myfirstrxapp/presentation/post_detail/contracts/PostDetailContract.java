package perso.renaud.com.myfirstrxapp.presentation.post_detail.contracts;

import io.reactivex.SingleObserver;
import perso.renaud.com.myfirstrxapp.ancestors.IBasePresenter;
import perso.renaud.com.myfirstrxapp.ancestors.IBaseView;
import perso.renaud.com.myfirstrxapp.presentation.post_detail.model.PostDetailData;

/**
 * Created by renaudfavier on 15/07/2017.
 */

public interface PostDetailContract {

    interface View extends IBaseView {
        SingleObserver<PostDetailData> getRefreshDataObserver();
    }

    interface Presenter extends IBasePresenter {

    }
}
