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


private val moshi = Moshi.Builder()
	.add(KotlinJsonAdapterFactory())
	.build()

private val retrofit = Retrofit.Builder()
	.addConverterFactory(MoshiConverterFactory.create(moshi))
	.baseUrl(BASE_URL)
	.build()

interface DogsApi {

	@GET("data.json")
	suspend fun getDogs(): List<Dogs>
}
object DogsApi {
	val retrofitService: DogsApi by lazy {
		retrofit.create(DogsApi::class.java)
	}

}




