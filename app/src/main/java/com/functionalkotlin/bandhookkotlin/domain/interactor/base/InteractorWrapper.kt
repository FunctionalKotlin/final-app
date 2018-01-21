// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.domain.interactor.base

import com.birbit.android.jobqueue.Job
import com.birbit.android.jobqueue.Params
import com.birbit.android.jobqueue.RetryConstraint

class InteractorWrapper(val interactor: Interactor, priority: InteractorPriority, val bus: Bus) :
    Job(Params(priority.value).requireNetwork()) {

    override fun onRun() {
        val event = interactor.invoke()
        bus.post(event)
    }

    override fun onAdded() = Unit
    override fun onCancel(p0: Int, p1: Throwable?) = Unit
    override fun shouldReRunOnThrowable(p0: Throwable, p1: Int, p2: Int): RetryConstraint =
        RetryConstraint.CANCEL
}
