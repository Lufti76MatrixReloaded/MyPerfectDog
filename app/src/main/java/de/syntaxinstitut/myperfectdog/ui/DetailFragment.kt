package de.syntaxinstitut.myperfectdog.ui

import android.os.Bundle
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import de.syntaxinstitut.myperfectdog.MainViewModel
import de.syntaxinstitut.myperfectdog.R
import de.syntaxinstitut.myperfectdog.databinding.Fragment06DogsDetailBinding
import de.syntaxinstitut.myperfectdog.databinding.FragmentDetailBinding

/**
 * Fragment Detail
 */
class DetailFragment : Fragment() {

	private lateinit var binding: FragmentDetailBinding
	private val viewModel: MainViewModel by activityViewModels()

	private var dogsId: Long = 0

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		arguments?.let {
			dogsId = it.getLong("id")
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {

		binding = DataBindingUtil.inflate(
			inflater, R.layout.fragment_06_dogs_detail, container, false
		)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

		viewModel.dogsList.observe(
			viewLifecycleOwner,
			Observer { list ->
				var dogsList = list.find { it.id == dogsId }

				if (dogsId != null) {
					binding.detailImage.setImageResource(dogsId.javaClass)
					binding.detailText.dogsDetailText = getString(dogsList.stringResource)
				}
			}
		)
	}
}

