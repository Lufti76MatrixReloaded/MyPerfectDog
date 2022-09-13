package de.syntaxinstitut.myperfectdog.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import de.syntaxinstitut.myperfectdog.data.model.Dogs
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = [Dogs::class], version = 1)
abstract class DogsDataBase : RoomDatabase() {

	abstract val dogsDataBaseDao: DogsDataBaseDao
}

private lateinit var INSTANCE: DogsDataBase


@OptIn(InternalCoroutinesApi::class)
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
