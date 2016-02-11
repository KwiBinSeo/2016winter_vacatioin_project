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

    //보내는사람 -> 받는사람
    public String sd_name = "";
    public String sd_phone = "";
    public String sd_address1 = "";
    public String sd_address2 = "";
    //끝

    //받는사람 -> 물품정보
    public String rc_name = "";
    public String rc_phone = "";
    public String rc_address1 = "";
    public String rc_address2 = "";
    //끝

    //물품정보 -> 물품요약
    public String if_info = "";
    public String if_price = "";
    public String if_memo = "";
    //끝

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

    //보내는사람 -> 받는사람으로 넘기는 데이터
    public Data(String sd_name, String sd_phone, String sd_address1, String sd_address2) {
        this.sd_name = sd_name;
        this.sd_phone = sd_phone;
        this.sd_address1 = sd_address1;
        this.sd_address2 = sd_address2;
    }

    //받는사람 -> 물품정보로 넘기는 데이터
    public Data(String sd_name, String sd_phone, String sd_address1, String sd_address2, String rc_name, String rc_phone, String rc_address1, String rc_address2) {
        this.sd_name = sd_name;
        this.sd_phone = sd_phone;
        this.sd_address1 = sd_address1;
        this.sd_address2 = sd_address2;

        this.rc_name = rc_name;
        this.rc_phone = rc_phone;
        this.rc_address1 = rc_address1;
        this.rc_address2 = rc_address2;
    }

    //물품정보 -> 물품요약으로넘기는 데이터
    public Data(String sd_name, String sd_phone, String sd_address1, String sd_address2, String rc_name, String rc_phone, String rc_address1, String rc_address2, String if_info, String if_price, String if_memo) {

        this.sd_name = sd_name;
        this.sd_phone = sd_phone;
        this.sd_address1 = sd_address1;
        this.sd_address2 = sd_address2;

        this.rc_name = rc_name;
        this.rc_phone = rc_phone;
        this.rc_address1 = rc_address1;
        this.rc_address2 = rc_address2;

        this.if_info = if_info;
        this.if_price = if_price;
        this.if_memo = if_memo;
    }

    //보내는사람 -> 받는사람으로 넘기는 데이터
    public Data(String if_info, String if_price, String if_memo) {
        this.if_info = if_info;
        this.if_price = if_price;
        this.if_memo = if_memo;
    }
}
