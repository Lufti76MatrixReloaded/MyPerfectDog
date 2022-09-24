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
import de.syntaxinstitut.myperfectdog.ui.main.MainViewModel

class CategoryFragment : Fragment() {

	private lateinit var binding: FragmentCategoryBinding
	private val viewModel: MainViewModel by activityViewModels()
	private var category: String = ""

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		arguments?.let {
			category = it.getString("category").toString()
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	                         ): View? {
		binding = DataBindingUtil.inflate(
				inflater,
				R.layout.fragment_category, container, false
		                                 )

		viewModel.dogsList.observe(
				viewLifecycleOwner,
				{ list ->
					val dogsList = list.filter { it.category == category }

					binding.categoryRecycler.adapter = ItemAdapter(requireContext(), dogsList)
					binding.categoryBackground.setImageResource(dogsList.random().imageResource)
				}
		                          )

		return binding.root
	}
}