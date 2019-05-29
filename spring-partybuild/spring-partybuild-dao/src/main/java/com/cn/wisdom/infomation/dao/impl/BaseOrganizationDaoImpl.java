package com.cn.wisdom.infomation.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.cn.wisdom.base.dao.impl.BaseDaoImpl;
import com.cn.wisdom.base.model.BaseOrganization;
import com.cn.wisdom.infomation.dao.BaseOrganizationDao;
import com.mysql.cj.MysqlType;
@Component
public class BaseOrganizationDaoImpl extends BaseDaoImpl<BaseOrganization,java.lang.String> implements BaseOrganizationDao{

	
	
	@Resource private JdbcTemplate jdbcTemplate;
	 
	
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int delBatchByPrimary(String id) {
		
		
		int count = (int) jdbcTemplate.execute(new CallableStatementCreator() {
			
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				String storedProc = "{call DELETE_ORGTREE_NODE (?,?)}";// 调用的存储过程
		           CallableStatement cs = con.prepareCall(storedProc); 
		           cs.setString(1, id);// 设置输入参数的值 
		           cs.registerOutParameter(2,MysqlType.INT);// 注册输出参数的类型 
		           return cs; 
			}
		}, new CallableStatementCallback() {

			@Override
			public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.execute(); 
		        return cs.getInt(2);// 获取输出参数的值 
			}
			
		});
		
		return count;
	}

}
