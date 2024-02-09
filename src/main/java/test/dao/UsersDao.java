package test.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import test.beans.Users;

public class UsersDao {
	
	JdbcTemplate t1;

	public void setT1(JdbcTemplate t1) {
		this.t1 = t1;
	}

	public int register(Users u1) {
		
		
		return t1.update("insert into users(uname,upass,ucpass) values('"+u1.getUname()+"','"+u1.getUpass()+"','"+u1.getUcpass()+"')");
		
	}

	public List<Users> checkdata(String uname, String upass)
	{
		
		return t1.query("select * from users where uname ='"+uname+"' and upass='"+upass+"'", new RowMapper<Users>() {

			@Override
			public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				
				Users u =new Users();
				u.setUid(rs.getInt(1));
				u.setUname(rs.getString(2));
				u.setUpass(rs.getString(3));
				u.setUcpass(rs.getString(4));
				
				
				return u;
			}
			
			
			
		});
		
	}

}
