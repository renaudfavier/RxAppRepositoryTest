package perso.renaud.com.myfirstrxapp.data.repository;

import java.util.List;

import io.reactivex.Observable;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSUser;
import perso.renaud.com.myfirstrxapp.data.repository.base_class.RepositoryImpl;
import perso.renaud.com.myfirstrxapp.network.Api;

/**
 * Created by renaud on 14/07/17.
 */

public class UserRepositoryImpl extends RepositoryImpl<JSUser> {

    private static volatile UserRepositoryImpl instance;

    public static UserRepositoryImpl getInstance(final Api.JsonPlaceholderInterface jsonPlaceholderInterface) {

        synchronized (Api.class) {
            if (instance == null) {
                instance = new UserRepositoryImpl(new Api.StandardRest<JSUser>() {
                    @Override
                    public Observable<List<JSUser>> getAll() {
                        return jsonPlaceholderInterface.users();
                    }

                    @Override
                    public Observable<JSUser> get(long id) {
                        return jsonPlaceholderInterface.user(id);
                    }
                });
            }
            return instance;
        }
    }

    private UserRepositoryImpl(Api.StandardRest<JSUser> jsonPlaceholder) {

        super(jsonPlaceholder);
    }
}
