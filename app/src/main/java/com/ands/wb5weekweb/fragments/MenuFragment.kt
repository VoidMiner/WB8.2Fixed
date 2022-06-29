package com.ands.wb5weekweb.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ands.wb5weekweb.databinding.FragmentMenuBinding
import com.ands.wb5weekweb.di.App
import com.ands.wb5weekweb.utils.Screens
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAdMob()
        binding.openSuperHeroList.setOnClickListener() {
            App.INSTANCE.router.navigateTo(Screens.superHeroesScreen())
        }

        binding.aboutBtn.setOnClickListener {
            App.INSTANCE.router.navigateTo(Screens.aboutScreen())
        }
    }
    override fun onResume() {
        super.onResume()
        binding.adView.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.adView.pause()
    }

    private fun initAdMob(){// тестовый баннер не работают на территории РФ из-за санкций
        MobileAds.initialize(requireContext())
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.adView.destroy()
    }

}