package com.ivoxavier.tasks.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ivoxavier.tasks.R
import com.ivoxavier.tasks.databinding.ActivityTaskFormBinding
import com.ivoxavier.tasks.service.constants.TaskConstants
import com.ivoxavier.tasks.service.model.PriorityModel
import com.ivoxavier.tasks.service.model.TaskModel
import com.ivoxavier.tasks.viewmodel.TaskFormViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class TaskFormActivity : AppCompatActivity(), View.OnClickListener,
    DatePickerDialog.OnDateSetListener {

    private lateinit var viewModel: TaskFormViewModel
    private lateinit var binding: ActivityTaskFormBinding
    private val dateFormat = SimpleDateFormat("dd/MM/yyyy")
    private var listPriority: List<PriorityModel> = mutableListOf()
    private var taskIdentification = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Variáveis da classe
        viewModel = ViewModelProvider(this)[TaskFormViewModel::class.java]
        binding = ActivityTaskFormBinding.inflate(layoutInflater)

        // Eventos
        binding.buttonSave.setOnClickListener(this)
        binding.buttonDate.setOnClickListener(this)

        viewModel.loadPriorities()


        loadDataFromActivity()

        observe()

        // Layout
        setContentView(binding.root)
    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_date){
            handleDate()
        } else if(v.id == R.id.button_save) {
            handleSave()
        }
    }


    private fun getIndex(priorityId: Int): Int{
        var index = 0
        for(l in listPriority){
            if(l.id == priorityId){
                break
            }
            index++
        }
        return index
    }

    private fun observe(){
        viewModel.priorityList.observe(this){
            listPriority = it
            val listPriority = mutableListOf<String>()
            for(p in it){
                listPriority.add(p.description)
            }
            val adapter = ArrayAdapter(
                applicationContext,
                android.R.layout.simple_spinner_dropdown_item,
                listPriority
            )
            binding.spinnerPriority.adapter = adapter
        }
        viewModel.taskSave.observe(this){
            if(it.status()){
                if(taskIdentification == 0){
                    toast("Tarefa criada com sucesso")
                }else{
                    toast("Tarefa atualizada com sucesso")
                }
                finish()
            }else{
                toast(it.message())
            }
        }
        viewModel.task.observe(this){
            binding.editDescription.setText(it.description)
            binding.checkComplete.isChecked = it.complete
            binding.spinnerPriority.setSelection(getIndex(it.priorityId))

            val date = SimpleDateFormat("yyyy-MM-dd").parse(it.dueDate)
            binding.buttonDate.text = SimpleDateFormat("dd/MM/yyyy").format(date)

        }
        viewModel.taskLoad.observe(this){
            if(!it.status()){
             toast(it.message())
            }
        }

    }


    private fun loadDataFromActivity(){
        val bundle = intent.extras
        if(bundle != null){
            taskIdentification = bundle.getInt(TaskConstants.BUNDLE.TASKID)
            viewModel.load(taskIdentification)
        }
    }



    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)

       val dueDate = dateFormat.format(calendar.time)
        binding.buttonDate.text = dueDate
    }
    private fun handleDate(){
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, this, year, month, day).show()
    }


    private fun toast(str: String){
        Toast.makeText(applicationContext, str, Toast.LENGTH_SHORT).show()
    }


    private fun handleSave(){
        val task = TaskModel().apply{
            this.id = taskIdentification
            this.description = binding.editDescription.text.toString()
            val index = binding.spinnerPriority.selectedItemPosition
            this.priorityId = listPriority[index].id
            this.complete = binding.checkComplete.isChecked
            this.dueDate = binding.buttonDate.text.toString()

        }
        viewModel.save(task)
    }

}