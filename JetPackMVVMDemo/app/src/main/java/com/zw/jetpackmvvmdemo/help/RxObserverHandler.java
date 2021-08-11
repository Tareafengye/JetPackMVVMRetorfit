package com.zw.jetpackmvvmdemo.help;

import android.util.SparseArray;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.jetpackmvvmdemo.help
 * @ClassName: RxObserverHandler
 * @Description: java类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/11 14:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/11 14:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class RxObserverHandler {

    private SparseArray<DisposableSingleObserver> mObservers;

    public RxObserverHandler() {
        mObservers = new SparseArray<>();
    }

    /**
     * 新建数据回调
     * @param tag               回调标记（只需要在此Presenter中唯一即可）
     * @param singleObserver    回调
     * @param <T>               数据
     */
    public <T> DisposableSingleObserver<T> newSingleObserver(String tag, DisposableSingleObserver<T> singleObserver){
        final int hashCode = tag.hashCode();
        Disposable observer = mObservers.get(hashCode);
        if (observer != null && !observer.isDisposed()){
            observer.dispose();
        }
        mObservers.put(hashCode,singleObserver);
        return singleObserver;
    }

    /**
     * 把所有观察者标记为已处理
     */
    public void dispose(){
        final int size = mObservers.size();
        for (int i = 0; i < size; i++) {
            Disposable disposable = mObservers.valueAt(i);
            if (disposable != null && !disposable.isDisposed()){
                disposable.dispose();
            }
        }
    }
}
