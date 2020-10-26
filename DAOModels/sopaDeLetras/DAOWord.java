package sopaDeLetras;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOWord implements Actions {
	private Database connection;
	private word result;
	
	public DAOWord() {
		this.connection = new Database();
	}
	
	@Override
	public Boolean insert(Object obj) {
		// TODO Auto-generated method stub
		Connection conn = this.connection.connect();
		PreparedStatement pst;
		
		this.result = (sopaDeLetras.word) obj;		
		String sql = "insert into words(value) values(?)";
		int count = 0;
		
		try {
			pst = conn.prepareStatement(sql);
	        pst.setString(1, this.result.getValue());
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
		List<word> words = new ArrayList<>();
		
		String sql = "select * from words";		
		try {
			pst = conn.prepareStatement(sql);	       
			 rs = pst.executeQuery();
	            while(rs.next()){
	            	words.add(
            			new word(
        					rs.getInt("id"),
        					rs.getString("value")
                        ));
	            }
	            conn.close();
	        } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        		
        return words;
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
