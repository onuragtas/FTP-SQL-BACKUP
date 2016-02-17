import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SQLiteJDBC {
	static Connection c = null;
	static Statement stmt = null;
	static String tableftp = "ftp";
	String fID = "id";
	String fTitle = "title";
	String fDomain = "domain";
	String fPort = "port";
	String fUsername = "username";
	String fPassword = "password";
	String tableSql = "sql";
	public SQLiteJDBC() {
		
		
		
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Opened database successfully");

			stmt = c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS "+tableftp+" " + "("+fID+" ID  auto increment,"
					+ " "+fTitle+"           TEXT    NOT NULL, " + " "+fDomain+"            TEXT     NOT NULL, "
					+ " "+fPort+" TEXT NOT NULL, "+" "+fUsername+" TEXT NOT NULL,"
					+ " "+fPassword+" TEXT NOT NULL"
					+ ")";
			stmt.executeUpdate(sql);
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
	}
	
	public ArrayList<ftp> getFTPs(){
		ArrayList<ftp> ftps = new ArrayList<ftp>();
		try{
		stmt = c.createStatement();
	      ResultSet rs = stmt.executeQuery( "SELECT * FROM "+tableftp );
	      ftp f;
	      int i = 0;
	      while ( rs.next() ) {
	    	  
	    	  f = new ftp();
	    	  
	         int id = rs.getInt("id");
	         String  title = rs.getString("title");
	         String  domain = rs.getString("domain");
	         String  port = rs.getString("port");
	         String  username = rs.getString("username");
	         String  password = rs.getString("password");
	         
	         f.setId(id);
	         f.setTitle(title);
	         f.setDomain(domain);
	         f.setPort(port);
	         f.setUsername(username);
	         f.setPassword(password);
	         ftps.add(f);
	         System.out.println(i);
	         i++;
	      }
	      rs.close();
	      stmt.close();
	      c.close();
		} catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		
		return ftps;
	}
	
	public void insertftp(String title, String domain, String port, String username, String password) throws SQLException{
		stmt = c.createStatement();
	      String sql = "INSERT INTO "+tableftp+" (title, domain,port,username,password) " +
	                   "VALUES ('"+title+"', '"+domain+"', '"+port+"','"+username+"','"+password+"');"; 
	      stmt.executeUpdate(sql);
	      stmt.close();
	      c.commit();
	      c.close();
	}
}