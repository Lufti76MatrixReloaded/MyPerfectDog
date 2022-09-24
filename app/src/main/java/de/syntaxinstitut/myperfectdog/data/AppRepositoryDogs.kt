package de.syntaxinstitut.myperfectdog.data

//import com.google.android.gms.common.api.Api
//import com.google.android.gms.common.api.GoogleApi
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import de.syntaxinstitut.myperfectdog.data.database.DogsDataBase
import de.syntaxinstitut.myperfectdog.ui.main.dogs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val TAG="AppRepositoryDogs"

class AppRepositoryDogs(private var dataBase: DogsDataBase, private val api: DogsDataBase) {

	val dogsList: LiveData<List<Dogs>> = dataBase.dogsDataBaseDao.getAll()

	suspend fun getDogs() {
		withContext(Dispatchers.IO) {
			val newDogsList = api.retrofitService.getDogsList().dogs
			dataBase.dogsDataBaseDao.insertAll(dogs)
		}
	}

	suspend fun updateDogs(dogs:List <Dogs>) {
		withContext(Dispatchers.IO){
			dataBase.dogsDataBaseDao.updateDogs(dogs)
		}
	}

	suspend fun deleteDogs(dogs: List <Dogs>.id) {
		try {
			dataBase.dogsDataBaseDao.deleteById(dogs.id)
		} catch (e: Exception) {
			Log.d(TAG, "Failed to delete from Database: $e")
		}
	}
}