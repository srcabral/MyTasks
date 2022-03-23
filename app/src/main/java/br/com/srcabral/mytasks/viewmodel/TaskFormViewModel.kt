package br.com.srcabral.mytasks.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.srcabral.mytasks.service.model.PriorityModel
import br.com.srcabral.mytasks.service.model.TaskModel
import br.com.srcabral.mytasks.service.repository.PriorityRepository

class TaskFormViewModel(application: Application) : AndroidViewModel(application) {
    private val mPriorityRepository = PriorityRepository(application)

    private val mPriorityList = MutableLiveData<List<PriorityModel>>()
    var priorities: LiveData<List<PriorityModel>> = mPriorityList

    fun listPriorities() {
        mPriorityList.value = mPriorityRepository.list()
    }

    fun save(task: TaskModel) {
        TODO("Not yet implemented")
    }


}