package com.kotlinliza.gladjunior.ui.curriculum

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kotlinliza.gladjunior.databinding.FragmentCurriculumBinding

class CurriculumFragment : Fragment() {

    private var _binding: FragmentCurriculumBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentCurriculumBinding.inflate(inflater, container, false)
        val root: View = binding.root


        binding.web.apply {
            loadUrl("https://www.gladstepjuniorschool.com/")
            settings.allowContentAccess=true
            settings.javaScriptEnabled=true
            webViewClient= WebViewClient()
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}