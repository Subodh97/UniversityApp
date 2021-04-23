package com.example.universityapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.example.universityapp.model.Repository
import com.example.universityapp.model.Student
import kotlinx.android.synthetic.main.activity_add_student.*
import kotlin.math.ln

class AddStudentActivity : AppCompatActivity() {
    lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        repository = Repository(this)

    }


    fun addClick(view: View) {
        val fname= fNameE.text.toString()
        val lname= lNameE.text.toString()
        val marks= marksE.text.toString()

        repository.addStudent(Student("$fname","$lname",marks.toInt()))
        finish()
    }

    fun cancelClick(view: View) {
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)

    }


}