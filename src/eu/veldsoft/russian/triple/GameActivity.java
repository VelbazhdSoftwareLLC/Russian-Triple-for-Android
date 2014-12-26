package eu.veldsoft.russian.triple;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

public class GameActivity extends Activity {
	private Map<Card, Integer> cardToDrawable = new HashMap<Card, Integer>();

	private ImageView cardsImages[] = { null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, null, null, null, null, null, null, null, null, null,
			null, null, };

	private Board board = new Board();

	private void redraw() {
		Card all[] = board.getCardsOnTheBoard();

		for (int i = 0; i < all.length && i < cardsImages.length; i++) {
			if (all[i] == null) {
				cardsImages[i].setImageBitmap(null);
				continue;
			}

			cardsImages[i].setImageResource(cardToDrawable.get(all[i]));
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

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
		
		board.deal();
		redraw();
	}
}
