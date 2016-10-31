import java.util.ArrayList;

/**
 * Created by puyihao on 16/10/22.
 */
public class Player {
    private int winNumber;
    private int loseNumber;
    private int point;
    private int cardPointSum;
    private boolean burst;
    private boolean stopGettingCard;
    private ArrayList<Card> cardInHand;

    public Player() {
        burst = false;
        stopGettingCard = false;
        cardPointSum = 0;
        winNumber = 0;
        loseNumber = 0;
        setPoint(10000);
        cardInHand = new ArrayList<Card>();
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void win() {
        winNumber++;
    }

    public void lose() {
        loseNumber++;
    }

    public int getWinNumber() {
        return winNumber;
    }

    public int getLoseNumber() {
        return loseNumber;
    }

    public void getCard(Deck deck) {
        cardInHand.add(deck.getCard());
        getCardPointSum();
    }

    public int getCardSize() {
        return cardInHand.size();
    }

    public ArrayList<Card> getCardInHand() {
        return cardInHand;
    }

    public int getCardPointSum() {
        cardPointSum = 0;
        int numOFA = 0;
        for (Card card : cardInHand) {
            if (card.getValue() == 1) {
                numOFA++;
            }
            cardPointSum += card.getValue();
        }
        if (cardPointSum > 21) {
            burst = true;
            return cardPointSum;
        }
        int maxCardPointSum = cardPointSum;
        for (int i = 0; i < numOFA; i++) {
            maxCardPointSum += 10;
            if (maxCardPointSum > 21) {
                maxCardPointSum -= 10;
                return maxCardPointSum;
            }
        }
        return maxCardPointSum;
    }

    public boolean isBurst() {
        return burst;
    }

    public void stopGettingCard() {
        stopGettingCard = true;
    }

    public boolean isStopGettingCard() {
        return stopGettingCard;
    }

    public void playAgain() {
        cardInHand.clear();
        burst = false;
        stopGettingCard = false;
        cardPointSum = 0;
    }

}
