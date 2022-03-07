package br.com.srcabral.mytasks.service.repository

import android.content.Context
import br.com.srcabral.mytasks.R
import br.com.srcabral.mytasks.service.constants.TaskConstants
import br.com.srcabral.mytasks.service.listener.APIListener
import br.com.srcabral.mytasks.service.model.HeaderModel
import br.com.srcabral.mytasks.service.repository.remote.PersonService
import br.com.srcabral.mytasks.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository(val context: Context) {

    private var mRemote = RetrofitClient.createService(PersonService::class.java)

    fun login(email: String, password: String, listener: APIListener){

        val call: Call<HeaderModel> = mRemote.login(email, password)
        call.enqueue(object : Callback<HeaderModel>{
            override fun onResponse(call: Call<HeaderModel>, response: Response<HeaderModel>) {
                if ( response.code() != TaskConstants.HTTP.SUCCESS){
                    val validation = Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<HeaderModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }

    fun create(name: String, email: String, password: String, listener: APIListener){

        val call: Call<HeaderModel> = mRemote.create(name, email, password, true)
        call.enqueue(object : Callback<HeaderModel>{
            override fun onResponse(call: Call<HeaderModel>, response: Response<HeaderModel>) {
                if ( response.code() != TaskConstants.HTTP.SUCCESS){
                    val validation = Gson().fromJson(response.errorBody()!!.string(), String::class.java)
                    listener.onFailure(validation)
                } else {
                    response.body()?.let { listener.onSuccess(it) }
                }
            }

            override fun onFailure(call: Call<HeaderModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }

        })
    }
}