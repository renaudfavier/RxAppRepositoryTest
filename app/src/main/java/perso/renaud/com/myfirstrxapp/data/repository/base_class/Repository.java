package perso.renaud.com.myfirstrxapp.data.repository.base_class;

import java.util.List;

import io.reactivex.Single;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;

/**
 * Created by renaud on 14/03/17.
 */

public interface Repository<T> {

    Single<T> get(int id);

    Single<List<T>> getAll();

}
