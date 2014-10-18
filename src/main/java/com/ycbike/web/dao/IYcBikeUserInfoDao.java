package com.ycbike.web.dao;

import org.springframework.stereotype.Component;

import com.ycbike.core.dao.BaseDao;
import com.ycbike.web.domain.YcBikeUserInfo;
@Component("IYcBikeUserInfoDao")
public interface IYcBikeUserInfoDao extends BaseDao<YcBikeUserInfo> {

}