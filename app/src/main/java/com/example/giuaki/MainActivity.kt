package com.example.giuaki

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.giuaki.databinding.ActivityMainBinding
import java.util.regex.Pattern

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    lateinit var db: SQLiteDatabase
    lateinit var rs: Cursor
    val EMAIL_ADDRESS_PATTERN = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var helper=myHelper(applicationContext)
        db=helper.readableDatabase
        rs=db.rawQuery("Select * from Users Limit 20",null)
        binding.btnInsert.setOnClickListener {
          if(binding.edtPassword.text.toString().compareTo(binding.edtPassword2.text.toString())==0
              && isValidString(binding.edtMail.text.toString() )
              && binding.edtNameUser.text.toString().trim().isNotEmpty()
              && binding.edtPassword.text.toString().trim().isNotEmpty()
          ){
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
    fun isValidString(str: String): Boolean{
        return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
    }
}