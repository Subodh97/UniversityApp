package com.example.universityapp.model

import androidx.room.*

@Entity(tableName = "student_table")

data class Student(@ColumnInfo(name ="first_name") var firstName: String,
                   var lastName: String,
                   var marks: Int,
                   @PrimaryKey(autoGenerate = true) var id: Int =0)
