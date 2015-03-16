/**
 * @author Administrator
 * @created 2015 2015年1月12日 下午7:46:46
 * @version 1.0
 */
package org.epiclouds.dao.imp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.epiclouds.dao.bean.Comment;
import org.epiclouds.dao.bean.Post;
import org.epiclouds.dao.bean.User;
import org.epiclouds.utils.LogRecord;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 *
 */
@Repository(value="commentdao")
public class CommentDaoImp{
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	public CommentDaoImp(){}
	
	

	public List<Comment> getCommentByPostid(int postId) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con=dataSource.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from comment where postid=?");
			ps.setInt(1, postId);
			ResultSet rs=ps.executeQuery();
			List<Comment> ll=new LinkedList<Comment>();
			while(rs.next()){
				ll.add(new Comment(new Post(postId, null, null, null, 0),
						new User(rs.getInt("userid"), null, null), rs.getString("content"), 
						rs.getDate("time")));
			}
			return ll;
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



	public boolean addComment(int postid,int userid,String content) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con=dataSource.getConnection();
			PreparedStatement ps=con.prepareStatement("insert into comment(content,time,"
					+ "postid,userid) values(?,?,?,?)");
			ps.setString(1, content);
			ps.setDate(2, new Date(new java.util.Date().getTime()));
			ps.setInt(3, postid);
			ps.setInt(4, userid);
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
