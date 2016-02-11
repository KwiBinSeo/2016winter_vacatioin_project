//설정 탭
package com.example.cg_vip.vip;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@SuppressLint("ValidFragment")
public class Tab4 extends Fragment {
	Context mContext;

	public String Url = "http://220.69.209.170/vip/test.php";

	public Tab4(Context context) {
		mContext = context;
	}

	EditText userId;
	EditText userPw1;
	EditText userPw2;
	EditText userName;
	EditText userPhone;
	EditText userAddress1;
	EditText userAddress2;

	public String userid = "";
	public String username = "";
	public String userpw = "";
	public String userphone = "";
	public String useraddress1 = ""; //주소1
	public String useraddress2 = ""; //주소2

	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_tab4, null);

		//추가 부분
		userId = (EditText) view.findViewById(R.id.useridText); //회원 ID 텍스트
		userPw1 = (EditText) view.findViewById(R.id.userpw1Text); //회원 비밀번호1 텍스트
		userPw2 = (EditText) view.findViewById(R.id.userpw2Text); //회원 비밀번호2 텍스트
		userName = (EditText) view.findViewById(R.id.NameText); //회원 이름 텍스트
		userPhone = (EditText) view.findViewById(R.id.phoneText); //회원 전화번호 텍스트
		userAddress1 = (EditText) view.findViewById(R.id.address1Text);
		userAddress2 = (EditText) view.findViewById(R.id.address2Text);

		Button next_btn = (Button) view.findViewById(R.id.next_btn); //가입 버튼
		Button clear_btn = (Button) view.findViewById(R.id.clear_btn); //초기화 버툰

		//확인 버튼 클릭하면 실행
		next_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Toast toast = Toast.makeText(getActivity(), "가입버튼 클릭", Toast.LENGTH_SHORT );
				toast.show();
				insert(view); //데이터 삽입 호출
			}
		});

		//초기화 버튼 클릭하면 실행
		clear_btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				userId.setText("");
				userPw1.setText("");;
				userPw2.setText("");;
				userName.setText("");;
				userPhone.setText("");;
				userAddress1.setText("");;
				userAddress2.setText("");;
				Toast toast = Toast.makeText(getActivity(), "초기화 완료", Toast.LENGTH_SHORT );
				toast.show();
			}
		});
		//추가 부분 끝
		return view;
	}

	public void insert(View view){
		userid = userId.getText().toString();
		userpw = userPw1.getText().toString();
		username = userName.getText().toString();
		userphone = userPhone.getText().toString();
		useraddress1 = userAddress1.getText().toString();
		useraddress2 = userAddress2.getText().toString();

		insertToDatabase(userid, userpw, userphone, useraddress1, useraddress2, username);
	}
	private void insertToDatabase(String userid, String userpw, String userphone, String useraddress1, String useraddress2, String username) {
		class InsertData extends AsyncTask<String, String, String> {
			ProgressDialog loading;

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				loading = ProgressDialog.show(getActivity(), "Please Wait", null, true, true);
			}

			@Override
			protected void onPostExecute(String s) {
				super.onPostExecute(s);
				loading.dismiss();
				Toast.makeText(getActivity(), s, Toast.LENGTH_LONG).show();
			}

			@Override
			protected String doInBackground(String... params) {

				try {
					String userid = (String)params[0];
					String userpw = (String)params[1];
					String userphone = (String)params[2];
					String useraddress1 = (String)params[3];
					String useraddress2 = (String)params[4];
					String username = (String)params[5];

					String link = "http://220.69.209.170/vip/android/winter/join.php";


					String data  = URLEncoder.encode("userid", "UTF-8") + "=" + URLEncoder.encode(userid, "UTF-8");
					data += "&" + URLEncoder.encode("userpw", "UTF-8") + "=" + URLEncoder.encode(userpw, "UTF-8");
					data += "&" + URLEncoder.encode("userphone", "UTF-8") + "=" + URLEncoder.encode(userphone, "UTF-8");
					data += "&" + URLEncoder.encode("useraddress1", "UTF-8") + "=" + URLEncoder.encode(useraddress1, "UTF-8");
					data += "&" + URLEncoder.encode("useraddress2", "UTF-8") + "=" + URLEncoder.encode(useraddress2, "UTF-8");
					data += "&" + URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8");

					URL url = new URL(link);
					URLConnection conn = url.openConnection();

					conn.setDoOutput(true);
					conn.setDoOutput(true);
					OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());


					wr.write(data);
					wr.flush();

					BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					StringBuilder sb = new StringBuilder();
					String line = "";

					// Read Server Response
					while((line = reader.readLine()) != null)
					{
						sb.append(line);
						break;
					}
					return sb.toString();
				}
				catch(Exception e) {
					return new String("Exception : " + e.getMessage());
				}
			}
		}
		InsertData task = new InsertData();
		task.execute(userid, userpw, userphone, useraddress1, useraddress2, username);
	}
}