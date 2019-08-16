package com.lambdaschool.shoppinglist

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ShoppingRepository.createShoppingList()

        rv_item_list.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = ShoppingListAdapter(ShoppingRepository.shoppingList)
        }

        btn_send_list.setOnClickListener {
            createNotification(getList())
        }
    }

    fun getList(): String {
        var listString = ""
        for (item in ShoppingRepository.shoppingList) {
            if (item.isAdded) listString += "${item.product}"
        }

        return listString
    }

    fun createNotification(added: String) {
        val channelId = "${this.packageName}.simplechannel"
        val notificationManager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Shopping List Channel"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val description = "Channel to send shopping list notification"

            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description

            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setSmallIcon(android.R.drawable.ic_popup_reminder)
            .setContentTitle("Shopping List Notification")
            .setContentText(added)
            .setAutoCancel(true)
        notificationManager.notify(1, notificationBuilder.build())
    }
}
