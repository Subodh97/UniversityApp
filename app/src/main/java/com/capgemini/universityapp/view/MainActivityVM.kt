package com.capgemini.universityapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.capgemini.universityapp.R
import com.capgemini.universityapp.model.Repository
import com.capgemini.universityapp.model.Student
import com.capgemini.universityapp.viewModels.StudentViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityVM : AppCompatActivity() {

    lateinit var model: StudentViewModel
    lateinit var repository: Repository
    var adapter: MyAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vmProvider = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(application)
        )
        model = vmProvider.get(StudentViewModel::class.java)
        setUpRecyclerView()
        //DON'T DO THIS
        //model = StudentViewModel(application)

        model.studentList.observe(this, Observer {
            val stdList = it
            Log.d("MainActivityVM","Observer: $stdList")
            //setup adapter

            adapter = MyAdapter(model.studentList.value!!)
            rView.adapter = adapter
        })

    }

    override fun onResume() {
        super.onResume()
        model.getStudents()
    }

    private fun setUpRecyclerView() {
        rView.layoutManager = LinearLayoutManager(this)
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Add Student")
        menu?.add("Update")
        menu?.add("Delete")
        menu?.add("Delete All")
        menu?.add("About Us")
       // menu?.add("Refresh")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.title) {
            "Add Student" -> {
                val intent = Intent(this, StudentData::class.java)
                startActivity(intent)
            }
            "Update" -> {
                val stdIntent = Intent(this,StudentData::class.java)
                stdIntent.putExtra("isUpdate",true)
                //stdIntent.putExtra()
                startActivity(stdIntent)
//                model.updateStudent(
////                    Student(
////                        "John",
////                        "Smith",
////                        75, 1
////                    )
//                )
            }
            "Delete" -> {
            }
            "Delete All" -> {
                model.deleteAll()
            }
//            "Refresh" -> {
////                updateList()
//            }
            "About Us" ->{
                    val i = Intent(this,AboutActivity::class.java)
                startActivity(i)
            }
        }

        return super.onOptionsItemSelected(item)
    }

//    private fun updateList() {
//        val stdList = model.studentList
//        if(adapter == null){
//            adapter = MyAdapter(model.studentList)
//            rView.layoutManager = LinearLayoutManager(this)
//            rView.adapter = adapter
//        }
//        else{
//            adapter?.notifyDataSetChanged()
//        }
//        Log.d("MainActivity","Updated List: $stdList")
//    }
}
