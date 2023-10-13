package com.droidcon.fix.anrs.samples.components

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.droidcon.fix.anrs.R

@RequiresApi(Build.VERSION_CODES.O)
class AnrForegroundService : Service() {

    override fun onCreate() {
        super.onCreate()
        showNotification(this)
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    companion object {

        fun showNotification(context: Context) {
            val notificationChannel = NotificationChannel(
                "AnrForegroundServiceChannel",
                "ANR Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)

            NotificationCompat.Builder(context, "AnrForegroundServiceChannel")
                .setContentTitle("ANR Foreground Service")
                .setContentText("Creating an ANR using a foreground service")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build()
        }

        fun startAnrService(context: Context) {
            val intent = Intent(context, AnrForegroundService::class.java)
            context.startForegroundService(intent)
        }
    }
}
