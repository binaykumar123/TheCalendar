package com.example.thecalendar.login.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.thecalendar.R
import com.example.thecalendar.auth.authHandler.AuthStatus
import com.example.thecalendar.core.userservice.AppUser
import com.example.thecalendar.databinding.FragmentLoginBinding
import com.example.thecalendar.login.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val loginViewModel by viewModels<LoginViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAuthHandler()
        binding.signInButton.setOnClickListener {
            signIn()
        }
    }

    private fun initAuthHandler() {
        loginViewModel.initAuthHandler(requireActivity()).observe(viewLifecycleOwner) {
            updateUI(it)
        }
    }

    private fun signIn() {
        loginViewModel.signIn(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        loginViewModel.onLoginActivityResult(requestCode, data, requireActivity())
    }


    private fun updateUI(authStatus: AuthStatus?) {
        when (authStatus) {
            is AuthStatus.LoginError -> onLoginError()
            is AuthStatus.LoginSuccessful -> onLoginSuccessful(authStatus.user)
            else -> {

            }
        }
    }

    private fun onLoginError() {
        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_LONG).show()
    }

    private fun onLoginSuccessful(user: AppUser?) {
        user?.let {
            showWelcomeText(it.name ?: "")
            Toast.makeText(requireContext(), "Welcome ${it.name}", Toast.LENGTH_LONG).show()
        }
    }

    private fun showWelcomeText(name: String) {
        binding.signInButton.visibility = View.GONE
        binding.tvWelcomeText.apply {
            text = "Welcome $name"
            visibility = View.VISIBLE
        }
        redirectToCalendarFragment()
    }

    private fun redirectToCalendarFragment(){
        lifecycleScope.launch {
            delay(2000)
            findNavController().navigate(R.id.action_loginFragment_to_calendarFragment)
        }
    }

    private fun showLoader(){}
}
