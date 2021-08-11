package com.zw.mylibrary.api

import com.google.gson.JsonSyntaxException
import com.zw.mylibrary.api.conver.CustomerException
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 *
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.mylibrary.api
 * @ClassName: ConverAPI
 * @Description: java类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/11 14:17
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/11 14:17
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
object ConverAPI {

    // 带基类的 javaBean || JSONArray || JSONObject
    fun <T> wrapObject(single: Single<BaseJson<T>>): Single<BaseJson<T>> {
        return single.flatMap {
            if (it.code == -1) {
                return@flatMap Single.error<BaseJson<T>>(ConVerException(if (it.message.isEmpty()) {
                    "未知异常"
                } else {
                    it.message
                }))
            }
            return@flatMap Single.just(it)
        }.onErrorResumeNext {
            return@onErrorResumeNext applyError(it)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



    // 带基类的 javaBean || JSONArray || JSONObject
    fun <T> wrapCodeObject(single: Single<BaseJson<T>>): Single<BaseJson<T>> {
        return single.flatMap {
            return@flatMap Single.just(it)
        }.onErrorResumeNext {
            return@onErrorResumeNext applyError(it)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    // 带基类的 List<javaBean>
    fun <T> wrapList(single: Single<BaseJson<ArrayList<T>>>): Single<BaseJson<ArrayList<T>>> {
        return single.flatMap {
            if (it.code == -1) {
                return@flatMap Single.error<BaseJson<ArrayList<T>>>(ConVerException(if (it.message.isEmpty()) {
                    "未知异常"
                } else {
                    it.message
                }))
            }
            return@flatMap Single.just(it)
        }.onErrorResumeNext {
            return@onErrorResumeNext applyError(it)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    // 不带基类的 空的(回调需要使用成功的状态码时不要使用这个)
    fun wrapEmptyNoBase(single: Single<BaseJson<Any>>): Single<Any> {
        return single.flatMap { baseJson ->
            return@flatMap if (baseJson.code == 0) {
                Single.just(Any())
            } else {
                Single.error<Any>(ConVerException(if (baseJson.message.isEmpty()) {
                    "未知异常"
                } else {
                    baseJson.message
                }))
            }
        }
            .onErrorResumeNext { throwable -> applyError(throwable) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    // 不带基类的 JavaBean(回调需要使用成功的状态码时不要使用这个)
    fun <T> wrapObjectNoBase(single: Single<BaseJson<T>>): Single<T> {
        return single.flatMap<T> { baseJson ->
            return@flatMap if (baseJson.code == 0) {
                Single.just(baseJson.data)
            } else {
                Single.error<T>(ConVerException(if (baseJson.message.isEmpty()) {
                    "未知异常"
                } else {
                    baseJson.message
                }))
            }
        }
            .onErrorResumeNext { throwable -> applyError(throwable) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }





    // 不带基类的 JavaBean(回调需要使用成功的状态码时不要使用这个)
    fun <T> wrapObjectNoBaseNoThread(single: Single<BaseJson<T>>): Single<T> {
        return single.flatMap<T> { baseJson ->
            return@flatMap if (baseJson.code == 0) {
                Single.just(baseJson.data)
            } else {
                Single.error<T>(ConVerException(if (baseJson.message.isEmpty()) {
                    "未知异常"
                } else {
                    baseJson.message
                }))
            }
        }
            .onErrorResumeNext { throwable -> applyError(throwable) }
    }

    fun wrapEmptyNoBaseNoThread(single: Single<BaseJson<Any>>): Single<Any> {
        return single.flatMap { baseJson ->
            return@flatMap if (baseJson.code == 0) {
                Single.just(Any())
            } else {
                Single.error<Any>(ConVerException(if (baseJson.message.isEmpty()) {
                    "未知异常"
                } else {
                    baseJson.message
                }))
            }
        }
            .onErrorResumeNext { throwable -> applyError(throwable) }
    }


    // 不带基类的 List<JavaBean>(回调需要使用成功的状态码时不要使用这个)
    fun <T> wrapListNoBase(single: Single<BaseJson<ArrayList<T>>>): Single<ArrayList<T>> {
        return single.flatMap<ArrayList<T>> { baseJson ->
            return@flatMap if (baseJson.code == 0) {
                Single.just(baseJson.data)
            } else {
                Single.error<ArrayList<T>>(ConVerException(if (baseJson.message.isEmpty()) {
                    "未知异常"
                } else {
                    baseJson.message
                }))
            }
        }
            .onErrorResumeNext { throwable -> applyError(throwable) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }



    /**
     * 异常说明
     */
    private fun <T> applyError(throwable: Throwable): Single<T> {
        return when (throwable) {
            is UnknownHostException -> Single.error(IOException("无法连接服务器，请检查网络"))
            is SocketTimeoutException -> Single.error(IOException("连接服务器超时，请检查网络"))
            is JsonSyntaxException -> Single.error(IllegalArgumentException("服务端返回数据异常"))
            is CustomerException -> Single.error(CustomerException(throwable.errorMsg))
            is HttpException -> {
                val code = throwable.code()
                Single.error<T>(IOException(HttpStatusCode.getMsgByCode(code)))
            }
            else -> Single.error(throwable)
        }
    }
}