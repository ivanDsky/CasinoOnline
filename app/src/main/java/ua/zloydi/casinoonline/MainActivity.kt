package ua.zloydi.casinoonline

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.messaging.FirebaseMessaging
import ua.zloyhr.casinoonline.R
import ua.zloyhr.casinoonline.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var behaviour: ApplicationBehaviour

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBehaviour()

        firebaseToken()

    }

    //Trying to get Firebase token
    private fun firebaseToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener{ task ->
            if (!task.isSuccessful) {
                Log.w("Token", "Fetching FCM registration token failed", task.exception)
                return@addOnCompleteListener
            }

            // Get new FCM registration token
            val token = task.result?:"NO token"

            // Log and toast
            Log.d("Token", "Token $token")
        }
    }

    //Setting application behaviour that depends on filters
    private fun setupBehaviour(){
        behaviour = ApplicationBehaviour()
        behaviour.addFilter { true }
        behaviour.addSuccessListener {
            setFragment(WebViewFragment())
        }
        behaviour.addFailedListener {
            setFragment(GameFragment())
        }
        //Runs application behaviour
        behaviour.update()
    }


    //Attach fragment to Fragment container view
    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container_view, fragment)
            commit()
        }
    }


}