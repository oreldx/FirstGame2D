package object;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.Objects;

public class ObjectChest extends SuperObject {

    UtilityTool uTool = new UtilityTool();
    GamePanel gp;

    public ObjectChest(GamePanel gp) {

        this.gp = gp;
        name = "Chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
