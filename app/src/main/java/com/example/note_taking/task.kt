package com.example.note_taking

import java.io.Serializable

class Task(
    var content: Sting,
    var isDone: Boolean= false,
    var isImportant: Boolean= false,
    var id: String
):Serializable