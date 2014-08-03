package update.server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Notice {
    private String url = "http://tp.iitkgp.ernet.in/notice/notice.php?sr_no=";
    private String USER_AGENT = "Mozilla/5.0";
    public ArrayList<String> getData( int num ) {
	ArrayList<String> ret = new ArrayList<String>();
	ret.add("-1");
	ret.add("");
	ret.add("");
	ret.add("");
	ret.add("");
	try {
	    URL obj = new URL( url + num );
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	    con.setRequestMethod( "GET" );
	    con.setRequestProperty( "User-Agent", USER_AGENT );
	    int responseCode = con.getResponseCode();
	    if( responseCode == 200 ) {
		BufferedReader in = new BufferedReader( new InputStreamReader(con.getInputStream()) );
		String html;
		StringBuffer response = new StringBuffer();
		while( (html = in.readLine()) != null )
		    response.append(html);
		in.close();
		html = response.toString();
		String str = html.substring(html.indexOf("<td><b>")+7, html.indexOf("</b></td><td align=right>"));
		if( str.length() > 0 ) {
		    ret.set( 0, num + "" );
		    str = str.replaceAll(Pattern.quote(""), "-");
		    ret.set( 1, str );
		    str = html.substring(html.indexOf("</b></td><td align=right>"));
		    str = str.substring( str.indexOf("<b>") + 3 );
		    str = str.substring( 0, str.indexOf("</b>") );
		    ret.set( 2, str );
		    str = html.substring( html.indexOf("colspan=2><div>")+15, html.indexOf("</div>") );
		    str = str.replaceAll( Pattern.quote(""), "-" );
		    ret.set( 3, str );
		    if( html.indexOf("<A HREF=") >= 0 ) {
			str = html.substring(html.indexOf("<A HREF=")+8);
			str = str.substring(0, str.indexOf(">"));
			str = "http://tp.iitkgp.ernet.in/notice/" + str;
		    }
		    else {
			str = "";
		    }
		    ret.set( 4, str );
		}
	    }
	}
	catch( Exception ex ) { }
	return ret;
    }
}
