package com.example.note_taking

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.DialogFragment
import kotlinx.android.synthetic.main.dialog_add.*
import kotlinx.android.synthetic.main.dialog_add.view.*
import java.util.zip.Inflater

class NewTask : DialogFragment(){
    lateinit var btnCancel: Button
    lateinit var btnAdd: Button
    lateinit var content: EditText

    var clickAdd: (task: Task) -> Unit={}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.dialog_add, container, false)
        btnCancel = view.findViewById(R.id.btn_cancel)
        btnAdd =view.findViewById(R.id.btn_add)
        content = view.findViewById(R.id.new_content)

        btnCancel.setOnClickListener(object :View.OnClickListener{
            override fun onClick(v: View?) {
                TODO("Not yet implemented")
            }
        })

        btnAdd.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
                TODO("Not yet implemented")
                if(new_content.text2 != null){
                    val task = Task("${new_content.text2}",0,0)
                    clickAdd(task)
                }
            }
        })
        return super.onCreateView(inflater, container, savedInstanceState)
    }

}