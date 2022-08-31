package de.syntaxinstitut.myperfectdog.ui.Details

import android.os.Bundle
import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import de.syntaxinstitut.myperfectdog.R
import de.syntaxinstitut.myperfectdog.databinding.FragmentDetailBinding

/**
 * Fragment Detail
 */
class DetailFragment : Fragment(R.layout.fragment_detail) {

    /* -------------------- Klassen Variablen -------------------- */

    /** Bindet das XML-View mit der Klasse um auf die Elemente zugreifen zu können */
    private lateinit var binding: FragmentDetailBinding
        private var stringId = 0
        private var pictureId = 0
    }
    /** Das ViewModel zu diesem Fragment */
    private val viewModel: DetailViewModel by viewModels()

    /* -------------------- Lifecycle -------------------- */
    /**
     * Lifecycle Methode wenn das View erstellt wird
     *
     * @param inflater                Layout Inflater
     * @param container               View Gruppe
     * @param savedInstanceState      Eventuelle saveStates
     */

    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    arguments?.let {
        stringId = it.getInt("stringId")
        pictureId = it.getInt("pictureId")
    }
}

override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?,
): View? {

    binding = DataBindingUtil.inflate(
        inflater, R.layout.fragment_detail, container, false
    )
    return binding.root
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    val offerText = getString(stringId)

    binding.detailText.text = offerText

    binding.detailImage.setImageResource(pictureId)

    binding.detailBackgroundImage.setImageResource(pictureId)

}

