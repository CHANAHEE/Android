package com.example.ex53_xmlpullparser_movie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    // 4_ 영화정보를 가진 MovieItem 을 여러개 관리하는 List 객체를 생성하자
    ArrayList<MovieItem> movieItems = new ArrayList<>();
    RecyclerView recyclerView;
    MovieAdapter adapter;
    String key = "f5eef3421c602c6cb7ea224104795888";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 5_ 원래는 서버에서 xml 을 파싱해와야 하지만, 리사이클러뷰가 제대로 작동하는지 알아보기 위해 가짜정보를 넣어보자.
        // 6_ 어댑터 만들자
//        movieItems.add(new MovieItem("1","1","2023-02-15","1234567"));
//        movieItems.add(new MovieItem("2","1","2023-02-15","1234567"));
//        movieItems.add(new MovieItem("3","1","2023-02-15","1234567"));
//        movieItems.add(new MovieItem("4","1","2023-02-15","1234567"));
//        movieItems.add(new MovieItem("5","1","2023-02-15","1234567"));
//        movieItems.add(new MovieItem("6","1","2023-02-15","1234567"));
//        movieItems.add(new MovieItem("7","1","2023-02-15","1234567"));
//        movieItems.add(new MovieItem("8","1","2023-02-15","1234567"));
//        movieItems.add(new MovieItem("9","1","2023-02-15","1234567"));
//        movieItems.add(new MovieItem("10","1","2023-02-15","1234567"));

        // 7_ 어댑터 붙이자
        recyclerView = findViewById(R.id.recycler);
        adapter = new MovieAdapter(this,movieItems);
        recyclerView.setAdapter(adapter);

        // 8_ 확인해보니 리사이클러뷰는 잘 동작한다!! 그럼 이제 서버로 부터 xml 을 파싱해오자
        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());

    }

    public void clickBtn(){

        // 9_ 영화진흥위원회 open api 정보(일일 박스오피스)를 가져와서 리사이클러뷰에 보여주기
        // xml 파일 포맷의 데이터를 읽어와서 분석하자. 그리고 이건 네트워크 작업이다. 그리고 네트워크 작업은 권한이 필요하며 별도의 스레드가 필요하다.
        new Thread(){
            @Override
            public void run() {

                // 10_ 네트워크 작업 비동기로 시작
                // 11_ xml 문서의 REST 방식의 url 주소를 넣자

                // 26_ 날짜를 어제 날짜로 자동 갱신되게 해보자.
                //String yesterday = new Date().toString(); // 27_ Date 객체가 생성되는 순간의 날짜와 시간을 가지고 있음.
                // 28_ 특정 포맷으로 날짜를 문자열로 만들어주는 객체가 있다.
                Date date = new Date();
                date.setTime(date.getTime() - (1000*60*60*24)); // 29_ 현재의 하루전 시간이 된다.
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String yesterday = sdf.format(date);
                String address = "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml"
                        + "?key=" + key
                        + "&targetDt=" + yesterday
                        + "&itemPerPage=5";

                // 12_ 위 서버 url 위치까지 무지개로드를 열어주는 해임달 객체를 만들자.
                try {
                    URL url = new URL(address);
                    // 13_ 해임달을 만들었으니 무지개로드를 열자!
                    InputStream is = url.openStream();
                    InputStreamReader isr = new InputStreamReader(is); // 14_ 바이트 스트림 -> 문자열 스트림

                    // 15_ xml 문서를 조금 더 쉽게 분석(Parse) 해주는 객체를 생성하자
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser(); // 16_ 공장한테 XmlPullParser 를 만들어달라고 요청하자
                    xpp.setInput(isr); // 17_ 분석가에게, 스트림을 통해 받아온 리더를 준다.. 그럼 xml 파일을 읽을 준비는 끝.

                    // 18_ Xml Parser 에게 분석작업을 요청하면 된다.
                    int eventType = xpp.getEventType(); // 19_ XmlPullParser 는 시작위치가 이미 START_DOCUMENT 임. 그래서 처음에 next() 한번을 안해줘도됨.



                    // 22_ 영화 1개 정보의 참조변수를 만들자.
                    MovieItem movieItem = null;

                    while(eventType != XmlPullParser.END_DOCUMENT){



                        switch (eventType){
                            case XmlPullParser.START_DOCUMENT:
                                // 20_ 별도의 스레드는 UI 작업이 불가능하다. 그래서 UI 스레드에서 작업하도록 요청하자.
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "파싱 작업이 시작되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                                break;
                            case XmlPullParser.START_TAG:
                                // 21_ if 문을 이용해 태그문을 나눠주자.
                                String tagName = xpp.getName();

                                // 23_ 그리고 dailyBoxOffice 를 만나면, 빈 movieItem 을 하나 만들어주고, 각 아이템을 만날때, 멤버변수에 값을 하나씩 넣어주자.
                                if(tagName.equals("dailyBoxOffice")){
                                    movieItem = new MovieItem();
                                }
                                else if(tagName.equals("rank")){
                                    xpp.next();
                                    movieItem.rank = xpp.getText();
                                }
                                else if(tagName.equals("movieNm")){
                                    xpp.next();
                                    movieItem.movieNm = xpp.getText();
                                }
                                else if(tagName.equals("openDt")){
                                    xpp.next();
                                    movieItem.openDt = xpp.getText();
                                }
                                else if(tagName.equals("audiAcc")){
                                    xpp.next();
                                    movieItem.audiAcc = xpp.getText();
                                }
                                break;
                            case XmlPullParser.END_DOCUMENT:
                                break;
                            case XmlPullParser.END_TAG:
                                String tagName2 = xpp.getName();
                                if(tagName2.equals("dailyBoxOffice")){
                                    // 24_ 영화하나가 끝났어? 그러면 리사이클러뷰가 보여주는 대량의 데이터의 리스트에 추가하자.
                                    movieItems.add(movieItem);
                                }
                                break;
                            case XmlPullParser.TEXT:
                                break;
                        }
                        eventType = xpp.next();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this, "파싱 작업이 끝났습니다.", Toast.LENGTH_SHORT).show();
                            //25_ 이 스레드 안에서 파싱 작업을 해주었고, 어댑터에게 UI 가 바뀌었다는 사실을 알려줘야 하므로 여기에서 notify() 해주어야 한다.
                            adapter.notifyDataSetChanged();
                        }
                    });


                }
                catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
                catch (XmlPullParserException e) {
                    throw new RuntimeException(e);
                }
            }
        }.start();
    }
}