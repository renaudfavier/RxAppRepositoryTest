package perso.renaud.com.myfirstrxapp.data.repository.user;

import perso.renaud.com.myfirstrxapp.data.api_objects.JSUser;
import perso.renaud.com.myfirstrxapp.data.repository.base_class.RepositoryImpl;
import perso.renaud.com.myfirstrxapp.network.Api;

/**
 * Created by renaud on 14/07/17.
 */

public class UserRepositoryImpl extends RepositoryImpl<JSUser> {

    private static volatile UserRepositoryImpl instance;

    public static UserRepositoryImpl getInstance(Api.StandardRest<JSUser> jsonPlaceholder) {

        synchronized (Api.class) {
            if (instance == null) {
                instance = new UserRepositoryImpl(jsonPlaceholder);
            }
            return instance;
        }
    }

    private UserRepositoryImpl(Api.StandardRest<JSUser> jsonPlaceholder) {

        super(jsonPlaceholder);
    }
}
