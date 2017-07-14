package perso.renaud.com.myfirstrxapp.data.repository;

import java.util.List;

import io.reactivex.Single;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;

/**
 * Created by renaud on 14/03/17.
 */

public interface PostRepository {

    Single<JSPost> get(int id);

    Single<List<JSPost>> getAll();

}
