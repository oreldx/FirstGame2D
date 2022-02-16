package object;

import javax.imageio.ImageIO;
import javax.xml.namespace.QName;
import java.io.IOException;
import java.util.Objects;

public class ObjectChest extends SuperObject {

    public ObjectChest() {

        name = "Chest";
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/objects/chest.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
