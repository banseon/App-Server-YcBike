package com.ycbike.web.dao;

import org.springframework.stereotype.Component;

import com.ycbike.core.dao.BaseDaoImpl;
import com.ycbike.web.domain.YcBikeRoute;

@Component("IYcBikeRouteDao")
public class YcBikeRouteDao extends BaseDaoImpl<YcBikeRoute> implements IYcBikeRouteDao {

}
