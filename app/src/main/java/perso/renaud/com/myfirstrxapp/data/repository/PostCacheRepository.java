package perso.renaud.com.myfirstrxapp.data.repository;

import java.util.List;

import io.reactivex.Observable;
import perso.renaud.com.myfirstrxapp.data.api_objects.Post;
import retrofit2.Response;

/**
 * Created by renaud on 14/03/17.
 */

public class PostCacheRepository implements PostRepository{


    @Override
    public Observable<Response<Post>> get(long id) {
        return null;
    }

    @Override
    public Observable<Response<List<Post>>> getAll() {
        return null;
    }

    @Override
    public boolean store(Post post) {
        return false;
    }
}
