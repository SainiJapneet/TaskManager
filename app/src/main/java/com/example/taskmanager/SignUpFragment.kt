package com.example.taskmanager

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var edtEmailSignUp: EditText
    lateinit var edtPassowrdSignUp: EditText
    lateinit var btnSignUp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var myFrag = inflater.inflate(R.layout.fragment_sign_up, container, false)
        edtEmailSignUp = myFrag.findViewById(R.id.edtEmailSignUp)
        edtPassowrdSignUp = myFrag.findViewById(R.id.edtPasswordSignUp)
        btnSignUp = myFrag.findViewById(R.id.btnSignUp)

        firebaseAuth = FirebaseAuth.getInstance()
        fun isEmailValid(mail: String): Boolean{
            return Patterns.EMAIL_ADDRESS.matcher(mail).matches()
        }

    fun validateInput(): Boolean{
        if(edtEmailSignUp.text.toString() == "" &&
            edtPassowrdSignUp.text.toString() == ""){
            Toast.makeText(context,"Fill all the fields",Toast.LENGTH_LONG).show()
        }
        if(!isEmailValid(edtEmailSignUp.text.toString())){
            edtEmailSignUp.error = "Please enter Valid Email"
            return false
        }
        if(edtPassowrdSignUp.text.length < 8){
            edtPassowrdSignUp.error = "Password length must be more than 8 characters"
            return false
        }
        return true
    }

        btnSignUp.setOnClickListener {
            if(validateInput()){
                firebaseAuth.createUserWithEmailAndPassword(
                    edtEmailSignUp.text.toString().trim(), edtPassowrdSignUp.text.toString().trim()
                ).addOnCompleteListener{
                    if(it.isSuccessful){
                        firebaseAuth.currentUser?.sendEmailVerification()?.addOnCompleteListener{
                            Toast.makeText(context,"User registered successfully",Toast.LENGTH_LONG).show()
                            var myFrag = parentFragmentManager.beginTransaction()
                            myFrag.replace(R.id.authContainer,LogInFragment())
                            myFrag.commit()
                        }
                    }
                    else{
                        Toast.makeText(context,"Try again later!",Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        return myFrag
    }
}