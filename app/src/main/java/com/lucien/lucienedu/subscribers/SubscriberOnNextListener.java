package com.lucien.lucienedu.subscribers;

/**
 * Created by lzk-pc on 2017/3/27.
 */

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
