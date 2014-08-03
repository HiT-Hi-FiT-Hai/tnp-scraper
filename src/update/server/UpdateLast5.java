package update.server;

import java.util.ArrayList;
import java.sql.*;

public class UpdateLast5 implements Runnable {
    private int num;
    private String url = "jdbc:mysql://localhost/latest?autoReconnect=true";
    private String username = "tnp";
    private String password = "tnp@hhfh";
    private String mysqlAutoreconnect = "autoReconnect";	
    private Connection conn = null;
    public void setNumber( int num ) {
	this.num = num;
    }
    @Override public void run() {
	try {
	    Class.forName( "com.mysql.jdbc.Driver" );
	    conn = DriverManager.getConnection( this.url, this.username, this.password );
	    int x = this.num - 5;
	    while( x <= num ) {
		Notice not = new Notice();
		ArrayList<String> data = not.getData( x );
		if( Integer.parseInt(data.get(0)) != -1 ) {
		    ThreadNetwork tn = new ThreadNetwork( data, conn );
		    Thread th = new Thread( tn );
		    th.start();
		}
		x++;
	    }
	}
	catch( SQLException s ) {
	    s.printStackTrace();
	}
	catch( Exception ex ) { 
	    ex.printStackTrace();
	}
	finally {
	    try {
		conn.close();
	    }
	    catch( SQLException e ) {
		e.printStackTrace();
	    }
	}
    }
}
