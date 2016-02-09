package com.example.cg_vip.vip;

import java.io.Serializable;

/**
 * Created by kwibinSeo on 2016-01-25.
 */
public class Data implements Serializable {

    public String address = "";
    public String address_number = "";
    public String goods = ""; //상품종류 9개
    public int mode = 0; //초기값 0

    public Data() { }

    //보내는분, 받는 사람 화면중 어느곳에서 선택되었는지 확인하는 Data메소드
    public Data(int mode) {
        this.mode = mode;
    }
    //주소 값 넘기는 Data 메소드
    public Data(String address, String address_number) {
        this.address = address;
        this.address_number = address_number;
    }

    //물품 선택 Data
    public Data(String goods) {
        this.goods = goods;
    }
}
