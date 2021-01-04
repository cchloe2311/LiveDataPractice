package com.example.livedatapractice.util

import android.content.Context

class ResourceProvider(private var mContext: Context) {
    fun getStringResource(resourdeId: Int) = mContext.resources.getString(resourdeId)
}