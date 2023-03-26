package tanchishexiaoyouxi_yunxingcuowu;

import javax.swing.*;
import java.net.URL;

//存放外部数据
public class Data {
    //顶部的图片   URL:定位图片的位置   ImageIcon:图片
    public static URL headerurl = Data.class.getResource("/tanchishe_tupian/header.png");
    public static ImageIcon header = new ImageIcon(headerurl);
    //头部
    public static URL upUrl = Data.class.getResource("/tanchishe_tupian/up.png");
    public static URL downUrl = Data.class.getResource("/tanchishe_tupian/down.png");
    public static URL leftUrl = Data.class.getResource("/tanchishe_tupian/left.png");
    public static URL rightUrl = Data.class.getResource("/tanchishe_tupian/right.png");
    public static ImageIcon up = new ImageIcon(upUrl);
    public static ImageIcon down = new ImageIcon(downUrl);
    public static ImageIcon left = new ImageIcon(leftUrl);
    public static ImageIcon right = new ImageIcon(rightUrl);
    //身体
    public static URL bodyUrl = Data.class.getResource("/tanchishe_tupian/body.png");
    public static ImageIcon body = new ImageIcon(bodyUrl);
    //食物
    public static URL foodUrl = Data.class.getResource("/tanchishe_tupian/food.png");
    public static ImageIcon food = new ImageIcon(foodUrl);
}