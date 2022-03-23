package br.com.srcabral.mytasks.view

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import br.com.srcabral.mytasks.R
import br.com.srcabral.mytasks.databinding.ActivityTaskFormBinding
import br.com.srcabral.mytasks.service.model.TaskModel
import br.com.srcabral.mytasks.viewmodel.RegisterViewModel
import br.com.srcabral.mytasks.viewmodel.TaskFormViewModel
import java.text.SimpleDateFormat
import java.util.*

//import kotlinx.android.synthetic.main.activity_register.*

class TaskFormActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityTaskFormBinding
    private lateinit var mViewModel: TaskFormViewModel
    private val mDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
    private val mListPriorityId: MutableList<Int> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this).get(TaskFormViewModel::class.java)

        // Inicializa eventos
        listeners()
        observe()

        mViewModel.listPriorities()
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {
            handleSave()
        } else if (id == R.id.button_date){
            showDatePicker()
        }
    }

    fun handleSave(){
        val task = TaskModel().apply {
            this.description = binding.editDescription.text.toString()
            this.complete = binding.checkComplete.isChecked
            this.dueDate = binding.buttonDate.text.toString()
            this.priorityId = mListPriorityId[binding.spinnerPriority.selectedItemPosition]
        }

        mViewModel.save(task)
    }

    fun showDatePicker(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, this, year, month, day).show()
    }

    private fun observe() {
        mViewModel.priorities.observe(this, androidx.lifecycle.Observer {
            val list: MutableList<String> = arrayListOf()
            for(item in it){
                list.add(item.description)
                mListPriorityId.add(item.id)
            }
            val s = list
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, list)
            binding.spinnerPriority.adapter = adapter
        })
    }

    private fun listeners() {
        //button_save.setOnClickListener(this)
        binding.buttonSave.setOnClickListener(this)
        binding.buttonDate.setOnClickListener(this)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

        binding.buttonDate.text = mDateFormat.format(calendar.time)
    }

}