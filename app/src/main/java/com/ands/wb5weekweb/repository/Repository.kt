package com.ands.wb5weekweb.repository

import com.ands.wb5weekweb.model.heroes.SuperHeroesResponse


interface Repository {

    suspend fun getSuperHeroes(): List<SuperHeroesResponse>


    fun saveSuperHeroes(superHeroes: List<SuperHeroesResponse>)

    fun getSavedSuperHeroes(): List<SuperHeroesResponse >

    fun isCacheExist() : Boolean



}