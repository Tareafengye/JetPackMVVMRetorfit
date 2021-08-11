package com.zw.mylibrary.api;

/**
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.mylibrary.api
 * @ClassName: BaseJson
 * @Description: java类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/11 14:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/11 14:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class BaseJson<T> {
    private int retCode;
    private String retMsg;
    private T data;

    public int getCode() {
        return retCode;
    }

    public String getMessage() {
        return retMsg;
    }


    public T getData() {
        return data;
    }

    @Deprecated
    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    @Deprecated
    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    @Deprecated
    public void setData(T data) {
        this.data = data;
    }
}
