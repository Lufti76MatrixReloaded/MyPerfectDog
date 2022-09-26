package de.syntaxinstitut.doggy_guide.api

import de.syntaxinstitut.doggy_guide.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient.Builder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
	companion object{
		private val retrofit by lazy {
			val logging = HttpLoggingInterceptor()
			logging.setLevel(HttpLoggingInterceptor.Level.Body)
			val rfClient = Builder().addInterceptor(logging).build()
			Retrofit.Builder().baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create()).client(rfClient).build()
		}
		val api: DogsBreed by lazy {
			retrofit.create(DogsApi::class.java)
		}
	}
}


