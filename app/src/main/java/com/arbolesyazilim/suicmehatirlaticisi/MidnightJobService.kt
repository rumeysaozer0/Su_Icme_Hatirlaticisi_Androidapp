package com.arbolesyazilim.suicmehatirlaticisi
import android.app.job.JobParameters
import android.app.job.JobService



class MidnightJobService : JobService() {
    private val userDataManager by lazy {
        UserDataManager(applicationContext)
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        userDataManager.resetWaterAmount()
        jobFinished(params, false)
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }
}

