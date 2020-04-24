package com.example.note_taking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FragImportant : Fragment(){
    lateinit var rcvImportant: RecyclerView
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
        rcvImportant=view.findViewById(R.id.rcvImportant)
        adapter= ListAdapter(this.context)
        rcvImportant.apply {
            layoutManager = LinearLayoutManager(this@FragImportant.context)
            adapter = this@FragImportant.adapter
        }
        adapter.update(Data.listImportant)
        adapter.onClickDone={
            adapter.isDone(it)
            Data.db.updateTask(it.id.toString(), it)
            if(it.isDone==true){
                clickDone(it)
                Data.listDone.add(it)
                Data.listImportant.remove(it)
            }
        }
    }
}