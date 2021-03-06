package com.ycbike.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ycbike.core.domain.Identifiable;
import com.ycbike.core.exception.DaoException;
import com.ycbike.core.utils.BeanUtils;
import com.ycbike.core.utils.UUIDUtils;
import com.ycbike.web.dao.YcBikeUserLoginDaoTest;

/**
 * 基础Dao接口实现类，实现改类的子类必须设置泛型类型
 * @author Banseon
 * @date 2014年3月3日下午1:02:31
 */
public abstract class BaseDaoImpl<T extends Identifiable> implements BaseDao<T> {
	
	@Autowired(required = true)
	protected SqlSessionTemplate sqlSessionTemplate;
 
	public static final String SQLNAME_SEPARATOR = ".";

	/**
	 * @fields sqlNamespace SqlMapping命名空间 
	 */
	private String sqlNamespace = getDefaultSqlNamespace();

	/**
	 * 获取泛型类型的实体对象类全名
	 * @author LiuJunGuang
	 * @return
	 * @date 2014年3月3日下午6:17:46
	 */
	protected String getDefaultSqlNamespace() {
		Class<?> genericClass = BeanUtils.getGenericClass(this.getClass());
		return genericClass == null ? null : genericClass.getName();
	}

	/**
	 * 获取SqlMapping命名空间 
	 * @author LiuJunGuang
	 * @return SqlMapping命名空间 
	 * @date 2014年3月4日上午12:33:15
	 */
	public String getSqlNamespace() {
		return sqlNamespace;
	}

	/**
	 * 设置SqlMapping命名空间。 以改变默认的SqlMapping命名空间，
	 * 不能滥用此方法随意改变SqlMapping命名空间。 
	 * @author LiuJunGuang
	 * @param sqlNamespace SqlMapping命名空间 
	 * @date 2014年3月4日上午12:33:17
	 */
	public void setSqlNamespace(String sqlNamespace) {
		this.sqlNamespace = sqlNamespace;
	}

	/**
	 * 将SqlMapping命名空间与给定的SqlMapping名组合在一起。
	 * @param sqlName SqlMapping名 
	 * @return 组合了SqlMapping命名空间后的完整SqlMapping名 
	 */
	protected String getSqlName(String sqlName) {
		return sqlNamespace + SQLNAME_SEPARATOR + sqlName;
	}

	/**
	 * 生成主键值。 默认使用{@link UUIDUtils#create()}方法
	 * 如果需要生成主键，需要由子类重写此方法根据需要的方式生成主键值。 
	 * @param entity 要持久化的对象 
	 */
	protected String generateId() {
		return UUIDUtils.create();
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectOne(java.io.Serializable)
	 */
	@Override
	public <V extends T> V selectOne(T query) {
		Assert.notNull(query);
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectOne(getSqlName(ConstantsSQL.SQL_SELECT), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询一条记录出错！语句：%s", getSqlName(ConstantsSQL.SQL_SELECT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectById(java.io.Serializable)
	 */
	@Override
	public <V extends T> V selectById(String id) {
		Assert.notNull(id);
		try {
			return sqlSessionTemplate.selectOne(getSqlName(ConstantsSQL.SQL_SELECT_BY_ID), id);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID查询对象出错！语句：%s", getSqlName(ConstantsSQL.SQL_SELECT_BY_ID)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectList(java.io.Serializable)
	 */
	@Override
	public <V extends T> List<V> selectList(T query) {
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectList(getSqlName(ConstantsSQL.SQL_SELECT), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象列表出错！语句：%s", getSqlName(ConstantsSQL.SQL_SELECT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectAll()
	 */
	@Override
	public <V extends T> List<V> selectAll() {
		System.out.println("====================SQL : "+getSqlName(ConstantsSQL.SQL_SELECT_ALL));
		try {
			return sqlSessionTemplate.selectList(getSqlName(ConstantsSQL.SQL_SELECT_ALL));
		} catch (Exception e) {
			throw new DaoException(String.format("查询所有对象列表出错！语句：%s", getSqlName(ConstantsSQL.SQL_SELECT_ALL)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectMap(java.io.Serializable, java.lang.String)
	 */
	@Override
	public <K, V extends T> Map<K, V> selectMap(T query, String mapKey) {
		Assert.notNull(mapKey, "[mapKey] - must not be null!");
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectMap(getSqlName(ConstantsSQL.SQL_SELECT), params, mapKey);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象Map时出错！语句：%s", getSqlName(ConstantsSQL.SQL_SELECT)), e);
		}
	}

	/**
	 * 设置分页
	 * @param pageInfo 分页信息
	 * @return SQL分页参数对象
	 */


	/**
	 * 获取分页查询参数
	 * @param query 查询对象
	 * @param pageable 分页对象
	 * @return Map 查询参数
	 */
	protected Map<String, Object> getParams(T query, int pageSize, int pageNow) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectList(com.viathink.core.dao.domain.Identifiable, org.springframework.data.domain.Pageable)
	 */
	@Override
	public <V extends T> List<V> selectList(T query, int pageSize, int pageNow) {
		try {
			return sqlSessionTemplate.selectList(getSqlName(ConstantsSQL.SQL_SELECT), getParams(query,  pageSize,  pageNow));
		} catch (Exception e) {
			throw new DaoException(String.format("根据分页对象查询列表出错！语句:%s", getSqlName(ConstantsSQL.SQL_SELECT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectMap(com.viathink.core.dao.domain.Identifiable, java.lang.String, org.springframework.data.domain.Pageable)
	 */
	@Override
	public <K, V extends T> Map<K, V> selectMap(T query, String mapKey, int pageSize, int pageNow) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectCount()
	 */
	@Override
	public Long selectCount() {
		try {
			return sqlSessionTemplate.selectOne(getSqlName(ConstantsSQL.SQL_SELECT_COUNT));
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName(ConstantsSQL.SQL_SELECT_COUNT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#selectCount(java.io.Serializable)
	 */
	@Override
	public Long selectCount(T query) {
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.selectOne(getSqlName(ConstantsSQL.SQL_SELECT_COUNT), params);
		} catch (Exception e) {
			throw new DaoException(String.format("查询对象总数出错！语句：%s", getSqlName(ConstantsSQL.SQL_SELECT_COUNT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#insert(java.io.Serializable)
	 */
	@Override
	public void insert(T entity) {
		Assert.notNull(entity);
		try {
			if (StringUtils.isBlank(entity.getUuid()))
				entity.setUuid(generateId());
			sqlSessionTemplate.insert(getSqlName(ConstantsSQL.SQL_INSERT), entity);
		} catch (Exception e) {
			throw new DaoException(String.format("添加对象出错！语句：%s", getSqlName(ConstantsSQL.SQL_INSERT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#delete(java.io.Serializable)
	 */
	@Override
	public int delete(T query) {
		Assert.notNull(query);
		try {
			Map<String, Object> params = BeanUtils.toMap(query);
			return sqlSessionTemplate.delete(getSqlName(ConstantsSQL.SQL_DELETE), params);
		} catch (Exception e) {
			throw new DaoException(String.format("删除对象出错！语句：%s", getSqlName(ConstantsSQL.SQL_DELETE)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#deleteById(java.io.Serializable)
	 */
	@Override
	public int deleteById(String id) {
		Assert.notNull(id);
		try {
			return sqlSessionTemplate.delete(getSqlName(ConstantsSQL.SQL_DELETE_BY_ID), id);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID删除对象出错！语句：%s", getSqlName(ConstantsSQL.SQL_DELETE_BY_ID)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#deleteAll()
	 */
	@Override
	public int deleteAll() {
		try {
			return sqlSessionTemplate.delete(getSqlName(ConstantsSQL.SQL_DELETE));
		} catch (Exception e) {
			throw new DaoException(String.format("删除所有对象出错！语句：%s", getSqlName(ConstantsSQL.SQL_DELETE)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#updateById(java.io.Serializable)
	 */
	@Override
	public int updateById(T entity) {
		Assert.notNull(entity);
		try {
			return sqlSessionTemplate.update(getSqlName(ConstantsSQL.SQL_UPDATE_BY_ID), entity);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID更新对象出错！语句：%s", getSqlName(ConstantsSQL.SQL_UPDATE_BY_ID)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#updateByIdSelective(java.io.Serializable)
	 */
	@Override
	@Transactional
	public int updateByIdSelective(T entity) {
		Assert.notNull(entity);
		try {
			return sqlSessionTemplate.update(getSqlName(ConstantsSQL.SQL_UPDATE_BY_ID_SELECTIVE), entity);
		} catch (Exception e) {
			throw new DaoException(String.format("根据ID更新对象某些属性出错！语句：%s", getSqlName(ConstantsSQL.SQL_UPDATE_BY_ID_SELECTIVE)),
					e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#deleteByIdInBatch(java.util.List)
	 */
	@Override
	@Transactional
	public void deleteByIdInBatch(List<String> idList) {
		if (idList == null || idList.isEmpty())
			return;
		for (String id : idList) {
			this.deleteById(id);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#updateInBatch(java.util.List)
	 */
	@Override
	@Transactional
	public void updateInBatch(List<T> entityList) {
		if (entityList == null || entityList.isEmpty())
			return;
		for (T entity : entityList) {
			this.updateByIdSelective(entity);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#insertInBatch(java.util.List)
	 */
	@Override
	public void insertInBatch(List<T> entityList) {
		if (entityList == null || entityList.isEmpty())
			return;
		for (T entity : entityList) {
			this.insert(entity);
		}
	}

}