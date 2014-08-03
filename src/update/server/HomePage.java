package update.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

public class HomePage {
    private String url="http://tp.iitkgp.ernet.in/notice/";
    private String USER_AGENT = "Mozilla/5.0";
    
    public int getNumber() {
	int ret = -1;
	try {
	    URL obj = new URL(url);
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	    con.setRequestMethod("GET");
	    con.setRequestProperty("User-Agent", USER_AGENT);
	    int responseCode = con.getResponseCode();
	    if (responseCode == 200) {
		BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()) );
		String html;
		StringBuffer response = new StringBuffer();
		while( (html = in.readLine()) != null )
		    response.append(html);
		in.close();
		html = response.toString();
		String[] temp = html.split( Pattern.quote("notice.php?sr_no=") );
		if( temp.length > 1 ) {
		    String temp1 = temp[1];
		    temp1 = temp1.substring( 0, temp1.indexOf("\"") );
		    ret = Integer.parseInt(temp1);
		    System.out.println("Latest: " + temp1);
		}
	    }
	}
	catch(Exception ex) {
	}
	return ret;
    }
}
