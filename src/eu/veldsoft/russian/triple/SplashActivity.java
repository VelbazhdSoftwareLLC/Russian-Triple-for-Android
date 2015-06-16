package eu.veldsoft.russian.triple;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

//TODO Put splash screen timeout.
public class SplashActivity extends Activity {

	private long timeout = 0L;

	private String redirect = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		/*
		 * Activate JavaScript.
		 */
		((WebView) findViewById(R.id.ads)).getSettings().setJavaScriptEnabled(
				true);

		/*
		 * Load local web page as banner holder.
		 */
		((WebView) findViewById(R.id.ads))
				.loadUrl("file:///android_asset/banner.html");

		/*
		 * Get splash screen timeout.
		 */
		try {
			timeout = getPackageManager().getApplicationInfo(
					this.getPackageName(), PackageManager.GET_META_DATA).metaData
					.getInt("timeout");
		} catch (NameNotFoundException e) {
			timeout = 0;
		}

		/*
		 * Get redirect activity class name.
		 */
		try {
			redirect = getPackageManager().getApplicationInfo(
					this.getPackageName(), PackageManager.GET_META_DATA).metaData
					.getString("redirect");
		} catch (NameNotFoundException e) {
			redirect = "SplashActivity.class";
			Toast.makeText(
					this,
					getResources().getString(
							R.string.redirect_activity_is_missing_message),
					Toast.LENGTH_LONG).show();
		}
	}
}
