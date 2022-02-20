package main;

import object.ObjectKey;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font arial40, arial80B;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public boolean gameFinished = false;
    public String message = "";
    int messageCounter = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {

        this.gp = gp;
        arial40 = new Font("Arial", Font.PLAIN, 40);
        arial80B = new Font("Arial", Font.BOLD, 80);
        ObjectKey key = new ObjectKey(gp);
        keyImage = key.image;
    }

    public void showMessage(String text) {

        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished) {

            String text;
            int textLenght;

            g2.setFont(arial40);
            g2.setColor(Color.white);
            text = "You found a treasure";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = (gp.screenWidth-textLenght)/2;
            int y = (gp.screenHeight/2)-(gp.tileSize*3);
            g2.drawString(text, x, y);

            text = "Your time is : " + dFormat.format(playTime);
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gp.screenWidth-textLenght)/2;
            y = (gp.screenHeight/2)+(gp.tileSize*5);
            g2.drawString(text, x, y);

            g2.setFont(arial80B);
            g2.setColor(Color.yellow);
            text = "Congratulation";
            textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = (gp.screenWidth-textLenght)/2;
            y = (gp.screenHeight/2)+(gp.tileSize*3);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        } else {
            g2.setFont(arial40);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x" + gp.player.hasKey, 74, 65);

            playTime += (double)1/60;
            g2.drawString("Time : " + dFormat.format(playTime), 11*gp.tileSize, 65);

            if (messageOn) {
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize/2, gp.tileSize*5);
                messageCounter ++;
                if (messageCounter > 90) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }

    }
}
