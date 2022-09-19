package de.syntaxinstitut.myperfectdog.data.model

import android.text.Layout

data class DogsList(

	val id: Long,
	val name: String,
	val imageResourceId: Long,
	val fci: Int,
	val heightsDogsList: String,
	val dogsLayout: Layout,
	val dogsTextDetail: String,
)