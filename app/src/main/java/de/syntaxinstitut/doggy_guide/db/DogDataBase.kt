package de.syntaxinstitut.doggy_guide.db

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteOpenHelper
import de.syntaxinstitut.doggy_guide.api.DogsApi
import de.syntaxinstitut.doggy_guide.db.DogsDao
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlin.coroutines.jvm.internal.CompletedContinuation.context

@Database(
		entities = [DogsApi::class],
		version = 1,
		exportSchema = false
         )
 class DogDatabase : RoomDatabase( ) {
	fun getDogsDao(): DogsDao {

		companion object {
			@Volatile
			private var instance: DogDatabase? = null
			private val LOCK = Any()
			private fun getDogsDao invoke(context: Context)= instance?: synchronized(LOCK)
			{
				instance ?: createDatabase(context).also { instance = it }
			}

			private fun createDatabase(context: Context) =
				Room.databaseBuilder(
						context.applicationContext, DogDatabase::class.java,
				                    .allowMainThreadQueries()
					.fallbackToDestructiveMigration()
					.build()
			)
		)
	}
}
	override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
	}

	override fun createInvalidationTracker(): InvalidationTracker {
		TODO("Not yet implemented")
	}

	override fun clearAllTables() {
		TODO("Not yet implemented")
	}
}

private late init var INSTANCE: DogDatabase

fun getDatabase(context: Context): DogDatabase {
	synchronized(DogDatabase::class.java) {
		if (!::INSTANCE.isInitialized) {
			INSTANCE = Room.databaseBuilder(
					context.applicationContext,
					DogDatabase::class.java,
					"dog_database"
			                               )
				.build()
		}
	}
	return INSTANCE
}
