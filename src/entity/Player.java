package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = (gp.screenWidth/2)-(gp.tileSize/2);
        screenY = (gp.screenHeight/2)-(gp.tileSize/2);

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

            up1 = setup("boy_up_1");
            up2 = setup("boy_up_2");
            down1 = setup("boy_down_1");
            down2 = setup("boy_down_2");
            left1 = setup("boy_left_1");
            left2 = setup("boy_left_2");
            right1 = setup("boy_right_1");
            right2 = setup("boy_right_2");
    }

    public BufferedImage setup(String imageName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage scaleImage = null;
        try {
            scaleImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/player/" + imageName + ".png")));
            scaleImage = uTool.scaleImage(scaleImage, gp.tileSize, gp.tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaleImage;
    }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed){
            if (keyH.upPressed) {
                direction = "up";

            }
            else if (keyH.downPressed) {
                direction = "down";

            }
            else if (keyH.leftPressed) {
                direction = "left";

            }
            else if (keyH.rightPressed) {
                direction = "right";

            }

            // Check Tile Collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);

            // Check Object Collision
            int objectIndex =  gp.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }

            spriteCounter ++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) spriteNum = 2;
                else if (spriteNum == 2) spriteNum = 1;
                spriteCounter = 0;
            }
        }

    }

    public void pickUpObject(int i) {
        if (i != 999) {
            String objectName = gp.objects[i].name;
            switch (objectName) {
                case "Key" -> {
                    gp.splaySE(1);
                    hasKey++;
                    gp.objects[i] = null;
                    gp.ui.showMessage("You got a key!");
                }
                case "Door" -> {
                    if (hasKey > 0) {
                        gp.splaySE(3);
                        gp.objects[i] = null;
                        hasKey--;
                        gp.ui.showMessage("You opened the door!");
                    }
                    else gp.ui.showMessage("You opened the door!");
                }
                case "Boots" -> {
                    gp.splaySE(2);
                    speed += 2;
                    gp.objects[i] = null;
                    gp.ui.showMessage("Speed Up!");
                }
                case "Chest" -> {
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.splaySE(4);
                }

            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) image = up2;
                if (spriteNum == 2) image = up1;
            }
            case "down" -> {
                if (spriteNum == 1) image = down2;
                if (spriteNum == 2) image = down1;
            }
            case "right" -> {
                if (spriteNum == 1) image = right2;
                if (spriteNum == 2) image = right1;
            }
            case "left" -> {
                if (spriteNum == 1) image = left2;
                if (spriteNum == 2) image = left1;
            }
        }

        g2.drawImage(image, screenX, screenY, null);
    }
}
