package perso.renaud.com.myfirstrxapp.data.repository.post;

import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;
import perso.renaud.com.myfirstrxapp.data.repository.base_class.RepositoryImpl;
import perso.renaud.com.myfirstrxapp.network.Api;

/**
 * Created by renaud on 14/07/17.
 */

public class GenerifiedPostRepositoryImpl extends RepositoryImpl<JSPost> {


    private static volatile GenerifiedPostRepositoryImpl instance;

    public static GenerifiedPostRepositoryImpl getInstance(Api.StandardRest<JSPost> jsonPlaceholder) {

        synchronized (Api.class) {
            if (instance == null) {
                instance = new GenerifiedPostRepositoryImpl(jsonPlaceholder);
            }
            return instance;
        }
    }

    private GenerifiedPostRepositoryImpl(Api.StandardRest<JSPost> jsonPlaceholder) {

        super(jsonPlaceholder);
    }
}
