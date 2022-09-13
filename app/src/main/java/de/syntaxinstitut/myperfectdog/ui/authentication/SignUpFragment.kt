package de.syntaxinstitut.myperfectdog.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.apicalls.R
import de.syntaxinstitut.myperfectdog.MainViewModel
import de.syntaxinstitut.myperfectdog.ui.Main.MainViewModel
import kotlinx.coroutines.Dispatchers.Main

class SignUpFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    class FragmentSignupBinding

    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signupCancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.signupSignupButton.setOnClickListener {
            signUp()
        }

        viewModel.currentUser.observe(
            viewLifecycleOwner,
            Observer {
                if (it != null) {
                    findNavController().navigate(R.id.homeFragment)
                }
            }
        )
    }

    private fun signUp() {
        val email = binding.signupMail.text.toString()
        val password = binding.signupPassword.text.toString()

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            viewModel.signUp(email, password)
        }
    }
}
