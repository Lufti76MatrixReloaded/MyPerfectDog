package de.syntaxinstitut.doggy_guide.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import de.syntaxinstitut.doggy_guide.model.Dogs
import de.syntaxinstitut.doggy_guide.api.DogsApi

@Dao
interface DogsDao {

	@Insert(onConflict = OnConflictStrategy.ABORT)
	suspend fun insertAll(dogs: LiveData<List<Dogs>>)

	@Delete
	suspend fun deleteById(id: DogsApi)

	@Update
	suspend fun updateDogs(dogs: List<DogsApi>)

	@Query("SELECT * FROM Dogs")
	suspend fun getDogsList():List<DogsApi>

	@Query("SELECT * FROM Dogs WHERE id = :dogsId")
	fun getDogsById(dogsId:Long): DogsApi

	@Query("SELECT * FROM Dogs")
	fun getAll():LiveData<List<DogsApi>>

	@Query("DELETE FROM Dogs WHERE  Id")
	suspend fun deleteById(id: Long): DogsApi

	@Query("DELETE FROM Dogs")
	suspend fun deleteAll()
}