package view;

import controller.GameController;
import model.ChessColor;

import javax.swing.*;
import java.awt.*;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE  窗体大小;
    private final int WIDTH;
    private final int HEIGTH;
    public final int CHESSBOARD_SIZE;
    private GameController gameController;

    private JPanel panel0 = new JPanel();
    private JPanel panel1 = new JPanel();

    public ChessGameFrame(int width, int height) {
        setTitle("2022 CS102A Chess Project Demo"); //设置标题
        this.WIDTH = width;
        this.HEIGTH = height;
        this.CHESSBOARD_SIZE = HEIGTH * 4 / 5;

        setSize(WIDTH, HEIGTH);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);

        panel0.setSize(WIDTH, HEIGTH);
        panel0.setLayout(null);

        panel1.setSize(WIDTH, HEIGTH);
        panel1.setLayout(null);

//        把平面加到窗体上
        getContentPane().add(panel0);
        setVisible(true);

        getContentPane().add(panel1);
        setVisible(true);

        addBeginGameButten(panel0);
        addChessboard(panel1);
        addRoundLabel(panel1);
        addStoreButton(panel1);
        addReBeginGameButten(panel1);
        addLoadButton(panel1);
    }


    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard(JPanel panel) {
        Chessboard chessboard = new Chessboard(CHESSBOARD_SIZE, CHESSBOARD_SIZE);
        gameController = new GameController(chessboard);
        chessboard.setLocation(20, 76);
        panel.add(chessboard);
    }

    /**
     * 在游戏面板中添加回合状态标签
     */
    private void addRoundLabel(JPanel panel) {
        JLabel statusLabel = new JLabel("第1回合  It's White's turn.");
        statusLabel.setLocation(200, 0);
        statusLabel.setSize(300, 60);
        statusLabel.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(statusLabel);
    }

    private void addBeginGameButten(JPanel panel) {
        JButton button = new JButton("开始游戏");
        button.addActionListener((e) -> {
            remove(panel0);
            setContentPane(panel1);
            validate();
            repaint();
        });
        button.setLocation(375, 500);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(button);
    }

    private void addReBeginGameButten(JPanel panel) {
        JButton button = new JButton("重新开始游戏");
        button.addActionListener((e) -> {
            remove(panel1);
            panel1.removeAll();
            addChessboard(panel1);
            addRoundLabel(panel1);
            addReBeginGameButten(panel1);
            setContentPane(panel1);
            validate();
            repaint();
        });
        button.setLocation(700, 600);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        panel.add(button);
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addHelloButton() {
        JButton button = new JButton("Hi!!");
        button.addActionListener((e) -> JOptionPane.showMessageDialog(this, "Hello, world!"));
        button.setLocation(HEIGTH, HEIGTH / 10 + 120);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(button);
    }

    private void addLoadButton(JPanel panel) {
        JButton button = new JButton("Load a game");
        button.setLocation(700, 400);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));

        button.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            gameController.loadGameFromFile(path);
        });
        panel.add(button);
    }

    private void addStoreButton(JPanel panel) {
        JButton button = new JButton("Store a game");
        button.setLocation(700, 200);
        button.setSize(200, 60);
        button.setFont(new Font("Rockwell", Font.BOLD, 20));

        button.addActionListener(e -> {
            System.out.println("Click store");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            gameController.storeGameFromFile(path);
        });
        panel.add(button);
    }

}
