package perso.renaud.com.myfirstrxapp.ancestors;


/**
 * Created by renaudfavier on 22/05/2017.
 */

public abstract class PresentedFragment extends MyFragment {

    @Override public void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    @Override public void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    @Override public void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }

    public abstract IBasePresenter getPresenter();
}
