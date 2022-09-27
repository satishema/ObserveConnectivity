package com.android.observeconnectivity

import kotlinx.coroutines.flow.Flow

/**
 * Created by Satish V on 27/09/22.
 * Company : HighOnSwift pvt Ltd
 * Email Id : iamsatishema@gmail.com
 */

interface ConnectivityObserver {

    fun observe(): Flow<Status>

    enum class Status{
        Available, Unavailable, Losing, Lost
    }
}