package de.syntaxinstitut.doggy_guide.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.doggy_guide.MainViewModel
import de.syntaxinstitut.doggy_guide.R
import de.syntaxinstitut.doggy_guide.databinding.FragmentDetailBinding
import de.syntaxinstitut.doggy_guide.ui.main.MainViewModel
import de.syntaxinstitut.myperfectdog.R

/**
 * Fragment Detail
 */
class DetailFragment : Fragment() {

	private lateinit var binding: DetailFragmentDirections
	private val viewModel: MainViewModel by activityViewModels()

	private var dogsId: Long = 0

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	                         ): View? {
	
		 fun onCreate(savedInstanceState: Bundle?) {
			super.onCreate(savedInstanceState)

		arguments?.let {
			dogsId = it.getLong("id")
		}
	}

		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dogs_detail, container, false)
		
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//		viewModel.dogs.observe(
//		Observer { list ->
//				var dogsList = list.find { it.id == dogsId }

//				if (dogsId != null) {
//					binding.detailImage.setImageResource(dogsId.javaClass)
//					binding.detailText.dogsDetailText = getString(dogsList.stringResource)
//				}
		
		binding.detailLayout.setOnClickListener {
			findNavController().navigateUp()
		}
	}
}

