package com.example.giuaki

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.giuaki.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    lateinit var db: SQLiteDatabase
    lateinit var rs: Cursor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var helper=myHelper(applicationContext)
        db=helper.readableDatabase
        rs=db.rawQuery("Select * from Users Limit 20",null)
        binding.btnInsert.setOnClickListener {
          if(binding.edtPassword.text.toString().compareTo(binding.edtPassword2.text.toString())==0 ){
              var cv=ContentValues()
              cv.put("email", binding.edtMail.text.toString())
              cv.put("userName", binding.edtNameUser.text.toString())
              cv.put("password", binding.edtPassword.text.toString())
              db.insert("Users",null,cv)
              rs.requery()
              binding.edtMail.setText("")
              binding.edtNameUser.setText("")
              binding.edtPassword.setText("")
              binding.edtPassword2.setText("")
              Toast.makeText(this,"Dang ki thanh cong",Toast.LENGTH_SHORT).show()
          }else{
              Toast.makeText(this,"Dang ki that bai",Toast.LENGTH_SHORT).show()
          }
        }
    }
}