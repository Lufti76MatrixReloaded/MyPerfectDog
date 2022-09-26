package de.syntaxinstitut.doggy_guide.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.doggy_guide.model.Category
import de.syntaxinstitut.myperfectdog.R

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
	inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item)

	private val differCallback = object : DiffUtil.ItemCallback<Category>() {
		override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
			return oldItem.idCategory == newItem.idCategory
		}

		override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
			return oldItem == newItem
		}

	}
	val differ = AsyncListDiffer(this, differCallback)
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
				LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false)
		                 )
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val dogs = differ.currentList[position]
		holder.itemView.apply {
			Glide.with(this).load(dogs.dogsName).into(image_view_item_category)
			text_view_item_category_name_category.text = dogs.dogsName
			setOnClickListener {
				onItemClickListener?.let { it(dogs) }
			}
		}
	}

	override fun getItemCount(): Int {
		return differ.currentList.size
	}

	private var onItemClickListener: ((Category) -> Unit)? = null
	fun setOnClickListener(listener: (Category) -> Unit) {
		onItemClickListener = listener
	}
}