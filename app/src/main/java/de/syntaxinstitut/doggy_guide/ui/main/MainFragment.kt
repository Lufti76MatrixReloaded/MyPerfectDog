package de.syntaxinstitut.doggy_guide.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.SnapHelper
import de.syntaxinstitut.doggy_guide.R
import de.syntaxinstitut.doggy_guide.adapter.DogAdapter
import de.syntaxinstitut.myperfectdog.databinding.FragmentMainBinding


class MainFragment : Fragment() {


	private lateinit var binding: FragmentMainBinding

	private val viewModel: MainViewModel by activityViewModels()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	                         ): View? {

		(activity as MainActivity).showToolbar()

		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

		val dogAdapter = DogAdapter()

		binding.dogsRecycler.adapter = dogAdapter

		viewModel.dogs.observe {
			viewLifecycleOwner,
			Observer {
				dogAdapter.submitList(it)
			}
		}

		val snapHelper: SnapHelper = PagerSnapHelper()
		snapHelper.attachToRecyclerView(binding.dogsRecycler)
	}
}
