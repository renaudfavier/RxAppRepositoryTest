package perso.renaud.com.myfirstrxapp.data.repository.post;

import java.util.List;

import io.reactivex.Observable;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;
import perso.renaud.com.myfirstrxapp.network.Api;

/**
 * Created by renaud on 14/03/17.
 */


class PostNetworkRepository {


    private final Api.JsonPlaceholderInterface jsonPlaceholder;


    public PostNetworkRepository(Api.JsonPlaceholderInterface jsonPlaceholder) {
        this.jsonPlaceholder = jsonPlaceholder;
    }


    public Observable<JSPost> get(int id) {
        return jsonPlaceholder.post(id);
    }

    public Observable<List<JSPost>> getAll() {
        Observable<List<JSPost>> obs = jsonPlaceholder.posts();
        return obs;
    }

}
