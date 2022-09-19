package com.example.recyclerview.adapter

import android.content.Context
import android.os.Parcel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.ui.HomeFragmentDirections
import de.syntaxinstitut.myperfectdog.R

// der Adapter braucht den Context um auf String Resourcen zuzugreifen
// und die Liste an Jokes um sie für die RecyclerView vorzubereiten

data class DogsListAdapter(
	private val context: Context,
	private val dataset: Parcel
) : RecyclerView.Adapter<DogsListAdapter.ItemViewHolder>() {

// der ViewHolder weiß welche Teile des Layouts beim Recycling angepasst werden

class ItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
	val categoryText: TextView = view.findViewById(R.id.ivDogs_Detail)
	val categoryLayout: ConstraintLayout = view.findViewById(R.id.cl_category_list)
}

	// hier werden neue ViewHolder erstellt
override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

	// das itemLayout wird gebaut
	val adapterLayout = LayoutInflater.from(parent.context)
		.inflate(R.layout.category_item, parent, false)

	// und in einem ViewHolder zurückgegeben
	return ItemViewHolder(adapterLayout)
}

// hier findet der Recyclingprozess statt
// die vom ViewHolder bereitgestellten Parameter werden verändert
override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
	val item = dataset[position]
	holder.categoryText.text = item.name.uppercase()
	holder.categoryLayout.setBackgroundColor(item.colorprimary)

	holder.categoryLayout.setOnClickListener {
		holder.view.findNavController()
			.navigate(HomeFragmentDirections.actionHomeFragmentToCategoryFragment(item.name))
	}
}

// damit der LayoutManager weiß wie lang die Liste ist
override fun getItemCount(): Int {
	return dataset.dataSize()
}
}
