package de.syntaxinstitut.myperfectdog.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.myperfectdog.R
import de.syntaxinstitut.myperfectdog.databinding.FragmentHomeBinding


class MainFragment : Fragment() {


	private lateinit var rootView: View

	private lateinit var rv:RecyclerView
	private lateinit var adapter: DogsListAdapter
	private lateinit var binding: FragmentHomeBinding

	private lateinit var viewModel: MainViewModel

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.fragment_main, container, false)
	}
		//!binding = inflate(inflater, R.layout.fragment_main, container, false)

		//!return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		rootView = view

		initRecyclerView()
		mainViewModel = ViewModelProvider(requireActivity(),MainViewModelFactory(requireActivity().get(MainViewModel::class.java)))
		mainViewModel.getLiveDogsList().observe(viewLifeCycleOwner, Observer{items ->
			adapter.updateContent(ArrayList(items))
		})
	}

	private fun initRecyclerView()
	{
		rv = rootView.findViewById(R.id.main_rv)
		adapter = DogsListAdapter(ArrayList())
		rv.adapter = adapter
	}
}
