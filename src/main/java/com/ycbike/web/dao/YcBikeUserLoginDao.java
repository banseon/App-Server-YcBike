package com.ycbike.web.dao;

import java.util.List;

import com.ycbike.core.model.YcBikeUserLogin;

public interface YcBikeUserLoginDao {

	
	public YcBikeUserLogin findById(int uuid);
	
	public YcBikeUserLogin findByPhone(int phone);
	
	public YcBikeUserLogin queryByLogin(int uuidOrphone,String password);
	
	public List<YcBikeUserLogin> query();
	
	public List<YcBikeUserLogin> query(Object...objects);
	
	public void add(YcBikeUserLogin login);
	
	public void update(YcBikeUserLogin login);
	
	public void deleteById(int uuid);
	
	public void deleleByBean(YcBikeUserLogin...bikeUserLogins);
	
}
