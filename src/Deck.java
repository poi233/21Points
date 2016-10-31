import java.util.ArrayList;
import java.util.Random;

/**
 * Created by puyihao on 16/10/22.
 */
public class Deck {
    private ArrayList<Card> cards = new ArrayList<Card>();
    private ArrayList<Card> washedCards = new ArrayList<Card>();

    public Deck() {
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 52; i++) {
                Card card = new Card(i);
                cards.add(card);
            }
        }
        //System.out.println(cards.size());
        wash();
    }

    public void wash() {
        Random r = new Random();
        for (int i = 0; i < 208; i++) {
            washedCards.add(cards.remove(r.nextInt(cards.size())));
        }
        //System.out.println(washedCards.size());
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 52; i++) {
                Card card = new Card(i);
                cards.add(card);
            }
        }
    }

    public Card getCard() {
        Random r = new Random();
        //System.out.println(washedCards.size());
        if (washedCards.size() == 0)
            wash();
        return washedCards.remove(r.nextInt(washedCards.size()));
    }

    public void clear() {
        washedCards.clear();
        wash();
    }

    public int getSize() {
        return washedCards.size();
    }

}
