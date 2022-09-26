package de.syntaxinstitut.doggy_guide.repository

//import com.google.android.gms.common.api.Api
//import com.google.android.gms.common.api.GoogleApi
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import de.syntaxinstitut.doggy_guide.data.db.DogDatabase
import de.syntaxinstitut.doggy_guide.api.DogsApi
import de.syntaxinstitut.doggy_guide.ui.main.dogs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val TAG = "AppRepositoryDogs"

class AppRepositoryDogs(private var dataBase: DogDatabase, private val api: DogDatabase) {

	val dogs: LiveData<List<DogsApi>> = dataBase.dogsDao.getAll()

	suspend fun getDogs() {
		withContext(Dispatchers.IO) {
			val newDogs = api.retrofitService.getDogs().dogs
			dataBase.dogsDao.insertAll(dogs)
		}
	}

	suspend fun updateDogs(dogs:List <DogsApi>) {
		withContext(Dispatchers.IO){
			dataBase.dogsDao.updateDogs(dogs)
		}
	}

	suspend fun deleteDogs(dogs: List <DogsApi>.id) {
		try {
			dataBase.dogsDao.deleteById(dogs.id)
		} catch (e: Exception) {
			Log.d(TAG, "Failed to delete from Database: $e")
		}
	}
}