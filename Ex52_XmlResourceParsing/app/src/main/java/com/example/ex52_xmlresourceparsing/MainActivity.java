package com.example.ex52_xmlresourceparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        findViewById(R.id.btn).setOnClickListener(view -> clickBtn());
    }

    public void clickBtn(){
        // 2_ Res 폴더에 있는 xml 문서를 읽어와서 분석(parse) 하는 작업을 수행하자..

        // 3_ res 폴더 창고관리자 소환
        Resources res = getResources();

        // 4_ 창고관리자로부터 파서객체 얻어오기
        XmlResourceParser xml = res.getXml(R.xml.movies);

        // 5_ xml 분석하기 - 분석 이벤트 종류는 5가지
        // 파서는 next!! 하면서 넘어간다. 그리고 스트림을 이용하기 때문에 try-catch 문을 이용하여 예외처리가 필요하다.
        try {
            xml.next();
            int eventType = xml.getEventType();

            // 8_ String 은 immutable 한 성질이 있어서 s += "aaa" 와 같은 코드는 좋지 못하다.
            // 그래서 StringBuffer 를 사용하자! 요놈은 객체를 매번 만들지 않는다! 일단 버퍼에 넣어두고, 나중에 한꺼번에 객체로 만든다!
            StringBuffer buffer = new StringBuffer();



            /*   리사이클러 뷰 용 버퍼  */
            StringBuffer buffer2 = new StringBuffer();
            StringBuffer buffer3 = new StringBuffer();

            while(eventType != XmlResourceParser.END_DOCUMENT){

                //7_ switch 문 활용
                switch (eventType){
                    case XmlResourceParser.START_DOCUMENT:
                        buffer.append("-- 파싱 작업을 시작합니다 --\n\n");
                        break;

                    case XmlResourceParser.END_DOCUMENT:
                        break;
                    case XmlResourceParser.START_TAG:
                        String tagName = xml.getName(); // 10_ 시작 태그의 이름을 먼저 알아내자<movie> 인지 <item> 인지 등등
                        if(tagName.equals("item")){

                        }else if(tagName.equals("no")){
                            buffer.append("순위 : ");
                        }else if(tagName.equals("title")) {
                            buffer.append("제목 : ");
                        }else if(tagName.equals("genre")){
                            buffer.append("장르 : ");
                        }
                        break;
                    case XmlResourceParser.END_TAG:
                        String tagName2 = xml.getName();
                        if(tagName2.equals("item")){
                            buffer.append("\nㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ\n");
                        }else {
                            buffer.append("\n");
                        }
                        break;
                    case XmlResourceParser.TEXT:
                        String text = xml.getText();
                        buffer.append(text);
                        break;
                }


//                xml.next();
//                eventType = xml.getEventType();
                // 6_ 넥스트와 이벤트타입을 리턴받는건 동시에 가능하다!
                eventType = xml.next();
            } // 9_ while 문이 끝났다. 즉, END_DOCUMENT 를 만났다 라는 이야기. 그러면 작업이 끝났음을 사용자에게 알려주자.
            buffer.append(" -- 파싱을 완료했습니다 --\n\n");
            tv.setText(buffer.toString());



        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        }

    }
}