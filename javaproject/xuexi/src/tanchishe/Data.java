package tanchishe;

import javax.swing.*;
import java.net.URL;

public class Data {
    //头部图片
    public static URL headerUrl = Data.class.getResource("/tanchishe/header.png");
    public static ImageIcon header = new ImageIcon(headerUrl);
    //头部：上下左右
    public static URL upUrl = Data.class.getResource("/tanchishe/up.png");
    public static URL downUrl = Data.class.getResource("/tanchishe/down.png");
    public static URL leftUrl = Data.class.getResource("/tanchishe/left.png");
    public static URL rightUrl = Data.class.getResource("/tanchishe/right.png");
    public static ImageIcon up = new ImageIcon(upUrl);
    public static ImageIcon down = new ImageIcon(downUrl);
    public static ImageIcon left = new ImageIcon(leftUrl);
    public static ImageIcon right = new ImageIcon(rightUrl);
    //身体
    public static URL bodyUrl = Data.class.getResource("/tanchishe/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);
    //食物
    public static URL foodUrl = Data.class.getResource("/tanchishe/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);
}
