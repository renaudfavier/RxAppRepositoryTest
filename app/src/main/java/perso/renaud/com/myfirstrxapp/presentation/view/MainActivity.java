package perso.renaud.com.myfirstrxapp.presentation.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import perso.renaud.com.myfirstrxapp.R;
import perso.renaud.com.myfirstrxapp.ancestors.MyActivity;
import perso.renaud.com.myfirstrxapp.data.api_objects.JSPost;
import perso.renaud.com.myfirstrxapp.data.repository.PostRepository;
import perso.renaud.com.myfirstrxapp.data.repository.PostRepositoryImpl;
import perso.renaud.com.myfirstrxapp.network.Api;
import perso.renaud.com.myfirstrxapp.presentation.model.viewholders.PostViewHolder;

public class MainActivity extends MyActivity {

    private final String TAG = "MainActivity";
    CompositeDisposable disposables = new CompositeDisposable();
    private RecyclerView recyclerView;
    private PostRecyclerAdapter adapter;

    TextView counterTextView;
    Button refreshButton;
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PostRecyclerAdapter(LayoutInflater.from(this));
        recyclerView.setAdapter(adapter);

        refreshButton = (Button) findViewById(R.id.refresh);
        counterTextView = (TextView) findViewById(R.id.counter);

        final Api.JsonPlaceholderInterface jsonPlaceHolder = Api.getInstance().jsonPlaceholder;
        final PostRepository postRepository = new PostRepositoryImpl(jsonPlaceHolder);


        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postRepository.getAll().subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableObserver<List<JSPost>>() {

                            @Override
                            public void onNext(List<JSPost> posts) {

                                i++;
                                counterTextView.setText("" + i);

                                Log.i(TAG, "onNext, size : " + posts.size());
                                adapter.updateData(posts);
                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        disposables.dispose();
    }

    public class PostRecyclerAdapter extends RecyclerView.Adapter<PostViewHolder> {

        List<JSPost> dataset = new ArrayList<>();

        final LayoutInflater inflater;

        public PostRecyclerAdapter(LayoutInflater inflater) {
            this.inflater = inflater;
        }

        public void updateData(List<JSPost> posts) {
            dataset = posts;
            notifyDataSetChanged();
        }

        @Override
        public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new PostViewHolder(inflater.inflate(R.layout.listitem_post, parent, false));
        }

        @Override
        public void onBindViewHolder(PostViewHolder holder, int position) {
            JSPost post = dataset.get(position);
            //Log.i("renaud", "post == null ? => " + (post == null) + "\n" + post.toLog());
            holder.postId.setText(String.valueOf(post.id));
            holder.userId.setText(String.valueOf(post.userId));
            holder.title.setText(post.title);
            holder.body.setText(post.body);
        }

        @Override
        public int getItemCount() {
            return dataset.size();
        }
    }
}
