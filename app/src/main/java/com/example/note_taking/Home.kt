package com.example.note_taking

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_home.*

class FragHome : Fragment(){
    lateinit var rcvHome: RecyclerView
    lateinit var btnAdd: Button
    lateinit var adapter: ListAdapter
    lateinit var task:Task

    val dialogNewTask =NewTask()
    var clickImportant: (task: Task) -> Unit{}
    var clickDone: (task: Task) -> Unit{}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcvHome=view.findViewById(R.id.rcvHome)
        adapter = ListAdapter(this.context)
        btnAdd=view.findViewById(R.id.btn_add)
        rcvHome.apply {
            layoutManager = LinearLayoutManager(this@FragHome.context)
            adapter = this@FragHome.adapter
        }
        adapter.update(Data.listHome)
        adapter.onClickDone={
            adapter.isDone(it)
            Data.db.updateTask(it.id.toString(), it)
            if(it.isDone==true){
                clickDone(it)
                Data.listDone.add(it)
                Data.listHome.remove(it)
            }
        }

        adapter.onClickImportant={
            adapter.isImportant(it)
            Data.db.updateTask(it.id.toString(), it)
            if(it.isImportant==true){
                clickImportant(it)
                Data.listImportant.add(it)
                Data.listHome.remove(it)
            }
        }
    }
        fun deleteTask(task: Task){
            adapter.deleteTask(task)
        }
        fun updateTask(task:Task){
            adapter.isImportant(task)
        }
        fun newTask(task: Task){
            adapter.newTask(task)
        }
}