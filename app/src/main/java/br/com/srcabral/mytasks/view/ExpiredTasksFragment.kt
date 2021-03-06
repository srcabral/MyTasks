package br.com.srcabral.mytasks.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.srcabral.mytasks.R
import br.com.srcabral.mytasks.viewmodel.ExpiredTasksViewModel

class ExpiredTasksFragment : Fragment() {

    private lateinit var mViewModel: ExpiredTasksViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View? {
        mViewModel = ViewModelProvider(this).get(ExpiredTasksViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_expired_tasks, container, false)

        return root
    }
}