package com.zw.mylibrary.room.help

import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList

/**
 *
 * @ProjectName: JetPackMVVMDemo
 * @Package: com.zw.mylibrary.room.help
 * @ClassName: DBConverters
 * @Description: java类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/10 14:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/10 14:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class DBConverters {
    private val GSON = Gson()

    @TypeConverter
    fun longToDate(time: Long): Date {
        return Date(time)
    }

    @TypeConverter
    fun dateToTime(date: Date): Long {
        return date.time
    }

}