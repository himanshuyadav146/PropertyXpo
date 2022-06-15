package com.example.propertyxpo.common

import android.content.SharedPreferences

class AppSharedPreferencesManager(val sharedPreferences: SharedPreferences) {

    private val editor : SharedPreferences.Editor by lazy {
        sharedPreferences.edit()
    }


    /**
     * saving the value to shared preference
     */
    fun putValueToSharedPref(key: String, value: Any) {
        when (value::class) {
            String::class -> {
                editor.putString(key, value as String)
            }
            Int::class -> {
                editor.putInt(key, value as Int)
            }
            Boolean::class -> {
                editor.putBoolean(key, value as Boolean)
            }
            Float::class -> {
                editor.putFloat(key, value as Float)
            }
        }
        editor.apply()
    }

    /**
     * fetch the value from shared preference
     */
    //=========SAMPLE:TO BE REMOVED=============
    /*val sampleInt: Int = SharedPrefManagerV2(sharedPrefModule,context)
        .getValueFromSharedPref(SharedPrefConstants.PREFERENCE_CART_ID,1)

    val sampleString: String = SharedPrefManagerV2(sharedPrefModule, context)
        .getValueFromSharedPref(SharedPrefConstants.PREF_COUPONS,"Offers")

    val sampleBoolean:Boolean = SharedPrefManagerV2(sharedPrefModule, context)
        .getValueFromSharedPref(SharedPrefConstants.IS_SEND_NOTIFICATIONS, false)

    val sampleFloat = SharedPrefManagerV2(sharedPrefModule, context)
        .getValueFromSharedPref(SharedPrefConstants.RETENTION__USER_ORDER_COUNT, 1f)*/
    //===========================
    inline fun <reified T> getValueFromSharedPref(key: String, defValue: T): T {
        return when (T::class) {
            Int::class -> {
                val result: Int =
                    sharedPreferences.getInt(key, defValue as Int)
                return result as T
            }
            String::class -> {
                val result: String? = sharedPreferences
                    .getString(key, defValue as String)
                return result as T
            }
            Boolean::class -> {
                val result: Boolean = sharedPreferences
                    .getBoolean(key, defValue as Boolean)
                return result as T
            }
            Float::class -> {
                val result: Float = sharedPreferences
                    .getFloat(key, defValue as Float)
                return result as T
            }
            else -> {
                null as T
            }
        }
    }

    /**
     * clearing the shared preferences on logout
     */
    fun removePreferencesForLogout() {
        editor.remove(StringConstants.LOGIN_TOKEN)
        editor.commit()
    }

    /**
     * removing all data saved in shared preferences
     */
    fun clearAllDataOnUpdate() {
        editor.clear().commit()
    }
}