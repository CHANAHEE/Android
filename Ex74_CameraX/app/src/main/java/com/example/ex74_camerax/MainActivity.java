package com.example.ex74_camerax;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentValues;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.common.util.concurrent.ListenableFuture;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;


// 0_ 개발자 사이트에 올라와있는 cameraX 튜토리얼을 그대로 따라하면 됨.

public class MainActivity extends AppCompatActivity {

    PreviewView previewView;
    CircleImageView civ;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        previewView = findViewById(R.id.preview_view);
        civ = findViewById(R.id.civ);
        tv = findViewById(R.id.tv);
        // 1_ Gradle 에 추가도 해줬고, 프리뷰뷰도 만들었다. 이제 퍼미션을 설정해보자. 메니페스트파일로~

        // 30_ 버튼 만들었으니, 클릭이벤트를 처리해주자.
        findViewById(R.id.fab).setOnClickListener(view -> clickBtn());

        // 28_ 테마에서 액션바를 없애줬고, 이제 상태바도 없애주자.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);







        // 8_ 동적 퍼미션 처리
        ArrayList<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.CAMERA);
        permissions.add(Manifest.permission.RECORD_AUDIO);
        if(Build.VERSION.SDK_INT <= 28) permissions.add(Manifest.permission.MANAGE_EXTERNAL_STORAGE);

        int checkResult = checkSelfPermission(permissions.get(0)); // 9_ CAMERA 의 동적 퍼미션만 확인해보자. 원래는 다 해주어야 함. 테스트니까 일단 하나만..( 여러개한다고 가정해보자 )
        int checkResult2 = checkSelfPermission(permissions.get(1));

        if(checkResult == PackageManager.PERMISSION_DENIED || checkResult2 == PackageManager.PERMISSION_DENIED){
            // 10_ 퍼미션이 허용되지 않았다면.. 퍼미션을 요청하는 대행사 객체를 이용하자.
            // 13_ 대행사 객체를 만들었다면 런칭하자.
            String[] arr = new String[permissions.size()];
            permissions.toArray(arr);
            resultLauncher.launch(arr);
            // 17_ 퍼미션 관련설정은 끝났음.
        }
    }

    // 11_ 퍼미션들을 요청하고 결과를 받아주는 계약을 체결하는 대행사 등록 , 단 계약객체로 RequestMultiplePermissions 를 사용해주어야 함.
    // 12_ 요청 퍼미션 배열이 필요함. 리스트로 제공하면 안댐.
    ActivityResultLauncher<String[]> resultLauncher = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
        @Override
        public void onActivityResult(Map<String, Boolean> result) {
            // 14_ 대행사가 요청값을 받아와서 할일을 정의해주자. 받아온 퍼미션은 result 에 맵방식으로 돌아옴. String 은 퍼미션의 이름, boolean 으로 허용결과를 나타냄.
            // 15_ Map 방식은 foeeach 문이 안되니까 우선 키값만 뺴오자.
            Set<String> keys = result.keySet(); // 16_ 중복없이 뺴오자
            for(String key : keys){
                boolean value = result.get(key);
                if(value) Toast.makeText(MainActivity.this, key + " 퍼미션이 허용되었음", Toast.LENGTH_SHORT).show();
                else Toast.makeText(MainActivity.this, key + " 퍼미션이 거부되었음", Toast.LENGTH_SHORT).show();
            }
        }
    });

    // 18_ 프리뷰기능을 시작하는 작업 메소드를 만들어주자.
    void startCamera(){
        // 19_ ListenableFuture 를 이용하여 ProcessCameraProvider 을 얻어오자. 근데 얻어올때는 비동기적인 방식으로 얻어와야 한다.
        // 20_ addListener 를 이용하여 ProcessCameraProvider 을 얻어와야하며, 파라미터로 Runnable 과 메인스레드의 실행자가 필요하다.
        ListenableFuture<ProcessCameraProvider> listenableFuture = ProcessCameraProvider.getInstance(this);
        listenableFuture.addListener(new Runnable() {
            @Override
            public void run() {
                // 21_ 여기에서 ProcessCameraProvider 를 만들어주자.
                try {
                    ProcessCameraProvider cameraProvider = listenableFuture.get();

                    // 23_ 첫번째 파라미터로를 어떤 액티비티의 생명주기를 따라서 카메라를 제어할것인지, 액티비티 생명주기를 가지고있는 액티비티를 넘겨주자.
                    // 24_ 카메라 셀렉터를 준비하자. 카메라 종류는 하나가아니라 후면도 있고 전면도 있다..
                    CameraSelector cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA;

                    // 25_ Preview 작업을 하는 객체
                    Preview.Builder builder = new Preview.Builder();
                    Preview preview = builder.build();

                    // 26_ SurfaceView 의 개념을 따로 알아보자! - 고속버퍼뷰임.
                    // 화면에 바로 이미지를 뿌려주면, 바이트로 된 이미지값을 하나하나 뿌리니까 오래걸림.
                    // 그래서 메모리 상에 먼저 그림을 그려주고 한번에 뿌리는게 서피스뷰의 원리. 이중버퍼뷰라고도 함.

                    // 27_ 다행스럽게도 PreviewView 가 서피스뷰를 가지고 있음. 그래서 서피스프로바이더 파라미터에 프리뷰뷰가 가진 서피스뷰를 전달해주자.
                    preview.setSurfaceProvider(previewView.getSurfaceProvider());

                    // 32_ 이미지 캡처 객체를 여기서 생성해주어야 함.
                    imageCapture = new ImageCapture.Builder().build();
                    cameraProvider.bindToLifecycle(MainActivity.this,cameraSelector,preview,imageCapture);// 22_ 여기서 액티비티에 생명주기에 따라 카메라 동작을 제어해준다.

                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, ContextCompat.getMainExecutor(this));
    }

    // 19_ startCamera 를 실행해주기 위해 액티비티가 화면에 완전히 보여질때 자동으로 발동하는 콜백메소드에 메소드를 실행시켜주자.

    @Override
    protected void onResume() {
        super.onResume();
        startCamera();
    }

    ImageCapture imageCapture = null;
    void clickBtn(){

        // 31_ 여기서 이미지 캡처 로직을 짜주면 된다.
        if(imageCapture == null) return;

        // 33_ 저장될 파일명 : 날짜와 시간을 이용하자.
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
        String fileName = sdf.format(System.currentTimeMillis());

        // 34_ CameraX 의 미디어 DB 에 저장할 한줄(record) 객체를 만들자.
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME,fileName);
        values.put(MediaStore.MediaColumns.MIME_TYPE,"image/jpeg");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) values.put(MediaStore.MediaColumns.RELATIVE_PATH,"Pictures/CameraX-Image");

        // 35_ 이미지 캡처에게 저장 옵션으로 설정하기 위해 옵션객체 생성
        ImageCapture.OutputFileOptions options = new ImageCapture.OutputFileOptions.Builder(getContentResolver(),MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values).build();

        // 36_ 이제 이미지 캡쳐에게 사진 촬영을 하도록 요청하자.
        imageCapture.takePicture(options, ContextCompat.getMainExecutor(this), new ImageCapture.OnImageSavedCallback() {
            @Override
            public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                Toast.makeText(MainActivity.this, "촬영 성공", Toast.LENGTH_SHORT).show();
                tv.setText(outputFileResults.getSavedUri().toString());
                Glide.with(MainActivity.this).load(outputFileResults.getSavedUri()).into(civ);
            }

            @Override
            public void onError(@NonNull ImageCaptureException exception) {
                Toast.makeText(MainActivity.this, "Error: " + exception, Toast.LENGTH_SHORT).show();
            }
        });
    }
}