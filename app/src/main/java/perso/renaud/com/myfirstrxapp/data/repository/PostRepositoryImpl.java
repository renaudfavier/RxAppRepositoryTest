package perso.renaud.com.myfirstrxapp.data.repository;

import java.util.List;

import io.reactivex.Observable;
import perso.renaud.com.myfirstrxapp.data.api_objects.Post;
import perso.renaud.com.myfirstrxapp.network.Api;
import retrofit2.Response;

/**
 * Created by renaud on 14/03/17.
 */

public class PostRepositoryImpl implements PostRepository {

    private final Api.JsonPlaceholderInterface jsonPlaceholder;

    private final PostRepository cacheRepository;
    private final PostRepository networkRepository;

    public PostRepositoryImpl(Api.JsonPlaceholderInterface jsonPlaceholder) {
        this.jsonPlaceholder = jsonPlaceholder;
        cacheRepository = new PostCacheRepository();
        networkRepository = new PostNetworkRepository(jsonPlaceholder);
    }

    @Override
    public Observable<Response<Post>> get(long id) {
        Observable<Response<Post>> obs = Observable.concat(cacheRepository.get(id), networkRepository.get(id));

        return obs;
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
