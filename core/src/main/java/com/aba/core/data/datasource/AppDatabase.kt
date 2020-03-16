package com.aba.core.data.datasource

import android.content.Context
import androidx.room.*
import com.aba.core.BuildConfig
import com.aba.core.data.datasource.dao.JobDao
import com.aba.core.data.model.JobItem

@Database(
    entities = [JobItem::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        fun getAppInstance(context: Context): AppDatabase = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            BuildConfig.APP_DATABASE
        ).allowMainThreadQueries().build()

        fun getTestInstance(context: Context) =
            Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
    }

    abstract fun jobDao(): JobDao
}