package de.syntaxinstitut.myperfectdog.data.model

import android.text.Layout
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Dogs_L
(@PrimaryKey(autoGenerate = true) var id: Long,
 var name: String,
 var imageResourceId: Long,
 var fci: Int,
 var heightsDogs_M: String,
 var dogsLayout: Layout,
 var dogsTextDetail: String,
)