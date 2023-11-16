package com.arbolesyazilim.suicmehatirlaticisi

import android.content.Context
import android.content.SharedPreferences


data class UserData(val weight: Float, val currentWater: Int)

class UserDataManager(private val context: Context) {
    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences("YOUR_PREF_NAME", Context.MODE_PRIVATE)
    }

    fun getStoredUserData(): UserData {
        return UserData(
            sharedPreferences.getFloat(KEY_WEIGHT, 0f),
            sharedPreferences.getInt(KEY_CURRENT_WATER, 0)
        )
    }

    fun saveWeight(weight: Float) {
        sharedPreferences.edit().putFloat(KEY_WEIGHT, weight).apply()
    }

    fun saveWaterAmount(amount: Int) {
        sharedPreferences.edit().putInt(KEY_CURRENT_WATER, amount).apply()
    }

    fun resetWaterAmount() {
        sharedPreferences.edit().putInt(KEY_CURRENT_WATER, 0).apply()
    }

    companion object {
        const val KEY_WEIGHT = "KEY_WEIGHT"
        const val KEY_CURRENT_WATER = "KEY_CURRENT_WATER"
    }
}
