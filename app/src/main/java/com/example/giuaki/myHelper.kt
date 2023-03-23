package com.example.giuaki

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class myHelper(context:Context) :SQLiteOpenHelper(context,"UserDB",null,1){
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("Create table Users(_id integer primary key autoincrement,email TEXT,userName TEXT,password TEXT)")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}