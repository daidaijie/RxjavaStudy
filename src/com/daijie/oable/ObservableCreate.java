package com.daijie.oable;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by baicheng on 2016/6/9.
 */
public class ObservableCreate {
    public static void main(String[] args) {
//        createObservale();

//        createObservaleByFrom();

        createObservaleByJust();
    }

    private static void createObservaleByJust() {
        //just()  方法可以传入一到九个参数，它们会按照传入的参数的顺序来发射它们
        Observable.just(helloWorld())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("onCompleted");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("throwable.getMessage() = " + throwable.getMessage());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext : " + s);
                    }
                });
    }

    private static String helloWorld(){
        return "Hello World!";
    }

    private static void createObservaleByFrom() {
        //from() 创建符可以从一个列表/数组来创建Observable,并一个接一个的从列表/数组中发射出来每一个对象
        List<Integer> items = new ArrayList<Integer>();
        items.add(1);
        items.add(10);
        items.add(200);

        Observable<Integer> integerObservable = Observable.from(items);
        Subscription subscription = integerObservable.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Observable onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("throwable.getMessage() = " + throwable.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext : " + integer);
            }
        });

    }

    private static void createObservale() {
        //创建一个新的  Observable<Integer>  ,它执行了5个元素的for循环，一个接一个的发射他们，最后完成。
        Observable<Integer> integerObservable = Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                for (int i = 0; i < 5; i++) {
                    subscriber.onNext(i);
                }
                subscriber.onCompleted();
            }
        });

//        我们订阅了Observable，返回一个Subscription 。一旦我们订阅了，我们就开始接受整数，并一个接一个的打印出它们。
        Subscription subscription = integerObservable.subscribe(new Observer<Integer>() {
            @Override
            public void onCompleted() {
                System.out.println("Observable onCompleted");
            }

            @Override
            public void onError(Throwable throwable) {
                System.out.println("throwable.getMessage() = " + throwable.getMessage());
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext : " + integer);
            }
        });

    }

}
