package com.example.s8104425_assignment2.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.s8104425_assignment2.databinding.ActivityLoginBinding
import com.example.s8104425_assignment2.ui.dashboard.DashboardActivity
import com.example.s8104425_assignment2.util.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    private val location = "footscray" //hardcoded api location

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //login button click listener
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            if (username.isBlank() || password.isBlank()) {
                toast("Username or password cannot be empty")//extension function
            } else {
                viewModel.login(location, username, password)
            }
        }

        //observe the login result
        viewModel.loginResult.observe(this) { result ->
            result.onSuccess { keypass ->
                //Navigate to dashboard on success
                val intent = Intent(this, DashboardActivity::class.java)
                intent.putExtra("KEYPASS", keypass)
                startActivity(intent)
                finish()
            }
            result.onFailure { e ->
                toast("Login failed: ${e.message}") //show error toast
            }
        }
    }

}