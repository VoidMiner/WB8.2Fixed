package com.ands.wb5weekweb.fragments.heroes

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import coil.size.Scale
import com.ands.wb5weekweb.R
import com.ands.wb5weekweb.databinding.FragmentDescriptionBinding
import com.ands.wb5weekweb.di.App
import com.ands.wb5weekweb.model.heroes.CommonHeroesStats
import com.ands.wb5weekweb.utils.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DescriptionFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionBinding

    private var sizeWidth = 0
    private var sizeHeight = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadScreenSize()
        setupDescription()
        binding.backBtn.setOnClickListener() {
            App.INSTANCE.router.backTo(Screens.superHeroesScreen())
        }
    }

    private fun loadScreenSize() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.R) {
            val display = requireActivity().windowManager.defaultDisplay
            sizeWidth = display.width
            sizeHeight = display.height
        } else {
            val bounds = requireActivity().windowManager.currentWindowMetrics.bounds
            sizeWidth = bounds.width()
            sizeHeight = bounds.height()
        }
    }

    private fun setupDescription() {

        val argument = requireArguments()

        val args = argument.getParcelable<CommonHeroesStats>(COMMON_STATS) ?: throw IllegalArgumentException("null argument")


        binding.apply {

            name.text = args.name
            baseInt.text = String.format(getString(R.string.baseIntPlaceholder), args.baseInt)
            baseSpeed.text =
                String.format(getString(R.string.baseMovementSpeedPlaceholder), args.movementSpeed)
            baseStr.text = String.format(getString(R.string.baseStrPlaceholder), args.baseStrength)

            imageView.load(args.image) {
                scale(Scale.FIT)
                size(sizeWidth, sizeHeight)
            }

        }
    }

    companion object {
        fun newInstanceCommonStats(commonStats: CommonHeroesStats) : DescriptionFragment {
            return DescriptionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(COMMON_STATS, commonStats)
                }
            }
        }
        private const val COMMON_STATS = "common_stats"
    }

}