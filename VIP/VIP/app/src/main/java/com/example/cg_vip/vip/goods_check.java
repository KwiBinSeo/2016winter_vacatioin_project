//받는 사람
package com.example.cg_vip.vip;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class goods_check extends AppCompatActivity {

    public String goods = ""; //상품명을 저장 할 변수
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_check);

        //액션바에 뒤로가기 버튼 추가 및 이벤트
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //9개의 버튼 생성
        Button button1 = (Button) findViewById(R.id.button1);
        Button button2 = (Button) findViewById(R.id.button2);
        Button button3 = (Button) findViewById(R.id.button3);
        Button button4 = (Button) findViewById(R.id.button4);
        Button button5 = (Button) findViewById(R.id.button5);
        Button button6 = (Button) findViewById(R.id.button6);
        Button button7 = (Button) findViewById(R.id.button7);
        Button button8 = (Button) findViewById(R.id.button8);
        Button button9 = (Button) findViewById(R.id.button9);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods = "의류";
                Move_Activity();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods = "서신서류";
                Move_Activity();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods = "가전제품류";
                Move_Activity();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods = "과일류";
                Move_Activity();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods = "곡물류";
                Move_Activity();
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods = "한약류";
                Move_Activity();
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods = "식품류";
                Move_Activity();
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods = "잡화서적";
                Move_Activity();
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goods = "제주감귤";
                Move_Activity();
            }
        });
    }

    //뒤로가기 옵션
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //NavUtils.navigateUpFromSameTask(this);
                Intent intent = new Intent(this, goods_info.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //엑티비티 넘겨주는 메소드
    public void Move_Activity()
    {
        Data data = new Data(goods);
        Intent intent = new Intent(goods_check.this, goods_info.class);
        intent.putExtra("data", data);

        startActivity(intent);
        finish();
    }
}
