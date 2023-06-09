package tanchishe;

import javax.swing.*;
import java.awt.event.KeyListener;

public class StartGame {
    public static void main(String[] args) {
        //新建一个窗口
        JFrame frame = new JFrame("Java贪吃蛇小游戏");
        //设置窗口的位置和大小
        frame.setBounds(10,10,900,720);
        //窗口大小不可调整,即固定窗口大小
        frame.setResizable(false);
        //设置关闭事件，游戏可以关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //添加我们自己编写的画布背景
        frame.add(new GamePanel());
        //将窗口展示出来
        frame.setVisible(true);
    }
}
