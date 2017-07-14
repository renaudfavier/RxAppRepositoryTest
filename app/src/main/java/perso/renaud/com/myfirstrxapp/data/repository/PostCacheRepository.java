package perso.renaud.com.myfirstrxapp.data.repository;

import android.util.Log;

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

public class PostCacheRepository {

    public static final String TAG = "PostCacheRepository";

    HashMap<Integer, Ephemerical<JSPost>> cacheMap = new HashMap<>();

    public Observable<JSPost> get(int id) {
        if (cacheMap.containsKey(id)) {
            Ephemerical<JSPost> post = cacheMap.get(id);
            if (post.isAlive()) {
                return Observable.just(post.get());
            } else {
                cacheMap.remove(id);
            }
        }
        return Observable.empty();
    }

    public void buryDeads() {
        Iterator<Ephemerical<JSPost>> iterator = cacheMap.values().iterator();
        while (iterator.hasNext()) {
            Ephemerical<JSPost> post = iterator.next();
            if (!post.isAlive()) {
                Log.w(TAG, "removing " + post.get().id);
                iterator.remove();
                //cacheMap.remove(post.get().id);
            }
        }
    }

    public Observable<List<JSPost>> getAll() {
        buryDeads();
        List<JSPost> jsPosts = new ArrayList<>();
        for (Ephemerical<JSPost> post : cacheMap.values()) {
            jsPosts.add(post.get());
        }

        Log.i(TAG, "cache getAll() size : " + jsPosts.size());

        if (jsPosts.isEmpty()) {
            return Observable.empty();
        }

        return Observable.just(jsPosts);
    }


    public void store(JSPost post) {
        cacheMap.put(post.id, new Ephemerical<>(post));
        Log.i(TAG, "stored " + post.id);

    }


    public void store(List<JSPost> jsPosts) {
        for (JSPost post : jsPosts) {
            store(post);
        }
    }
}
