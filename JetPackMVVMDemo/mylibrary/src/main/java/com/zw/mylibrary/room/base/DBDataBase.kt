package com.zw.mylibrary.room.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.zw.mylibrary.room.dao.UserInfoDao
import com.zw.mylibrary.room.dao.loginUserDao
import com.zw.mylibrary.room.entity.LoginUserEntity
import com.zw.mylibrary.room.entity.UserInfoEntity
import com.zw.mylibrary.room.help.DBConverters

/**
 * entityes中包含表、版本号、日志等
 */
@TypeConverters(DBConverters::class)
@Database(entities = [UserInfoEntity::class, LoginUserEntity::class],version = 1,exportSchema = true)
abstract class DBDataBase: RoomDatabase() {
    companion object{
        fun getDataBase(context: Context): DBDataBase {
            return Room.databaseBuilder(context.applicationContext,DBDataBase::class.java,"app_database.db")
                .allowMainThreadQueries()
                .addMigrations(MIGRATION_1_to_2)
                .build()
        }
        val MIGRATION_1_to_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
            }
        }
    }
    abstract fun UserInfoDao():UserInfoDao

    abstract fun loginDao():loginUserDao
}