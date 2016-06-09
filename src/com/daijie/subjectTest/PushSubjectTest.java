package com.daijie.subjectTest;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subjects.PublishSubject;

/**
 * Created by baicheng on 2016/6/9.
 */
public class PushSubjectTest {
    public static void main(String[] args) {
//        publishCreateAndSend();
        privateSubject();
    }

    private static void privateSubject() {
        //首先，我们创建一个新的PublishSubject来响应它的  onNext()  方法，并且外部也可以访问它。
        final PublishSubject<Boolean> booleanPublishSubject = PublishSubject.create();
        booleanPublishSubject.subscribe(new Observer<Boolean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onNext(Boolean aBoolean) {
                System.out.println("onCompleted");
            }
        });
        //然后，我们创建“私有”的Observable，只有subject才可以访问的到。
        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(5);
                }
                subscriber.onCompleted();
            }
        }).doOnCompleted(new Action0() {
            //当Observable结束时要做什么事情
            @Override
            public void call() {
                booleanPublishSubject.onNext(true);
            }
        }).subscribe();
        //空的subscribe() 调用仅仅是为了开启Observable

    }

    private static void publishCreateAndSend() {
        //在刚才的例子中，我们创建了一个  PublishSubject
        PublishSubject<String> stringPublishSubject = PublishSubject.create();

        //一直等待数据
        Subscription subscription = stringPublishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println(throwable.getMessage());
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext : " + s);
            }
        });
        //发射数据
        stringPublishSubject.onNext("Hello World");
    }
}
