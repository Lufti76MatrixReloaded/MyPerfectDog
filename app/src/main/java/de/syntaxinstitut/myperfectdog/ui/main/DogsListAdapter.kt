package de.syntaxinstitut.myperfectdog.ui.main

import android.annotation.SuppressLint
import android.service.autofill.Dataset
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.ui.HomeFragmentDirections
import com.google.firebase.database.core.Context
import de.syntaxinstitut.myperfectdog.R
import de.syntaxinstitut.myperfectdog.data.model.DogsList_A_Z
import okhttp3.internal.notify

// der Adapter braucht den Context um auf String Resourcen zuzugreifen
// und die Liste an Jokes um sie für die RecyclerView vorzubereiten

class DogsListAdapter(
	private val content: Context,
	var dataset: List<DogsList_A_Z>
):RecyclerView.Adapter<DogsListAdapter.ItemViewHolder>()
{

	@SuppressLint("NotifyDataSetChanged")
	fun submitList(list: List<DogsList_A_Z>) {
		dataset = list
		notifyDataSetChanged()
	}

// der ViewHolder weiß welche Teile des Layouts beim Recycling angepasst werden

	class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
		val imageView = view.findViewById<ImageView>(R.id.cl_dogslist_item)
		val textView = view.findViewById<TextView>(R.id.)
		val cardView = view.findViewById<CardView>(R.id.cv_dogslist_A_Z)
		val cardView = view.findViewById<CardView>(R.id.cv_dogslist_XS_S)
		val cardView = view.findViewById<CardView>(R.id.cv_dogslist_M)
		val cardView = view.findViewById<CardView>(R.id.cv_dogslist_L_XL)
	}

	// hier werden neue ViewHolder erstellt
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

		// das itemLayout wird gebaut
		val adapterLayout = LayoutInflater.from(parent.context)
			.inflate(R.layout.fragment_dl_item, parent, false)

		// und in einem ViewHolder zurückgegeben
		return ItemViewHolder(adapterLayout)
	}

	// hier findet der Recyclingprozess statt
// die vom ViewHolder bereitgestellten Parameter werden verändert
	override fun onBindViewHolder(holder: DogsListAdapter, position: Int) {
		val dogslistAZ = content[position]
		holder.categoryText.text = dogs.name.uppercase()
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
		TODO("Not yet implemented")
	}
}
