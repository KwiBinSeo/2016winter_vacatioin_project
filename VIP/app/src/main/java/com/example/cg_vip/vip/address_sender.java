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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_sender);

        /* 주소 EditText클릭 시 주소검색으로 이동 */
        EditText name_txt = (EditText) findViewById(R.id.editText); //이름
        EditText phone_txt = (EditText) findViewById(R.id.editText2); //전화번호
        EditText address_txt = (EditText) findViewById(R.id.editText3); //주소검색
        EditText address_extra_txt = (EditText) findViewById(R.id.editText4); //추가주소
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
                Intent intent = new Intent(address_sender.this, address_recipient.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
