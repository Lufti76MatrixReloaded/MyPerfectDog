package de.syntaxinstitut.myperfectdog

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.common.api.Api
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import de.syntaxinstitut.myperfectdog.data.local.getDatabase
import de.syntaxinstitut.myperfectdog.data.model.Dogs
import kotlinx.coroutines.launch


const val TAG = "MAINVIEWMODEL"

/**
 * Das MainModel des Home Fragments
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

	private val dataBase = getDatabase(application)
	private val dogsRepository = de.syntaxinstitut.myperfectdog.data.model.DogsRepository(dataBase)

	private val _loading = MutableLiveData<Api>()
	val loading: LiveData<Api>
		get() = _loading

	val dogs = dogsRepository.dogsList

	init {
		loadData()
	}

	private fun loadData() {
		viewModelScope.launch {
			_loading.value = ApiStatus.LOADING
			try {
				dogsRepository.getDogs()
				_loading.value = ApiStatus.DONE
			} catch (e: Exception) {
				Log.e(TAG, "Error loading Data $e")
				if (dogs.value.isNullOrEmpty())
					_loading.value = ApiStatus.ERROR
				else
					_loading.value = ApiStatus.DONE
			}
		}
	}
}


private val _complete = MutableLiveData<Boolean>()
val complete: LiveData<Boolean>
	get() = _complete

fun insertDogs(dogs: Dogs) {
	viewModelScope.launch {
		dogsRepository.insert(dogs)
		_complete.value = true
	}
}

fun update(dogs: Dogs) {
	viewModelScope.launch {
		dogsRepository.update(dogs)
		_complete.value = true
	}
}

fun delete(dogs: Dogs) {
	viewModelScope.launch {
		Log.d("ViewModel", "Calling dogRepository delete with ${dogs.id}")
		dogsRepository.delete(dogs)
		_complete.value = true
	}
}

fun unsetComplete() {
	_complete.value = false
}

// Kommunikationspunkt mit der FirebaseAuth
private val firebaseAuth = FirebaseAuth.getInstance()

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
var navigateToFragmentDetail = MutableLiveData(false)

/* -------------------- Öffentliche Funktionen -------------------- */

/**
 * Mit dieser Funktion wird der Trigger ausgelöst um zum zweiten Fragment zu wechseln
 */
fun navigateToFragmentDetail() {
	navigateToFragmentDetail.value = true
}

/**
 * Setzt alle Werte der Variablen auf ihren "Werkszustand" zurück
 */
fun resetAllValues() {
	navigateToFragmentDetail.value = false
}

