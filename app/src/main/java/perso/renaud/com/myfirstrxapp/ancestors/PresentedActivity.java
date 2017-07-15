package perso.renaud.com.myfirstrxapp.ancestors;

/**
 * Created by renaud on 07/03/17.
 */

public abstract class PresentedActivity extends MyActivity {

    @Override protected void onResume() {
        super.onResume();
        getPresenter().resume();
    }

    @Override protected void onPause() {
        super.onPause();
        getPresenter().pause();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        getPresenter().destroy();
    }

    public abstract IBasePresenter getPresenter();
}
