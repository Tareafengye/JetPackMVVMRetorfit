package com.zw.mylibrary.api

/**
 *
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.mylibrary.api
 * @ClassName: HttpStatusCode
 * @Description: 自定义状态信息
 * @Author: ltt
 * @CreateDate: 2021/8/11 14:23
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/11 14:23
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
object HttpStatusCode {
    fun getMsgByCode(code: Int): String {
        return when (code / 100) {
            1 -> getInformationalMsg(code)
            2 -> getSuccessMsg(code)
            3 -> getRedirectionMsg(code)
            4 -> getClientErrorMsg(code)
            5, 6 -> getServerErrorMsg(code)
            else -> "未知状态"
        }
    }

    /**
     * HTTPCODE:[100,200)
     */
    private fun getInformationalMsg(code: Int): String {
        return when (code) {
            100 -> "请求已被接受，需要继续处理"
            101 -> "需要切换协议完成请求"
            102 -> "请求将被继续执行"
            else -> "请求已被接受，需要继续处理"
        }
    }

    /**
     * HTTPCODE:[200,300)
     */
    private fun getSuccessMsg(code: Int): String {
        return when (code) {
            200 -> "请求成功"
            201 -> "请求成功并且服务器创建了新的资源"
            202 -> "服务器已接受请求，但尚未处理,请等待"
            203 -> "服务器已成功处理了请求，但返回的信息可能来自另一来源"
            204, 205 -> "请求成功，但无任何内容返回"
            206 -> "服务器成功处理了部分 GET 请求"
            else -> "请求成功"
        }
    }

    /**
     * HTTPCODE:[300,400)
     */
    private fun getRedirectionMsg(code: Int): String {
        return when (code) {
            300 -> "需要进行重定向"
            else -> "需要进行重定向"
        }
    }

    /**
     * HTTPCODE:[400,500)
     */
    private fun getClientErrorMsg(code: Int): String {
        return when (code) {
            400 -> "请求参数有误或语义错误，服务端无法解析"
            401, 407 -> "请求要求身份验证"
            403 -> "服务器拒绝请求"
            404 -> "请求地址未找到"
            405 -> "请求行中指定的请求方法不能被用于请求相应的资源"
            406 -> "请求的资源的内容特性无法满足请求头中的条件，因而无法生成响应实体"
            408 -> "请求超时"
            409 -> "请求数据失败，产生冲突"
            410 -> "请求的数据不存在"

            else -> "客户端请求异常"
        }
    }

    /**
     * HTTPCODE:[500,700)
     */
    private fun getServerErrorMsg(code: Int): String {
        return when (code) {
            500 -> "服务器遇到了一个未曾预料的状况"
            501 -> "服务器不具备完成请求的功能"
            502 -> "服务器作为网关或代理，从上游服务器收到无效响应"
            503 -> "服务器目前无法使用（由于超载或停机维护）"
            504 -> "服务器作为网关或代理，但是没有及时从上游服务器收到请求"
            505 -> "服务器不支持请求中所用的 HTTP 协议版本"
            507 -> "服务器无法存储完成请求所必须的内容"
            509 -> "服务器达到带宽限制"
            600 -> "未返回响应头信息"
            else -> "服务器异常"
        }
    }

}