package eu.veldsoft.russian.triple;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

//TODO Put splash screen timeout.
public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		((WebView) findViewById(R.id.ads)).getSettings().setJavaScriptEnabled(
				true);

		((WebView) findViewById(R.id.ads))
				.loadUrl("file:///android_asset/banner.html");
	}
}
