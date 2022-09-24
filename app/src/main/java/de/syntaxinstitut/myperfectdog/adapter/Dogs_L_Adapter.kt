package de.syntaxinstitut.myperfectdog.adapter

import android.content.Context
import android.os.Parcel
import androidx.recyclerview.widget.RecyclerView


data class Dogs_L_Adapter(
	private val context: Context,
	private val database: Parcel
                         ): RecyclerView.Adapter<Dogs_L_Adapter.ItemViewHolder>() {}