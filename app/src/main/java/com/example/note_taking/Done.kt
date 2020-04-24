package com.example.note_taking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragDone : Fragment(){
    lateinit var rcvDone: RecyclerView
    lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcvDone=view.findViewById(R.id.rcvDone)
        adapter= ListAdapter(this.context)
        rcvDone.apply {
            layoutManager = LinearLayoutManager(this@FragDone.context)
            adapter = this@FragDone.adapter
        }
        adapter.update(Data.listDone)
        adapter.onClickDone={
            adapter.isDone(it)
            Data.db.updateTask(it.id.toString(), it)
            if(it.isDone==true){
                clickDone(it)
                Data.listHome.add(it)
                Data.listDone.remove(it)
            }
        }

        adapter.onClickImportant={
            adapter.isImportant(it)
            Data.db.updateTask(it.id.toString(), it)
            if(it.isImportant==true){
                clickImportant(it)
                Data.listImportant.add(it)
            }
        }
    }
}