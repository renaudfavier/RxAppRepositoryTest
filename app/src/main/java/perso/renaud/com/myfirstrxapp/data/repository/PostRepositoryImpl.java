package perso.renaud.com.myfirstrxapp.data.repository;

import android.util.Log;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;
import perso.renaud.com.myfirstrxapp.network.Api;

/**
 * Created by renaud on 14/03/17.
 */

public class PostRepositoryImpl implements PostRepository {


    private static volatile PostRepositoryImpl instance;

    public static PostRepositoryImpl getInstance(Api.JsonPlaceholderInterface jsonPlaceholder) {

        synchronized (Api.class) {
            if (instance == null) {
                instance = new PostRepositoryImpl(jsonPlaceholder);
            }
            return instance;
        }
    }


    public static final String TAG = "PostRepositoryImpl";

    private final PostCacheRepository cacheRepository;
    private final PostNetworkRepository networkRepository;

    private PostRepositoryImpl(Api.JsonPlaceholderInterface jsonPlaceholder) {
        cacheRepository = new PostCacheRepository();
        networkRepository = new PostNetworkRepository(jsonPlaceholder);
    }


    @Override
    public Single<JSPost> get(int id) {

        Log.i(TAG, "get(" + id + ")");


        Observable<JSPost> obs = Observable.concat(cacheRepository.get(id), networkRepository.get(id).doOnNext(new Consumer<JSPost>() {
            @Override
            public void accept(JSPost jsPost) throws Exception {
                cacheRepository.store(jsPost);
            }
        }));

        return obs.firstOrError();
    }

    @Override
    public Single<List<JSPost>> getAll() {

        Log.i(TAG, "getAll()");


        Observable<List<JSPost>> obs = Observable.concat(cacheRepository.getAll(), networkRepository.getAll().doOnNext(new Consumer<List<JSPost>>() {
            @Override
            public void accept(List<JSPost> jsPosts) throws Exception {
                cacheRepository.store(jsPosts);
            }
        }));

        return obs.firstOrError();


    }


}
