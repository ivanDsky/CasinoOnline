package ua.zloydi.casinoonline

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_ONE_SHOT
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ua.zloyhr.casinoonline.R
import kotlin.random.Random


class FirebaseService : FirebaseMessagingService() {
    private val CHANNEL_ID = "Firebase notification"
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.d("Token","New token: $p0")
    }


    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        Log.d("Message","From: ${message.from}")

        val intent = Intent(this,MainActivity::class.java)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = Random.nextInt()

        val pendingIntent = PendingIntent.getActivity(this,0,intent, FLAG_ONE_SHOT)

        val title = if(message.notification == null) "Title" else message.notification?.title
        val body = if(message.notification == null) "Body" else message.notification?.body

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.ic_fire)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationId,notification)
    }
}