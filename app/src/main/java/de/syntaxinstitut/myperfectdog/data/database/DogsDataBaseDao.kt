package de.syntaxinstitut.myperfectdog.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface DogsDataBaseDao {

	@Insert(onConflict = OnConflictStrategy.ABORT)
	suspend fun insertAll(dogs: List<Dogs>)

	@Delete
	suspend fun deleteById(id: Dogs)

	@Update
	suspend fun updateDogs(dogs: List<Dogs>)

	@Query("SELECT * FROM Dogs")
	suspend fun getDogsList():List<Dogs>

	@Query("SELECT * FROM Dogs WHERE id = :dogsId")
	fun getDogsById(dogsId:Long): Dogs

	@Query("SELECT * FROM Dogs")
	fun getAll():LiveData<List<Dogs>>

	@Query("DELETE FROM Dogs WHERE  Id")
	suspend fun deleteById(id: Long): Dogs

	@Query("DELETE FROM Dogs")
	suspend fun deleteAll()
}