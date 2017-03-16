package perso.renaud.com.myfirstrxapp.data.repository;

import java.util.List;

import io.reactivex.Observable;
import perso.renaud.com.myfirstrxapp.data.api_objects.Post;
import perso.renaud.com.myfirstrxapp.network.Api;
import retrofit2.Response;

/**
 * Created by renaud on 14/03/17.
 */


public class PostNetworkRepository implements PostRepository {


    private final Api.JsonPlaceholderInterface jsonPlaceholder;


    public PostNetworkRepository(Api.JsonPlaceholderInterface jsonPlaceholder) {
        this.jsonPlaceholder = jsonPlaceholder;
    }

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
