package perso.renaud.com.myfirstrxapp.data.repository.base_class;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.functions.Consumer;
import java.util.List;
import perso.renaud.com.myfirstrxapp.network.Api;

/**
 * Created by renaud on 14/03/17.
 */

public abstract class RepositoryImpl<T extends PrimaryKeyObject> implements Repository<T> {

    public static final String TAG = "RepositoryImpl";

    private final CacheRepository<T> cacheRepository;
    private final NetworkRepository<T> networkRepository;

    protected RepositoryImpl(Api.StandardRest<T> jsonPlaceholder) {
        cacheRepository = new CacheRepository<T>();
        networkRepository = new NetworkRepository<T>(jsonPlaceholder);
    }

    @Override public Single<T> get(int id) {

        Observable<T> obs =
                Observable.concat(cacheRepository.get(id), networkRepository.get(id).doOnNext(new Consumer<T>() {
                    @Override public void accept(T item) throws Exception {
                        cacheRepository.store(item);
                    }
                }));

        return obs.firstOrError();
    }

    @Override public Single<List<T>> getAll() {

        Observable<List<T>> obs = Observable.concat(cacheRepository.getAll(),
                networkRepository.getAll().doOnNext(new Consumer<List<T>>() {
                    @Override public void accept(List<T> items) throws Exception {
                        cacheRepository.store(items);
                    }
                }));

        return obs.firstOrError();
    }
}
