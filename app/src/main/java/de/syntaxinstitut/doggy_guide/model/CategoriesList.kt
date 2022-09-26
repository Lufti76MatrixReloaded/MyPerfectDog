package de.syntaxinstitut.doggy_guide.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.ui.HomeFragmentDirections
import com.google.firebase.database.core.Context
import de.syntaxinstitut.doggy_guide.R
import de.syntaxinstitut.doggy_guide.ui.main.dogs
import de.syntaxinstitut.myperfectdog.R

class CategoriesList(

	private val content: Context,
	var dataset: List<Dogs>
                    ):RecyclerView.Adapter<CategoriesList.ItemViewHolder>()
{

	@SuppressLint("NotifyDataSetChanged")
	fun submitList(list: List<Dogs>) {
		dataset = list
		notifyDataSetChanged()
	}

// der ViewHolder weiß welche Teile des Layouts beim Recycling angepasst werden

	class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
		val imageView = view.findViewById<ImageView>(R.id.cl_dogslist_item)
		val textView = view.findViewById<TextView>(R.id.)
		val cardView = view.findViewById<CardView>(R.id.cv_dogslist)
	}

	// hier werden neue ViewHolder erstellt
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

		// das itemLayout wird gebaut
		val adapterLayout = LayoutInflater.from(parent.context)
			.inflate(R.layout.fragment_dogslist_adapter, parent, false)

		// und in einem ViewHolder zurückgegeben
		return ItemViewHolder(adapterLayout)
	}

	// hier findet der Recyclingprozess statt
// die vom ViewHolder bereitgestellten Parameter werden verändert
	override fun onBindViewHolder(holder: CategoriesList, position: Int) {
		val dogsId = content[position]
		holder.categoryText.text = dogsId.name.uppercase()
		holder.categoryLayout.setBackgroundColor(item.colorprimary)

		holder.categoryLayout.setOnClickListener {
			holder.content.findNavController()
				.navigate(HomeFragmentDirections.actionHomeFragmentToCategoryFragment(item.name))
		}
	}

	// damit der LayoutManager weiß wie lang die Liste ist
	override fun getItemCount(): Int {
		return content.size
	}

	override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
	}
}
