package de.syntaxinstitut.doggy_guide.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.doggy_guide.model.CategoriesList
import de.syntaxinstitut.doggy_guide.model.Category
import de.syntaxinstitut.doggy_guide.model.Dogs
import de.syntaxinstitut.doggy_guide.ui.FragmentHomeDirections
import de.syntaxinstitut.myperfectdog.R


class DogAdapter(

				): RecyclerView.Adapter<DogAdapter.ItemViewHolder>() {

	private var dataset: List<Dogs> = listOf()

	@SuppressLint("NotifyDataSetChanged")
	fun submitList(list: List<Dogs>) {
	}


	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int): CategoriesList.ItemViewHolder {

		val binding = DogsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

		val item = dataset[position]

		val imgUri = item.imageResource.toUri().scheme("https").build()

		holder.binding.dogsImage.load(imgUri)

		holder.binding.dogsNameText.text = item.dogsName
		holder.binding.fciClassesText.text = "${item.fciKlassen}"
		holder.binding.dogsCard.setOnClickListener {
			holder.itemView.findNavController()

				.navigate(FragmentHomeDirections.actionFragmentHomeToDetailFragment(item.id))
		}
	}
}
