@file:OptIn(InternalCoroutinesApi::class)

package de.syntaxinstitut.myperfectdog.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Dogs::class], version = 1)
abstract class DogsDataBase : RoomDatabase() {

	abstract val dogsDataBaseDao: DogsDataBaseDao
}

private lateinit var INSTANCE: DogsDataBase

fun getDatabase(context: Context): DogsDataBase {
	synchronized(DogsDataBase::class.java) {
		if (!::INSTANCE.isInitialized) {
			INSTANCE = Room.databaseBuilder(
				context.applicationContext,
				DogsDataBase::class.java,
				"dogs_database"
			)
				.build()
		}
	}
	return INSTANCE
}
