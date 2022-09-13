package de.syntaxinstitut.myperfectdog.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import de.syntaxinstitut.myperfectdog.data.model.Dogs
import de.syntaxinstitut.myperfectdog.data.model.DogsList

@Dao
interface DogsDataBaseDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(dogsSmall: Dogs)

	@Update
	suspend fun update(dogsSmall: Dogs)

	@Query("SELECT * FROM Dogs")
	fun getAll(): LiveData<List<DogsList>>

	@Query("SELECT * FROM Dogs WHERE id = :key")
	fun getById(key: String): LiveData<Dogs>

	@Query("DELETE FROM Dogs WHERE id = :id")
	suspend fun deleteById(id: Long)

	@Query("DELETE FROM Dogs")
	suspend fun deleteAll()
}