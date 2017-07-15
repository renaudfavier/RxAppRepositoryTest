package perso.renaud.com.myfirstrxapp.ancestors;

/**
 * Created by renaud on 07/03/17.
 */

/**
 * Interface representing a IBasePresenter in a model view presenter (MVP) pattern.
 */
public interface IBasePresenter {
    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();
}
