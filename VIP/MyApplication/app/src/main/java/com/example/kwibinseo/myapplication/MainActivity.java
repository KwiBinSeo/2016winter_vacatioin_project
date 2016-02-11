package com.example.kwibinseo.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;



public class MainActivity extends Activity {

    public EditText editText1; //이름
    public EditText editText2; //학번
    public TextView textview; //아이피 정보를 가지고 있는 텍스트뷰

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText1 = (EditText) findViewById(R.id.editText); //이름
        editText2 = (EditText) findViewById(R.id.editText2); //학번
        textview = (TextView) findViewById(R.id.textView); //ip
        Button button = (Button) findViewById(R.id.button); //다음버튼

        //웹뷰 시작
        WebView wb = (WebView) findViewById(R.id.webView);
        String url = "http://220.69.209.170/vip/android/winter/check_list.php";
        //String url = "http://220.69.209.170/check/check.php";

        wb.setWebViewClient(new WebViewClient());
        wb.loadUrl(url);
        //웹뷰 끝

        //버튼 클릭
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert(view); //데이터 삽입
            }
        });
    }
    public void goURL(View view) {

    }
    public void insert(View view){
        String name = editText1.getText().toString(); //이름
        String hak = editText2.getText().toString(); //학번
        String ip = textview.getText().toString(); //아이피

        insertToDatabase(name, hak, ip);


    }

    private void insertToDatabase(String name, String hak, String ip){

        class InsertData extends AsyncTask<String, String, String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String name = (String)params[0];
                    String hak = (String)params[1];
                    String ip = (String)params[2];

                    String link="http://220.69.209.170/vip/android/winter/check.php";

                    String data  = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8");
                    data += "&" + URLEncoder.encode("hak", "UTF-8") + "=" + URLEncoder.encode(hak, "UTF-8");
                    data += "&" + URLEncoder.encode("ip", "UTF-8") + "=" + URLEncoder.encode(ip, "UTF-8");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                }
                catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        task.execute(name,hak, ip);
    }
}