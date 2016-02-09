package com.example.cg_vip.vip;

/**
 * Created by songmho on 2015-07-11.
 * 두번째 탭 뷰 아이템
 */
public class Recycler_item_trace {
    int image;
    String title;

    int getImage(){
        return this.image;
    }
    String getTitle(){
        return this.title;
    }

    Recycler_item_trace(int image, String title){
        this.image=image;
        this.title=title;
    }
}
