package com.lucien.lucienedu.interf;

import com.lucien.lucienedu.entity.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lzk-pc on 2017/3/23.
 */

public interface MovieService {

    @GET("top250")
    Call<MovieEntity> getTopMovie2(@Query("start") int start, @Query("count") int count);

    @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}