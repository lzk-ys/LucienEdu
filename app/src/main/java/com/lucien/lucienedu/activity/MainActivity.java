package com.lucien.lucienedu.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lucien.lucienedu.R;
import com.lucien.lucienedu.entity.MovieEntity;
import com.lucien.lucienedu.entity.Subject;
import com.lucien.lucienedu.http.HttpMethods;
import com.lucien.lucienedu.subscribers.ProgressSubscriber;
import com.lucien.lucienedu.subscribers.SubscriberOnNextListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import rx.Subscriber;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.main_btn_click_me)
    Button mClickMeBtn;

    @Bind(R.id.main_tv_result)
    TextView resultTV;
    private SubscriberOnNextListener getTopMovieOnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getTopMovieOnNext = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                resultTV.setText(subjects.toString());
            }
        };
    }

    @OnClick(R.id.main_btn_click_me)
    public void onClick(){
        getMovie();
    }

    //进行网络请求
    private void getMovie(){
        Subscriber<MovieEntity> subscriber = new Subscriber<MovieEntity>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this,"Get Top Movie Completed",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                resultTV.setText(movieEntity.toString());
            }
        };
        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber(getTopMovieOnNext, MainActivity.this), 0, 10);
    }
}
