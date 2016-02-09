//설정 탭
package com.example.cg_vip.vip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ValidFragment")
public class Tab4 extends Fragment {
	Context mContext;

	public Tab4(Context context) {
		mContext = context;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
							 ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_tab4, null);

		return view;
	}

}