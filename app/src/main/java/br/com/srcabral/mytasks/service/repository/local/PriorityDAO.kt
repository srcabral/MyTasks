package br.com.srcabral.mytasks.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.srcabral.mytasks.service.model.PriorityModel

@Dao
interface PriorityDAO {

    @Insert
    fun save(list: List<PriorityModel>)

    @Query("DELETE FROM priority")
    fun clear()
}