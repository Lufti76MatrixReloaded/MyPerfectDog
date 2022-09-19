package de.syntaxinstitut.myperfectdog.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import de.syntaxinstitut.myperfectdog.data.model.DogsList

@Dao
interface DogsDataBaseDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insert(dogs: DogsList)

	@Update
	suspend fun update(dogs: DogsList)

	@Query("SELECT * FROM DogsList")
	fun getAll(): LiveData<List<DogsList>>

	@Query("SELECT * FROM Dogs WHERE id = :key")
	fun getById(key: String): LiveData<DogsList>

	@Query("DELETE FROM Dogs WHERE id = :id")
	suspend fun deleteById(id: Long)

	@Query("DELETE FROM Dogs")
	suspend fun deleteAll()
}