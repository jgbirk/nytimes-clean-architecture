package com.nytimes.presentation.view

import com.squareup.rx2.idler.Rx2Idler
import io.reactivex.plugins.RxJavaPlugins
import android.support.test.runner.AndroidJUnitRunner
import io.reactivex.android.plugins.RxAndroidPlugins

open class EspressoTestRunner : AndroidJUnitRunner() {

    override fun onStart() {
        RxJavaPlugins.setInitComputationSchedulerHandler(
            Rx2Idler.create("ComputationScheduler")
        )

        RxJavaPlugins.setInitIoSchedulerHandler(
            Rx2Idler.create("IoScheduler")
        )

        RxJavaPlugins.setInitNewThreadSchedulerHandler(
            Rx2Idler.create("NewThreadScheduler")
        )

        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
            Rx2Idler.create("ComputationScheduler")
        )

        super.onStart()
    }
}