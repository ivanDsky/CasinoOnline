package ua.zloydi.casinoonline

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ua.zloyhr.casinoonline.R
import ua.zloyhr.casinoonline.databinding.FragmentGameBinding

class GameFragment : Fragment(R.layout.fragment_game) {
    private lateinit var binding: FragmentGameBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameBinding.bind(view)
    }
}