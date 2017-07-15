package perso.renaud.com.myfirstrxapp.data.repository.base_class;

import io.reactivex.Observable;
import java.util.List;
import perso.renaud.com.myfirstrxapp.network.Api;

/**
 * Created by renaud on 14/03/17.
 */

class NetworkRepository<T> {

    private final Api.StandardRest<T> jsonPlaceholder;

    public NetworkRepository(Api.StandardRest<T> jsonPlaceholder) {
        this.jsonPlaceholder = jsonPlaceholder;
    }

    public Observable<T> get(int id) {
        return jsonPlaceholder.get(id);
    }

    public Observable<List<T>> getAll() {
        return jsonPlaceholder.getAll();
    }
}
