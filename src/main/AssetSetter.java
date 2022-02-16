package main;

import object.ObjectBoots;
import object.ObjectChest;
import object.ObjectDoor;
import object.ObjectKey;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {

        this.gp = gp;
    }

    public void setObject() {

        gp.objects[0] = new ObjectKey();
        gp.objects[0].worldX = 23*gp.tileSize;
        gp.objects[0].worldY = 7*gp.tileSize;

        gp.objects[1] = new ObjectKey();
        gp.objects[1].worldX = 23*gp.tileSize;
        gp.objects[1].worldY = 40*gp.tileSize;

        gp.objects[2] = new ObjectDoor();
        gp.objects[2].worldX = 10*gp.tileSize;
        gp.objects[2].worldY = 11*gp.tileSize;

        gp.objects[3] = new ObjectDoor();
        gp.objects[3].worldX = 8*gp.tileSize;
        gp.objects[3].worldY = 28*gp.tileSize;

        gp.objects[4] = new ObjectDoor();
        gp.objects[4].worldX = 12*gp.tileSize;
        gp.objects[4].worldY = 22*gp.tileSize;

        gp.objects[5] = new ObjectChest();
        gp.objects[5].worldX = 10*gp.tileSize;
        gp.objects[5].worldY = 7*gp.tileSize;

        gp.objects[6] = new ObjectBoots();
        gp.objects[6].worldX = 37*gp.tileSize;
        gp.objects[6].worldY = 42*gp.tileSize;
    }
}
