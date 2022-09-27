package de.syntaxinstitut.doggy_guide.repository

import android.provider.SyncStateContract.Helpers.insert
import android.provider.SyncStateContract.Helpers.update
import androidx.lifecycle.LiveData
import de.syntaxinstitut.doggy_guide.db.DogsDao
import de.syntaxinstitut.doggy_guide.data.db.DogDatabase
import de.syntaxinstitut.doggy_guide.model.Dogs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.file.Files.delete

class DogsRepository(private val db: DogDatabase)
{
	private class dogsDao : DogsDao

	init {
		val db = DogDatabase.createInstance(db)
		dogsDao = db.dogsDao
	}

	suspend fun insert(dogs: Dogs) {
		withContext(Dispatchers.IO)
		{
			dogsDao.insert(dogs)
		}
	}

	suspend fun update(dogs: Dogs) {
		withContext(Dispatchers.IO) {
			dogsDao.update(dogs)
		}
	}

	suspend fun delete(dogs: Dogs)
	{
		withContext(Dispatchers.IO)
		{
			dogsDao.delete(dogs)
		}
	}
	suspend fun getDogDatabaseById(dogsId: Long):Dogs?
	{
		var dogs:Dogs? = null
		withContext(Dispatchers.IO)
		{
			dogs = dogsDao.getDogsById(dogsId)
		}

		return dogs
	}

	suspend fun getAllDogs():List<Dogs>?
	{
		var dogs:List<Dogs>? = null
		withContext(Dispatchers.IO)
		{
			dogs = dogsDao.getDogs()
		}

		return dogs
	}
	fun getLiveDataDogs(): LiveData<List<Dogs>> = dogsDao.getLiveDataDogs()

}