package com.si.Utils

import android.content.Context
import android.content.res.Resources

fun getStringByName(context: Context, stringName: String): String {
    val res: Resources = context.resources
    return res.getString(res.getIdentifier(stringName, "string", context.packageName))
}

