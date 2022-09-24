package de.syntaxinstitut.myperfectdog.data.repository

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.provider.SyncStateContract.Helpers.update
import androidx.lifecycle.LiveData
import de.syntaxinstitut.myperfectdog.data.database.DogsDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.nio.file.Files.delete

class AppRepository(application: Application)
{
	private class dogsDao : DogsDao

	init {
		val db = DogsDataBase.createInstance(application)
		dogsDao = db.dogsDao
	}

	suspend fun insert(dogs: Dogs) {
		withContext(Dispatchers.IO)
		{
			dogsDataBaseDao.insert(dogs)
		}
	}

	suspend fun update(dogs: Dogs) {
		withContext(Dispatchers.IO) {
			dogsDataBaseDao.update(dogs)
		}
	}

	suspend fun delete(dogs: Dogs)
	{
		withContext(Dispatchers.IO)
		{
			dogsDataBaseDao.delete(dogs)
		}
	}
	suspend fun getDogsDataBaseById(dogsId: Long):Dogs?
	{
		var dogs:Dogs? = null
		withContext(Dispatchers.IO)
		{
			dogs = dogsDataBaseDao.getDogsById(dogsId)
		}

		return dogs
	}

	suspend fun getAllDogs():List<Dogs>?
	{
		var dogs:List<Dog>? = null
		withContext(Dispatchers.IO)
		{
			dogs = dogsDataBaseDao.getDogsList_A_Z()
		}

		return dogs
	}
	fun getLiveDataDogs_A_Z(): LiveData<List<Dogs>> = dogsDataBaseDao.getLiveDataDogsList_A_Z()

}