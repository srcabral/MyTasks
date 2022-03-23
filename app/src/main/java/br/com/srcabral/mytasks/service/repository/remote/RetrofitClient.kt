package br.com.srcabral.mytasks.service.repository.remote

import br.com.srcabral.mytasks.service.constants.TaskConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {

        private lateinit var retrofit: Retrofit
        private val mBaseUrl = "http://devmasterteam.com/CursoAndroidAPI/"
        private var tokenkey = ""
        private var personkey = ""

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()
            /*httpClient.addInterceptor(object : Interceptor{
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request =
                        chain.request()
                            .newBuilder()
                            .addHeader(TaskConstants.HEADER.PERSON_KEY, tokenkey)
                            .addHeader(TaskConstants.HEADER.TOKEN_KEY, personkey)
                            .build()
                    return chain.proceed(request)
                }
            })*/

            if (!Companion::retrofit.isInitialized) {
                retrofit = Retrofit.Builder()
                    .baseUrl(mBaseUrl)
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }

            return retrofit
        }

        fun addHeader(token: String, person: String){
            this.tokenkey = token
            this.personkey = person
        }

        fun <S> createService(serviceClass: Class<S>): S {
            return getRetrofitInstance().create(serviceClass)
        }
    }
}