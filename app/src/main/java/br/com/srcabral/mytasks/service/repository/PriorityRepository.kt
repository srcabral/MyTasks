package br.com.srcabral.mytasks.service.repository

import android.content.Context
import br.com.srcabral.mytasks.service.constants.TaskConstants
import br.com.srcabral.mytasks.service.model.PriorityModel
import br.com.srcabral.mytasks.service.repository.local.TaskDatabase
import br.com.srcabral.mytasks.service.repository.remote.PriorityService
import br.com.srcabral.mytasks.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PriorityRepository(context: Context) {

    private val mRemote = RetrofitClient.createService(PriorityService::class.java)
    private val mPriorityDatabase = TaskDatabase.getDatabase(context).priorityDAO()

    fun all() {
        val call: Call<List<PriorityModel>> = mRemote.list()
        call.enqueue(object : Callback<List<PriorityModel>> {
            override fun onResponse(
                call: Call<List<PriorityModel>>,
                response: Response<List<PriorityModel>>
            ) {
                if (response.code() == TaskConstants.HTTP.SUCCESS) {
                    mPriorityDatabase.clear()
                    response.body()?.let { mPriorityDatabase.save(it) }
                }
            }

            override fun onFailure(call: Call<List<PriorityModel>>, t: Throwable) {
            }

        })
    }
}