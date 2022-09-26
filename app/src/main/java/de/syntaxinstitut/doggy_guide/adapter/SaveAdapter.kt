package de.syntaxinstitut.doggy_guide.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.doggy_guide.model.Detail
import de.syntaxinstitut.myperfectdog.R

class SaveAdapter : RecyclerView.Adapter<SaveAdapter.ViewHolder>() {
	inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item)

	private val differCallback = object : DiffUtil.ItemCallback<Detail>() {
		override fun areItemsTheSame(oldItem: Detail, newItem: Detail): Boolean {
			return oldItem.Detail == newItem.Detail
		}

		override fun areContentsTheSame(oldItem: Detail, newItem: Detail): Boolean {
			return oldItem == newItem
		}

	}
	val differ = AsyncListDiffer(this, differCallback)
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
				LayoutInflater.from(parent.context).inflate(R.layout.item_save, parent, false)
		                 )
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val save = differ.currentList[position]
		holder.itemView.apply {
			Glide.with(context).load(save.strMealThumb).into(image_view_saved_meal)
			text_view_saved_meal_title.text = save.strMeal
//            text_view_saved_meal_description.text = save.strCategory
			setOnClickListener {
				onItemClickListener?.let { it(save) }
			}
		}
	}

	override fun getItemCount(): Int {
		return differ.currentList.size
	}

	private var onItemClickListener: ((Detail) -> Unit)? = null
	fun setOnItemClickListener(listener: (Detail) -> Unit) {
		onItemClickListener = listener
	}
}