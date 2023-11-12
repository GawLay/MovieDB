package com.example.data.repository

import com.example.data.remote.IApiHelper

open class ApiHelperImpl(
    private val apiHelper: IApiHelper
) : IApiHelper by apiHelper