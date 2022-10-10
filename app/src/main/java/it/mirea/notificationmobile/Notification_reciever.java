package it.mirea.notificationmobile;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.time.OffsetDateTime;

public class Notification_reciever extends BroadcastReceiver {


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("Pog", "AlarmRecived");

        DatabaseHelper mDatabaseHelper = new DatabaseHelper(context);
        Cursor data = mDatabaseHelper.getName();
        String name = data.getString(1);
//        double total = 0;
//        OffsetDateTime offset = OffsetDateTime.now();
//        while (data.moveToNext()) {
//            if (data.getInt(4) == offset.getDayOfMonth()) {
//                total += data.getFloat(3);
//            }
//        }

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);


        Notification notification = new NotificationCompat.Builder(context, MainActivity.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_baseline_accessible_24)
                .setContentText("Твой друг " + name + " сегодня празднует день рождения!")
                .setContentTitle("День рождения")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_REMINDER)
                .build();


        notificationManager.notify(1, notification);

    }
}
