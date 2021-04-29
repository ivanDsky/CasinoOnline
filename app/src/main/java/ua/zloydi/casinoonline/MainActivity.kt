package ua.zloydi.casinoonline

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ua.zloyhr.casinoonline.R
import ua.zloyhr.casinoonline.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var behaviour: ApplicationBehaviour

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        behaviour = ApplicationBehaviour()
        behaviour.addFilter { false }
        behaviour.addSuccessListener {
            setFragment(WebViewFragment())
        }
        behaviour.addFailedListener {
            setFragment(GameFragment())
        }
        behaviour.update()
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, fragment)
            commit()
        }
    }
}