package de.syntaxinstitut.myperfectdog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.apicalls.R
import com.google.android.ads.mediationtestsuite.viewmodels.ItemViewHolder
import de.syntaxinstitut.myperfectdog.R
import de.syntaxinstitut.myperfectdog.ui.Main.HomeFragmentDirections
import kotlinx.coroutines.Dispatchers.Main

class DogsAdapter(
	private val context: List<Any>,
	private val dataset: List<ListView>,
) : RecyclerView.Adapter<DogsAdapter.ItemViewHolder>() {

	class ItemViewHolder(view: View) : RecyclerView.ItemViewHolder(view) {
		val title: TextView = view.findViewById(de.syntaxinstitut.myperfectdog.R.id.dogs_titel_text)
		val height: TextView = view.findViewById(de.syntaxinstitut.myperfectdog.R.id.dogs_height_text)
		val image: ImageView = view.findViewById(de.syntaxinstitut.myperfectdog.R.id.dogs_image)
		val card: CardView = view.findViewById(de.syntaxinstitut.myperfectdog.R.id.dogs_View)
		val fci: TextView = view.findViewById(de.syntaxinstitut.myperfectdog.R.id.dogs_fci_text)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

		val itemLayout = LayoutInflater.from(parent.context)
			.inflate(de.syntaxinstitut.myperfectdog.R.layout.fragment_dogslist, parent, false)

		return ItemViewHolder(itemLayout)
	}

	override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
		val item = dataset[position]

		holder.title.text = item.title
		holder.height.text = item.height.toString()
		holder.image.setImageDrawable(ImageView(item.id))
		holder.fci.text = item.fci.id
		holder.card.setOnClickListener {
			holder.itemView.findNavController()
				.navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(item.id))
		}
	}

	override fun getItemCount(): Int {
		return dataset.size
	}
}

