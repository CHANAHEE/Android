package com.example.ex59_broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

// 8_ 안드로이드의 4대 컴포넌트 클래스들은 반드시 AndroidManifest.xml 에 등록해야만 한다. 등록하러가자~
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // 7_ 방송을 수신했을 때 자동으로 실행되는 콜백 메소드, 브로드캐스트는 운영체제의 능력이 없다. 그래서 파라미터로 전달받아야함.
        // 인텐트도 방송을 받을 때 오는 인텐트임.

        Toast.makeText(context, "received", Toast.LENGTH_SHORT).show();
    }
}
