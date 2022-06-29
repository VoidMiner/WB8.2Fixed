package com.ands.wb5weekweb.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.ands.wb5weekweb.api.ApiServiceSuperHeroes
import com.ands.wb5weekweb.model.heroes.SuperHeroesResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File


class RepositoryImpl(
    context: Context,
    private val apiServiceSuperHeroes: ApiServiceSuperHeroes,


) : Repository {

    private val preferences: SharedPreferences =
        context.getSharedPreferences(SUPERHEROES_PREFERENCES, AppCompatActivity.MODE_PRIVATE)

    override suspend fun getSuperHeroes(): List<SuperHeroesResponse> {
        return apiServiceSuperHeroes.getSuperHeroes()
    }

    override fun saveSuperHeroes(superHeroes: List<SuperHeroesResponse>) {
        val savedJsonHeroes = subjctsToJson(superHeroes)
        preferences.edit()
            .putString(PREF_SUPERHEROES_VALUE, savedJsonHeroes)
            .apply()
        Log.d(
            "SaveSuperHeroes",
            "path:/data/data/com.ands.wb5weekweb/shared_prefs/SUPERHEROES_PREFERENCE.xml"
        )
    }

    private val gson = Gson()
    private fun subjctsToJson(subjcts: List<SuperHeroesResponse>?): String? {
        return if (subjcts == null || subjcts.isEmpty()) {
            null
        } else {
            gson.toJson(subjcts)
        }
    }

    private fun jsonToSubject(json: String?): List<SuperHeroesResponse> {
        return if (json.isNullOrEmpty()) {
            listOf()
        } else {
            val type = object : TypeToken<List<SuperHeroesResponse>>() {}.type
            gson.fromJson(json, type)
        }
    }

    override fun getSavedSuperHeroes(): List<SuperHeroesResponse> {
        val prefSavedHeroes = preferences.getString(PREF_SUPERHEROES_VALUE, "")
        Log.d("SavedSuperHeroes", "${jsonToSubject(prefSavedHeroes)}")
        return jsonToSubject(prefSavedHeroes)

    }

    override fun isCacheExist(): Boolean {
        val file = File(FILE_PATH)
        return file.exists() && getSavedSuperHeroes().isNotEmpty()
    }



    companion object {
        private const val SUPERHEROES_PREFERENCES = "SUPERHEROES_PREFERENCE"
        private const val PREF_SUPERHEROES_VALUE = "PREF_SUPERHEROES_VALUE"
        private const val FILE_PATH = "/data/data/com.ands.wb5weekweb/shared_prefs/${PREF_SUPERHEROES_VALUE}.xml"
    }
}
