package de.syntaxinstitut.myperfectdog.data.model

import android.text.Layout

data class DogsExtraSmall(

	val id: Long,
	val name: String,
	val imageResourceId: Long,
	val fci: Int,
	val heightsDogsExtraSmall: String,
	val dogsLayout: Layout,
	val dogsTextDetail: String,
)