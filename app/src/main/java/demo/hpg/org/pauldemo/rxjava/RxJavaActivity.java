package demo.hpg.org.pauldemo.rxjava;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import demo.hpg.org.pauldemo.R;
import demo.hpg.org.pauldemo.base.BaseActivity;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Paul on 15/11/26.
 */
public class RxJavaActivity extends BaseActivity {


    private String[] names = {"huang","lin","zhu"};
    private int drawableRes = R.drawable.ic_launcher;
    private ImageView imageView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        imageView = fvById(R.id.iv_test);
//        ThreadExecutor.getInstance().getThreadPool().execute(new TaskThread(new CallBack() {
//            @Override
//            public Object runOnThread() {
//                return "helloworld";
//            }
//
//            @Override
//            public void runOnMain(Object object) {
//                Log.e("HPG", (String) object);
//            }
//
//            @Override
//            public void failed() {
//
//            }
//        }));




//        observable.subscribe(subscriber);
//
//        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
//        observable.subscribe(onNextAction);
//        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
//        observable.subscribe(onNextAction, onErrorAction);
//        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
//        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
//
//        Observable.just(1, 2, 3, 4)
//                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
//                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer number) {
//                        Log.e("HPG", "number:" + number);
//                    }
//                });

//        Observable.from(names).subscribe(new Action1<String>() {
//            @Override
//            public void call(String s) {
//                Log.e("HPG",s);
//            }
//        });



//        //IO线程
//        Observable.just(1,2,3,4)
//                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
//                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程
//                .subscribe(new Action1<Integer>() {
//                    @Override
//                    public void call(Integer integer) {
//                        Log.e("HPG", "number=" + integer);//主线程中操作
//                    }
//                });

//        //列子，同一个线程之中调用,同步观察者模式
//        Observable.create(new Observable.OnSubscribe<Drawable>() {
//            @Override
//            public void call(Subscriber<? super Drawable> subscriber) {
//                Drawable drawable = getResources().getDrawable(drawableRes);
//                subscriber.onNext(drawable);
//                subscriber.onCompleted();
//            }
//        }).subscribeOn(Schedulers.io())
//          .observeOn(AndroidSchedulers.mainThread())
//          .subscribe(new Observer<Drawable>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Drawable drawable) {
//                        imageView.setImageDrawable(drawable);
//                    }
//                });

//        Observable.just(R.drawable.ic_launcher)
//                .map(new Func1<Integer, Drawable>() {
//                    @Override
//                    public Drawable call(Integer integer) {
//                        return getResources().getDrawable(integer);
//                    }
//                })
//                .subscribe(new Action1<Drawable>() {
//                    @Override
//                    public void call(Drawable drawable) {
//                        imageView.setImageDrawable(drawable);
//                    }
//                });

        //flatMap使用
//        Student[] students = new Student[5];
//        for (int i = 0;i <5 ;i++){
//            Student s = new Student();
//            s .setName(i + "--i");
//            ArrayList<Course> list = new ArrayList<>();
//            for (int j = 0;j<3;j++){
//                Course course = new Course();
//                course.setCourse(j+"--j--i--"+i);
//                list.add(course);
//            }
//            s .setCourses(list);
//            students[i] = s ;
//        }
//
//        Subscriber<Course> subscriber = new Subscriber<Course>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(Course course) {
//                Log.d("HPG", course.getCourse());
//            }
//        };
//
//        Observable.from(students)
//                .flatMap(new Func1<Student, Observable<Course>>() {
//                    @Override
//                    public Observable<Course> call(Student student) {
//                        return Observable.from(student.getCourses());
//                    }
//                })
//                .subscribe(subscriber);


        

    }

    //创建观察者
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {
            Log.e("HPG", "Completed!");
        }

        @Override
        public void onError(Throwable e) {
            Log.e("HPG", "Error!");
        }

        @Override
        public void onNext(String s) {
            Log.e("HPG", "Item: " + s);
        }
    };

    Subscriber<String> subscriber = new Subscriber<String>() {
        @Override
        public void onStart() {
            Log.e("HPG", "onStart");
        }

        @Override
        public void onNext(String s) {
            Log.e("HPG", "Item: " + s);
        }

        @Override
        public void onCompleted() {
            Log.e("HPG", "Completed!");
        }

        @Override
        public void onError(Throwable e) {
            Log.e("HPG", "Error!");
        }

    };

    Observable observable = Observable.create(new Observable.OnSubscribe<String>() {

        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("Hello");
            subscriber.onNext("Hi");
            subscriber.onNext("Aloha");
            subscriber.onCompleted();
        }
    });
    Action1<String> onNextAction = new Action1<String>() {
        // onNext()
        @Override
        public void call(String s) {
            Log.e("HPG", s);
        }
    };
    Action1<Throwable> onErrorAction = new Action1<Throwable>() {
        // onError()
        @Override
        public void call(Throwable throwable) {
            // Error handling
        }
    };
    Action0 onCompletedAction = new Action0() {
        // onCompleted()
        @Override
        public void call() {
            Log.e("HPG", "completed");
        }
    };


}
