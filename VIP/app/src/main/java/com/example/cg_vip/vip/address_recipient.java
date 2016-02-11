//받는 사람
package com.example.cg_vip.vip;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class address_recipient extends AppCompatActivity {

    public String address = "";
    public String address_number = "";
    public int mode = 0;

    //보내는사람 -> 받는사람
    public String sd_name = "";
    public String sd_phone = "";
    public String sd_address1 = "";
    public String sd_address2 = "";
    //끝

    //받는사람 -> 물품정보
    //텍스트필드
    public EditText name_txt;
    public EditText phone_txt;
    public EditText address_txt;
    public EditText address_extra_txt;
    //끝

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_recipient);

        SenderToRecipient(); //보내는사람 -> 받는사람으로 데이터 보내기

        /* 주소 EditText클릭 시 주소검색으로 이동 */
        name_txt = (EditText) findViewById(R.id.editText); //이름
        phone_txt = (EditText) findViewById(R.id.editText2); //전화번호
        address_txt = (EditText) findViewById(R.id.editText3); //주소검색
        address_extra_txt = (EditText) findViewById(R.id.editText4); //추가주소
        Button next_btn = (Button) findViewById(R.id.next_btn);

        //주소검색에서 데이터 찾아서 받아오기
        Intent intent = getIntent(); //전달받을 데이터 intent
        Data data = (Data) intent.getSerializableExtra("data");
        try {
            address = data.address;
            address_number = data.address_number;

            if (address == "") {
            }
            else {
                address_txt.setText(data.address);
                address_extra_txt.setText(data.address_number);
            }

        }catch(NullPointerException ae){

        }

        //액션바에 뒤로가기 버튼 추가 및 이벤트
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //주소검색
        address_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mode = 2;
                Data data_mode = new Data(mode);
                //address_search_webview로 데이터 넘겨주기
                Intent intent = new Intent(address_recipient.this, address_search_webview.class);
                intent.putExtra("data", data_mode);

                startActivity(intent);
                finish();
            }
        });


        //다음 버튼
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecipientToInfo(); //받는사람 -> 물품정보로 데이터 보내기 메소드
                /*
                Intent intent = new Intent(address_recipient.this, goods_info.class);
                startActivity(intent);
                finish();
                */
            }
        });
    }

    //뒤로가기 옵션
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                Intent intent = new Intent(this, address_sender.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //보내는사람 -> 받는사람으로 보낸 데이터 받아오기
    public void SenderToRecipient() {
        Intent intent = getIntent(); //전달받을 데이터 intent
        Data data_SDtoRC = (Data) intent.getSerializableExtra("data");

        sd_name = data_SDtoRC.sd_name;
        sd_phone = data_SDtoRC.sd_phone;
        sd_address1 = data_SDtoRC.sd_address1;
        sd_address2 = data_SDtoRC.sd_address2;

        Log.d("moguwai", "보내는사람 화면에서 보내는사람 데이터가 제대로 왔는지 출력");
        Log.d("moguwai",sd_name);
        Log.d("moguwai", sd_phone);
        Log.d("moguwai", sd_address1);
        Log.d("moguwai", sd_address2);
    }

    //받는사람 -> 물품정보으로 데이터 보내기
    public void RecipientToInfo() {
        //받는사람 정보 데이터
        String rc_name = name_txt.getText().toString();
        String rc_phone = phone_txt.getText().toString();
        String rc_address1 = address_txt.getText().toString();
        String rc_address2 = address_extra_txt.getText().toString();

        //추가
        String sd_name = this.sd_name;
        String sd_phone = this.sd_phone;
        String sd_address1 = this.sd_address1;
        String sd_address2 = this.sd_address2;
        //추가 끝

        Data data_RCtoIF = new Data(sd_name, sd_phone, sd_address1, sd_address2, rc_name, rc_phone, rc_address1, rc_address2); //보내는사람정보와 받는사람정보를 물품정보 화면으로 전송하는 Data객체

        Intent intent = new Intent(address_recipient.this, goods_info.class);

        intent.putExtra("data",data_RCtoIF); //데이터 넘기기

        startActivity(intent);
        finish();
    }
}
