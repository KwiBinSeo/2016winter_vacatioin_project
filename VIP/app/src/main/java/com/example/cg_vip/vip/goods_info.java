//받는 사람
package com.example.cg_vip.vip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class goods_info extends AppCompatActivity {

    public String goods = "";

    //보내는사람 정보 변수
    public static String sd_name = "";
    public static String sd_phone = "";
    public static String sd_address1 = "";
    public static String sd_address2 = "";
    //끝

    //받는사람 정보 변수
    public static String rc_name = "";
    public static String rc_phone = "";
    public static String rc_address1 = "";
    public static String rc_address2 = "";
    //끝

    public String if_info = "";
    public String if_price = "";
    public String if_memo = "";

    //텍스트필드
    public EditText info_txt;
    public EditText price_txt;
    public EditText memo_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);

        info_txt = (EditText) findViewById(R.id.editText);
        price_txt = (EditText) findViewById(R.id.editText2);
        memo_txt = (EditText) findViewById(R.id.editText3);

        Button next_btn = (Button) findViewById(R.id.next_btn); //다음버튼

        RecipientToInfo(); //받는사람으로부터 전달받은 데이터 받아오는 메소드

        //액션바에 뒤로가기 버튼 추가 및 이벤트
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //데이터 전달받기
        Intent intent = getIntent(); //전달받을 데이터 intent
        Data data = (Data) intent.getSerializableExtra("data");
        try {
            goods = data.goods;

            if (goods == "") {
            } else {
                info_txt.setText(data.goods);
            }

        }catch(NullPointerException ae){

        }

        //물품검색 클릭
        info_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(goods_info.this, goods_check.class);
                startActivity(intent);
            }
        });

        //다음버튼 클릭
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InfoToSummary(); //물품요약으로 데이터 보내는 메소드
            }
        });
    }

    //뒤로가기 옵션
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                Intent intent = new Intent(this, address_recipient.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //받는사람 -> 물품정보로 보낸 데이터 받아오기
    public void RecipientToInfo() {
        Intent intent = getIntent(); //전달받을 데이터 intent
        Data data_RCtoIF = (Data) intent.getSerializableExtra("data");

        sd_name = data_RCtoIF.sd_name;
        sd_phone = data_RCtoIF.sd_phone;
        sd_address1 = data_RCtoIF.sd_address1;
        sd_address2 = data_RCtoIF.sd_address2;

        rc_name = data_RCtoIF.rc_name;
        rc_phone = data_RCtoIF.rc_phone;
        rc_address1 = data_RCtoIF.rc_address1;
        rc_address2 = data_RCtoIF.rc_address2;

        Log.d("moguwai", "**물품정보 화면**");
        Log.d("moguwai", "보내는사람 데이터가 제대로 왔는지 출력");
        Log.d("moguwai", sd_name);
        Log.d("moguwai", sd_phone);
        Log.d("moguwai", sd_address1);
        Log.d("moguwai", sd_address2);

        Log.d("moguwai", "받는사람 데이터가 제대로 왔는지 출력");
        Log.d("moguwai",rc_name);
        Log.d("moguwai", rc_phone);
        Log.d("moguwai", rc_address1);
        Log.d("moguwai", rc_address2);
    }

    //물품정보 -> 물품요약으로 데이터 보내기
    public void InfoToSummary() {
        //물품정보 데이터 받아오기
        String if_info = info_txt.getText().toString();
        String if_price = price_txt.getText().toString();
        String if_memo = memo_txt.getText().toString();

        //추가 1
        String sd_name = this.sd_name;
        String sd_phone = this.sd_phone;
        String sd_address1 = this.sd_address1;
        String sd_address2 = this.sd_address2;
        //추가 끝

        //추가 2
        String rc_name = this.rc_name;
        String rc_phone = this.rc_phone;
        String rc_address1 = this.rc_address1;
        String rc_address2 = this.rc_address2;

        Log.d("moguwai", "************************************************************************");
        Log.d("moguwai", "보내는사람 데이터가 제대로 왔는지 출력");
        Log.d("moguwai", sd_name);
        Log.d("moguwai", sd_phone);
        Log.d("moguwai", sd_address1);
        Log.d("moguwai", sd_address2);

        Log.d("moguwai", "받는사람 데이터가 제대로 왔는지 출력");
        Log.d("moguwai", rc_name);
        Log.d("moguwai", rc_phone);
        Log.d("moguwai", rc_address1);
        Log.d("moguwai", rc_address2);
        Log.d("moguwai", "************************************************************************");

        Data data_IFtoSM = new Data(sd_name, sd_phone, sd_address1, sd_address2, rc_name, rc_phone, rc_address1, rc_address2, if_info, if_price, if_memo); //보내는사람정보와 받는사람정보를 물품정보 화면으로 전송하는 Data객체
      //  Data data1 = new Data(sd_name, sd_phone, sd_address1, sd_address2);
      //  Data data2 = new Data(rc_name, rc_phone, rc_address1, rc_address2);
      //  Data data3 = new Data(if_info, if_price, if_memo);

        Intent intent = new Intent(goods_info.this, goods_summary.class);

        intent.putExtra("data",data_IFtoSM); //데이터 넘기기
        /*
        intent.putExtra("data1",data1);
        intent.putExtra("data2",data2);
        intent.putExtra("data3",data3);
*/
        startActivity(intent);
        finish();
    }
}
