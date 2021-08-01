package com.example.roomdb_simple_crud

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomdb_simple_crud.BuildConfig.VERSION_CODE

@Database(entities = [User::class], version = VERSION_CODE, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}