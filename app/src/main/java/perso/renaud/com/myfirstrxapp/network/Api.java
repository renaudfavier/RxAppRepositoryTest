package perso.renaud.com.myfirstrxapp.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;
import perso.renaud.com.myfirstrxapp.network.interceptors.LoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by renaud on 14/03/17.
 */

public class Api {

    public final static String BASE_URL = "https://jsonplaceholder.typicode.com";

    private static volatile Api instance;

    public static Api getInstance() {

        synchronized (Api.class) {
            if (instance == null) {
                instance = new Api();
            }
            return instance;
        }
    }

    public JsonPlaceholderInterface jsonPlaceholder;


    private Api() {

        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();


        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        jsonPlaceholder = retrofit.create(JsonPlaceholderInterface.class);

    }


    public interface StandardRest<T> {
        Observable<List<T>> getAll();

        Observable<T> get(long id);
    }


    public interface JsonPlaceholderInterface {

        @GET("/posts")
        Observable<List<JSPost>> posts();

        @GET("/posts/{id}")
        Observable<JSPost> post(@Path("id") long id);
    }
}
