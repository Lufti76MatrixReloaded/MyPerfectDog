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
import de.syntaxinstitut.doggy_guide.api.DogsApi
import de.syntaxinstitut.doggy_guide.model.*
import de.syntaxinstitut.doggy_guide.repository.DogsRepository
import de.syntaxinstitut.doggy_guide.util.Resource
import kotlinx.coroutines.launch
import okhttp3.internal.platform.android.AndroidLogHandler.getFilter
import retrofit2.Response
import java.io.IOException

class MainViewModel : ViewModel() {

//    wurde durch Repository ersetzt
//    private val datasource = Datasource()

	private val repository = Repository (DogsApi)

//    private val _fans = MutableLiveData<List<Fan>>()
//    val fans: LiveData<List<Fan>>
//        get() = _fans

	val fans: LiveData<List<Dogs>> = repository.categoriesList

//    private val _test = MutableLiveData<String>()
//    val test: LiveData<String>
//        get() = _test

	init {
//        _fans.value = datasource.loadFans()
		loadData()
//        loadTest()
	}

	private fun loadData() {
		viewModelScope.launch {
			repository.getDogs()
		}
	}

//  	fun addToCart() {
	}

//    private fun loadTest() {
//        viewModelScope.launch {
//            _test.value = FanApi.retrofitService.getFans()[0].imageResource
//        }

}	/**
	 * Das MainModel des Home Fragments
	 */

	suspend fun safeGetCategoryList( ) {
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

			// Kommunikationspunkt mit der FirebaseAuth
	private val firebaseAuth = FirebaseAuth.getInstance() {

			// currentuser is null wenn niemand eingeloggt ist

				 val _currentUser = MutableLiveData<FirebaseUser?>(firebaseAuth.currentUser)
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