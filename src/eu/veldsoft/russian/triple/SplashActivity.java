package eu.veldsoft.russian.triple;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

//TODO Put splash screen timeout.
public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		//TODO Put HTML as external resource.
		//TODO Load banner link in new browser.
		String html = "<html> <head> <script type='text/javascript'><!--//<![CDATA[ var m3_u = (location.protocol=='https:'?'https://adserver.veldsoft.eu/www/delivery/ajs.php':'http://adserver.veldsoft.eu/www/delivery/ajs.php'); var m3_r = Math.floor(Math.random()*99999999999); if (!document.MAX_used) document.MAX_used = ','; document.write (\"<scr\"+\"ipt type='text/javascript' src='\"+m3_u); document.write (\"?zoneid=3\"); document.write ('&amp;cb=' + m3_r); if (document.MAX_used != ',') document.write (\"&amp;exclude=\" + document.MAX_used); document.write (document.charset ? '&amp;charset='+document.charset : (document.characterSet ? '&amp;charset='+document.characterSet : '')); document.write (\"&amp;loc=\" + escape(window.location)); if (document.referrer) document.write (\"&amp;referer=\" + escape(document.referrer)); if (document.context) document.write (\"&context=\" + escape(document.context)); if (document.mmm_fo) document.write (\"&amp;mmm_fo=1\"); document.write (\"'><\\/scr\"+\"ipt>\"); //]]>--></script><noscript><a href='http://adserver.veldsoft.eu/www/delivery/ck.php?n=ac137171&amp;cb=INSERT_RANDOM_NUMBER_HERE' target='_blank'><img src='http://adserver.veldsoft.eu/www/delivery/avw.php?zoneid=3&amp;cb=INSERT_RANDOM_NUMBER_HERE&amp;n=ac137171' border='0' alt='' /></a></noscript> </head> <body bgcolor='black'> </body> </html>";
		((WebView) findViewById(R.id.ads)).loadData(html, "text/html", null);
	}
}
