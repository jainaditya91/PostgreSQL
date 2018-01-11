package jain;
import java.sql.*;

public class abc {
	public static void main(String args[]){
	try{
	Class.forName("org.postgresql.Driver");
	Connection con = DriverManager.getConnection
	("jdbc:postgresql://cop5725-postgresql.cs.fiu.edu:5432/fall16_ajain018", "fall16_ajain018", "5970907");
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery ("select fname from dbms.patients where pt_id in (1,2,3,4,5,6);");
	while( rs.next() )
	System.out.println(rs.getString(1));
	rs.close();
	stmt.close();
	con.close();
	} catch(Exception e){
	System.err.println(e); 
	}
	 }
}