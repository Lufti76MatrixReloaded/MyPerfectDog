package de.syntaxinstitut.myperfectdog.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.apicalls.R
import de.syntaxinstitut.myperfectdog.MainViewModel
import java.util.*

class LoginFragment : Fragment() {

    private lateinit var binding: LoginFragment

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding..setOnClickListener {
            findNavController().navigate(LoginFragment.actionLoginFragmentToSignUpFragment())
        }

        binding.loginFragment.loginBtn.setOnClickListener {
            val email = binding.LoginFragment.loginEmailEdit.text.toString()
            val password = binding.LoginFragment.loginPasswordEdit.text.toString()

            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                viewModel.login(email, password)
            }
        }

        viewModel.dogs.observe(
            Observer(
            ) lifeCycleOwner
        ) {
            if (it != null) {
                findNavController()
                    .navigate(R.id.loginFragment)
            }
        }
    }
}
