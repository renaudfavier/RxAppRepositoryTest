package perso.renaud.com.myfirstrxapp.data.repository;

import java.util.List;

import io.reactivex.Observable;
import perso.renaud.com.myfirstrxapp.data.api_objects.Post;
import retrofit2.Response;

/**
 * Created by renaud on 14/03/17.
 */

public interface PostRepository {

    Observable<Response<Post>> get(long id);

    Observable<Response<List<Post>>> getAll();

    boolean store(Post post);

}
