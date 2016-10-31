/**
 * Created by puyihao on 16/10/22.
 */
public class Card {
    private final String[] face = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
    private final String[] suit = {"黑桃", "梅花", "方块", "红心"};
    private int value = 0;

    public Card(int value) {
        this.value = value;
    }

    public String getFace() {
        return face[value % face.length];
    }

    public String getSuit() {
        return suit[value / face.length];
    }

    public int getValue() {
        int point = value % face.length + 1;
        if (point == 11 || point == 12 || point == 13) {
            point = 10;
        }
        return point;
    }

    public int getCardNum() {
        return value + 1;
    }

}
