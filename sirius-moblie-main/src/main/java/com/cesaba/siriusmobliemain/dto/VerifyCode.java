package com.cesaba.siriusmobliemain.dto;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.Random;

public class VerifyCode {

    private int w = 105;
    private int h = 35;
    private Random r = new Random();
    private String[] font = {"宋体", "华文楷体", "黑体", "微软雅黑", "楷体_GB2312"};
    private String codes = "0123456789abcdefghjkmnopqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ";
    private Color color = new Color(255,255,255);
    private String codeBody;
    private LocalDateTime localDateTime;

    public VerifyCode(){

    }

    public VerifyCode(LocalDateTime localDateTime){
        this.localDateTime = localDateTime;
    }

    public VerifyCode(int second){
        this.localDateTime = LocalDateTime.now().plusSeconds(second);
    }

    private Color randomColor(){
        int red = r.nextInt(150);
        int green = r.nextInt(150);
        int blue = r.nextInt(150);
        return new Color(red, green, blue);
    }

    private Font randomFont(){
        int index = r.nextInt(font.length);
        String fontName = font[index];
        int style = r.nextInt(4);
        int size = r.nextInt(5) + 24;
        return new Font(fontName, style , size);
    }

    private void drawLine (BufferedImage image){
        int num = 3;
        Graphics2D g2 = (Graphics2D)image.getGraphics();
        for(int i = 0; i < num ; i++){
            int x1 = r.nextInt(w);
            int y1 = r.nextInt(h);
            int x2 = r.nextInt(w);
            int y2 = r.nextInt(h);
            g2.setStroke(new BasicStroke(1.4F));
            g2.setColor(Color.BLUE);
            g2.drawLine(x1, y1, x2 , y2);
        }
    }

    private char randomChar(){
        int index = r.nextInt(codes.length());
        return codes.charAt(index);
    }

    private BufferedImage createImage(){
        BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D)image.getGraphics();
        g2.setColor(this.color);
        g2.fillRect(0,0,w,h);
        return image;
    }

    public BufferedImage getImage(){
        BufferedImage image = createImage();
        Graphics2D g2 = (Graphics2D)image.getGraphics();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++){
            String s = randomChar()+"";
            sb.append(s);
            float x = i * 1.0F * w/6;
            g2.setFont(randomFont());
            g2.setColor(randomColor());
            g2.drawString(s, x, h-5);
        }
        this.codeBody = sb.toString();
        drawLine(image);
        return image;
    }

    public void write(OutputStream sos) throws IOException{
        ImageIO.write(getImage(), "JPEG", sos);
        sos.close();
    }

    public String getCodeBody(){
        return codeBody;
    }

    public static void output(BufferedImage image, OutputStream out)
             throws IOException {
        ImageIO.write(image, "JPEG", out);
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(localDateTime);

    }



}
