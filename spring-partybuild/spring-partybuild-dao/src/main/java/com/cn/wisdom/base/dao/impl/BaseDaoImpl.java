package com.cn.wisdom.base.dao.impl;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cn.wisdom.base.dao.BaseDao;
import com.cn.wisdom.base.model.SqlEntry;
import com.cn.wisdom.utils.EventType;
import com.cn.wisdom.utils.PageHelper;
import com.cn.wisdom.utils.TableInfoAnnotation;

import io.netty.util.internal.StringUtil;
@Component
public class BaseDaoImpl<T, K> implements BaseDao<T, java.lang.String> {

	@Resource
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean saveOrUpdate(T t, String eventType) {
		SqlEntry entry = null;
		try {
			if(EventType.EVENT_ADD.equals(eventType)) {
				entry = getSaveSql(t);
			}
			if(EventType.EVENT_UPDATE.equals(eventType)) {
				entry = getUpdateSql(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		int count = jdbcTemplate.update(entry.getSql(), entry.getObj());
		return count==1? true:false;
	}

	@Override
	public boolean delete(T t) {
		
		
		  SqlEntry entry = null; try { entry = getDeleteSql(t); } catch (Exception e) {
		  e.printStackTrace(); } int count = jdbcTemplate.update(entry.getSql(),
		  entry.getObj());
		  
		  return count==1? true:false;
		 
	}
	
	public int deleteBatch(final List<T> list, ConcurrentHashMap<String,Object> map) {
		
		StringBuffer buffer = new StringBuffer("delete from ");
		
		Class<? extends Object> clazz = list.get(0).getClass();
		
		buffer.append(clazz.getAnnotation(TableInfoAnnotation.class).tableName() + " where 1 = 1 ");
		
		if(null!= map&&!map.isEmpty()) {
			
			for (Map.Entry<String, Object>  entry  : map.entrySet()) {
				
				buffer.append(" and "+entry.getKey()+"='"+entry.getValue().toString()+"'");
			}
		}
		
		int count = jdbcTemplate.update(buffer.toString());
		
		return count;
	}
	
	public int deleteBatchByPrimary(Class<T> clazz, List<String> list) {
		
			int sum = 0;
		
			StringBuffer buffer = new StringBuffer("delete from ");
			
			buffer.append(clazz.getAnnotation(TableInfoAnnotation.class).tableName() + " where "+clazz.getAnnotation(TableInfoAnnotation.class).primaryKey()+"=?");
			
			Object[] array = list.toArray();
			
			int[] delCountArray = jdbcTemplate.batchUpdate(buffer.toString(), new BatchPreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps, int i) throws SQLException {
					ps.setString(1, (String) array[0]);
				}
				
				@Override
				public int getBatchSize() {
					
					return array.length;
				}
			});
			
			for(int a: delCountArray) {
				
				sum+=a;
			}
			
			return sum;
	}
	
	@Override
	public T get(Class<T> clazz, String key) throws SQLException {
		StringBuffer buffer = new StringBuffer("select * from "+clazz.getAnnotation(TableInfoAnnotation.class).tableName()+" where id = '"+key+"'");
		List<T> dataList = jdbcTemplate.query(buffer.toString(), new Object[]{}, new BeanPropertyRowMapper<T>(clazz));
		if(dataList.isEmpty()) {return null;}
		return dataList.get(0);
	}

	@Override
	public List<T> getList(Class<T> clazz, ConcurrentHashMap<String,Object> map) {
		StringBuffer buffer = new StringBuffer("select * from "+clazz.getAnnotation(TableInfoAnnotation.class).tableName()+" where 1 = 1 ");
		if(null!= map&&!map.isEmpty()) {
			for (Map.Entry<String, Object>  entry  : map.entrySet()) {
				buffer.append(" and "+entry.getKey()+"='"+entry.getValue().toString()+"'");
			}
		}
		List<T> dataList = jdbcTemplate.query(buffer.toString(), new Object[]{}, new BeanPropertyRowMapper<T>(clazz));
		if(dataList.isEmpty()) {return null;}
		return dataList;
	}

	private  synchronized  SqlEntry  getSaveSql(T t) throws InstantiationException, IllegalAccessException {
		
		SqlEntry entry = new SqlEntry();
		
		StringBuffer buffer = new StringBuffer("insert into "); 
		
		Class<? extends Object> clazz = t.getClass();
		
		buffer.append(""+clazz.getAnnotation(TableInfoAnnotation.class).tableName()+" values(");
		
		Field[] fields = clazz.getDeclaredFields();
		
		List<Object> listField = new ArrayList<>();
		
		int i = 0;
		
		for (Field field : fields) {
			
			i++;
			
			field.setAccessible(true);
			
			if(field.getName().indexOf("serialVersionUID")!=-1) {
				
				continue;
			}
			
			System.out.println(field.getName()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<"+field.get(t));
			
			Object val = new Object();
			
			val = field.get(t) == "" ? null : field.get(t);
			
			if(fields.length==i) { buffer.append("?"); }else { buffer.append("?,"); }
			
			listField.add(val);
		}
		buffer.append(")");
		
		entry.setSql(buffer.toString());
		
		entry.setObj(listField.toArray(new Object[listField.size()]));
		
		return entry;
	}
	
	private  synchronized  SqlEntry  getUpdateSql(T t) throws InstantiationException, IllegalAccessException {
		
		SqlEntry entry = new SqlEntry();
		
		StringBuffer buffer = new StringBuffer("update "); 
		
		Class<? extends Object> clazz = t.getClass();
		
		buffer.append(""+clazz.getAnnotation(TableInfoAnnotation.class).tableName()+" set ");
		
		Field[] fields = clazz.getDeclaredFields();
		
		List<Object> listField = new ArrayList<>();
		
		int i = 0; Object primary = null;
		
		for (Field field : fields) {
			
			i++;
			
			field.setAccessible(true);
			
			if(field.getName().indexOf("serialVersionUID")!=-1) {
				
				continue;
			}
			
			if(field.getName().equals(clazz.getAnnotation(TableInfoAnnotation.class).primaryKey())) {
				
				primary = field.get(t);
				
				continue;
			}
			
			System.out.println(field.getName()+"<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
			
			Object val = new Object();
			
			val = field.get(t);
			
			if(fields.length==i) { buffer.append(field.getName()+"=?"); }else { buffer.append(field.getName()+" = ?,"); }
			
			listField.add(val);
		}
		buffer.append(" where ");
		
		buffer.append(""+clazz.getAnnotation(TableInfoAnnotation.class).primaryKey()+" = ?");
		
		entry.setSql(buffer.toString());
		
		listField.add(primary);
		
		entry.setObj(listField.toArray(new Object[listField.size()]));
		
		return entry;
	}
	
	private  synchronized  SqlEntry  getDeleteSql(T t) throws InstantiationException, IllegalAccessException {
		
		SqlEntry entry = new SqlEntry();
		
		StringBuffer buffer = new StringBuffer("delete from "); 
		
		Class<? extends Object> clazz = t.getClass();
		
		buffer.append(""+clazz.getAnnotation(TableInfoAnnotation.class).tableName()+" where ").append(clazz.getAnnotation(TableInfoAnnotation.class).primaryKey()+ "=?" );
		
		Field[] fields = clazz.getDeclaredFields();
		
		Object primary = null;  List<Object> listField = new ArrayList<>();
		
		for (Field field : fields) {
			
			field.setAccessible(true);
			
			if(field.getName().equals(clazz.getAnnotation(TableInfoAnnotation.class).primaryKey())) {
				
				primary = field.get(t);   listField.add(primary);  break;
			}
		}
		entry.setSql(buffer.toString());
		
		entry.setObj(listField.toArray(new Object[listField.size()]));
		
		return entry;
	}

	@Override
	@SuppressWarnings("static-access")
	public PageHelper<T> getListObjectPage(Class<T> clazz, ConcurrentHashMap<String, Object> map, PageHelper<T> pageModel ) {
		
		StringBuffer buffer = new StringBuffer("select count(*) from "+clazz.getAnnotation(TableInfoAnnotation.class).tableName()+" where 1 = 1 ");
		
		if(null!= map&&!map.isEmpty()) {
			
			for (Map.Entry<String, Object>  entry  : map.entrySet()) {
				
				buffer.append(" and "+entry.getKey()+"='"+entry.getValue().toString()+"'");
			}
		}
		
		int count = jdbcTemplate.queryForObject(buffer.toString(),new Object[]{}, Integer.class);
		
		if(0==count) {
			
			return pageModel;
		}
		
		pageModel.setTotalPage(count%pageModel.getPageSize()==0?count/pageModel.getPageSize():count/pageModel.getPageSize()+1);//页数
		
		pageModel.setTotalCount(count);
		
		StringBuffer sql = new StringBuffer("select * from "+clazz.getAnnotation(TableInfoAnnotation.class).tableName()+" where 1 = 1 ");
		
		if(null!= map&&!map.isEmpty()) {
			
			for (Map.Entry<String, Object>  entry  : map.entrySet()) {
				
				sql.append(" and "+entry.getKey()+"='"+entry.getValue().toString()+"'");
			}
		}
		
		if(StringUtil.isNullOrEmpty(pageModel.getOrderBy())) {
			
			sql.append(" order by ").append(clazz.getAnnotation(TableInfoAnnotation.class).primaryKey()).append(" "+pageModel.DESC);
		}else {
			
			sql.append(" order by ").append(pageModel.getOrderBy()).append(" "+pageModel.DESC);
		}
		
		sql.append(" limit ").append((pageModel.getPageNo()-1)*pageModel.getPageSize()+" , "+pageModel.getPageSize());
		
		List<T> dataList = jdbcTemplate.query(sql.toString(), new Object[]{}, new BeanPropertyRowMapper<T>(clazz));
		
		pageModel.setResult(dataList);
		
		return pageModel;
	}
	
	@Override
	@SuppressWarnings({ "static-access", "unchecked" })
	public PageHelper<T> getListMapPage(StringBuffer sql, PageHelper<T> pageModel ) {
		
		StringBuffer buffer = new StringBuffer("select count(*) from ("+sql+") c");
		
		int count = jdbcTemplate.queryForObject(buffer.toString(), new Object[]{}, Integer.class);
		
		if(0==count) {
			
			return pageModel;
		}
		
		pageModel.setTotalPage(count%pageModel.getPageSize()==0?count/pageModel.getPageSize():count/pageModel.getPageSize()+1);//页数
		
		pageModel.setTotalCount(count);
		
		StringBuffer executeSql = new StringBuffer( "select * from ("+sql+") c " );
		
		if(!StringUtil.isNullOrEmpty(pageModel.getOrderBy())) {
			
			executeSql.append("order by "+pageModel.getOrderBy()+" ").append(pageModel.DESC);
		}
		
		executeSql.append(" limit ").append((pageModel.getPageNo()-1)*pageModel.getPageSize()+" , "+pageModel.getPageSize());;
		
		List<T> dataList = (List<T>) jdbcTemplate.queryForList(executeSql.toString());
		
		pageModel.setResult(dataList);
		
		return pageModel;
	}

}
