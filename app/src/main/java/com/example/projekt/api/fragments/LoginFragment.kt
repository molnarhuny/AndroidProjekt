package com.example.projekt.api.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.projekt.MyApplication
import com.example.projekt.R
import com.example.projekt.model.LoginRequest
import com.example.projekt.model.LoginResult
import com.example.projekt.repository.TrackerRepository
import com.example.projekt.viewmodel.LoginViewModel
import com.example.projekt.viewmodel.LoginViewModelFactory

class LoginFragment : Fragment() {

    private lateinit var emailEditText: AppCompatEditText
    private lateinit var passwordEditText: AppCompatEditText
    // viewmodel, which we got from the ViewModelProvider
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //returns an implemented ViewModel, with the repository specified?
        val factory = LoginViewModelFactory(TrackerRepository())
        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emailEditText = view.findViewById(R.id.email_input_field)
        passwordEditText = view.findViewById(R.id.password_input_field)
        val loginButton: Button = view.findViewById(R.id.login_button)

        // check if the fragment is associated with any fragment, or not. If not, it throws an exception
        val prefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
        if (!prefs.getString("email", "").equals("")) {
            emailEditText.setText(prefs.getString("email", ""))
        }

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(
                    this.requireContext(),
                    "Please, enter your email and password",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                // we need to serialize the request from a Java object to a Json format
                loginViewModel.login(LoginRequest(email, password))
            }
        }

        loginViewModel.loginResult.observe(viewLifecycleOwner) {
            // Save data to preferences
            if( it == LoginResult.INVALID_CREDENTIALS){
                Toast.makeText(
                    this.requireContext(),
                    "Invalid credentials",
                    Toast.LENGTH_LONG
                ).show()
            }
            if ( it == LoginResult.SUCCESS ) {
                val prefs = requireActivity().getPreferences(Context.MODE_PRIVATE)
                val edit = prefs.edit()
                edit.putString("token", MyApplication.token)
                edit.putLong("deadline", MyApplication.deadline)
                edit.putString("email", emailEditText.text.toString())
                edit.apply()
                findNavController().navigate(R.id.action_LoginFragment_to_ActivitiesFragment)
            }
        }
    }
}