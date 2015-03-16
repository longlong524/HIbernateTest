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
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.epiclouds.dao.bean.Post;
import org.epiclouds.dao.bean.User;
import org.epiclouds.utils.LogRecord;
import org.springframework.stereotype.Repository;

/**
 * @author Administrator
 *
 */
@Repository(value="postdao")
public class PostDaoImp{
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}


	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}


	public PostDaoImp(){}
	
	

	public List<Post> getAllPostByDate() {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con=dataSource.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from post order by time desc");
			ResultSet rs=ps.executeQuery();
			List<Post> ll=new LinkedList<Post>();
			while(rs.next()){
				ll.add( new Post(rs.getInt("id"),new User(rs.getInt("userid"),
						null,null), 
						rs.getString("content")
						,rs.getDate("time"),rs.getInt("good"))
				);
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
	

	public Post getPost(int postid) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con=dataSource.getConnection();
			PreparedStatement ps=con.prepareStatement("select * from post where id=?");
			ps.setInt(1, postid);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()){
				return new Post(rs.getInt("id"),new User(rs.getInt("userid"),
						null,null), 
						rs.getString("content")
						,rs.getDate("time"),rs.getInt("good"))
				;
			}
			return null;
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

	public boolean addGood(int postid) {
		// TODO Auto-generated method stub
		Connection con=null;
		try {
			con=dataSource.getConnection();
			PreparedStatement ps=con.prepareStatement("update"
					+ " post set good=good+1 where id=?");
			ps.setInt(1, postid);
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
