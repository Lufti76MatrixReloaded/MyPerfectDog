package de.syntaxinstitut.myperfectdog.ui.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import de.syntaxinstitut.myperfectdog.R
import de.syntaxinstitut.myperfectdog.adapter.ItemAdapter
import de.syntaxinstitut.myperfectdog.data.Datasource
import de.syntaxinstitut.myperfectdog.databinding.FragmentHomeBinding

/**
 * Fragment Home
 */
class HomeFragment : Fragment(R.layout.fragment_home) {

    /* -------------------- Klassen Variablen -------------------- */

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    private lateinit var binding: FragmentHomeBinding

    /** Das ViewModel zu diesem Fragment */
    private val viewModel: HomeViewModel by viewModels()


    /* -------------------- Lifecycle -------------------- */

    /**
     * Lifecycle Methode wenn das View erstellt wird
     *
     * @param inflater                Layout Inflater
     * @param container               View Gruppe
     * @param savedInstanceState      Eventuelle saveStates
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        return binding.root
    }

    /**
     * Lifecycle Methode nachdem das View erstellt wurde
     *
     * @param view                    Das angezeigte View
     * @param savedInstanceState      Eventuelle saveStates
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val dogs = Datasource().loadDogs()

        val recyclerView = binding.lifecycleOwner

        recyclerView.lifecycleOwner = ItemAdapter(requireContext(), dogs)

        recyclerView.setHasFixedSize(true)


        /* -------------------- UI-Interaktionen -------------------- */

        binding.mainBtn.setOnClickListener {
            viewModel.navigateToFragmentDetail()
        }


        /* -------------------- Observer -------------------- */

        // Navigation zum zweiten Fragment
        viewModel.navigateToFragmentDetail.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToDetailsFragment()
                )

                viewModel.resetAllValues()
            }
        }
    }
}


