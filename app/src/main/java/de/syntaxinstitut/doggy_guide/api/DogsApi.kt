package de.syntaxinstitut.doggy_guide.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import de.syntaxinstitut.doggy_guide.model.Dogs
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL = "https://dogbreeddb.p.rapidapi.com/paginated/"

val client = OkHttpClient()

val request = Request.Builder()
	.url("https://dogbreeddb.p.rapidapi.com/paginated/")
	.get()
	.addHeader("X-RapidAPI-Key", "5d4b62fdeamsh4cdc539d4be5c4fp1fa087jsnec26af487002")
	.addHeader("X-RapidAPI-Host", "dogbreeddb.p.rapidapi.com")
	.build()

val response = client.newCall(request).execute()

private val moshi = Moshi.Builder()
	.add(KotlinJsonAdapterFactory())
	.build()

private val retrofit = Retrofit.Builder()
	.addConverterFactory(MoshiConverterFactory.create(moshi))
	.baseUrl(BASE_URL)
	.build()

interface ApiDogDataList {
	@GET("https://dogbreeddb.p.rapidapi.com/paginated/")
	suspend fun getDogsList(@Query("lat") lat: Double,
	                        @Query("lon") lon: Double, @Query("appid") appid: String
	                       ) : Dogs
}
object DogsApi {
	val retrofitService: ApiDogDataList by lazy {
		retrofit.create(ApiDogDataList::class.java)
	}
}




