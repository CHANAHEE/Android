package com.example.ex60_broadcastreceiver_booting;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationChannelCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class MyBootingReceiver extends BroadcastReceiver {
    Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        // 1_ Android 13 버전부터 액티비티가 없으면 토스트의 발동을 제한함.. 원래 토스트 띄워서 예제 확인해보려 했는데 안될것같고 로그를 통해 확인해보자
        // 2_ 우리는 이제 부팅을 하면 이 메소드가 발동되게끔 해보려고 한다.
        // 3_ 그리고 4대 컴포넌트를 만들었으면 항상 메니페스트에 등록해주어야 한다.
        Log.i("Ex60", "booting receive");

        // 9_ Android N 버전(API 25) 부터 부팅완료를 들으려면 앱을 설치한 후 적어도 1회 사용자가 직접 런처화면(앱목록 아이콘) 에서
        // 아이콘을 클릭하여 실행한 이력이 있는 앱만 부팅완료를 들을 수 있음.

        // 10_ 부팅완료가 되면 MainActivity 화면이 실행되도록 해보자.
        String action = intent.getAction();
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {

            // 12_ 안드로이드 10 버전 (API 29) 부터 애석하게 리시버에서 직접 액티비티 실행하는것을 금지했다.
            // 대신 알림(Notification) 을 통해 사용자에게 신호를 주고 액티비티를 실행할지의 여부를 선택하도록 변경되었다.

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {

                NotificationManagerCompat manager = NotificationManagerCompat.from(context);
                // 14_ 채널 만들자
                NotificationChannelCompat channelCompat = new NotificationChannelCompat.Builder("ch01", NotificationManager.IMPORTANCE_HIGH).setName("Ex60").build();
                // 15_ 알림 매니저에게 전달하자
                manager.createNotificationChannel(channelCompat);
                // 13_ 알림객체를 만들어 주는 건축가 객체 생성
                NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "ch01");

                builder.setSmallIcon(R.mipmap.ic_launcher_round);
                builder.setContentTitle("부팅완료");
                builder.setContentText("부팅이 완료되었습니다");

                // 16_ 안드로이드 13 부터 알림에 대한 동적 퍼미션을 해주어야 함.
                if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    launcher.launch(Manifest.permission.POST_NOTIFICATIONS);
                }
                manager.notify(100, builder.build());
            }else {
                Intent i = new Intent(context, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }


    //            Intent i = new Intent(context, MainActivity.class);
    //            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 11_ 이 앱에서 액티비티가 처음 실행되는거라면, 백스택이 생성되지 못했으므로 실행이 안된다. 그래서 왼쪽과 같은 설정이 필요하다.
    //            context.startActivity(i);
        }
    }

    ActivityResultLauncher<String> launcher = ((MainActivity)context).registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback<Boolean>() {
        @Override
        public void onActivityResult(Boolean result) {
            if(result) Toast.makeText(((MainActivity)context), "알림 허용", Toast.LENGTH_SHORT).show();
            else Toast.makeText(((MainActivity)context), "알림 허용하지 않음", Toast.LENGTH_SHORT).show();
        }
    });
}
