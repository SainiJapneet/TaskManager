package com.example.taskmanager

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class LogInFragment : Fragment() {
    lateinit var edtEmail: EditText
    lateinit var edtPassword: EditText
    lateinit var txtPasswordForget: TextView
    lateinit var txtSignUp: TextView
    lateinit var btnLogIn: Button
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var myFrag = inflater.inflate(R.layout.fragment_log_in, container, false)
        edtEmail = myFrag.findViewById(R.id.edtEmailSignUp)
        edtPassword = myFrag.findViewById(R.id.edtPasswordSignUp)
        txtPasswordForget = myFrag.findViewById(R.id.txtPasswordForget)
        txtSignUp = myFrag.findViewById(R.id.txtSignUp)
        btnLogIn = myFrag.findViewById(R.id.btnSignUp)

        firebaseAuth = FirebaseAuth.getInstance()
        sharedPreferences = requireContext().getSharedPreferences("Shared_Prefs",MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        fun isEmailValid(em: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(em).matches()
        }

        fun validateInput(): Boolean{
            if(edtEmail.text.toString() == "" && edtPassword.text.toString() == ""){
                edtEmail.error = "Please enter Email"
                edtPassword.error = "Please enter a password"
                return false
            }


            if(!isEmailValid(edtEmail.text.toString().trim())){
                edtEmail.error = "Please enter valid email"
                return false
            }

            if(edtPassword.text.toString().length < 8){
                edtPassword.error = "Password length must be greater than 8"
                return false
            }
            return true
        }

        txtSignUp.setOnClickListener {
            var myFrag = parentFragmentManager.beginTransaction()
            myFrag.replace(R.id.authContainer,SignUpFragment())
            myFrag.commit()
        }

        btnLogIn.setOnClickListener {
            if (validateInput()){
                firebaseAuth.signInWithEmailAndPassword(edtEmail.text.toString().trim(), edtPassword.text.toString())
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            editor.putString("Cred_ID",edtEmail.text.toString())
                            editor.putBoolean("Cred_Pref",true)
                            editor.commit()
                            edtEmail.setText("")
                            edtPassword.setText("")
                            val intent = Intent(context,MainActivity::class.java)
                            startActivity(intent)
                            getActivity()?.getFragmentManager()?.popBackStack();
                        }
                        else{
                            Toast.makeText(context, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

            }
        }

        return myFrag
    }
}