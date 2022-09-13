package de.syntaxinstitut.myperfectdog.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import com.example.apicalls.R
import com.example.apicalls.databinding.FragmentHomeBinding
import de.syntaxinstitut.myperfectdog.MainViewModel
import de.syntaxinstitut.myperfectdog.adapter.DogsAdapter
import de.syntaxinstitut.myperfectdog.data.Datasource
import de.syntaxinstitut.myperfectdog.data.model.Dogs

/**
 * Fragment Home
 */
class FragmentHome : Fragment(R.layout.activity_main) {

	/* -------------------- Klassen Variablen -------------------- */

	/** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
	private lateinit var binding: FragmentHomeBinding

	/** Das ViewModel zu diesem Fragment */
	private val viewModel: MainViewModel by activityViewModels()


	/* -------------------- Lifecycle -------------------- */

	/**
	 * Lifecycle Methode wenn das View erstellt wird
	 *
	 * @param inflater                Layout Inflater
	 * @param container               View Gruppe
	 * @param savedInstanceState      Eventuelle saveStates
	 */
	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {
		binding = DataBindingUtil.inflate(inflater, R.layout.activity_main, container, false)

		return binding.root
	}

	/**
	 * Lifecycle Methode nachdem das View erstellt wurde
	 *
	 * @param view                    Das angezeigte View
	 * @param savedInstanceState      Eventuelle saveStates
	 */
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val recyclerView = binding.imageView

		val imageViewAdapter = DogsAdapter(emptyList())

		imageView.adapter = imageViewAdapter

		viewModel.loading.observe(
			viewLifecycleOwner
		) {
			when (it) {
				ApiStatus.LOADING -> binding.mainBtn = View.VISIBLE
				ApiStatus.ERROR -> {
					binding.mainBtn.visibility = View.GONE
					binding.imageView.visibility = View.VISIBLE
				}
				else -> {
					binding.mainBtn.visibility = View.GONE
					binding.imageView.visibility = View.GONE
				}
			}
		}

		viewModel.dogs.observe(
			viewLifecycleOwner
		) {
			imageViewAdapter.submitList(it)
		}
	}
	binding.addDogButton.setOnClickListener
	{
		viewModel.insertDogs.Dogs
		)
		)
	}
}

val dogs = Datasource().loadDogs() {


/* -------------------- UI-Interaktionen -------------------- */

	binding.homeBtn.setOnClickListener {
		viewModel.navigateToFragmentDetail()
	}


/* -------------------- Observer -------------------- */

// Navigation zum zweiten Fragment
	viewModel.navigateToFragmentDetail.observe(viewLifecycleOwner) {
		if (it) {
			findNavController.navigate(
				MainFragmentDirections.actionMainFragmentToDetailFragment()
			)
			viewModel.resetAllValues()
			}

