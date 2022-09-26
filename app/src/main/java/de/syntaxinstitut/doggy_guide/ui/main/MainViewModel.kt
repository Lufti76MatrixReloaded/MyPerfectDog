package de.syntaxinstitut.doggy_guide.ui.main


import android.accounts.AccountManager.get
import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context.CONNECTIVITY_SERVICE
import android.net.ConnectivityManager
import android.net.ConnectivityManager.*
import android.net.NetworkCapabilities.*
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat.getCategory
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.core.Context
import de.syntaxinstitut.doggy_guide.model.*
import de.syntaxinstitut.doggy_guide.repository.DogsRepository
import de.syntaxinstitut.doggy_guide.util.Resource
import kotlinx.coroutines.launch
import okhttp3.internal.platform.android.AndroidLogHandler.getFilter
import retrofit2.Response
import java.io.IOException

class MainViewModel(
	app: Application,
	private val dogsRepository : DogsRepository
	) : AndroidViewModel(app){

	private val searchDogs: MutableLiveData<Resource<RandomDogs>> = MutableLiveData()
	private val filter: MutableLiveData<Resource<RandomDogs>> = MutableLiveData()
	private val details: MutableLiveData<Resource<RandomDogs>> = MutableLiveData()
	private val categoriesList: MutableLiveData<Resource<CategoriesList>> = MutableLiveData()
	var randomDogs: RandomDogs? = null

	init {
		getCategoryList()
		getFilter("XS")
	}

	private fun getSearch(search: String) = viewModelScope.launch {
		safeGetSearch(search)
	}

	private fun getFilter(category: String) = viewModelScope.launch {
		safeGetFilter(category)
	}

	fun getDetails(id: String) = viewModelScope.launch {
		safeGetDetails(id)
	}

	private fun getCategoryList() = viewModelScope.launch {
		safeGetCategoryList()
	}

	private fun handleDetailsDogs(response: Response<RandomDogs>): Resource<RandomDogs>? {
		if (response.isSuccessful) {
			response.body()?.let { resultResponse ->
				return Resource.Success(resultResponse)
			}
		}
		return Resource.Error(response.message())
	}

	private fun handleFilterDogs(response: Response<RandomDogs>): Resource<RandomDogs>? {
		if (response.isSuccessful) {
			response.body()?.let { resultResponse ->
				return Resource.Success(resultResponse)
			}
		}
		return Resource.Error(response.message())
	}

	private fun handleCategoryDogs(response: Response<CategoriesList>): Resource<CategoriesList> {
		if (response.isSuccessful) {
			response.body()?.let { resultResponse ->
				return Resource.Success(resultResponse)
			}
		}
		return Resource.Error(response.message())
	}

	private fun handleRandomDogs(response: Response<RandomDogs>): Resource<RandomDogs> {
		if (response.isSuccessful) {
			response.body()?.let { resultResponse ->
				return Resource.Success(resultResponse)
			}
		}
		return Resource.Error(response.message())
	}

	fun insertDogs() {
		viewModelScope.launch {
			val dogs = Dogs(1, "affenpinscher", 1, 2, "S" )
			dogsRepository.insert(dogs)
		}
	}

	fun updateDogs(dogs: Dogs) {
		viewModelScope.launch {
			dogsRepository.update(dogs)
		}
	}

	fun deleteDogs(dogs: Dogs) {
		viewModelScope.launch {
			dogsRepository.delete(dogs)
		}
	}

	fun getDogsById(dogsId: Long): Dogs {
		var dogs: Dogs? = null
		viewModelScope.launch {
			dogs = dogsRepository.getDogsById(dogsId)
		}
		return idDogs
	}

	fun getAllDogs(): List<Dogs>? {
		var dogs: List<Dogs>? = null
		viewModelScope.launch {
			dogs = dogsRepository.getAllDogs()
		}
		return dogs
	}
	/**
	 * Das MainModel des Home Fragments
	 */

	private suspend fun safeGetCategoryList(){
		categoriesList.postValue(Resource.Loading())
		try {
			if(hasInternetConnection()) {
				val response = dogsRepository.getCategory()
				categoriesList.postValue(handleCategoryList(response))
			} else {
				categoriesList.postValue(Resource.Error("No internet Connection"))
			}
		} catch (t: Throwable) {
			when (t) {
				is IOException -> categoriesList.postValue(Resource.Error("Network failure"))
				else -> categoriesList.postValue(Resource.Error("Conversion error"))
			}
		}
	}

	private suspend fun safeGetFilter(category: String) {
	filter.postValue(Resource.Loading())
		try{
			if (hasInternetConnection()) {
				val response = dogsRepository.getFilter(category)
				filter.postValue(handleFilterDogs(response))
			} else {
				filter.postValue(Resource.Error("No internet connection"))
			}
		} catch (t: Throwable) {
			when (t) {
				is IOException -> filter.postValue(Resource.Error("Network failure"))
				else -> filter.postValue(Resource.Error("Conversion error"))
			}
		}
	}

	private suspend fun safeGetSearch(search: String) {
		searchDogs.postValue(Resource.Loading())
		try {
			if (hasInternetConnection()) {
				val response = dogsRepository.getSearchDogs(search)
				searchDogs.postValue(handleRandomDogs(response))
			} else {
				searchDogs.postValue(Resource.Error("No internet connection"))
			}
		} catch (t: Throwable) {
			when (t) {
				is IOException -> searchDogs.postValue(Resource.Error("Network failure"))
				else -> searchDogs.postValue(Resource.Error("Conversion error"))
			}
		}
	}

	private suspend fun safeGetDetails(id: String) {
		details.postValue(Resource.Loading())
		try {
			if (hasInternetConnection()) {
				val response = dogsRepository.getDetails(id)
				details.postValue(handleDetailsDogs(response))
			} else {
				details.postValue(Resource.Error("No internet connections"))
			}
		} catch (t: Throwable) {
			when (t) {
				is IOException -> details.postValue(Resource.Error("Network failure"))
				else -> details.postValue(Resource.Error("Conversion error"))
			}
		}
	}

	// function for checking internet connection for API>=23 and <23
	private fun hasInternetConnection(): Boolean {
		val connectivityManager = getApplication<DoggyGuideApplication>()
			.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M) {
			val activeNetwork = connectivityManager.activeNetwork?: return false
			val capabilities =
				connectivityManager.getNetworkCapabilities(activeNetwork)?: return false
			return when {
				capabilities.hasTransport(TRANSPORT_WIFI) -> true
				capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
				capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
				else -> false
			}
		} else {
			connectivityManager.activeNetworkInfo?.run {
				return when (type) {
					TYPE_WIFI -> true
					TYPE_MOBILE -> true
					TYPE_ETHERNET -> true
					else -> false
				}
			}
		}
		return false
	}
}
		// Kommunikationspunkt mit der FirebaseAuth
	private val firebaseAuth = FirebaseAuth.getInstance() {

			// currentuser is null wenn niemand eingeloggt ist
			private val _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
			val currentUser: LiveData<FirebaseUser?>
			get() = _currentUser

			// hier wird versucht einen User zu erstellen um diesen anschließend auch gleich
			// einzuloggen

			fun signUp(email: String, password: String) {
				firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
					if (it.isSuccessful) {
						login(email, password)
					} else {
						Log.e(TAG, "SignUp failed: ${it.exception}")
					}
				}
			}

			private fun login(email: String, password: String, firebaseAuth: Any) {
				firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
					if (it.isSuccessful) {
						_currentUser.value = firebaseAuth.currentUser
					} else {
						Log.e(TAG, "Login failed: ${it.exception}")
					}
				}
			}

			fun logout() {
				firebaseAuth.signOut()
				_currentUser.value = firebaseAuth.currentUser
			}

				/* -------------------- Klassen Variablen -------------------- */

				/** Signal um zu signalisieren, dass zum zweiten Fragment gewechselt werden soll */
			fun navigateToFragmentDetail() {
					navigateToFragmentDetail.value = MutableLiveData(false) {

					}
					/* -------------------- Öffentliche Funktionen -------------------- */

					/**
					 * Mit dieser Funktion wird der Trigger ausgelöst um zum zweiten Fragment zu wechseln
					 */
					fun navigateToFragmentDetail() {
						navigateToFragmentDetail.value = true

						/**
						 * Setzt alle Werte der Variablen auf ihren "Werkszustand" zurück
						 */
						fun resetAllValues() {
							navigateToFragmentDetail.value = false
						}
					}
				}