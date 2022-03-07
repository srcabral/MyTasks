package br.com.srcabral.mytasks.service.repository

import br.com.srcabral.mytasks.service.constants.TaskConstants
import br.com.srcabral.mytasks.service.listener.APIListener
import br.com.srcabral.mytasks.service.model.HeaderModel
import br.com.srcabral.mytasks.service.repository.remote.PersonService
import br.com.srcabral.mytasks.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PersonRepository {

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
                listener.onFailure(t.message.toString())
            }

        })
    }
}