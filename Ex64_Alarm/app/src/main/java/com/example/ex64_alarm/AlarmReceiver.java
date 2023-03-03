package com.example.ex64_alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "알람 받았음", Toast.LENGTH_SHORT).show();
        Log.i("Ex64","알람 받았음");

        // 4_ 보통은 이곳에서 액티비티를 실행했으나, 막혔음. 통상적으로 Notification 을 띄운다.
    }
}
