package de.syntaxinstitut.myperfectdog.adapter

import android.content.Context
import android.os.Parcel
import androidx.recyclerview.widget.RecyclerView

data class Dogs_S_Adapter(
	private val context: Context,
	private val dataset: Parcel
                         )   : RecyclerView.Adapter<Dogs_S_Adapter.ItemViewHolder>() {
	}
