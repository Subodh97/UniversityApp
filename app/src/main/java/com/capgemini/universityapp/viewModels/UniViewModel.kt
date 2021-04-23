package com.capgemini.universityapp.viewModels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.capgemini.universityapp.model.Repository
import com.capgemini.universityapp.model.RepositoryVM
import com.capgemini.universityapp.model.StudentDatabase
import com.capgemini.universityapp.model.University
import com.capgemini.universityapp.view.AboutActivity
import com.capgemini.universityapp.view.MyAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UniViewModel(app:Application):AndroidViewModel(app) {
    private val studentDao = StudentDatabase.getInstance(AboutActivity()).studentDao()
    val univ = University("VIT University",
        "Vellore TamilNadu",
        "about@vit.ac.in")

    var studentCount =  MutableLiveData<Int>()
    val repository = RepositoryVM(app)

    init {
        updateCount()
    }
    fun updateCount(){

        viewModelScope.launch {
            val list = repository.allStudents()
       studentCount.postValue((list.size))
        }


    }
}