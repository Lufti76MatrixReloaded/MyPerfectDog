package de.syntaxinstitut.myperfectdog.data.model

import android.text.Layout
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DogsList_A_Z

		(@PrimaryKey(autoGenerate = true) var id: Long,
	var name: String,
	var imageResourceId: Long,
	var fci: Int,
	var heightsDogsList_A_Z: String,
	var dogsLayout: Layout,
	var dogsTextDetail: String,
)