package com.example.ex90_firebasechat;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.ex90_firebasechat.databinding.ActivityChattingBinding;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;


public class ChattingActivity extends AppCompatActivity {
    ActivityChattingBinding binding;
    FirebaseFirestore firestore;
    CollectionReference chatRef;
    ArrayList<MessageItem> messageItems = new ArrayList<>();
    MessageAdapter adapter;

    String chatRoomName = "chat1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChattingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 1_ 제목줄에 채팅방 이름이 표시됨.
        getSupportActionBar().setTitle(chatRoomName);
        getSupportActionBar().setSubtitle("상대방 이름");


        adapter = new MessageAdapter(this,messageItems);
        binding.recycler.setAdapter(adapter);

        // 2_ Firebase Firestore 관리객체 및 채팅방 이름의 참조객체 소환
        firestore = FirebaseFirestore.getInstance();
        chatRef = firestore.collection(chatRoomName);

        // 10_ chat1 컬렉션에 저장되어 있는 데이터 읽어오기
        // chatRef 의 데이터가 변경될 때마다 반응하는 리스너를 추가하자.
        chatRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                  // 11_ 근데 변경된 도큐먼트만 찾아달라고 요청하자. 아니면 다 스냅샷으로 찍어버리니..
                List<DocumentChange> changes = value.getDocumentChanges();
                for(DocumentChange documentChange : changes){
                    // 12_ 변경된 문서 내역중에서 데이터를 촬영한 스냅샷을 얻어오자
                    DocumentSnapshot snapshot = documentChange.getDocument();

                    // 13_ Document 에 있는 필드값 가져오기
                    Map<String,Object> msg = snapshot.getData();
                    String name = msg.get("name").toString();
                    String message = msg.get("message").toString();
                    String profileUrl = msg.get("profileUrl").toString();
                    String time = msg.get("time").toString();

                    // 14_ 읽어온 메시지를 리스트에 추가하자.
                    messageItems.add(new MessageItem(name,message,profileUrl,time));

                    // 15_ 어댑터에게 데이터가 추가되었다고 공지해주어야 리사이클러뷰에 잘 뿌려준다.
                    //adapter.notifyItemInserted(messageItems.size()-1);

                    // 리사이클러뷰의 스크롤 위치가 가장 아래로 이동해야한다.
                    //binding.recycler.scrollToPosition(messageItems.size()-1);
                }
                //Toast.makeText(ChattingActivity.this, messageItems.size()+"", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                //binding.recycler.scrollToPosition(adapter.getItemCount()-1);

            }
        });

        binding.btnSend.setOnClickListener(view -> clickSend());
    }


    void clickSend(){
        // 3_ firebase DB 에 저장할 데이터들
        String nickName = G.nickName;
        String message = binding.et.getText().toString();
        String profileUrl = G.profileUrl;

        // 4_ 메시지 작성 시간을 문자열 시:분 으로 표시
        Calendar calendar = Calendar.getInstance();
        String time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);

        // 5_ Field 에 넣을 값들을 MessageItem 객체로 만들어서 한방에 입력하자.

        MessageItem item = new MessageItem(nickName,message,profileUrl,time);

        // 7_ 채팅방이름으로 컬렉션에 채팅메시지들을 저장
        // 단, 시간순으로 정렬되도록 .. document 의 이름은 현재 시간(1970년부터 카운트된 ms)으로 지정하자.
        chatRef.document("MSG_" + System.currentTimeMillis()).set(item);
        // 8_ 다음 메시지 입력이 수월하도록 EditText 에 있는 글씨를 초기화하자.
        binding.et.setText("");

        // 9_ 소프트키보드 숨기기
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);



    }

}