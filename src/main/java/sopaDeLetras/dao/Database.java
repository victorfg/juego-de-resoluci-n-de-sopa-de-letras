package sopaDeLetras.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	//private String driver;
	private String url;
	private String user;
	private String password;
    public Database() {
        //this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/p7?useSSL=false"; ///tutorial
        this.user = "root";
        this.password= "123"; //""
       
    }
    public Connection connect(){   
		 Connection conn = null;
    	 try {
		        conn = DriverManager.getConnection(
		        		this.url,
		        		this.user,
		        		this.password            
		        );
		        
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}  
    	 return conn;
    }
}
