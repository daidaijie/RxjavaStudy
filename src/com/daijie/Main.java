package com.daijie;

import rx.Observable;
import rx.Observer;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                System.out.println("completed");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };
        Observable.just("121","fsafa")
                .subscribe(observer);
    }
}
