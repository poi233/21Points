/**
 * Created by puyihao on 16/10/22.
 */

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlayGame extends JPanel implements ActionListener {
    private Player player;
    private Player computer;
    private Deck deck;
    private int pointForEach;
    private int insurancePoint;
    //GUI
    JFrame jf;
    JPanel cardPanel;
    JPanel menu, scoreBoard, gameButton, end;
    JButton start, resetting;
    JButton draw, halt, insurance, doubling;
    JTextField point;
    JLabel comCardLabel[];
    JLabel playerCardLabel[];
    JLabel score, win, lose, remainingCards;
    JLabel result;

    public PlayGame() {
        pointForEach = 20;
        deck = new Deck();
        player = new Player();
        computer = new Player();
        insurancePoint = 0;
        player.setPoint(10000);
        computer.setPoint(10000);
        //GUI
        comCardLabel = new JLabel[5];
        for (int i = 0; i < comCardLabel.length; i++) {
            comCardLabel[i] = new JLabel(new ImageIcon(""));
        }
        playerCardLabel = new JLabel[5];
        for (int i = 0; i < playerCardLabel.length; i++) {
            playerCardLabel[i] = new JLabel(new ImageIcon(""));
        }
    }

    //used for command line test
//    public void play() {
//        Scanner input = new Scanner(System.in);
//        int i = 1;
//        while (!gameOver) {
//            while (!player.isStopGettingCard() && !gameOver) {
//                System.out.println("------------------");
//                System.out.println("是否继续抽牌？是1否0");
//                int yes = input.nextInt();//输入一个正整数
//                if (yes == 1) {
//                    player.getCard(deck);
//                    if (player.isBurst()) {
//                        computerWin();
//                    } else if (player.getCardPointSum() == 21) {
//                        player.stopGettingCard();
//                    } else if (player.getCardSize() == 5) {
//                        player.stopGettingCard();
//                    }
//                    break;
//                } else if (yes == 0) {
//                    player.stopGettingCard();
//                    break;
//                } else {
//                    continue;
//                }
//            }
//            if (!gameOver && !computer.isStopGettingCard()) {
//                if (computer.getCardPointSum() < 18) {
//                    computer.getCard(deck);
//                    if (computer.isBurst()) {
//                        playerWin();
//                    } else if (computer.getCardSize() == 5) {
//                        computer.stopGettingCard();
//                    }
//                } else {
//                    computer.stopGettingCard();
//                }
//            }
//
//
//            if (!gameOver && player.isStopGettingCard() && computer.isStopGettingCard()) {
//                if (player.getCardPointSum() > computer.getCardPointSum()) {
//                    playerWin();
//                } else if (player.getCardPointSum() < computer.getCardPointSum()) {
//                    computerWin();
//                } else if (player.getCardPointSum() == computer.getCardPointSum()) {
//                    if (player.getCardSize() < computer.getCardSize()) {
//                        playerWin();
//                    } else if (player.getCardSize() > computer.getCardSize()) {
//                        computerWin();
//                    } else {
//                        showResult("两方无人");
//                    }
//                }
//            }
//            if (!gameOver) {
//                System.out.println("第" + i + "轮结果:");
//                System.out.print("玩家:");
//                player.showCardInHand();
//                System.out.println(player.getCardPointSum());
//                System.out.print("电脑:");
//                computer.showCardInHand();
//                System.out.println(computer.getCardPointSum());
//            }
//        }
//    }

    public void setPointForEach(int point) {
        pointForEach = point;
    }

    public void doublePointForEach() {
        pointForEach = 2 * pointForEach;
    }

    public void playerWin() {
        player.win();
        player.setPoint(player.getPoint() + pointForEach - insurancePoint);
        computer.lose();
        computer.setPoint(computer.getPoint() + pointForEach);
        printResult();
        playAgain();
        result.setText("YOU WIN!");
    }

    public void computerWin() {
        player.lose();
        player.setPoint(player.getPoint() - pointForEach - insurancePoint);
        computer.win();
        computer.setPoint(computer.getPoint() + pointForEach + insurancePoint);
        printResult();
        playAgain();
        result.setText("YOU LOSE!");
    }

    public void playAgain() {
        player.playAgain();
        computer.playAgain();
        score.setText("你的分数：" + String.valueOf(player.getPoint()));
        win.setText("赢场：" + String.valueOf(player.getWinNumber()));
        lose.setText("输场：" + String.valueOf(player.getLoseNumber()));
        remainingCards.setText("剩余卡牌：" + String.valueOf(deck.getSize()));
        insurancePoint = 0;
        gameButton.setVisible(false);
        menu.setVisible(true);
        insurance.setVisible(false);
        doubling.setVisible(true);

    }

    public void initUI() throws InterruptedException {
        //设置JFrame
        jf = new JFrame();// 实例化窗口对象
        jf.setSize(1000, 600);// 设置面板大小  //stickLenght=600
        //jf.setResizable(false);// 设置不可调节大小
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭按钮
        jf.setLocationRelativeTo(null);// 设置窗体居中
        jf.setLayout(null);
        //添加JPanel
        cardPanel = new JPanel();
        cardPanel.setLayout(null);
        cardPanel.setBounds(50, 50, 800, 400);
        jf.add(cardPanel);
        //JPanel中添加电脑卡牌框
        JPanel comCard1 = new JPanel();
        comCard1.setLayout(null);
        comCard1.setBounds(0, 0, 105, 150);
        comCard1.setBorder(BorderFactory.createEtchedBorder());
        comCardLabel[0].setIcon(new ImageIcon(""));
        comCardLabel[0].setLayout(null);
        comCardLabel[0].setBounds(0, 0, 105, 150);
        comCard1.add(comCardLabel[0]);
        cardPanel.add(comCard1);

        JPanel comCard2 = new JPanel();
        comCard2.setLayout(null);
        comCard2.setBounds(150, 0, 105, 150);
        comCard2.setBorder(BorderFactory.createEtchedBorder());
        comCardLabel[1].setIcon(new ImageIcon(""));
        comCardLabel[1].setLayout(null);
        comCardLabel[1].setBounds(0, 0, 105, 150);
        comCard2.add(comCardLabel[1]);
        cardPanel.add(comCard2);

        JPanel comCard3 = new JPanel();
        comCard3.setLayout(null);
        comCard3.setBounds(300, 0, 105, 150);
        comCard3.setBorder(BorderFactory.createEtchedBorder());
        comCardLabel[2].setIcon(new ImageIcon(""));
        comCardLabel[2].setLayout(null);
        comCardLabel[2].setBounds(0, 0, 105, 150);
        comCard3.add(comCardLabel[2]);
        cardPanel.add(comCard3);

        JPanel comCard4 = new JPanel();
        comCard4.setLayout(null);
        comCard4.setBounds(450, 0, 105, 150);
        comCard4.setBorder(BorderFactory.createEtchedBorder());
        comCardLabel[3].setIcon(new ImageIcon(""));
        comCardLabel[3].setLayout(null);
        comCardLabel[3].setBounds(0, 0, 105, 150);
        comCard4.add(comCardLabel[3]);
        cardPanel.add(comCard4);

        JPanel comCard5 = new JPanel();
        comCard5.setLayout(null);
        comCard5.setBounds(600, 0, 105, 150);
        comCard5.setBorder(BorderFactory.createEtchedBorder());
        comCardLabel[4].setIcon(new ImageIcon(""));
        comCardLabel[4].setLayout(null);
        comCardLabel[4].setBounds(0, 0, 105, 150);
        comCard5.add(comCardLabel[4]);
        cardPanel.add(comCard5);

        //JPanel中添加玩家卡牌框
        JPanel playerCard1 = new JPanel();
        playerCard1.setLayout(null);
        playerCard1.setBounds(0, 200, 105, 150);
        playerCard1.setBorder(BorderFactory.createEtchedBorder());
        playerCardLabel[0].setIcon(new ImageIcon(""));
        playerCardLabel[0].setLayout(null);
        playerCardLabel[0].setBounds(0, 0, 105, 150);
        playerCard1.add(playerCardLabel[0]);
        cardPanel.add(playerCard1);

        JPanel playerCard2 = new JPanel();
        playerCard2.setLayout(null);
        playerCard2.setBounds(150, 200, 105, 150);
        playerCard2.setBorder(BorderFactory.createEtchedBorder());
        playerCardLabel[1].setIcon(new ImageIcon(""));
        playerCardLabel[1].setLayout(null);
        playerCardLabel[1].setBounds(0, 0, 105, 150);
        playerCard2.add(playerCardLabel[1]);
        cardPanel.add(playerCard2);

        JPanel playerCard3 = new JPanel();
        playerCard3.setLayout(null);
        playerCard3.setBounds(300, 200, 105, 150);
        playerCard3.setBorder(BorderFactory.createEtchedBorder());
        playerCardLabel[2].setIcon(new ImageIcon(""));
        playerCardLabel[2].setLayout(null);
        playerCardLabel[2].setBounds(0, 0, 105, 150);
        playerCard3.add(playerCardLabel[2]);
        cardPanel.add(playerCard3);

        JPanel playerCard4 = new JPanel();
        playerCard4.setLayout(null);
        playerCard4.setBounds(450, 200, 105, 150);
        playerCard4.setBorder(BorderFactory.createEtchedBorder());
        playerCardLabel[3].setIcon(new ImageIcon(""));
        playerCardLabel[3].setLayout(null);
        playerCardLabel[3].setBounds(0, 0, 105, 150);
        playerCard4.add(playerCardLabel[3]);
        cardPanel.add(playerCard4);

        JPanel playerCard5 = new JPanel();
        playerCard5.setLayout(null);
        playerCard5.setBounds(600, 200, 105, 150);
        playerCard5.setBorder(BorderFactory.createEtchedBorder());
        playerCardLabel[4].setIcon(new ImageIcon(""));
        playerCardLabel[4].setLayout(null);
        playerCardLabel[4].setBounds(0, 0, 105, 150);
        playerCard5.add(playerCardLabel[4]);
        cardPanel.add(playerCard5);

        //JPanel中添加选项栏
        menu = new JPanel();
        menu.setLayout(null);
        menu.setBounds(850, 400, 100, 200);
        JLabel pointLable = new JLabel("押注：");
        pointLable.setLayout(null);
        pointLable.setBounds(0, 0, 40, 20);
        point = new JTextField("20");
        point.setLayout(null);
        point.setBounds(50, 0, 50, 20);
        menu.add(pointLable);
        menu.add(point);
        //menu中添加按钮
        start = new JButton("开始");
        start.setLayout(null);
        start.setBounds(0, 50, 90, 30);
        start.addActionListener(this);
        menu.add(start);
        //menu中添加按钮
        resetting = new JButton("重置游戏");
        resetting.setLayout(null);
        resetting.setBounds(0, 100, 90, 30);
        resetting.addActionListener(this);
        menu.add(resetting);
        jf.add(menu);
        //JPanel中添加选项栏
        scoreBoard = new JPanel();
        scoreBoard.setLayout(null);
        scoreBoard.setBounds(850, 50, 200, 200);
        score = new JLabel("你的分数：" + player.getPoint());
        score.setLayout(null);
        score.setBounds(0, 0, 150, 20);
        win = new JLabel("赢场：" + player.getWinNumber());
        win.setLayout(null);
        win.setBounds(0, 50, 150, 20);
        lose = new JLabel("输场：" + player.getLoseNumber());
        lose.setLayout(null);
        lose.setBounds(0, 100, 150, 20);
        remainingCards = new JLabel("剩余卡牌：" + deck.getSize());
        remainingCards.setLayout(null);
        remainingCards.setBounds(0, 150, 150, 20);
        scoreBoard.add(score);
        scoreBoard.add(win);
        scoreBoard.add(lose);
        scoreBoard.add(remainingCards);
        jf.add(scoreBoard);
        //JPanel中添加选项栏
        gameButton = new JPanel();
        gameButton.setLayout(null);
        gameButton.setBounds(50, 450, 400, 100);
        gameButton.setVisible(false);
        draw = new JButton("补牌");
        draw.setLayout(null);
        draw.setBounds(0, 0, 90, 30);
        draw.addActionListener(this);
        halt = new JButton("停牌");
        halt.setLayout(null);
        halt.setBounds(100, 0, 90, 30);
        halt.addActionListener(this);
        insurance = new JButton("保险");
        insurance.setLayout(null);
        insurance.setBounds(300, 0, 90, 30);
        insurance.setVisible(false);
        insurance.addActionListener(this);
        doubling = new JButton("加倍");
        doubling.setLayout(null);
        doubling.setBounds(200, 0, 90, 30);
        doubling.addActionListener(this);
        gameButton.add(draw);
        gameButton.add(halt);
        gameButton.add(insurance);
        gameButton.add(doubling);
        jf.add(gameButton);
        //结束画面
        end = new JPanel();
        end.setLayout(null);
        end.setBounds(400, 450, 200, 50);
        result = new JLabel("");
        result.setLayout(null);
        result.setBounds(0, 0, 200, 50);
        end.add(result);
        //end.setVisible(false);
        jf.add(end);
        //jf.getLayeredPane().add(end,new Integer(Integer.MAX_VALUE));//置顶
        // 设置窗体可见
        jf.setVisible(true);
    }

    public void printCard() {
        ArrayList<Card> playerCards = player.getCardInHand();
        ArrayList<Card> comCards = computer.getCardInHand();
        for (int i = 0; i < comCardLabel.length; i++) {
            comCardLabel[i].setIcon(new ImageIcon(""));
        }
        for (int i = 0; i < playerCardLabel.length; i++) {
            playerCardLabel[i].setIcon(new ImageIcon(""));
        }
        for (int i = 0; i < playerCards.size(); i++) {
            int picNum = playerCards.get(i).getCardNum();
            playerCardLabel[i].setIcon(new ImageIcon("pictures/" + picNum + ".jpg"));
        }
        for (int i = 0; i < comCards.size(); i++) {
            int picNum = comCards.get(i).getCardNum();
            if (i == 0) {
                comCardLabel[i].setIcon(new ImageIcon("pictures/" + picNum + ".jpg"));
            } else {
                comCardLabel[i].setIcon(new ImageIcon("pictures/back.jpg"));
            }

        }
    }

    public void printResult() {
        ArrayList<Card> playerCards = player.getCardInHand();
        ArrayList<Card> comCards = computer.getCardInHand();
        for (int i = 0; i < comCardLabel.length; i++) {
            comCardLabel[i].setIcon(new ImageIcon(""));
        }
        for (int i = 0; i < playerCardLabel.length; i++) {
            playerCardLabel[i].setIcon(new ImageIcon(""));
        }
        for (int i = 0; i < playerCards.size(); i++) {
            int picNum = playerCards.get(i).getCardNum();
            playerCardLabel[i].setIcon(new ImageIcon("pictures/" + picNum + ".jpg"));
        }
        for (int i = 0; i < comCards.size(); i++) {
            int picNum = comCards.get(i).getCardNum();
            comCardLabel[i].setIcon(new ImageIcon("pictures/" + picNum + ".jpg"));
        }
    }

    public void start() {
        result.setText("");
        for (int i = 0; i < 2; i++) {
            computer.getCard(deck);
            player.getCard(deck);
        }
        printCard();
        if (player.getCardPointSum() == 21) {
            setPointForEach((int) (Double.parseDouble(point.getText()) * 1.5));
            playerWin();
            return;
        }
        menu.setVisible(false);
        gameButton.setVisible(true);
        if (computer.getCardInHand().get(0).getFace() == "A") {
            insurance.setVisible(true);
        }
        if (player.getCardPointSum() < 17) {
            halt.setVisible(false);
        }
        setPointForEach(Integer.parseInt(point.getText()));
    }

    public void addCard() {
        doubling.setVisible(false);
        if (computer.getCardInHand().get(0).getFace().equals("A") && computer.getCardSize() == 2 && computer.getCardPointSum() == 21) {
            if (insurancePoint == 0) {
                //没买保险赔1.5倍黑杰克
                setPointForEach((int) (Double.parseDouble(point.getText()) * 1.5));
                computerWin();
            } else if (insurancePoint != 0) {
                //买保险只赔普通的
                computerWin();
                player.setPoint(player.getPoint() + insurancePoint);
                computer.setPoint(computer.getPoint() - insurancePoint);
            }
        } else {
            insurance.setVisible(false);
            player.getCard(deck);
            printCard();
            if (player.getCardPointSum() >= 17) {
                halt.setVisible(true);
            }
            if (player.getCardSize() == 5) {
                if (!player.isBurst())
                    stopGetCard();
            }
            if (player.isBurst()) {
                computerWin();
            }
        }
    }

    public void stopGetCard() {
        player.stopGettingCard();
        while (!computer.isStopGettingCard()) {
            if (computer.getCardPointSum() < 17) {
                computer.getCard(deck);
                printCard();
                if (computer.isBurst()) {
                    playerWin();
                    return;
                } else if (computer.getCardSize() == 5) {
                    computer.stopGettingCard();
                }
            } else {
                computer.stopGettingCard();
            }
        }

        if (player.getCardPointSum() > computer.getCardPointSum()) {
            playerWin();
        } else if (player.getCardPointSum() < computer.getCardPointSum()) {
            computerWin();
        } else if (player.getCardPointSum() == computer.getCardPointSum()) {
            if (insurancePoint != 0) {
                player.setPoint(player.getPoint() - insurancePoint);
                computer.setPoint(computer.getPoint() + insurancePoint);
            }
            printResult();
            playAgain();
            result.setText("PUSH");
        }
    }

    public void doublePoint() {
        doublePointForEach();
        addCard();
        if (player.getCardInHand().size() != 0)
            stopGetCard();
    }

    public void setInsurance() {
        insurancePoint = pointForEach / 2;
        insurance.setVisible(false);
        if (computer.getCardPointSum() == 21) {
            printResult();
            playAgain();
        }
    }

    public void resetGame() {
        player = new Player();
        computer = new Player();
        result.setText("");
        deck.clear();
        playAgain();
        printResult();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String str = e.getActionCommand();
        if (str.equals("开始")) {
            start();
        } else if (str.equals("补牌")) {
            addCard();
        } else if (str.equals("停牌")) {
            stopGetCard();
        } else if (str.equals("加倍")) {
            doublePoint();
        } else if (str.equals("保险")) {
            setInsurance();
        } else if (str.equals("重置游戏")) {
            resetGame();
        }
    }
}
