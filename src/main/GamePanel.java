package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16;
    final int scale = 3;

    public final int tileSize = originalTileSize * scale;  // 48x48 px
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize*maxScreenCol;  // 768 px
    public final int screenHeight = tileSize*maxScreenRow; // 576 px

    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int FPS = 60;

//  System
    KeyHandler keyH = new KeyHandler(this);
    TileManager tileM = new TileManager(this);
    Sound music = new Sound();
    Sound SE = new Sound();
    public AssetSetter assetSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);

//    Entity & Object
    public Player player = new Player(this, keyH);
    public SuperObject[] objects = new SuperObject[10];

//  Game Stat
    public int gameState;
    public final int playState = 1;
    public final int pauseState = 2;

    Thread gameThread;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        playMusic(0);
        gameState = playState;
    }

    public void startGameThread() {

       gameThread = new Thread(this);
       gameThread.start();
    }

    @Override
    public  void run() {
        double drawInterval = 1000000000.0/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            timer += currentTime - lastTime;
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();
                delta --;
                drawCount ++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS : " + drawCount);
                drawCount = 0;
                timer = 0;
            }

        }
    }

    public void update() {
        if (gameState == playState) {
            player.update();
        }
        if (gameState == pauseState) {

        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // DEBUG
        long drawStart = 0;
        if (keyH.checkDrawTime){
            drawStart = System.nanoTime();
        }


        tileM.draw(g2);

        for (SuperObject object : objects) {
            if (object != null) {

                object.draw(g2, this);
            }
        }

        player.draw(g2);

        ui.draw(g2);

        // DEBUG
        if (keyH.checkDrawTime){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.drawString(" "+passed, 10, 400);
        }

        g2.dispose();
    }

    public void playMusic(int i){

        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void splaySE(int i){
        SE.setFile(i);
        SE.play();
    }
}

// Premiere game loop
//    @Override
//    public void run() {
//
//        double drawInterval = 1000000000/FPS;   // 0.0166 seconds
//        double nextDrawTime = System.nanoTime() + drawInterval;
//
//        while (gameThread != null) {
//            // 1 Update information [Character Position]
//            update();
//            // 2 Draw the information with updated information
//            repaint();
//
//            try {
//                double remainingTime = nextDrawTime - System.nanoTime();
//                remainingTime /= 1000000;   // Conversion en mili pour la fonction sleep
//
//                if (remainingTime < 0) remainingTime = 0;
//
//                Thread.sleep((long) remainingTime);
//
//                nextDrawTime += drawInterval;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//        }
//    }