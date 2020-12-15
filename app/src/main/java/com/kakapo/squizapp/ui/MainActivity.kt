package com.kakapo.squizapp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.kakapo.squizapp.R
import com.kakapo.squizapp.common.Common
import com.kakapo.squizapp.model.User
import com.rengwuxian.materialedittext.MaterialEditText
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.sign_up_layout.*

class MainActivity : AppCompatActivity() {

    private lateinit var editNewUser: MaterialEditText
    private lateinit var editNewPassword: MaterialEditText
    private lateinit var editNewEmail: MaterialEditText

    private lateinit var editUser: MaterialEditText
    private lateinit var editPassword: MaterialEditText

    private lateinit var btnSignUp: Button
    private lateinit var btnSignIn: Button

    private lateinit var database: FirebaseDatabase
    private lateinit var users: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //firebase
        database = FirebaseDatabase.getInstance()
        users =database.reference

        editUser = findViewById(R.id.edtUser)
        editPassword = findViewById(R.id.edtPassword)

        btnSignIn = findViewById(R.id.btn_sign_in)
        btnSignUp = findViewById(R.id.btn_sign_up)

        btnSignUp.setOnClickListener {
            showSignUpDialog()
        }

        btnSignIn.setOnClickListener {
            signIn(edtUser.text.toString(), edtPassword.text.toString())
        }
    }

    private fun signIn(user: String, password: String){
        users.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.child(user).exists()) {
                    if (user.isNotEmpty()) {
                        val login: User? = dataSnapshot.child(user).getValue(User::class.java)
                        if (login?.password.equals(password)) {
                            val homeActivity = Intent(this@MainActivity, Home::class.java)
                            Common.currentUser = login!!
                            startActivity(homeActivity)
                            finish()
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Wrong Password",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Please enter your user name",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@MainActivity,
                        "User is not exist !",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun showSignUpDialog(){
        val alertDialog =AlertDialog.Builder(this)
        alertDialog.setTitle("Sign Up")
        alertDialog.setMessage("Please fill full information")

        val inflater = LayoutInflater.from(parent) // error paling '<'
        val signUpLayout: View = inflater.inflate(R.layout.sign_up_layout, null)


        editNewUser = signUpLayout.findViewById(R.id.edtNewUserName)
        editNewPassword = signUpLayout.findViewById(R.id.edtNewPassword)
        editNewEmail = signUpLayout.findViewById(R.id.edtNewEmail)

        alertDialog.setView(signUpLayout)
        alertDialog.setIcon(R.drawable.ic_account_circle_black_24dp)

        alertDialog.setNegativeButton("NO"
        ) { dialog, _ -> dialog?.dismiss() }

        alertDialog.setPositiveButton("YES"
        ) { dialog, _ ->
            val user = User(
                edtNewUserName.text.toString(),
                edtNewPassword.text.toString(),
                edtNewEmail.text.toString()
            )

            users.addListenerForSingleValueEvent(object : ValueEventListener{
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    if(dataSnapshot.child(user.userName!!).exists()){
                        Toast.makeText(
                            this@MainActivity,
                            "User already exist !",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onCancelled(p0: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })
            dialog.dismiss()
        }
        alertDialog.show()
    }
}