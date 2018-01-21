// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain

import android.os.Handler
import android.os.Looper
import com.functionalkotlin.bandhookkotlin.domain.interactor.base.Bus
import de.greenrobot.event.EventBus

class BusImpl : EventBus(), Bus {

    val mainThread = Handler(Looper.getMainLooper())

    override fun post(event: Any) {
        mainThread.post({ super.post(event) })
    }
}
