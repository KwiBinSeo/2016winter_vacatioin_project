//보내는사람
package com.example.cg_vip.vip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class address_sender extends AppCompatActivity {

    public String address = "";
    public String address_number = "";
    public int mode = 0;

    //텍스트필드
    public EditText name_txt;
    public EditText phone_txt;
    public EditText address_txt;
    public EditText address_extra_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_sender);

        /* 주소 EditText클릭 시 주소검색으로 이동 */
        name_txt = (EditText) findViewById(R.id.editText); //이름
        phone_txt = (EditText) findViewById(R.id.editText2); //전화번호
        address_txt = (EditText) findViewById(R.id.editText3); //주소검색
        address_extra_txt = (EditText) findViewById(R.id.editText4); //추가주소
        Button next_btn = (Button) findViewById(R.id.next_btn); //다음 버튼

        //주소검색
        address_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode = 1;
                Data data_mode = new Data(mode);
                //address_search_webview로 데이터 넘겨주기
                Intent intent = new Intent(address_sender.this, address_search_webview.class);
                intent.putExtra("data",data_mode);

                startActivity(intent);
                finish();
            }
        });

        Intent intent = getIntent(); //전달받을 데이터 intent
        Data data = (Data) intent.getSerializableExtra("data");
        try {
            address = data.address;
            address_number = data.address_number;

            if (address == "") {
            } else {
                address_txt.setText(data.address);
                address_extra_txt.setText(data.address_number);
            }

        }catch(NullPointerException ae){

        }

        //액션바에 뒤로가기 버튼 추가 및 이벤트
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SenderToRecipient(); //받는사람 페이지로 데이터 보내기
            }
        });
    }

    //보내는사람 -> 받는사람으로 데이터 보내기
    public void SenderToRecipient() {
        //보내는사람 -> 받는사람
        String sd_name = name_txt.getText().toString();
        String sd_phone = phone_txt.getText().toString();
        String sd_address1 = address_txt.getText().toString();
        String sd_address2 = address_extra_txt.getText().toString();
        //끝
        Data data_SDtoRC = new Data(sd_name, sd_phone, sd_address1, sd_address2); //보내는사람 -> 받는사람 화면으로 넘기는 Data
        Intent intent = new Intent(address_sender.this, address_recipient.class);

        intent.putExtra("data",data_SDtoRC); //데이터 넘기기

        startActivity(intent);
        finish();
    }
}
