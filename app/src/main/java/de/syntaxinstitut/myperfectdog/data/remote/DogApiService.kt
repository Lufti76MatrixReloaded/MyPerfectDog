package de.syntaxinstitut.myperfectdog.data.remote

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntaxinstitut.myperfectdog.data.model.DogsList_A_Z
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


const val BASE_URL = "https://myperfectdog-80c6e-default-rtdb.europe-west1.firebasedatabase.app/"

private val moshi = Moshi.Builder()
	.add(KotlinJsonAdapterFactory())
	.build()

private val retrofit = Retrofit.Builder()
		.addConverterFactory(MoshiConverterFactory.create(moshi))
		.baseUrl(BASE_URL)
	.build()

interface DogsApiService {

	@GET("https://myperfectdog-80c6e-default-rtdb.europe-west1.firebasedatabase.app/")
	suspend fun getDogsList(): DogsList_A_Z
}

object DogsApi {
	val retrofitService: DogsApiService by lazy { retrofit.create(DogsApiService::class.java) }
}