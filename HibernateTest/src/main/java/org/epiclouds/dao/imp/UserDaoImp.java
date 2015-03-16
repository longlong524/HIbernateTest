/**
 * @author Administrator
 * @created 2015 2015年1月12日 下午7:46:46
 * @version 1.0
 */
package org.epiclouds.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.epiclouds.dao.bean.User;
import org.epiclouds.utils.LogRecord;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 *
 */
 @Component(value="userdao")
public class UserDaoImp{
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	public UserDaoImp(){}
	
	

	public User getUserByID(int userid) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con=dataSource.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from user where userid=?");
			ps.setInt(1, userid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				return new User(userid, rs.getString("username")
						,rs.getString("password"));
			}else{
				return null;
			}
		} catch (SQLException e) {
			LogRecord.error(e);
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					
				}
			}
		}
		return null;
	}



	public User getUserByNameAndPass(String name, String pass) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con=dataSource.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from user where username=? "
					+ "and password=?");
			ps.setString(1, name);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				return new User(rs.getInt("id"), rs.getString("username")
						,rs.getString("password"));
			}else{
				return null;
			}
		} catch (SQLException e) {
			LogRecord.error(e);
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					
				}
			}
		}
		return null;
	}



	public User getUserByName(String username) {
		Connection con=null;
		try {
			con=dataSource.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from user where username=?");
			ps.setString(1, username);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				return new User(rs.getInt("id"), rs.getString("username")
						,rs.getString("password"));
			}else{
				return null;
			}
		} catch (SQLException e) {
			LogRecord.error(e);
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					
				}
			}
		}
		return null;
	}



	public boolean addUser(User ub) {
		if(ub==null) return false;
		Connection con=null;
		try {
			con=dataSource.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into user(username,password)"
					+ " values(?,?)");
			ps.setString(1, ub.getUsername());
			ps.setString(2, ub.getPassword());
			ps.execute();
			return true;
		} catch (SQLException e) {
			LogRecord.error(e);
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
					
				}
			}
		}
		return false;
	}



	public boolean removeUser(int userid) {
		Connection con=null;
		try {
			con=dataSource.getConnection();
			PreparedStatement ps=con.prepareStatement("delete from user where id=?");
			ps.setLong(1, userid);
			ps.execute();
			return true;
		} catch (SQLException e) {
			LogRecord.error(e);
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
				}
			}
		}
		return false;
	}



	public boolean updateUser(User ub) {
		if(ub==null) return false;
		Connection con=null;
		try {
			con=dataSource.getConnection();
			PreparedStatement ps=con.prepareStatement("update user set password=? where id=?");
			ps.setString(1, ub.getUsername());
			ps.setInt(2, ub.getId());
			ps.execute();
			return true;
		} catch (SQLException e) {
			LogRecord.error(e);
		}finally{
			if(con!=null){
				try {
					con.close();
				} catch (SQLException e) {
				
				}
			}
		}
		return false;
	}
}
