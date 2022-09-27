package de.syntaxinstitut.doggy_guide.ui.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.doggy_guide.R
import de.syntaxinstitut.doggy_guide.ui.main.MainActivity
import de.syntaxinstitut.myperfectdog.databinding.FragmentMainBinding
import java.util.*

class LoginFragment : Fragment() {

    lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
                             ): View? {
        (activity as MainActivity).hideToolbar()

        (activity as MainActivity).hideToolbar()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.loginLoginButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragment2ToSignUpFragment())
        }
        binding.loginSignUpButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragment2ToMainFragment())
        }
    }
}