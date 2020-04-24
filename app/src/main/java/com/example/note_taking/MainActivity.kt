package com.example.note_taking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private val fragmentManager = supportFragmentManager
    private val fragHome = FragHome()
    private val fragImportant = FragImportant()
    private val fragDone = FragDone()
    private val fragNewTask = NewTask()
    lateinit var taskDelete : Task
    val fragmentTransaction = fragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.activity_main))
    }
    loadFragment(FragHome())

    private fun loadFragment( fragment: Fragment){
        supportFragmentManager.beginTransaction().also { fragmentTransaction ->
            fragmentTransaction.replace(R.id.rcvHome, fragment)
            fragmentTransaction.commit()
        }
    }

}
