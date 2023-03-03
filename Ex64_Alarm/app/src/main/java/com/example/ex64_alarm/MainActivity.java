package com.example.ex64_alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(view -> clickBtn1());
        findViewById(R.id.btn2).setOnClickListener(view -> clickBtn2());
        findViewById(R.id.btn3).setOnClickListener(view -> clickBtn3());
    }

    void clickBtn1(){
        // 1_ 이미 깔려있는 알람시계 시스템 앱을 통해 알람을 설정하자. [퍼미션 필요]
        Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
        intent.putExtra(AlarmClock.EXTRA_HOUR,14);
        intent.putExtra(AlarmClock.EXTRA_MINUTES, 30);
        intent.putExtra(AlarmClock.EXTRA_MESSAGE,"TEST ALARM");
        intent.putExtra(AlarmClock.EXTRA_DAYS,new int[] {Calendar.MONDAY,Calendar.WEDNESDAY});
        startActivity(intent);
    }

    void clickBtn2(){
        // 2_ 알람 매니저를 이용하여 직접 알람을 설정해보기
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        // 3_ 알람 시간이 되었을 때 실행될 컴포넌트[Activity, BroadcastReceiver, Service] 를 지정하자.
        Intent intent = new Intent(this,AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 10, intent, PendingIntent.FLAG_IMMUTABLE);
        manager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + 10000,pendingIntent);
    }

    int year, month, day;

    int hour,minute;

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            // 파라미터 i, i1 : hour, minute
            hour = i;
            minute = i1;

            // 정해진 날짜로 캘린더 객체를 다시 만들자.
            Calendar calendar = Calendar.getInstance();
            calendar.set(year,month,day,hour,minute);

            // 정한 시간으로 알람을 설정하자.
            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,10,intent,PendingIntent.FLAG_IMMUTABLE);
            alarmManager.setAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        }
    };
    void clickBtn3(){
        DatePickerDialog dialog = new DatePickerDialog(this);
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                // 파라미터 i, i1, i2  : 각각 year, month, day 를 의미한다.
                year = i;
                month = i1;
                day = i2;

                // 이번에는 시간 선택기 보이자.
                // 현재 시간을 기준 시간으로 설정하자.
                Calendar calendar = Calendar.getInstance();
                hour = calendar.get(Calendar.HOUR_OF_DAY);
                minute = calendar.get(Calendar.MINUTE);
                new TimePickerDialog(MainActivity.this,timeSetListener,hour,minute,true).show();
            }
        });
        dialog.show();
    }

}