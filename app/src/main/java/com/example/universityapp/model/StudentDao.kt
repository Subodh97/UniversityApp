package com.example.universityapp.model

import androidx.room.*

@Dao
interface StudentDao {
    //define Methods for Queries

    @Insert
    fun insert(std: Student)

    @Update
    fun update(std: Student)

    @Delete
    fun delete(std: Student)

    @Query("DELETE FROM student_table")
    fun deleteAll()

    @Query("select * from student_table order by marks desc")
    fun getStudents() : List<Student>

}