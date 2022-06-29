package com.ands.wb5weekweb.utils

import com.ands.wb5weekweb.fragments.AboutFragment
import com.ands.wb5weekweb.fragments.MenuFragment
import com.ands.wb5weekweb.fragments.heroes.DescriptionFragment
import com.ands.wb5weekweb.fragments.heroes.DescriptionFragment.Companion.newInstanceCommonStats
import com.ands.wb5weekweb.fragments.heroes.SuperHeroesFragment
import com.ands.wb5weekweb.model.heroes.CommonHeroesStats
import com.github.terrakok.cicerone.androidx.FragmentScreen


object Screens {    //cicerone

    fun menuScreen() = FragmentScreen{ MenuFragment() }

    fun superHeroesScreen ()= FragmentScreen{SuperHeroesFragment()}

    fun descriptionScreen(common: CommonHeroesStats) =
        FragmentScreen { DescriptionFragment.newInstanceCommonStats(commonStats = common) }


    fun aboutScreen()= FragmentScreen{AboutFragment()}



}