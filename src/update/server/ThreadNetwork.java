package update.server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ThreadNetwork implements Runnable {
    private PreparedStatement s = null;
    //    private String sqlQuery = "";
    private String sql = "INSERT IGNORE INTO notice (number, title, time, body, attlink) VALUES ( ?, ?, ?, ?, ? )";
    public ThreadNetwork( ArrayList<String> data, Connection conn ) {
	try {
	    s = conn.prepareStatement( sql );
	    s.setInt( 1, Integer.parseInt(data.get(0)) );
	    s.setString( 2, data.get(1) );
	    s.setString( 3, data.get(2) );
	    s.setString( 4, data.get(3) );
	    s.setString( 5, data.get(4) );
	    if( s.executeUpdate() > 0 )
		System.out.println( "INSERTED: " + data.get(0) );
	    else
		System.out.println( "IGNORING: " + data.get(0) );
	}
	catch(SQLException se) {
	    System.out.println("SQL excetion in ThreadNetwork");
	    se.printStackTrace();
	}
	catch(Exception e) {
	    System.out.println("Some exception in thread Netwrok");
	    e.printStackTrace();
	}
    }
    public void run() {
	return;
    }
}
