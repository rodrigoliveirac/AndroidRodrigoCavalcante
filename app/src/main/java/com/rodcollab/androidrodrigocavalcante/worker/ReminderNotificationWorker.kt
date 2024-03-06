package com.rodcollab.androidrodrigocavalcante.worker

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
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


            val periodicRefreshRequest = PeriodicWorkRequestBuilder<ReminderNotificationWorker>(15,
                TimeUnit.MINUTES,15, TimeUnit.MINUTES)
                .setInitialDelay(15,TimeUnit.MINUTES)
                .addTag("TAG_REMINDER_WORKER").build()

            WorkManager.getInstance(appContext)
                .enqueueUniquePeriodicWork(
                    "reminder_notification_work",
                    ExistingPeriodicWorkPolicy.REPLACE,
                    periodicRefreshRequest
                )
        }
    }
}