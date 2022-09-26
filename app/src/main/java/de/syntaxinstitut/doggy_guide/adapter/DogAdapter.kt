package de.syntaxinstitut.doggy_guide.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import de.syntaxinstitut.doggy_guide.model.Category
import de.syntaxinstitut.myperfectdog.R


class DogAdapter: RecyclerView.Adapter<DogAdapter.ViewHolder>() {
	inner class ViewHolder(item: View) : RecyclerView.ViewHolder(item)

	private val differCallback = object : DiffUtil.ItemCallback<Category>() {
		override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
			return oldItem.idDogs == newItem.idDogs
		}

		override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
			return oldItem == newItem
		}

	}
	val differ = AsyncListDiffer(this, differCallback)
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
				LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
		                 )
			}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val dogs = differ.currentList[position]
		holder.itemView.apply {
			Glide.with( this ).load(dogs.dogsDrawable).into(image_view_item_dogs)
			name_dogs.text = dogs.dogsName
		  card_save.setOnClickListener {
			  onItemClickListener2?.let{ it(dogs) }
			  card_save.setCardBackGroundColor(Color.WHITE);
		  }
			setOnClickListener {
				onItemClickListener?.let{ it(dogs) }
			}
		}
	}

	override fun getItemCount(): Int {
		return differ.currentList.size
	}

	private var onItemClickListener: ((Category) -> Unit)? =null
	private var onItemClickListener2: ((Category) -> Unit)? =null
	fun setOnItemClickListener(listener: (Category) -> Unit) {
		onItemClickListener = listener
	}

	fun setOnItemClickListener2(listener: (Category) -> Unit) {
		onItemClickListener2 = listener
	}

}