import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.itextpdf.text.log.SysoLogger;



public class Main {
	/*public static void main(String[] args) {
	String password = "123456";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		System.out.println(passwordEncoder.encode(password));
	    
	}*/
	

	public static void main(String[] args) {
		//System.out.println(new Transaction().getTransaction());
		
		String s= "/home/protechjava/apache-tomcat-7.0.63Copy/webapps/tmpFiles/monthlybill-15544894275061.pdf";
		String ss =s.substring(42,s.length());
		//String ss =s.substring(51,s.length());
		System.out.println(ss);
		try {
            Date d= new Date();
            System.out.println(d.getMonth()+" - "+(d.getYear()+1900));
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://10.138.1.48/bpconnect_prod","bpconnect_prod"," ATtaJM2dhMiVk$%2HDAyxO95#gu!9*eb");
			
			System.out.println("Connection:-"+connection);
			Statement statement = null;
			statement = connection.createStatement();
			//int rs = statement.executeUpdate("update measurement set entry_type = 0" + 
				//	" where entry_type=null and id < 200");
		    //  rs.last();
			/*while (rs.next()) {
		     // System.out.println("rows before batch execution= "+ rs.getString("text"));
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
