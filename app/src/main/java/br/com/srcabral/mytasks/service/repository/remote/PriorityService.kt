package br.com.srcabral.mytasks.service.repository.remote

import br.com.srcabral.mytasks.service.model.PriorityModel
import retrofit2.Call
import retrofit2.http.GET

interface PriorityService {

    @GET("Priority")
    fun list(): Call<List<PriorityModel>>
}