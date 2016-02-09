package com.example.cg_vip.vip;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class address_search_webview extends AppCompatActivity{

    Dialog dialogWeb;
    private final Handler handler = new Handler();
    public String address_number ="";
    public String address ="";
    public int mode = 0; //보내는사람 = 1 / 받는사람 = 2

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_search_webview);

        WebView wb = (WebView)findViewById(R.id.webView);
        wb.getSettings().setJavaScriptEnabled(true);

        wb.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wb.addJavascriptInterface(new AndroidBridge(), "vip_app");

        wb.setWebChromeClient(new WebChromeClient());
        wb.loadUrl("http://220.69.209.170/vip/address.php");

        //액션바에 뒤로가기 버튼 추가 및 이벤트
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //데이터 받기 부분
        Intent intent = getIntent(); //전달받을 데이터 intent
        Data data_mode = (Data) intent.getSerializableExtra("data");
        mode = data_mode.mode;
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

    //안드로이드 브릿지
    private class AndroidBridge {
        @JavascriptInterface
        public void testMove(final String arg1, final String arg2) {
            handler.post(new Runnable(){

                @Override
                public void run() {
                    Log.d("moguwai","핸들러 호출");
                    Log.d("moguwai",arg1);
                    Log.d("moguwai", arg2);

                    //데이터가 들어오면 address_sender로 주소와 우편번호를 넘겨준다.
                    address_number = arg2; //주소와 우편번호 저장
                    address = arg1;

                    Data data = new Data(address_number, address);
                    //address_sender엑티비티로 넘겨주기

                    //mode가 1이면 받는사람에게 이동
                    if(mode == 1) {
                        Intent intent = new Intent(address_search_webview.this, address_sender.class);
                        intent.putExtra("data",data);
                        startActivity(intent);
                        finish();
                    }
                    //mode가 2이면 보내는사람에게 이동
                    else if(mode == 2) {
                        Intent intent = new Intent(address_search_webview.this, address_recipient.class);
                        intent.putExtra("data",data);
                        startActivity(intent);
                        finish();
                    }

                }
            });
        }
    }

    public void init() {
        dialogWeb = new Dialog(this); //WebDialog 초기화
    }

}


