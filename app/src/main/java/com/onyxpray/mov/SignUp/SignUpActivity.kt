package com.onyxpray.mov.SignUp

import android.Manifest
import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.onyxpray.mov.Home.HomeScreenActivity
import com.onyxpray.mov.R
import com.onyxpray.mov.Utils.Preferences
import java.util.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import com.google.firebase.storage.FirebaseStorage as FirebaseStorage1
import com.google.firebase.storage.StorageReference
class SignUpActivity : AppCompatActivity(),PermissionListener {
    val REQUEST_IMAGE_CAPTURE = 1
    var statusAdd:Boolean = false
    lateinit var filePath: Uri

    lateinit var storage : FirebaseStorage1
    lateinit var storageReference: StorageReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        preferences = Preferences(this)
        storage = FirebaseStorage1.getInstance()
        storageReference = storage.getReference()


        tv_hello.text = "Selamat Datang\n"+intent.getStringExtra("nama")

        iv_add.setOnClickListener {
            if (statusAdd) {
                statusAdd = false
                btn_save.visibility = View.INVISIBLE
                iv_add.setImageResource(R.drawable.ic_btn_upload)
                iv_profile.setImageResource(R.drawable.avaprofil)

            } else {
              Dexter.withActivity(this)
                  .withPermission(Manifest.permission.CAMERA)
                   .withListener(this)
                  .check()


            }
        }

        btn_home.setOnClickListener {

            finishAffinity()

            val goHome = Intent(this@SignUpActivity,
                HomeScreenActivity::class.java)
            startActivity(goHome)
        }

        btn_save.setOnClickListener {
            if (filePath != null) {
                val progressDialog = ProgressDialog(this)
                progressDialog.setTitle("Uploading...")
                progressDialog.show()

                val ref = storageReference.child("images/" + UUID.randomUUID().toString())
                ref.putFile(filePath)
                    .addOnSuccessListener {
                        progressDialog.dismiss()
                        Toast.makeText(this@SignUpActivity, "Uploaded", Toast.LENGTH_SHORT).show()
                        ref.downloadUrl.addOnSuccessListener {
                           preferences.setValues("url",it.toString())
                        }
                    }
                    .addOnFailureListener { e ->
                        progressDialog.dismiss()
                        Toast.makeText(this@SignUpActivity, "Failed " + e.message, Toast.LENGTH_SHORT).show()
                    }
                    .addOnProgressListener { taskSnapshot ->
                        val progress = 100.0 * taskSnapshot.bytesTransferred / taskSnapshot.totalByteCount
                        progressDialog.setMessage("Uploaded " + progress.toInt() + "%")
                    }

            }
        }

    }

    override fun onPermissionGranted(response: PermissionGrantedResponse?) {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
        takePictureIntent.resolveActivity(packageManager)?.also {
              startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
          }
      }
    }

    override fun onPermissionDenied(response: PermissionDeniedResponse?) {

    }

    override fun onPermissionRationaleShouldBeShown(
        permission: PermissionRequest?,
        token: PermissionToken?
    ) {
        Toast.makeText(this, "Anda tidak bisa menambahkan photo profile", Toast.LENGTH_LONG ).show()
    }
    override fun onBackPressed() {
        Toast.makeText(this, "Tergesah? Klik tombol Upload Nanti aja", Toast.LENGTH_LONG ).show()
    }
    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
           var bitmap = data?.extras?.get("data") as Bitmap
            statusAdd = true
           filePath = data.getData()!!
           Glide.with(this)
              .load(bitmap)
              .apply(RequestOptions.circleCropTransform())
               .into(iv_profile)
          btn_save.visibility = View.VISIBLE
          iv_add.setImageResource(R.drawable.ic_btn_delete)
      }
}}
