package eu.veldsoft.russian.triple;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import eu.veldsoft.russian.triple.model.Board;
import eu.veldsoft.russian.triple.model.Card;
import eu.veldsoft.russian.triple.model.ComputerPlayer;
import eu.veldsoft.russian.triple.model.Deck;
import eu.veldsoft.russian.triple.model.HumanPlayer;
import eu.veldsoft.russian.triple.model.State;

/**
 * Game screen.
 * 
 * @author Todor Balabanov
 */
public class GameActivity extends Activity {
	/**
	 * Bid request identifier.
	 */
	private static int BID_REQUEST_ID = 1;

	/**
	 * 
	 * @param delay
	 */
	private void checkEndBidding(int delay) {
		if (board.getBidding().finished() == false) {
			if (board.getBidding().hasLast() == true) {
				Toast.makeText(
						GameActivity.this,
						"Bid of "
								+ board.getBidding().winner().getScore()
								+ " done by "
								+ board.getBidding().winner().getPlayer()
										.getName() + ".", Toast.LENGTH_SHORT)
						.show();
			}

			handler.postDelayed(biddingThread, delay);
		} else {
			if (board.getBidding().hasWinner() == true) {
				Toast.makeText(
						GameActivity.this,
						"End bidding by "
								+ board.getBidding().winner().getPlayer()
										.getName() + " with bid of "
								+ board.getBidding().winner().getScore() + ".",
						Toast.LENGTH_LONG).show();

				board.setState(State.CONTRACTING);
				updateViews();
			} else {
				board.setState(State.DEALING);
				updateViews();
			}
		}
	}

	/**
	 * Bidding thread object.
	 */
	private Runnable biddingThread = new Runnable() {
		// TODO Reorganize in bidding class.
		@Override
		public void run() {
			if (board.getState() == State.CLOSED) {
				return;
			} else if (board.getState() != State.BIDDING) {
				handler.postDelayed(this, 1000);
			} else if (board.getState() == State.BIDDING
					&& board.getBidding().getCurrentBidder() instanceof HumanPlayer) {
				startActivityForResult((new Intent(GameActivity.this,
						BiddingActivity.class)).putExtra(
						BiddingActivity.EXTRA_BIDDING_KEY, board.getBidding()),
						BID_REQUEST_ID);
			} else if (board.getState() == State.BIDDING
					&& board.getBidding().getCurrentBidder() instanceof ComputerPlayer) {
				board.getBidding().doBid();
				board.getBidding().nextBidder();
				updateViews();

				checkEndBidding(2700);
			} else {
				throw (new RuntimeException("Invalid bidder."));
			}
		}
	};

	/**
	 * Card click listener object.
	 */
	private View.OnClickListener cardClicked = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			// TODO Do it in better way.
			for (int i = 0; i < cardsImages.length; i++) {
				if (view == cardsImages[i]) {
					board.click(i);
					break;
				}
			}

			updateViews();
		}
	};

	/**
	 * 
	 */
	private Handler handler = new Handler();

	/**
	 * Array with all information for the players, which should be displayed in
	 * the game screen.
	 */
	private TextView playersInfo[] = { null, null, null };

	/**
	 * 
	 */
	private Integer backDrawable = null;

	/**
	 * Associative array with cards drawabels.
	 */
	private Map<Card, Integer> cardToDrawable = new HashMap<Card, Integer>();

	/**
	 * Reference to trump image view.
	 */
	private ImageView trumpImage = null;

	/**
	 * All cards image view as references.
	 */
	private ImageView cardsImages[] = { null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, };

	/**
	 * Board object, from the model, reference.
	 */
	private Board board = new Board();

	/**
	 * Update user interface according object model state.
	 */
	private void updateViews() {
		if (board.getState() == State.NOT_STARTED) {
			return;
		}

		String players[] = board.getPlayersInfo();

		if (Card.Suit.isTrumpSelected() == false) {
			trumpImage.setImageBitmap(null);
		} else if (Card.Suit.SPADES.isTrump() == true) {
			trumpImage.setImageResource(R.drawable.spades);
		} else if (Card.Suit.HEARTS.isTrump() == true) {
			trumpImage.setImageResource(R.drawable.hearts);
		} else if (Card.Suit.CLUBS.isTrump() == true) {
			trumpImage.setImageResource(R.drawable.clubs);
		} else if (Card.Suit.DIAMONDS.isTrump() == true) {
			trumpImage.setImageResource(R.drawable.diamonds);
		}

		Card cards[] = board.getCardsOnTheBoard();

		for (ImageView image : cardsImages) {
			image.setAlpha(1.0F);
		}

		for (int i = 0; i < cards.length && i < cardsImages.length; i++) {
			if (cards[i] == null || cards[i].isInvisible() == true) {
				cardsImages[i].setImageBitmap(null);
				continue;
			}

			if (cards[i].isFaceDown() == true) {
				cardsImages[i].setImageResource(backDrawable);
			} else if (cards[i].isFaceUp() == true) {
				cardsImages[i].setImageResource(cardToDrawable.get(cards[i]));
			}

			if (cards[i].isHighlighted() == true) {
				cardsImages[i].setAlpha(0.5F);
			}
		}

		/*
		 * Update user interface for game to start.
		 */
		if (board.getState() == State.STARTING) {
			for (int i = 0; i < playersInfo.length && i < players.length; i++) {
				playersInfo[i].setText("");
			}
		} else {
			for (int i = 0; i < playersInfo.length && i < players.length; i++) {
				playersInfo[i].setText(players[i]);
			}
		}

		/*
		 * Update user interface during the bidding process.
		 */
		if (board.getState() == State.BIDDING
				&& board.getBidding().hasLast() == true) {
			((TextView) findViewById(R.id.currentBid)).setText(""
					+ board.getBidding().last().getScore());
		} else {
			((TextView) findViewById(R.id.currentBid)).setText("");
		}

		/*
		 * Update user interface during talon collection.
		 */
		if (board.getState() == State.CONTRACTING) {
			((TextView) findViewById(R.id.currentBid)).setText(""
					+ board.getBidding().last().getScore());
		} else {
			((TextView) findViewById(R.id.currentBid)).setText("");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		handler.postDelayed(biddingThread, 0);

		playersInfo[0] = (TextView) findViewById(R.id.playerInfo01);
		playersInfo[1] = (TextView) findViewById(R.id.playerInfo02);
		playersInfo[2] = (TextView) findViewById(R.id.playerInfo03);

		backDrawable = R.drawable.back01;

		cardToDrawable.put(Deck.cardAtPosition(0), R.drawable.card009c);
		cardToDrawable.put(Deck.cardAtPosition(1), R.drawable.card010c);
		cardToDrawable.put(Deck.cardAtPosition(2), R.drawable.card011c);
		cardToDrawable.put(Deck.cardAtPosition(3), R.drawable.card012c);
		cardToDrawable.put(Deck.cardAtPosition(4), R.drawable.card013c);
		cardToDrawable.put(Deck.cardAtPosition(5), R.drawable.card001c);
		cardToDrawable.put(Deck.cardAtPosition(6), R.drawable.card009h);
		cardToDrawable.put(Deck.cardAtPosition(7), R.drawable.card010h);
		cardToDrawable.put(Deck.cardAtPosition(8), R.drawable.card011h);
		cardToDrawable.put(Deck.cardAtPosition(9), R.drawable.card012h);
		cardToDrawable.put(Deck.cardAtPosition(10), R.drawable.card013h);
		cardToDrawable.put(Deck.cardAtPosition(11), R.drawable.card001h);
		cardToDrawable.put(Deck.cardAtPosition(12), R.drawable.card009d);
		cardToDrawable.put(Deck.cardAtPosition(13), R.drawable.card010d);
		cardToDrawable.put(Deck.cardAtPosition(14), R.drawable.card011d);
		cardToDrawable.put(Deck.cardAtPosition(15), R.drawable.card012d);
		cardToDrawable.put(Deck.cardAtPosition(16), R.drawable.card013d);
		cardToDrawable.put(Deck.cardAtPosition(17), R.drawable.card001d);
		cardToDrawable.put(Deck.cardAtPosition(18), R.drawable.card009s);
		cardToDrawable.put(Deck.cardAtPosition(19), R.drawable.card010s);
		cardToDrawable.put(Deck.cardAtPosition(20), R.drawable.card011s);
		cardToDrawable.put(Deck.cardAtPosition(21), R.drawable.card012s);
		cardToDrawable.put(Deck.cardAtPosition(22), R.drawable.card013s);
		cardToDrawable.put(Deck.cardAtPosition(23), R.drawable.card001s);

		trumpImage = (ImageView) findViewById(R.id.trumpImageView);

		cardsImages[0] = (ImageView) findViewById(R.id.imageView001);
		cardsImages[1] = (ImageView) findViewById(R.id.imageView002);
		cardsImages[2] = (ImageView) findViewById(R.id.imageView003);
		cardsImages[3] = (ImageView) findViewById(R.id.imageView004);
		cardsImages[4] = (ImageView) findViewById(R.id.imageView005);
		cardsImages[5] = (ImageView) findViewById(R.id.imageView006);
		cardsImages[6] = (ImageView) findViewById(R.id.imageView007);
		cardsImages[7] = (ImageView) findViewById(R.id.imageView008);
		cardsImages[8] = (ImageView) findViewById(R.id.imageView009);
		cardsImages[9] = (ImageView) findViewById(R.id.imageView010);
		cardsImages[10] = (ImageView) findViewById(R.id.imageView011);
		cardsImages[11] = (ImageView) findViewById(R.id.imageView012);
		cardsImages[12] = (ImageView) findViewById(R.id.imageView013);
		cardsImages[13] = (ImageView) findViewById(R.id.imageView014);
		cardsImages[14] = (ImageView) findViewById(R.id.imageView015);
		cardsImages[15] = (ImageView) findViewById(R.id.imageView016);
		cardsImages[16] = (ImageView) findViewById(R.id.imageView017);
		cardsImages[17] = (ImageView) findViewById(R.id.imageView018);
		cardsImages[18] = (ImageView) findViewById(R.id.imageView019);
		cardsImages[19] = (ImageView) findViewById(R.id.imageView020);
		cardsImages[20] = (ImageView) findViewById(R.id.imageView021);
		cardsImages[21] = (ImageView) findViewById(R.id.imageView022);
		cardsImages[22] = (ImageView) findViewById(R.id.imageView023);
		cardsImages[23] = (ImageView) findViewById(R.id.imageView024);
		cardsImages[24] = (ImageView) findViewById(R.id.imageView025);
		cardsImages[25] = (ImageView) findViewById(R.id.imageView026);
		cardsImages[26] = (ImageView) findViewById(R.id.imageView027);
		cardsImages[27] = (ImageView) findViewById(R.id.imageView028);
		cardsImages[28] = (ImageView) findViewById(R.id.imageView029);
		cardsImages[29] = (ImageView) findViewById(R.id.imageView030);

		cardsImages[24].bringToFront();
		cardsImages[25].bringToFront();
		cardsImages[26].bringToFront();

		for (ImageView view : cardsImages) {
			view.setOnClickListener(cardClicked);
		}

		board.setState(State.STARTING);
		updateViews();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK && requestCode == BID_REQUEST_ID) {
			// TODO Handle bid in the bidding process. Share this info with the
			// thread.
			int bidValue = data.getIntExtra(
					BiddingActivity.EXTRA_RESULT_BID_KEY, 0);
			boolean passValue = data.getBooleanExtra(
					BiddingActivity.EXTRA_PASS_BID_KEY, true);

			if (passValue == true) {
				board.getBidding().getCurrentBidder().stopBidding();
			} else {
				board.getBidding().doBid(bidValue);
			}
			board.getBidding().nextBidder();
			updateViews();

			checkEndBidding(100);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.game_option_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.new_game:
			board.setState(State.STARTING);
			updateViews();
			break;
		case R.id.new_deal:
			board.setState(State.DEALING);
			updateViews();
			break;
		case R.id.help:
			startActivity(new Intent(GameActivity.this, HelpActivity.class));
			break;
		case R.id.about:
			startActivity(new Intent(GameActivity.this, AboutActivity.class));
			break;
		case R.id.exit_game:
			finish();
			System.exit(0);
			break;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onDestroy() {
		super.onDestroy();
		board.setState(State.CLOSED);

		handler.removeCallbacks(biddingThread);
	}
}
