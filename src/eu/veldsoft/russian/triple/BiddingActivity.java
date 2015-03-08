package eu.veldsoft.russian.triple;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Button;

public class BiddingActivity extends Activity {

	public static final String EXTRA_CURRENT_BID_KEY = "eu.veldsoft.russian.triple.currentBidKey";

	public static final String EXTRA_MAX_BID_KEY = "eu.veldsoft.russian.triple.maxBidKey";

	private int current = 0;

	private int maximum = 0;

	private void setCurrentBid() {
		if (current <= 0) {
			return;
		}

		((TextView) findViewById(R.id.bidValue)).setText("" + current);

		Spinner spinner = (Spinner) findViewById(R.id.bidding);
		for (int i = 0; i < spinner.getCount(); i++) {
			Object value = spinner.getItemAtPosition(i);
			if (("" + current).equals(value)) {
				spinner.setSelection(i);
				break;
			}
		}
	}

	@Override
	protected void onStart() {
		super.onStart();

		current = getIntent().getIntExtra(EXTRA_CURRENT_BID_KEY, 0);
		maximum = getIntent().getIntExtra(EXTRA_MAX_BID_KEY, 200);

		setCurrentBid();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bidding);


		((Button) findViewById(R.id.pass))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						//TODO Close activity without bid.
					}
				});

		((Button) findViewById(R.id.plus_one))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						//TODO Add to user bid.
					}
				});

		((Button) findViewById(R.id.plus_ten))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						//TODO Add to user bid.
					}
				});

		((Button) findViewById(R.id.done))
				.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						//TODO Check spinner value and close activity.
					}
				});

		((Spinner) findViewById(R.id.bidding))
				.setOnItemSelectedListener(new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long arg3) {
						/*
						 * First value is not a number.
						 */
						if (position == 0) {
							return;
						}

						int value = new Integer(parent.getItemAtPosition(
								position).toString()).intValue();
						if (value <= current || value > maximum) {
							setCurrentBid();
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});
	}
}
