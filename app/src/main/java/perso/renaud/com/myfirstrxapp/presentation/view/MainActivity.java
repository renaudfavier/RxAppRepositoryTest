package perso.renaud.com.myfirstrxapp.presentation.view;

import android.os.Bundle;

import io.reactivex.Observable;
import perso.renaud.com.myfirstrxapp.R;
import perso.renaud.com.myfirstrxapp.ancestors.MyActivity;
import perso.renaud.com.myfirstrxapp.data.api_objects.Post;
import perso.renaud.com.myfirstrxapp.data.repository.PostRepository;
import perso.renaud.com.myfirstrxapp.data.repository.PostRepositoryImpl;
import perso.renaud.com.myfirstrxapp.network.Api;
import retrofit2.Response;

public class MainActivity extends MyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Api.JsonPlaceholderInterface jsonPlaceHolder = Api.getInstance().jsonPlaceholder;
        PostRepository postRepository = new PostRepositoryImpl(jsonPlaceHolder);




    }
}
