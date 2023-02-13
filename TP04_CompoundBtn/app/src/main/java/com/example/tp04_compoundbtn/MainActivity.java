package com.example.tp04_compoundbtn;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    Button resistBtn;
    CheckBox cbEmail, cbPhone, cbVisit, cbSMS;
    RadioGroup genderRadio, cityRadio;
    RadioButton maleRadio,femaleRadio,seoulRadio,busanRadio,etcRadio;
    EditText nameEdit, phoneEdit1, phoneEdit2, phoneEdit3;
    String s = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        resistBtn = findViewById(R.id.resistBtn);
        cbEmail = findViewById(R.id.cbEmail);
        cbPhone = findViewById(R.id.cbPhone);
        cbVisit = findViewById(R.id.cbVisit);
        cbSMS = findViewById(R.id.cbSMS);

        genderRadio = findViewById(R.id.genderRadio);
        cityRadio = findViewById(R.id.cityRadio);
        maleRadio = findViewById(R.id.maleRadio);
        femaleRadio = findViewById(R.id.femaleRadio);
        seoulRadio = findViewById(R.id.seoulRadio);
        busanRadio = findViewById(R.id.busanRadio);
        etcRadio = findViewById(R.id.etcRadio);

        nameEdit = findViewById(R.id.nameEdit);
        phoneEdit1 = findViewById(R.id.phoneEdit1);
        phoneEdit2 = findViewById(R.id.phoneEdit2);
        phoneEdit3 = findViewById(R.id.phoneEdit3);



        resistBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s += nameEdit.getText().toString() + " "
                        + getGenderRadioBtn() + " "
                        + getCityRadioBtn() + " "
                        + phoneEdit1.getText().toString() + "-"
                        + phoneEdit2.getText().toString() + "-"
                        + phoneEdit3.getText().toString() + " "
                        + getCheckBoxText() + "\n";

                tv.setText(s);


            }
        });

        phoneEdit1.addTextChangedListener(listener);
        phoneEdit2.addTextChangedListener(listener);
        phoneEdit3.addTextChangedListener(listener);
    }
    TextWatcher listener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if(phoneEdit1.getText().toString().equals(editable.toString()) && (phoneEdit1.getText().toString().length() == 3))
                phoneEdit2.requestFocus();
            if(phoneEdit2.getText().toString().equals(editable.toString()) && (phoneEdit2.getText().toString().length() == 4))
                phoneEdit3.requestFocus();
            if(phoneEdit3.getText().toString().length() == 4)
                cbEmail.requestFocus();
        }
    };

    public String getGenderRadioBtn(){
        int id = genderRadio.getCheckedRadioButtonId();
        if(id == maleRadio.getId()) return maleRadio.getText().toString();
        else return femaleRadio.getText().toString();
    }

    public String getCityRadioBtn(){
        int id = cityRadio.getCheckedRadioButtonId();
        if(id == seoulRadio.getId()) return seoulRadio.getText().toString();
        else if(id == busanRadio.getId()) return busanRadio.getText().toString();
        else return etcRadio.getText().toString();
    }

    public String getCheckBoxText(){
        String s = "";
        if(cbEmail.isChecked()) s += cbEmail.getText().toString() + " ";
        if(cbPhone.isChecked()) s += cbPhone.getText().toString() + " ";
        if(cbVisit.isChecked()) s += cbVisit.getText().toString() + " ";
        if(cbSMS.isChecked()) s += cbSMS.getText().toString();

        return s;
    }
}