package com.example.note_taking

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQL( context: Context, factory: SQLiteDatabase.CursorFactory?) : SQLiteOpenHelper(context, DATABASE_NAME, factory, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        TODO("Not yet implemented")
        db!!.execSQL(
            "CREATE TABLE_$TABLE_NAME" + "($COLUMN_ID INTEGER PRIMARY KEY $COLUMN_CONTENT TEXT $COLUMN_ISDONE INTEGER $COLUMN_ISIMPORTANT INTEGER)"

        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun addTask(list: List<Task>) {
        val contentValue = ContentValues()
        val db = this.writableDatabase
        for (i in list) {
            contentValue.put(COLUMN_CONTENT, i.content)
            contentValue.put(COLUMN_ISDONE, i.isDone)
            contentValue.put(COLUMN_ISIMPORTANT, i.isImportant)
            db.insert(TABLE_NAME, null, contentValue)
        }
    }

    fun updateTask(id: String, task: Task) {
        val contentValue = ContentValues()
        contentValue.put(COLUMN_CONTENT, task.content)
        contentValue.put(COLUMN_ISDONE, task.isDone)
        contentValue.put(COLUMN_ISIMPORTANT, task.isImportant)

        val db = this.writableDatabase
        db.update(TABLE_NAME, contentValue, "$COLUMN_ID = ?", arrayOf(id))
    }

    fun deleteTask(id: String) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id))
    }

    fun readAll(): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }
}

object Data{
    var listHome = mutableListOf<Task>()
    var listDone = mutableListOf<Task>()
    var listImportant = mutableListOf<Task>()

    lateinit var db : SQL
    fun loadListData(){
        listHome.clear()
        listImportant.clear()
        listDone.clear()

        val cursor = db.readAll()
        cursor!!.moveToFirst()
        while(!cursor.isAfterLast){
            var task = Task("2", 0, 0)
            task.id = cursor.getInt(cursor.getColumnIndex(SQL.COLUMN_ID))
            task.content = cursor.getString(cursor.getColumnIndex(SQL.COLUMN_CONTENT))
            task.isDone = cursor.getInt(cursor.getColumnIndex(SQL.COLUMN_ISDONE))
            task.isImportant = cursor.getInt(cursor.getColumnIndex(SQL.COLUMN_ISIMPORTANT))
            if(task.isImportant==true) listImportant.add(task)
            if(task.isDone==true) listDone.add(task)
            else listHome.add(task)
            cursor.moveToNext()
        }
    }
}