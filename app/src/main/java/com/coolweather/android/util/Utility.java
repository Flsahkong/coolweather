package com.coolweather.android.util;


import android.text.TextUtils;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.zip.CheckedInputStream;

/**
 * Created by 李帅锋 on 2018/2/1.
 */

public class Utility {
    /*
    *解析服务器返回的json数据
     */
    public static boolean handleCityResponse(String response,int ProvinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                //返回的json数据为一个json数组
                JSONArray allCities=new JSONArray(response);
                for(int i=0;i<allCities.length();i++){
                    JSONObject provinceObject=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(provinceObject.getString("name"));
                    city.setCityCode(provinceObject.getInt("id"));
                    city.setProvinceId(ProvinceId);
                    city.save();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //为什么要return False？？
        return false;
    }

    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                //返回的json数据为一个json数组
                JSONArray allProvinces=new JSONArray(response);
                for(int i=0;i<allProvinces.length();i++){
                    JSONObject provinceObject=allProvinces.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        //为什么要return False？？
        return false;
    }

    public static boolean handleCountyResponse(String response,int CityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray allCounties=new JSONArray(response);
                for(int i=0;i<allCounties.length();i++){
                    JSONObject countyObj=allCounties.getJSONObject(i);
                    County county=new County();
                    county.setCityId(CityId);
                    county.setCountyName(countyObj.getString("name"));
                    county.setWeatherId(countyObj.getString("weather_id"));
                    county.save();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
}
