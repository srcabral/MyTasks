package br.com.srcabral.mytasks.service.listener

import br.com.srcabral.mytasks.service.model.HeaderModel

interface APIListener {
    fun onSuccess(model: HeaderModel)

    fun onFailure(mensage: String)
}