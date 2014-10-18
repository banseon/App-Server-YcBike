package com.ycbike.web.dao;

import org.springframework.stereotype.Component;

import com.ycbike.core.dao.BaseDaoImpl;
import com.ycbike.web.domain.YcBikeRouteNode;


@Component("IYcBikeRouteNodeDao")
public class YcBikeRouteNodeDao extends BaseDaoImpl<YcBikeRouteNode> implements IYcBikeRouteNodeDao {

}
