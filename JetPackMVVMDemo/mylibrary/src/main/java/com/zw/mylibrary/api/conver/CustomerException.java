package com.zw.mylibrary.api.conver;

/**
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.mylibrary.api.conver
 * @ClassName: CustomerException
 * @Description: 自定义错误信息
 * @Author: ltt
 * @CreateDate: 2021/8/11 14:22
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/11 14:22
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class CustomerException extends IllegalStateException{
    private String errorMsg;
    private String errorCode;

    public CustomerException(String errorMsg) {
        super(errorMsg);
        this.errorMsg = errorMsg;
    }

    CustomerException(String errorMsg, String errorCode) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
