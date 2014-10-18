package com.ycbike.web.dao;

import org.springframework.stereotype.Component;

import com.ycbike.core.dao.BaseDaoImpl;
import com.ycbike.web.domain.YcBikeUserInfo;

@Component("YcBikeUserInfoDao")
public class YcBikeUserInfoDao extends BaseDaoImpl<YcBikeUserInfo> implements IYcBikeUserInfoDao  {

}
