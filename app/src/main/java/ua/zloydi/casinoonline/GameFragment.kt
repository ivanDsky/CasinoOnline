package ua.zloydi.casinoonline

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ua.zloyhr.casinoonline.R
import ua.zloyhr.casinoonline.databinding.FragmentGameBinding
import kotlin.random.Random

class GameFragment : Fragment(R.layout.fragment_game) {
    private lateinit var binding: FragmentGameBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentGameBinding.bind(view)

        binding.btnSpin.setOnClickListener {
            spinRoulette()
        }
    }

    //Spins roulette on random value
    private fun spinRoulette() {
        val startValue = binding.ivRoulette.rotation
        val endValue = startValue + Random.nextInt(3000, 8000)
        ObjectAnimator.ofFloat(binding.ivRoulette, "rotation", startValue, endValue).apply {
            duration = 5000
            start()
        }
    }
}