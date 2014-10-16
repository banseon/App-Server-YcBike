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
 * ����Dao�ӿ�ʵ���࣬ʵ�ָ��������������÷�������
 * @author Banseon
 * @date 2014��3��3������1:02:31
 */
public abstract class BaseDaoImpl<T extends Identifiable> implements BaseDao<T> {
	
	@Autowired(required = true)
	protected SqlSessionTemplate sqlSessionTemplate;
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {  
        this.sqlSessionTemplate = sqlSessionTemplate;  
   }  
		
	public static final String SQLNAME_SEPARATOR = ".";

	/**
	 * @fields sqlNamespace SqlMapping�����ռ� 
	 */
	private String sqlNamespace = getDefaultSqlNamespace();

	/**
	 * ��ȡ�������͵�ʵ�������ȫ��
	 * @author LiuJunGuang
	 * @return
	 * @date 2014��3��3������6:17:46
	 */
	protected String getDefaultSqlNamespace() {
		Class<?> genericClass = BeanUtils.getGenericClass(this.getClass());
		return genericClass == null ? null : genericClass.getName();
	}

	/**
	 * ��ȡSqlMapping�����ռ� 
	 * @author LiuJunGuang
	 * @return SqlMapping�����ռ� 
	 * @date 2014��3��4������12:33:15
	 */
	public String getSqlNamespace() {
		return sqlNamespace;
	}

	/**
	 * ����SqlMapping�����ռ䡣 �Ըı�Ĭ�ϵ�SqlMapping�����ռ䣬
	 * �������ô˷�������ı�SqlMapping�����ռ䡣 
	 * @author LiuJunGuang
	 * @param sqlNamespace SqlMapping�����ռ� 
	 * @date 2014��3��4������12:33:17
	 */
	public void setSqlNamespace(String sqlNamespace) {
		this.sqlNamespace = sqlNamespace;
	}

	/**
	 * ��SqlMapping�����ռ��������SqlMapping�������һ��
	 * @param sqlName SqlMapping�� 
	 * @return �����SqlMapping�����ռ�������SqlMapping�� 
	 */
	protected String getSqlName(String sqlName) {
		return sqlNamespace + SQLNAME_SEPARATOR + sqlName;
	}

	/**
	 * ��������ֵ�� Ĭ��ʹ��{@link UUIDUtils#create()}����
	 * �����Ҫ������������Ҫ��������д�˷���������Ҫ�ķ�ʽ��������ֵ�� 
	 * @param entity Ҫ�־û��Ķ��� 
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
			throw new DaoException(String.format("��ѯһ����¼������䣺%s", getSqlName(ConstantsSQL.SQL_SELECT)), e);
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
			throw new DaoException(String.format("����ID��ѯ���������䣺%s", getSqlName(ConstantsSQL.SQL_SELECT_BY_ID)), e);
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
			throw new DaoException(String.format("��ѯ�����б������䣺%s", getSqlName(ConstantsSQL.SQL_SELECT)), e);
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
			throw new DaoException(String.format("��ѯ���ж����б������䣺%s", getSqlName(ConstantsSQL.SQL_SELECT_ALL)), e);
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
			throw new DaoException(String.format("��ѯ����Mapʱ������䣺%s", getSqlName(ConstantsSQL.SQL_SELECT)), e);
		}
	}

	/**
	 * ���÷�ҳ
	 * @param pageInfo ��ҳ��Ϣ
	 * @return SQL��ҳ��������
	 */


	/**
	 * ��ȡ��ҳ��ѯ����
	 * @param query ��ѯ����
	 * @param pageable ��ҳ����
	 * @return Map ��ѯ����
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
			throw new DaoException(String.format("���ݷ�ҳ�����ѯ�б�������:%s", getSqlName(ConstantsSQL.SQL_SELECT)), e);
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
			throw new DaoException(String.format("��ѯ��������������䣺%s", getSqlName(ConstantsSQL.SQL_SELECT_COUNT)), e);
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
			throw new DaoException(String.format("��ѯ��������������䣺%s", getSqlName(ConstantsSQL.SQL_SELECT_COUNT)), e);
		}
	}

	/* (non-Javadoc)
	 * @see com.viathink.core.dao.BaseDao#insert(java.io.Serializable)
	 */
	@Override
	public void insert(T entity) {
		Assert.notNull(entity);
		try {
			if (StringUtils.isBlank(entity.getId()))
				entity.setId(generateId());
			sqlSessionTemplate.insert(getSqlName(ConstantsSQL.SQL_INSERT), entity);
		} catch (Exception e) {
			throw new DaoException(String.format("��Ӷ��������䣺%s", getSqlName(ConstantsSQL.SQL_INSERT)), e);
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
			throw new DaoException(String.format("ɾ�����������䣺%s", getSqlName(ConstantsSQL.SQL_DELETE)), e);
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
			throw new DaoException(String.format("����IDɾ�����������䣺%s", getSqlName(ConstantsSQL.SQL_DELETE_BY_ID)), e);
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
			throw new DaoException(String.format("ɾ�����ж��������䣺%s", getSqlName(ConstantsSQL.SQL_DELETE)), e);
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
			throw new DaoException(String.format("����ID���¶��������䣺%s", getSqlName(ConstantsSQL.SQL_UPDATE_BY_ID)), e);
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
			throw new DaoException(String.format("����ID���¶���ĳЩ���Գ�����䣺%s", getSqlName(ConstantsSQL.SQL_UPDATE_BY_ID_SELECTIVE)),
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