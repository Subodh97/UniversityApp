package com.example.universityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.universityapp.model.Repository
import com.example.universityapp.model.Student
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var repository: Repository
    lateinit var studentList : List<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        repository = Repository(this)

        updateList()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menu?.add("Add Student")
        menu?.add("Update")
        menu?.add("Delete")
        menu?.add("Delete All")
        menu?.add("Refresh")
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.title){
            "Add Student" -> {
                //repository.addStudent(Student("Yashvardhan","Gupta",95))
                val i = Intent(this, AddStudentActivity::class.java)
                startActivity(i)

            }
            "Update" -> {

            }
            "Delete" -> {

            }
            "Delete All" -> {

            }
            "Refresh" -> {
                updateList()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun updateList(){

        CoroutineScope(Dispatchers.Default).launch {
            studentList = repository.allStudents()
            // adapter.notifyDatasetChanged()
            Log.d("MainActivity", "UpdateList(): $studentList")
        }
    }
}