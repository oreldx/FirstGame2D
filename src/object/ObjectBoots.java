package object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Objects;

public class ObjectBoots extends SuperObject{

        GamePanel gp;
        UtilityTool uTool = new UtilityTool();

    public ObjectBoots(GamePanel gp) {

        name = "Boots";
        this.gp = gp;

        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/boots.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
