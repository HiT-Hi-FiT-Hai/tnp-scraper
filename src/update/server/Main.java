package update.server;

public class Main {
    public static void main( String[] args ) {
	while( true ) {
	    HomePage hp = new HomePage();
	    UpdateLast5 ul5 = new UpdateLast5();
	    ul5.setNumber( hp.getNumber() );
	    Thread th = new Thread( ul5 );
	    th.start();
            try {
		Thread.sleep( 5 * 60 * 1000 );  // 5 minute intervals
	    }
	    catch (Exception ex) {
		ex.printStackTrace();
	    }
	}
    }
}
