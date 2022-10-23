package com.doxx.tvscredit.utils

import android.content.Context
import com.doxx.tvscredit.utils.Constants.EMPLOYEE_TOKEN
import com.doxx.tvscredit.utils.Constants.PREFS_TOKEN_FILE
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {
    private var prefs = context.getSharedPreferences(PREFS_TOKEN_FILE,Context.MODE_PRIVATE)
    fun saveToken(token:String){
        val editor = prefs.edit().apply{
            putString(EMPLOYEE_TOKEN,token)
            apply()
        }

    }
    fun getToken():String?{
        return prefs.getString(EMPLOYEE_TOKEN,null)
    }

}