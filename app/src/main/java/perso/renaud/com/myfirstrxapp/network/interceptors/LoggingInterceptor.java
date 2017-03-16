package perso.renaud.com.myfirstrxapp.network.interceptors;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by renaud on 04/12/15.
 */
public class LoggingInterceptor implements Interceptor {

    private final static String TAG = "LOGGING_INTERCEPTOR";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();


        Log.i(TAG, "request : " + request.url());
        //Log.i("renaud","requestBody : "+bodyToString(request));

        /*Log.i(TAG,"request : "+chain.connection());
        Log.i(TAG,"request : "+request.headers());*/

        Response response = chain.proceed(request);
        String bodyString = response.body().string();

        Log.i("renaud", "answer for request : " + request.url() + "\n"+bodyString.replaceAll("\n", ""));
        ResponseBody body = ResponseBody.create(MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"), bodyString);
        response = response.newBuilder().body(body).build();

        //Log.i(TAG,"response : "+response.request().url());
        //Log.i(TAG, "response : " + response.headers());

        return response;
    }

    private static String bodyToString(final Request request) {

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final Exception e) {
            return "did not work";
        }
    }


}