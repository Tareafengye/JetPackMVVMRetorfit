package com.zw.jetpackmvvmdemo.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zw.jetpackmvvmdemo.BuildConfig
import com.zw.jetpackmvvmdemo.MyApplication
import com.zw.jetpackmvvmdemo.help.RxObserverHandler
import com.zw.mylibrary.repository.DataRepository
import com.zw.mylibrary.utils.ActivityUtil
import com.zw.mylibrary.view.LoadingDialog
import io.reactivex.observers.DisposableSingleObserver

abstract class BaseNoModelActivity : AppCompatActivity() {
    protected var context: Context? = null
    private var loadingDialog: LoadingDialog? = null
    // Rxjava 生命周期管理
    private var mRxObserverHandler: RxObserverHandler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
        ActivityUtil.getInstance().addActivity(this)
        setContentView(onCreate())
        initView()
        initData()
    }

    /**
     * 初始化要加载的布局资源ID
     * 此函数优先执行于onCreate()可以做window操作
     */
    protected abstract fun onCreate(): Int

    /**
     * 初始化视图
     */
    protected abstract fun initView()

    /**
     * 初始化数据
     */
    protected abstract fun initData()

    /**
     * 显示用户等待框
     *
     * @param msg 提示信息
     */
    protected fun showDialog(msg: String?) {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog!!.setLoadingMsg(msg)
        } else {
            loadingDialog = LoadingDialog(context!!)
            loadingDialog!!.setLoadingMsg(msg)
            loadingDialog!!.show()
        }
    }

    /**
     * 隐藏等待框
     */
    protected fun dismissDialog() {
        if (loadingDialog != null && loadingDialog!!.isShowing) {
            loadingDialog!!.dismiss()
            loadingDialog = null
        }
    }

    override fun onDestroy() {
        mRxObserverHandler?.dispose()
        ActivityUtil.getInstance().removeActivity(this)
        super.onDestroy()

    }

    protected fun getRepository(): DataRepository {
        return (application as MyApplication).getRepository()
    }
    /**
     * 新建数据回调
     *
     * @param tag            回调标记（只需要在此Activity中唯一即可）
     * @param singleObserver 回调
     * @param <T>            数据
    </T> */
    protected fun <T> newSingleObserver(tag: String, singleObserver: DisposableSingleObserver<T>): DisposableSingleObserver<T> {
        if (mRxObserverHandler == null) {
            mRxObserverHandler = RxObserverHandler()
        }
        mRxObserverHandler!!.newSingleObserver(tag, singleObserver)
        return singleObserver
    }

    /**
     * 新建一个观察
     *
     * @param tag       回调标记（只需要在此Activity中唯一即可）
     * @param onSuccess 成功回调
     * @param onError   错误回调
     * @param <T>       数据
     */
    protected fun <T> newSingleObserver(tag: String, onSuccess: (data: T) -> Unit, onError: (e: Throwable) -> Unit = {}): DisposableSingleObserver<T> {
        val singleObserver = object : DisposableSingleObserver<T>() {
            override fun onSuccess(t: T) {
                onSuccess(t)
            }

            override fun onError(e: Throwable) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace()
                }
                onError(e)
            }
        }
        if (mRxObserverHandler == null) {
            mRxObserverHandler = RxObserverHandler()
        }
        return mRxObserverHandler!!.newSingleObserver(tag, singleObserver)
    }

}