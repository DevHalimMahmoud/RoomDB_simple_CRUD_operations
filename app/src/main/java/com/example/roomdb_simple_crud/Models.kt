package com.example.roomdb_simple_crud

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


@Entity
data class User(


    @PrimaryKey var id: Long = 0,

    @ColumnInfo(name = "name") var name: String="",

    @ColumnInfo(name = "age") var age: Int=0,
    @ColumnInfo @Ignore var notes: ArrayList<Note> =ArrayList<Note>()


)

@Entity
class Note(
    @PrimaryKey var id: Int=0,

    @ColumnInfo var text: String=""


)


