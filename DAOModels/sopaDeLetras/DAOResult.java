package sopaDeLetras;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sopaDeLetras.result;

public class DAOResult implements Actions {
	private Database connection;
	private result result;
	public DAOResult() {
		this.connection = new Database();
	}

	@Override
	public Boolean insert(Object obj) {
		// TODO Auto-generated method stub
		Connection conn = this.connection.connect();
		PreparedStatement pst;
		
		this.result = (sopaDeLetras.result) obj;		
		String sql = "insert into results(ldap_id,words,table,start_date,end_date) values(?,?,?,?,?)";
		int count = 0;
		
		try {
			pst = conn.prepareStatement(sql);
	        pst.setInt(1, this.result.getLdap_id());
	        pst.setString(2, this.result.getWord());
	        pst.setString(3, this.result.getTable());
	        pst.setDate(4, this.result.getStart_date());
	        pst.setDate(5, this.result.getEnd_date());
	        pst.executeUpdate();
	        conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        		
        return count>0;
	}
	
	@Override
	public List<?> all() {
		// TODO Auto-generated method stub
		Connection conn = this.connection.connect();
		PreparedStatement pst;
		ResultSet rs;
		List<result> result = new ArrayList<>();
		
		String sql = "select * from results";		
		try {
			pst = conn.prepareStatement(sql);	       
			 rs = pst.executeQuery();
	            while(rs.next()){
	            	result.add(
	            			new result(
	            				rs.getInt("id"), 
		            			rs.getInt("ldap_id"),
		                        rs.getString("word"),
		                        rs.getString("table"), 
		                        rs.getDate("start_date"),
		                        rs.getDate("end_date")
	                        ));
	            }
	            conn.close();
	        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        		
        return result;
	}
	
	
	//not implemented for this dao model
	@Override
	public Boolean delete(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<?> select(String column, String value) {
		// TODO Auto-generated method stub
		return null;
	}



}
