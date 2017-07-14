package perso.renaud.com.myfirstrxapp.data.repository.base_class;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import io.reactivex.Observable;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;
import perso.renaud.com.myfirstrxapp.data.helper.Ephemerical;

/**
 * Created by renaud on 14/03/17.
 */

class CacheRepository<T extends PrimaryKeyObject> {

    public static final String TAG = "CacheRepository";

    HashMap<Integer, Ephemerical<T>> cacheMap = new HashMap<>();

    public Observable<T> get(int id) {

        if (cacheMap.containsKey(id)) {
            Ephemerical<T> post = cacheMap.get(id);
            if (post.isAlive()) {
                return Observable.just(post.get());
            } else {
                cacheMap.remove(id);
            }
        }

        return Observable.empty();
    }

    public void buryDeads() {
        Iterator<Ephemerical<T>> iterator = cacheMap.values().iterator();
        while (iterator.hasNext()) {
            Ephemerical<T> post = iterator.next();
            if (!post.isAlive()) {
                iterator.remove();
            }
        }
    }

    public Observable<List<T>> getAll() {

        buryDeads();

        List<T> jsPosts = new ArrayList<>();
        for (Ephemerical<T> post : cacheMap.values()) {
            jsPosts.add(post.get());
        }

        if (jsPosts.isEmpty()) {
            return Observable.empty();
        }

        return Observable.just(jsPosts);
    }


    public void store(T post) {
        cacheMap.put(post.getPrimaryKey(), new Ephemerical<>(post));
    }


    public void store(List<T> jsPosts) {
        for (T post : jsPosts) {
            store(post);
        }
    }
}
