package br.com.srcabral.mytasks.view

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.lifecycle.ViewModelProvider
import br.com.srcabral.mytasks.R
import br.com.srcabral.mytasks.databinding.ActivityTaskFormBinding
import br.com.srcabral.mytasks.viewmodel.RegisterViewModel
import java.text.SimpleDateFormat
import java.util.*

//import kotlinx.android.synthetic.main.activity_register.*

class TaskFormActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener {

    private lateinit var binding: ActivityTaskFormBinding
    private lateinit var mViewModel: RegisterViewModel
    private val mDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        // Inicializa eventos
        listeners()
        observe()
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.button_save) {

            /*val name = edit_name.text.toString()
            val email = edit_email.text.toString()
            val password = edit_password.text.toString()

            mViewModel.create(name, email, password)*/
        } else if (id == R.id.button_date){
            showDatePicker()
        }
    }

    fun showDatePicker(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, this, year, month, day).show()
    }

    private fun observe() {
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