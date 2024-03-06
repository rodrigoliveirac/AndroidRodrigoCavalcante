package com.rodcollab.androidrodrigocavalcante.worker

import android.content.Context
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.rodcollab.androidrodrigocavalcante.notifications.NotificationHandler
import java.util.concurrent.TimeUnit

class ReminderNotificationWorker (private val appContext: Context, workerParams: WorkerParameters,) : Worker(appContext, workerParams) {
    override fun doWork(): Result {
        NotificationHandler.createReminderNotification(appContext)
        return Result.success()
    }

    companion object {

        fun schedule(appContext: Context) {

            val periodicRefreshRequest = PeriodicWorkRequest.Builder(
                ReminderNotificationWorker::class.java,
                15,
                TimeUnit.MINUTES,
                15,
                TimeUnit.MINUTES
            ).build()

            WorkManager.getInstance(appContext).enqueue(periodicRefreshRequest)
        }
    }
}