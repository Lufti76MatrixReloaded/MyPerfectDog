package de.syntaxinstitut.myperfectdog.ui.main


import android.accounts.AccountManager.get
import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import de.syntaxinstitut.myperfectdog.R
import de.syntaxinstitut.myperfectdog.data.AppRepositoryDogs
import de.syntaxinstitut.myperfectdog.data.database.AppRepository
import de.syntaxinstitut.myperfectdog.data.database.DataBase
import de.syntaxinstitut.myperfectdog.data.model.*
import de.syntaxinstitut.myperfectdog.data.remote.DogsApi
import kotlinx.coroutines.launch


data class MainViewModel(application: Application) : AndroidViewModel(application)
{
	private val repository = AppRepositoryDogs(application)
	private var liveDogs = AppRepositoryDogs.getLiveDataDogs()

	fun insert() {
		viewModelScope.launch {
			val dogs = Dogs(0, "", 0, )
			repository.insert(dogs)
		}
	}

	fun update(dogs: Dogs) {
		viewModelScope.launch {
			repository.update(dogs)
		}
	}

	fun delete(dogs: Dogs) {
		viewModelScope.launch {
			repository.delete(dogs)
		}
	}

	fun getDogsById(dogsId: Long): Dogs? {
		var dogs: Dogs? = null
		viewModelScope.launch {
			dogs = repository.getDogsById(dogsId)
		}
		return dogs
	}

	fun getAllDogs(): List<Dogs>? {
		var dogs: List<Dogs>? = null
		viewModelScope.launch {
			dogs = repository.getAllDogs()
		}
		return dogs
	}

	/**
	 * Das MainModel des Home Fragments
	 */

	var dataBase = List<DataBase> {

		DataBase(
				Dogs_XS: ("Yorkshire Terrier")
				R.drawable.yorkshire_terrier

		DataBase(
				Dogs_S:("Affenpinscher")
				R.drawable.affenpinscher

		DataBase(
				Dogs_M:("American Cocker Spaniel")
				R.drawable.american_cocker_spaniel

		DataBase(
				Dogs_L:("Australian Kelpie"),
				R.drawable.australian_kelpie
		        )

		DataBase(
				Dogs_XL: ("Bernhardiner St. Bernhardshund")
				R.drawable.bernhardiner_st_bernhardshund
		)
	}
}

private val repository = AppRepository(DogsApi, database)
private var liveDogsList_A_Z = repository.getLiveDataDogs()

public val _dogs = MutableLiveData<List<DogsList_A_Z>>(DataBase().loadDogs())
val dogs: LiveData<List<DogsList_A_Z>>
getDogs() = _dogs

private val _loading = MutableLiveData<ApiStatus>()
val loading: LiveData<ApiStatus>
	get() = _dogs

val dogsList_A_Z = reporitory.dogsList_A_Z

init {
	loadData(
}

fun loadData() {
	viewModelScope.launch {
		_loading.value = ApiStatus.LOADING
		try {
			repository.getDogs()
			_loading.value = ApiStatus.DONE
		} catch (e: Exception) {
			Log.e(TAG, "Error loading Data $e")
			if (dogs.value.isNullOrEmpty()) {
				_loading.value = ApiStatus.ERROR
			} else {
				_loading.value = ApiStatus.DONE
			}
		}
	}
}

fun updateDogs(dogsList_A_Z: List<Dogs>) {
	viewModelScope.launch {
		try {
			repository.updateDogs(dogsList)
		} catch (e: Exception) {
			Log.e(TAG, "Error updating Data $e")
		}
	}
	fun insertDogs(dogs: Dogs) {
		viewModelScope.launch {
			repository.insertDogs(dogsList_A_Z)
			_complete.value = true
		}
	}

	fun deleteDogs(dogsList_A_Z: List<Dogs>) {
		viewModelScope.launch {
			Log.d("MainViewModel", "Calling AppRepositoryDogs delete with ${dogs.id}")
			appRepositoryDogs.deleteDogs(dogs)
			_complete.value = true
		}
	}

	fun unsetComplete() {
		_complete.value = false
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

		private fun login(email: String, password: String) {
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

		private val dogs

		val navigateToFragmentDetail = MutableLiveData(false) {

/* -------------------- Öffentliche Funktionen -------------------- */

			/**
			 * Mit dieser Funktion wird der Trigger ausgelöst um zum zweiten Fragment zu wechseln
			 */
			fun navigateToFragmentDetail() {
				navigateToFragmentDetail.value = true
			}
		}


		/**
		 * Setzt alle Werte der Variablen auf ihren "Werkszustand" zurück
		 */
		fun resetAllValues() {
			navigateToFragmentDetail.value = false
		}


