package com.rodcollab.androidrodrigocavalcante.database

import android.content.Context
import androidx.room.Database
import androidx.room.Index
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rodcollab.androidrodrigocavalcante.database.entity.ClientLocal
import com.rodcollab.androidrodrigocavalcante.database.entity.ContactLocal
import com.rodcollab.androidrodrigocavalcante.database.entity.OrderHistoryLocal

@Database(entities = [ClientLocal::class, ContactLocal::class, OrderHistoryLocal::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun clientDao(): ClientDao

    abstract fun contactDao(): ContactDao

    abstract fun orderHistoryDao(): OrderHistoryDao

    companion object {

        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(AppDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance!!
        }

        private const val DATABASE_NAME = "app-database.db"
    }
}