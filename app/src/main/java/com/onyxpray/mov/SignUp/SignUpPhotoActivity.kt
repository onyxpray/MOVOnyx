package com.onyxpray.mov.SignUp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.onyxpray.mov.R
import com.onyxpray.mov.SignIn.User
import com.onyxpray.mov.Utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_up_photo.*

class SignUpPhotoActivity : AppCompatActivity() {

    lateinit var sUsername: String
    lateinit var sPassword: String
    lateinit var sNama: String
    lateinit var sEmail: String

    lateinit var mDatabaseReference: DatabaseReference
    lateinit var mFirebaseInstance: FirebaseDatabase
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up_photo)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        btn_lanjutkan.setOnClickListener {
            sUsername = et_username.text.toString()
            sPassword = et_password.text.toString()
            sNama = et_nama.text.toString()
            sEmail = et_email.text.toString()

            if (sUsername.equals("")) {
                et_username.error = "Silahkan isi Username"
                et_username.requestFocus()
            } else if (sPassword.equals("")) {
                et_password.error = "Silahkan isi Password"
                et_password.requestFocus()
            } else if (sNama.equals("")) {
                et_nama.error = "Silahkan isi Nama"
                et_nama.requestFocus()
            } else if (sEmail.equals("")) {
                et_email.error = "Silahkan isi Email"
                et_email.requestFocus()
            } else {
                saveUsername(sUsername, sPassword, sNama, sEmail)
            }

        }
    }


    fun saveUsername(sUsername: String, sPassword: String, sNama: String, sEmail: String) {
        val user = User()
        user.email = sEmail
        user.username = sUsername
        user.nama = sNama
        user.password = sPassword

        if (sUsername != null) {
            checkingUsername(sUsername, user)
        }
    }

    private fun checkingUsername(sUsername: String, data: User) {
        mDatabaseReference.child(sUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpPhotoActivity, "" + databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    mDatabaseReference.child(sUsername).setValue(data)

                    var goSignUp = Intent(this@SignUpPhotoActivity,
                            SignUpActivity::class.java).putExtra("nama", data.nama)
                    startActivity(goSignUp)

                } else {
                    Toast.makeText(this@SignUpPhotoActivity, "User sudah digunakan", Toast.LENGTH_LONG).show()

                }
            }
        })
    }
}







