package main;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UtilityTool {

    public BufferedImage scaleImage (BufferedImage originalImage, int widht, int height) {

        BufferedImage scaledImage = new BufferedImage(widht, height, originalImage.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(originalImage, 0, 0, widht, height, null);
        g2.dispose();

        return scaledImage;
    }
}
