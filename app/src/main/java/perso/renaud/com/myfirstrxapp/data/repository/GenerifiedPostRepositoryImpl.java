package perso.renaud.com.myfirstrxapp.data.repository;

import java.util.List;

import io.reactivex.Observable;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;
import perso.renaud.com.myfirstrxapp.data.repository.base_class.RepositoryImpl;
import perso.renaud.com.myfirstrxapp.network.Api;

/**
 * Created by renaud on 14/07/17.
 */

public class GenerifiedPostRepositoryImpl extends RepositoryImpl<JSPost> {


    private static volatile GenerifiedPostRepositoryImpl instance;

    public static GenerifiedPostRepositoryImpl getInstance(final Api.JsonPlaceholderInterface jsonPlaceholderInterface) {

        synchronized (Api.class) {
            if (instance == null) {
                instance = new GenerifiedPostRepositoryImpl(new Api.StandardRest<JSPost>() {
                    @Override
                    public Observable<List<JSPost>> getAll() {
                        return jsonPlaceholderInterface.posts();
                    }

                    @Override
                    public Observable<JSPost> get(long id) {
                        return jsonPlaceholderInterface.post(id);
                    }
                });
            }
            return instance;
        }
    }

    private GenerifiedPostRepositoryImpl(Api.StandardRest<JSPost> jsonPlaceholder) {

        super(jsonPlaceholder);
    }
}
