package com.ycbike.core.service;

import java.util.List;
import java.util.Map;

import com.ycbike.core.domain.Identifiable;
 
public interface BaseService<T extends Identifiable>  {

	/**
	 * ��ѯһ������������صĽ������һ�����󽫻��׳�TooManyResultsException
	 * @param obj ��ѯ���󣬲���Ϊnull
	 * @return Mapper��ӳ��Ķ��󣬼̳��� T����һ����Vo����
	 * @date 2014��3��3������1:34:27
	 */
	public <V extends T> V queryOne(T query);

	/**
	 * ͨ��Id��ѯһ���������idΪnull����׳�IllegalArgumentException�쳣
	 * @param id ����������Ϊnull
	 * @return  ����������δ�ҵ�����null
	 * @date 2014��3��3������5:58:07
	 */
	public <V extends T> V queryById(String id);

	/**
	 * ��ѯ�����б�
	 * @param query ��ѯ���������δnull���ѯ���У��൱�ڵ��÷���{@link com.ycbike.core.dao.BaseDao.selectAll }
	 * @return ��������б�
	 * @date 2014��3��3������5:43:33
	 */
	public <V extends T> List<V> queryList(T query);

	/**
	 * ��ѯ���м�¼�б�
	 * @return List ����б�
	 * @date 2014��3��3������5:35:01
	 */
	public <V extends T> List<V> queryAll();

	/**
	 * ���ݽ�����е�һ����Ϊkey���������ת����Map
	 * @param <K> ����Map��key����
	 * @param <V> ����Map��Value����
	 * @param query ��ѯ����,���δnull���ѯ���ж���
	 * @param mapKey ���ؽ��List�С�mapKey������ֵ��ΪKey (The property to use as key for each value in the list.)
	 * @return Map ����key����ֵ��Map����
	 */
	public <K, V extends T> Map<K, V> queryMap(T query, String mapKey);

	/**
	 * ��ѯ�ܼ�¼��
	 * @return long ��¼����
	 * @date 2014��3��3������5:35:36
	 */
	public Long queryCount();

	/**
	 * ��ѯ��¼��
	 * @param query ��ѯ�������Ϊnull�����ѯ��������
	 * @return long ��¼����
	 * @date 2014��3��3������5:45:40
	 */
	public Long queryCount(T query);

	/**
	 * ��Ӷ���,���Ҫ��ӵĶ���û������Id����IdΪ���ַ��������ǿո����������֮ǰ����� generateId()��������Id
	 * @param entity Ҫʵ������ʵ�壬����Ϊnull
	 * @return ��Ӱ��Ľ����
	 * @date 2014��3��3������5:47:15
	 */
	public void insert(T entity);

	/**
	 * ɾ������
	 * @param entity Ҫɾ����ʵ����󣬲���Ϊnull
	 * @return int ��Ӱ������
	 * @date 2014��3��3������5:47:47
	 */
	public int delete(T query);

	/**
	 * ����Idɾ������
	 * @param id  Ҫɾ����ID������Ϊnull
	 * @return int ��Ӱ������
	 * @date 2014��3��3������5:59:59
	 */
	public int deleteById(String id);

	/**
	 * ɾ������
	 * @return int ��Ӱ������
	 * @date 2014��3��3������5:49:08
	 */
	public int deleteAll();

	/**
	 * ���¶��󣬶����������ID
	 * @param entity ʵ���Id����Ϊnull
	 * @return int ��Ӱ������
	 * @date 2014��3��3������5:48:05
	 */
	public int updateById(T entity);

	/**
	 * ���¶����������õ��ֶΣ�δ���õ��ֶβ�����
	 * @param entity Ҫ���µ�ʵ����󣬲���Ϊnull����ID���벻Ϊnull
	 * @return int ��Ӱ������
	 * @date 2014��3��3������5:48:05
	 */
	public int updateByIdSelective(T entity);

	/**
	 * ����id������ɾ����¼�����������б�Ϊnull��Ϊ���б���ֱ�ӷ���
	 * @param idList ����ɾ��ID�б�
	 * @date 2014��3��3������6:09:50
	 */
	public void deleteByIdInBatch(List<String> idList);

	/**
	 * �������룬���Ϊ���б���ֱ�ӷ���
	 * @param entityList ��Ҫ���������ʵ������б�
	 * @date 2014��3��3������10:37:05
	 */
	public void insertInBatch(List<T> entityList);

	/**
	 * �������£��ķ�������ʵ��ID���������õ��ֶΣ�δ���õ��ֶβ�����
	 * @param entityList �������µ�ʵ������б�
	 */
	public void updateInBatch(List<T> entityList);

	/**
	 *<pre>��ѯ�����б�ע�⣺�ڸ�����null�ķ�ҳ����ʱ�÷����Զ����÷�ҳ�ܼ�¼��,���queryͬʱΪnull���ѯ����</pre>
	 * @param query ��ѯ���� 
	 * @param pageSize ��ҳ��
	 * @param pageNow  ��ǰ��ҳ
	 * @return List ���ݷ�ҳ�����ѯ�ķ�ҳ����б�
	 * @date 2014��3��3������5:43:33
	 */
	public <V extends T> List<V> queryList(T query,int pageSize, int pageNow);


	/**
	 * ���ݽ�����е�һ����Ϊkey���������ת����Map
	 * @param <K> ����Map��key����
	 * @param <V> ����Map��Value����
	 * @param query ��ѯ����
	 * @param mapKey ���ؽ��List�С�mapKey������ֵ��ΪKey (The property to use as key for each value in the list.)
	 * @param pageSize ��ҳ��
	 * @param pageNow  ��ǰ��ҳ
	 * @return Map containing key pair data. 
	 */
	public <K, V extends T> Map<K, V> queryMap(T query, String mapKey,int pageSize, int pageNow);
	
	
	
}
