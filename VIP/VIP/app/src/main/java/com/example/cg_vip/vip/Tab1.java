//택배예약 탭
package com.example.cg_vip.vip;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class Tab1 extends Fragment {
		Context mContext;

		public Tab1(Context context) {
			mContext = context;
		}

		@Override
		public View onCreateView(LayoutInflater inflater,
				ViewGroup container, Bundle savedInstanceState) {
			//View view = inflater.inflate(R.layout.activity_tab1, null);
			View view = inflater.inflate(R.layout.activity_tab1, null);

			/* 카드뷰 시작 */
			RecyclerView recyclerView = (RecyclerView)view.findViewById(R.id.recyclerview);
			LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
			recyclerView.setHasFixedSize(true);
			recyclerView.setLayoutManager(layoutManager);

			List<Recycler_item> items=new ArrayList<>();
			Recycler_item[] item = new Recycler_item[5];
			item[0] = new Recycler_item(R.drawable.aa,"#1");
			item[1] = new Recycler_item(R.drawable.bb,"#2");
			item[2] = new Recycler_item(R.drawable.cc,"#3");
			item[3] = new Recycler_item(R.drawable.dd,"#4");
			item[4] = new Recycler_item(R.drawable.ee,"#5");

			for(int i=0;i<5;i++) items.add(item[i]);

			recyclerView.setAdapter(new RecyclerAdapter(getActivity().getApplicationContext(),items,R.layout.activity_tab1));

	    	return view;
		}
}