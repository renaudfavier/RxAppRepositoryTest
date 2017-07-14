package perso.renaud.com.myfirstrxapp.data.repository;

import java.util.List;

import io.reactivex.Observable;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;

/**
 * Created by renaud on 14/03/17.
 */

public interface PostRepository {

    Observable<JSPost> get(int id);

    Observable<List<JSPost>> getAll();

}
