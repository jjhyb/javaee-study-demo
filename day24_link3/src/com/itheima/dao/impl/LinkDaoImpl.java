package com.itheima.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import com.itheima.dao.LinkDao;
import com.itheima.domain.Bean;
import com.itheima.domain.City;
import com.itheima.domain.District;
import com.itheima.domain.Province;
import com.itheima.utils.ManagerThreadLocal;

public class LinkDaoImpl implements LinkDao {

	@Override
	public List<Province> searchPro() throws SQLException {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from province", new BeanListHandler<>(Province.class));
	}

	@Override
	public List<City> searchCity(String pid) throws SQLException  {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from city where pid=?", new BeanListHandler<>(City.class),pid);
	}

	@Override
	public List<District> searchDis(String cid) throws SQLException  {
		QueryRunner qr = new QueryRunner();
		return qr.query(ManagerThreadLocal.getConnection(),"select * from district where cid=?", new BeanListHandler<>(District.class),cid);
	}

	@Override
	public List<Object[]> search(String name) throws SQLException {
		QueryRunner qr = new QueryRunner();
		String sql = "SELECT p.pname ,c.cname,d.dname FROM province p,city c,district d WHERE p.id=c.pid AND c.id=d.cid AND d.dname like ?"; 
		List<Object[]> list = qr.query(ManagerThreadLocal.getConnection(), sql, new ArrayListHandler(), "%"+name+"%");
		/*if(list == null){
			sql = "SELECT p.pname ,c.cname,d.dname FROM province p,city c,district d WHERE p.id=c.pid AND c.id=d.cid AND c.cname like ?"; 
			list = qr.query(ManagerThreadLocal.getConnection(), sql, new ArrayListHandler(), "%"+name+"%");
		}
		
		if(list == null){
			sql = "SELECT p.pname ,c.cname,d.dname FROM province p,city c,district d WHERE p.id=c.pid AND c.id=d.cid AND d.dname like ?"; 
			list = qr.query(ManagerThreadLocal.getConnection(), sql, new ArrayListHandler(), "%"+name+"%");
		}*/
		return list;
	}
	

}
