package br.com.srcabral.mytasks.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import br.com.srcabral.mytasks.R
import br.com.srcabral.mytasks.databinding.ActivityTaskFormBinding
import br.com.srcabral.mytasks.viewmodel.RegisterViewModel
//import kotlinx.android.synthetic.main.activity_register.*

class TaskFormActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityTaskFormBinding
    private lateinit var mViewModel: RegisterViewModel

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
        }
    }

    private fun observe() {
    }

    private fun listeners() {
        //button_save.setOnClickListener(this)
    }

}