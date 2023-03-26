package tanchishexiaoyouxi_yunxingcuowu;

import javax.swing.*;

public class StartGame {
    public static void main(String[] args) {
        //绘制一个静态窗口   JFrame
        JFrame frame = new JFrame("Java贪吃蛇小游戏");
        //设置界面大小
        frame.setBounds(10, 10, 900, 720);
        //设置关闭事件，游戏可以关闭
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //面板   JFrame   可以加入JFrame
        frame.add(new GamePanel());
        //让窗口能够展示出来
        frame.setVisible(true);
    }
}