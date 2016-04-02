package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.db.CoolWeatherDB;
import com.coolweather.app.model.City;
import com.coolweather.app.model.Country;
import com.coolweather.app.model.Province;

public class Utility
{
	/**
	 * 
	 * @�����ʹ����ӷ��������ص�ʡ����Ϣ
	 */
	public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeather, String response)
	{
		if(!TextUtils.isEmpty(response))
		{
			String[] allProvinces = response.split(",");
			
			if(allProvinces != null && allProvinces.length > 0)
			{
				for(String p : allProvinces)
				{
					String[] array = p.split("\\|");
					
					Province province = new Province();
					
					province.setProvinceCode(array[0]);
					
					province.setProvinceName(array[1]);
					
					coolWeather.saveProvince(province);
				}
				
				return true;
			}
		}
		return false;
	}
	/*
	 * �����ʹ����ӷ��������ص��м�����
	 */
	
	public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId)
	{
		if(!TextUtils.isEmpty(response))
		{
			String[] allCities = response.split(",");
			
			if(allCities != null && allCities.length > 0)
			{
				for(String c : allCities)
				{
					String[] array = c.split("\\|");
					
					City city = new City();
					
					city.setCityCode(array[0]);
					
					city.setCityName(array[1]);
					
					city.setProvinceId(provinceId);
					
					coolWeatherDB.saveCity(city);
				}
				
				return true;
			}
		}
		return false;
	}
	
		
	/**
	 * �����ʹ����ӷ��������ص��ؼ�����
	 */
	public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId)
	{
		if(!TextUtils.isEmpty(response))
		{
			String[] allCountries = response.split(",");
			
			if(allCountries != null && allCountries.length > 0)
			{
				for(String c : allCountries)
				{
					String[] array = c.split("\\|");
					
					Country country = new Country();
					
					country.setCountryCode(array[0]);
					
					country.setCountryName(array[1]);
					
					country.setCityId(cityId);
					
					coolWeatherDB.saveCountry(country);
				}
				return true;
			}
		}
		return false;
	}
}