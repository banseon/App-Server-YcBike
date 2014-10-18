package com.ycbike.web.dao;

import org.springframework.stereotype.Component;

import com.ycbike.core.dao.BaseDaoImpl;
import com.ycbike.web.domain.YcBikeCircle;
import com.ycbike.web.domain.YcBikeCircleUser;

@Component("YcBikeCircleDao")
public class YcBikeCircleDao extends BaseDaoImpl<YcBikeCircle> implements IYcBikeCircleDao {


}
