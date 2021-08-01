package com.example.roomdb_simple_crud

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.roomdb_simple_crud.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.txv.text = "aaaaaaa"

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database-name"
        ).build()

        create(db.userDao())
        read(db.userDao())
//        update(db.userDao())
        delete(db.userDao())
    }

    private fun create(db: UserDao) {
        binding.create.setOnClickListener(View.OnClickListener {
            Thread {
                val data = db.getAll()



                if (data.isEmpty()) {


                    createDummyData(db)
                    runOnUiThread {
                        Toast.makeText(
                            applicationContext,
                            "Database Added!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } else {


                    runOnUiThread {
                        Toast.makeText(
                            applicationContext,
                            "Database is not Empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }
            }.start()
        })


    }

    private fun createDummyData(db: UserDao) {
        for (userCount in 0 until 10) {
            //The value we send as parameter is the primary key


            val notes: ArrayList<Note> = ArrayList()
            for (noteCount in 0 until 2) {
                val note = Note(noteCount, "note${noteCount}")

                note.id = noteCount
                note.text = "note${noteCount}"
                notes.add(note)
            }
            val user = User(userCount.toLong(), "testName{$userCount}", 21, notes)
            user.name = "testName{$userCount}"
            user.age = 30

            user.notes = notes

            db.insertAll(user)
        }
    }

    private fun read(db: UserDao) {
        binding.read.setOnClickListener(View.OnClickListener {

            Thread {
                val data = db.getAll()

                if (data.isNotEmpty()) {


                    binding.txv.text = data.toString()
                    runOnUiThread {
                        Toast.makeText(
                            applicationContext,
                            "Data readed successfully",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                } else {
                    runOnUiThread {
                        Toast.makeText(
                            applicationContext,
                            "Database is Empty",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }
            }.start()
        })

    }

    private fun update(db: UserDao) {


    }

    private fun delete(db: UserDao) {

        binding.delete.setOnClickListener(View.OnClickListener {
            Thread {
                val data = db.getAll()

                if (data.isEmpty()) {
                    runOnUiThread {
                        Toast.makeText(
                            applicationContext,
                            "Database is Empty there is nothing to delete",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                } else {


                    db.deleteTable()

                    runOnUiThread {
                        Toast.makeText(
                            applicationContext,
                            "Database deleted! ",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    binding.txv.text = "Database deleted"

                }
            }.start()
        })
    }


}