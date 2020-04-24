package com.example.note_taking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class ListAdapter(
    val context: Context?,
    val onLongClick: (task: Task) -> unit={},
    val onClickDone: (task: Task) -> Unit={},
    val onClickImportant: (task : Task) -> Unit={}
) : RecyclerView.Adapter<ListAdapter.TaskHolder>(){
    var listData = mutableListOf<Task>()

    inner class TaskHolder(val view : View) : RecyclerView.ViewHolder(view){
        val btnDone :ImageButton
        val btnContent: EditText
        val btnImportant: ImageButton
        val textContent: TextView

        fun set(task: Task){
            textContent.text=task.content

            if(task.isDone==true)
                btnDone.setBackgroundResource(R.drawable.circle_full)
            else btnDone.setBackgroundResource(R.drawable.circle)

            if(task.isImportant==true)
                R.drawable.star
            else R.drawable.star_0

            btnDone.setOnClickListener{
                onClickDone(task)
            }
            btnImportant.setOnClickListener{
                onClickImportant(task)
            }
            itemView.setOnLongClickListener{
                longClick(task)
                true
        }
    }
}

    fun update(list : List<Task>){
        listData.clear()
        listData.addAll(list)
        notifyDataSetChanged()
    }
    fun isDone(task : Task){
        if(task.isDone==true)
            task.isDone=false
        notifyDataSetChanged()
    }
    fun isImportant(task: Task){
        if(task.isImportant==true)
            task.isImportant=false
        notifyDataSetChanged()
    }
    fun addTask(task: Task){
        listData.add(task)
        notifyDataSetChanged()
    }
    fun deleteTask(task: Task){
        listData.remove(task)
        notifyDataSetChanged()
    }
}