package ua.zloydi.casinoonline

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ua.zloyhr.casinoonline.R
import ua.zloyhr.casinoonline.databinding.FragmentWebViewBinding

class WebViewFragment : Fragment(R.layout.fragment_web_view) {
    companion object {
        const val LOAD_URL = "https://pin-up.games/"
    }

    private lateinit var binding: FragmentWebViewBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentWebViewBinding.bind(view)

        binding.webView.loadUrl(LOAD_URL)
    }
}