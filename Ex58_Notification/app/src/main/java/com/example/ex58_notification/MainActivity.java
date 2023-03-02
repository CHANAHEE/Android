package com.example.ex58_notification;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());
    }

    void clickBtn(){
        // 1_ 안드로이드의 13버전(API 33) 부터 알림에 대한 퍼미션이 추가됨. -> 메니페스트 파일에 퍼미션 추가하러 가자~

        // 6_ 이 앱이 알림에 대한 퍼미션을 허용한 상태인지 체크해야한다. 이게 동적퍼미션의 시작.
        // 7_ checkSelfPermission() 요 메서드는 26 버전 이후에 나온 기능이다. 그래서 하위 버전들에서는 동자갛지 않는다.
        // 8_ 만일 24버전까지 호환하게 만들고 싶다면 다음을 보자.
        int checkResult = ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS);
        // 9_ Compat 은 호환용으로 만들어진 클래스. checkSelfPermission 를 이용해 퍼미션 허용을 확인하자.
        // 10_ 첫번째 파라미터로는 Context 객체를, 두번째 파라미터는 확인하고자 하는 퍼미션을 전달하자.
        // 11_ 메니페스트 파일의 permission 참조변수 안에는 퍼미션들이 들어있음. 활용하자.

        // 12_ 그리고 위 메소드는 int 형으로 반환한다. 0 이면 허가받은것, 아니면 안받은것.
        // 13_ 숫자만 써놓으면 뭔지 잘 모르니까, 패키지 매니저 클래스 안에 0과 -1 숫자가 들어있음. 이걸 사용하면 보기 편하다.
        if(checkResult == PackageManager.PERMISSION_DENIED){
            // 14_ 알림이 허용되지 않았다면, 알림 허용을 요청하는 다이얼로그를 보이자.
            // 15_ requestPermission(); -> 이건 예전방식이다. 물론 해도 상관은 없다. 인터넷에 찾아보면 수두리뺵뺵
            // 16_ 퍼미션 요청결과를 받아주는 대행사 객체를 이용하자. 이 대행사 참조변수는 무조건 멤버변수로 선언하자. 라이프사이클상 그렇다고 함.
            // 21_ 대행사는 만들었으니, 이제 대행사를 사용해서 퍼미션의 요청결과를 받아오자.
            permissionResultLauncher.launch(Manifest.permission.POST_NOTIFICATIONS);
            return; // 22_ 리턴을 해줘야 이 뒤에 적는 코드에 에러가 안난다.
        }

        // 23_ 알림을 관리하는 관리자 객체 소환하자.
        // 24_ getSystemService() 은 스머프들을 불러오는 메소드. getResource() 등은 너무 많이 써서 따로 부르는 메소드가 있는것. 원래는 getSystemService()을 써야함.
        // 25_ Context 내부의 스머프들의 식별자를 담아놓은 클래스가 있다 : Context
        // 26_ getSystemService() 의 반환타입은 Object , 그래서 형변환이 필요하다.
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        // 29_ 건축가에게 알림객체 생성을 요청해야 한다. 다이얼로그랑 비슷. Compat 으로 호환성까지 생각하자.
        NotificationCompat.Builder builder = null;

        // 30_ 빌더를 생성하는 문법이 26버전(오레오 버전) 부터 변경되었는데, 알림채널 이라는 개념이 도입되었음. 그래서 26버전 이상의 폰에서는 알림 채널 객체를 생성해야 하고, 그 이전버전에서는 알림채널 객체 생성없이 빌더를 만들어야 함.
        // 31_ 그래서 우리는 이 코드가 실행될 핸드폰의 버전환경 별로 나눠 코드를 작성해주어야 한다.
        // 32_ 아래 if 문의 조건은 26버전 이상이라면 if 실행문으로, 그 이하라면 else 실행문을 실행시킨다.
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            // 34_ 알림 채널 객체를 생성하자.
            // 36_ NotificationChannel() 생성자 파라미터, 첫번째는 id, 두번째는 채널 이름, 세번째는 알림의 중요도. 중요도에 따라 화면을 가리는지, 소리는 나는지 등등의 설정이 가능하다.
            NotificationChannel channel = new NotificationChannel("ch01","Ex58 channel",NotificationManager.IMPORTANCE_HIGH);


            // 57_ 알림 사운드, 진동 등 역시 채널이 있을 때와 없을때를 구분해서 작성해주어야 한다.
            // 58_ setSound() 의 파라미터로 Uri 객체를 전달해주어야 하므로, 객체 먼저 만들자.
            //Uri uri = RingtoneManager.getActualDefaultRingtoneUri(this,RingtoneManager.TYPE_NOTIFICATION);// 59_ 이게 우리가 들었던 기본 알람소리임.
            // 60_ 이제 우리가 가져온 사운드를 만들어보자.
            Uri uri = Uri.parse("android.resource://"+ getPackageName() + "/" +R.raw.s_sijak); // 61_ android.resource: 은 정해진 글자임. 그리고 그 다음 경로에는 패키지명이 와야함.(getPackageName()) 그리고 R 장부번호를 작성해주어야 함.
            channel.setSound(uri,new AudioAttributes.Builder().build());

            // 64_ 진동 만들기 , 근데 사용자의 정적 퍼미션이 요구된다!! 메니페스트에 추가해주자.
            channel.setVibrationPattern(new long[]{0,2000,1000});

            // 35_ 매니저 객체에게 채널을 만들어달라고 해야함. 채널 객체를 파라미터로 전달해야 하므로, 만들자.
            // 63_ 매니저에게 채널 등록을 하기 이전에 사운드 작업을 해줘야함.
            notificationManager.createNotificationChannel(channel);


            // 37_ 빌더를 이용해 만들자.
            builder = new NotificationCompat.Builder(this,"ch01");
        }else {
            builder = new NotificationCompat.Builder(this,""); // 33_ channel 이 없어서 channelId 도 없다. 그러니 빈 문자열 넣어두자.
            // 62_ 26 이전 버전도 사운드 개념은 똑같다
            Uri uri = Uri.parse("android.resource://"+ getPackageName() + "/" +R.raw.s_sijak);
            builder.setSound(uri);
        }

        // 38_ 이제 다 만들었으니, 빌더에게 알림에 관련된 설정들을 해보자.
        // 39_ 상태 표시줄에 보이는 아이콘을 만들자.
        builder.setSmallIcon(R.drawable.ic_noti);

        // 40_ 상태바를 아래로 드래그하면 나오는 창이 알림창(확장 상태바)이다. 그거에 대한 설정을 해보자.
        builder.setContentTitle("우산알림");
        builder.setContentText("우산 들고가세요~");

        // 41_ 알림창 오른쪽에 큰 이미지 넣기
        Resources res = getResources();
        Bitmap bm = BitmapFactory.decodeResource(res,R.drawable.one_chopa); // 벡터 이미지는 안되고 레스터 이미지만 가능한듯.
        builder.setLargeIcon(bm);

        // 42_ 알림창을 클릭했을 때, 새로운 화면(Activity) 가 실행되도록...
        // 43_ 그러니 두번째 액티비티를 먼저 만들자.
        // 45_ 만들었으면 인텐트 객체를 만들자.
        Intent intent = new Intent(this, SecondActivity.class);

        // 46_ 근데 그냥 인텐트 객체를 보내려니 에러 -> 파라미터로는 PendingIntent 객체를 전달해야함.
        // 47_ 인텐트 객체에게 바로 실행하지말고 잠시 보류해달라고 요청해야함. 왜? 지금 바로 실행할게 아니라, 알림창을 눌렀을 때 실행되어야 하므로.
        // 48_ 그래서 보류중인 인텐트(PendingIntent) 객체로 생성해야한다.
        // 49_ 두번째 파라미터는 각 알림을 식별할 수 있는 코드를 적어줘야 한다.
        PendingIntent pendingIntent = PendingIntent.getActivity(this,10,intent,PendingIntent.FLAG_IMMUTABLE); // 50_ 마지막 플래그는 알림이 중복으로 여러개 떴을때 어떤 방식으로 처리할것인가를 정하는 파라미터. 그냥 IMMUTABLE 로 써야한다.
        builder.setContentIntent(pendingIntent);

        // 51_ 알림창을 클릭하여 화면이 변경되면, 상태표시줄의 작은 아이콘이미지가 없어지도록 해보자.
        // 52_ 이 기능은 인텐트가 있을 때만 사용할 수 있는 기능이다.
        builder.setAutoCancel(true);

        // 53_ 요즘들어 자주 보이는 알림창 스타일 꾸미기가 있다.
        NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle();
        style.bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.bg_one09));
        builder.setStyle(style);

        // 54_ 다른 스타일의 종류도 엄청 많다. 개발자 사이트를 통해 알아보도록 하자.

        // 55_ 알림창의 클릭 액션에 의해 실행될 화면이 여러개 일 때 사용하는 기능 뭐 세팅을 누르면 세팅창으로 갈 수도있고,
        // 그냥 창을 누르면 그냥 다른 창으로 넘어갈 수 있게끔 하자.
        builder.addAction(R.drawable.bg_one09, "setting",pendingIntent); // 56_ 여기에는 원래 다른 PendingIntent 가 와야한다.예제니까 그냥 아까 만든걸로 사용







        // 28_ notify() 의 두번째 파라미터로 Notification 객체가 필요하니까 만들자. 근데 이걸 만들기 위해서는 빌더가 필요하다.
        Notification notification = builder.build();
        // 27_ 알림 매니저에게 알림을 보이도록 요청
        notificationManager.notify(100,notification);
    }
    // 17_ 퍼미션 요청 결과를 받아오는 작업을 대행해주는 객체 생성 및 등록을 해보자. 액티비티 화면전환때 사용하던 방식 그대로임.
    // 18_ 단 인텐트를 사용할 때는 제네릭타입으로 intent 를, 우리는 퍼미션 글자 덩어리를 사용하므로 String 을 주자.
    // 19_ registerForActivityResult 을 이용하면 ActivityResultLauncher의 객체를 반환하며, 첫번째 생성자 파라미터로 어떤 계약을 할지 전달해주어야 한다.
    // 20_ 인텐트 때는 startActivityForResult 를 계약했지만, 이번에는 RequestPermission 를 계약하자.
    ActivityResultLauncher<String> permissionResultLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(),result -> {
       if(result) Toast.makeText(MainActivity.this, "알림 허용", Toast.LENGTH_SHORT).show();
       else Toast.makeText(MainActivity.this, "알림 허용하지 않음", Toast.LENGTH_SHORT).show();
    });
}