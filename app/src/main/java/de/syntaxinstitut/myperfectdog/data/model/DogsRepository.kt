package de.syntaxinstitut.myperfectdog.data.model

//import com.google.android.gms.common.api.Api
//import com.google.android.gms.common.api.GoogleApi
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import de.syntaxinstitut.myperfectdog.data.local.DogsDataBase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class DogsRepository(private var dataBase: DogsDataBase, private var dogs: DogsList) {

	val dogsList: LiveData<List<DogsList>> = dataBase.dogsDataBaseDao.getAll()

	suspend fun getDogs() {
		withContext(Dispatchers.IO) {
			val dogsList = this@DogsRepository.dataBase.dogsDataBaseDao
			dataBase.dogsDataBaseDao.getAll(dogs)
		}
	}

	suspend fun insertDogs(dogs: DogsList) {
		try {
			dataBase.dogsDataBaseDao.insert(dogs)
		} catch (e: Exception) {
			Log.d(TAG, "Failed to insert into Database: $e")
		}
	}

	suspend fun updateDogs(dogs: DogsList) {
		try {
			dataBase.dogsDataBaseDao.update(dogs)
		} catch (e: Exception) {
			Log.d(TAG, "Failed to update Database: $e")
		}
	}

	suspend fun deleteDogs(dogs: DogsList) {
		try {
			dataBase.dogsDataBaseDao.deleteById(dogs.id)
		} catch (e: Exception) {
			Log.d(TAG, "Failed to delete from Database: $e")
		}
	}
}