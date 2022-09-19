package de.syntaxinstitut.myperfectdog.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import de.syntaxinstitut.myperfectdog.MainViewModel
import de.syntaxinstitut.myperfectdog.R
import de.syntaxinstitut.myperfectdog.adapter.DogsAdapter
import de.syntaxinstitut.myperfectdog.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding

	private val viewModel: MainViewModel by activityViewModels()

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View? {

		binding = DataBindingUtil.inflate(inflater, R.layout.fragment_00_home, container, false)

		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

		val recyclerView = binding.recyclerViewHome

		recyclerView.adapter = DogsAdapter(viewModel.dogs)

		recyclerView.setHasFixedSize(true)
	}
}
