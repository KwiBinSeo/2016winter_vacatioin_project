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

public class goods_info extends AppCompatActivity {

    public String goods = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);

        EditText editText = (EditText) findViewById(R.id.editText);

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
                editText.setText(data.goods);
            }

        }catch(NullPointerException ae){

        }

        //물품검색 클릭
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(goods_info.this, goods_check.class);
                startActivity(intent);
                finish();
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
}
