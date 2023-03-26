package tanchishexiaoyouxi_yunxingcuowu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener, ActionListener {
    //蛇的长度
    int lenth;
    //蛇的坐标X
    int[] snakeX = new int[600];
    //蛇的坐标Y
    int[] snakeY = new int[500];
    //L:左，R:右，U:上，D:下
    String fx = "R";
    //游戏是否开始
    boolean isStart = false;
    //定时器
    Timer timer = new Timer(100, this);
    //定义一个食物
    int foodX;
    int foodY;
    Random random = new Random();
    //死亡判断
    boolean isFail = false;
    //积分系统
    int score;

    //构造器
    public GamePanel() {
        //初始化
        init();
        //获取键盘的监听事件
        this.setFocusable(true);
        //键盘监听事件
        this.addKeyListener(this);
        //让时间动起来
        timer.start();
    }

    //初始化方法
    public void init() {
        //初始小蛇有三节,包括小脑袋
        lenth = 3;
        //初始化开始的蛇,给蛇定位
        //头部坐标
        snakeX[0] = 100;
        snakeY[0] = 100;
        //第一个身体的坐标
        snakeX[1] = 75;
        snakeY[1] = 100;
        //第二个身体的坐标
        snakeX[2] = 50;
        snakeY[2] = 100;
        //初始化食物数据
        foodX = 25 + 25 * random.nextInt(34);
        foodY = 75 + 25 * random.nextInt(24);
        //初始化游戏分数
        score = 0;
    }

    //面板   画界面   画射
    //Graphics   画笔
    @Override
    protected void paintComponent(Graphics g) {
        //清屏
        super.paintComponent(g);
        //设置面板背景颜色
        this.setBackground(Color.YELLOW);
        //绘制头部的广告栏
        Data.header.paintIcon(this, g, 25, 11);
        //绘制游戏区域
        g.fillRect(25, 75, 850, 600);
        //画一条静态的小蛇//蛇的头通过方向变量来判断
        if (fx.equals("R")) {
            Data.right.paintIcon(this, g, snakeX[0], snakeX[0]);
        } else if (fx.equals("L")) {
            Data.left.paintIcon(this, g, snakeX[0], snakeX[0]);
        } else if (fx.equals("U")) {
            Data.up.paintIcon(this, g, snakeX[0], snakeX[0]);
        } else if (fx.equals("D")) {
            Data.down.paintIcon(this, g, snakeX[0], snakeX[0]);
        }
        //蛇的身体长度由lenth来控制
        for (int i = 1; i < lenth; i++) {
            Data.body.paintIcon(this, g, snakeX[i], snakeX[i]);
        }
        //画积分
        g.setColor(Color.WHITE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 10));
        g.drawString("长度", 750, 35);
        g.drawString("分数", 750, 50);
        //画食物
        Data.food.paintIcon(this, g, foodX, foodY);
        //游戏提示：是否开始
        if (isStart == false) {
            //画一个文字，String
            //设置笔画的颜色
            g.setColor(Color.WHITE);
            //设置字体
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawString("按下空格开始游戏", 300, 300);
            //失败判断
            if (isFail) {
                //画一个文字，String
                //设置画笔的颜色
                g.setColor(Color.RED);
                //设置字体
                g.setFont(new Font("微软雅黑", Font.BOLD, 40));
                g.drawString("游戏失败，按下空格重新开始", 200, 300);

            }
        }
    }

    //键盘按下，弹起，敲击
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //键盘监听事件
    //键盘按下，未释放
    @Override
    public void keyPressed(KeyEvent e) {
        //获取按下的键盘
        int keyCode = e.getKeyCode();
        //如果按下的是空格键
        if (keyCode == KeyEvent.VK_SPACE) {
            //如果失败，游戏再来一次
            if (isFail) {
                isFail = false;
                //重新初始化游戏
                init();
                //否则，暂停游戏
            } else {
                isStart = !isStart;
            }
            isStart = !isStart;
            //刷新界面
            repaint();
        }
        //键盘控制走向
        if (keyCode == KeyEvent.VK_UP) {
            fx = "U";
        } else if (keyCode == KeyEvent.VK_DOWN) {
            fx = "D";
        } else if (keyCode == KeyEvent.VK_LEFT) {
            fx = "L";
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            fx = "R";
        }
    }

    //释放某个键
    @Override
    public void keyReleased(KeyEvent e) {

    }

    //定时器   监听时间   帧   执行定时操作
    @Override
    public void actionPerformed(ActionEvent e) {
        //如果游戏处于开始状态，并且游戏没有结束，则小蛇可以移动
        if (isStart && isFail == false) {
            //右移:即让后一个移到前一个的位置即可 !
            // 身体移动 //除了脑袋都往前移：身体移动
            for (int i = lenth - 1; i > 0; i--) {//即第i节(后一节)的位置变为(i-1：前一节)节的位置！
                snakeX[i] = snakeX[i - 1];
                snakeY[i] = snakeY[i - 1];
            }
            //通过控制方向让头部移动
            if (fx.equals("U")) {
                //头部移动
                snakeX[0] = snakeX[0] - 25;
                //边界判断
                if (snakeX[0] < 75) {
                    snakeX[0] = 650;
                }
            } else if (fx.equals("D")) {
                //头部移动
                snakeX[0] = snakeX[0] + 25;
                //边界判断
                if (snakeX[0] > 650) {
                    snakeX[0] = 75;
                }
            } else if (fx.equals("R")) {
                //头部移动
                snakeX[0] = snakeX[0] + 25;
                //边界判断
                if (snakeX[0] > 850) {
                    snakeX[0] = 25;
                }
            } else if (fx.equals("L")) {
                //头部移动
                snakeX[0] = snakeX[0] - 25;
                //边界判断
                if (snakeX[0] < 25) {
                    snakeX[0] = 850;
                }
            }
            //如果小蛇的头和食物坐标重合了
            if (snakeX[0] == foodX && snakeY[0] == foodY) {
                //长度+1
                lenth++;//每吃一个食物，增加积分
                score = score + 10;
                //重新生成食物
                foodX = 25 + 25 * random.nextInt(34);
                foodY = 75 + 25 * random.nextInt(24);
            }
            //结束判断，头和身体撞到了
            for (int i = 1; i < lenth; i++) {
                if (snakeX[i] == snakeX[0] && snakeY[i] == snakeY[0]) {
                    isFail = true;
                }
            }
            //刷新界面
            repaint();
        }
        //让时间动起来
        timer.start();
    }
}
