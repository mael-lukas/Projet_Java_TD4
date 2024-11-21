import javax.imageio.ImageIO;
import javax.swing.*;

import java.io.File;
import java.security.Key;
import java.util.ArrayList;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    JFrame displayZoneFrame;
    RenderEngine renderEngine;
    int fps = 60;

    public Main() throws Exception {
        displayZoneFrame = new JFrame();
        displayZoneFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        displayZoneFrame.setTitle("TD4 dungeon crawler");
        displayZoneFrame.setSize(384,600);
        displayZoneFrame.setLocationRelativeTo(null);
        displayZoneFrame.setResizable(false);

        GameEngine gameEngine = new GameEngine();
        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png")),80,300,48,50,gameEngine);
        hero.setHitbox(10,18,27,28);

        RenderEngine renderEngine = new RenderEngine();
        PhysicsEngine physicsEngine = new PhysicsEngine();
        int delay = (int) 1500/fps; // 1 sec (in millisec) divided by fps : 16.666 millisec

        Timer renderTimer = new Timer(delay,(time)->renderEngine.update());
        //Timer gameTimer = new Timer(delay,(time)->gameEngine.update());
        Timer physicsTimer = new Timer(delay,(time)->physicsEngine.update());
        renderTimer.start();
        //gameTimer.start();
        physicsTimer.start();

        displayZoneFrame.getContentPane().add(renderEngine);

        Playground level = new Playground("./data/level1.txt");
        renderEngine.addToRenderList(level.getSpriteList());
        renderEngine.addToRenderList(hero);
        physicsEngine.addToMovingSpriteList(hero);
        physicsEngine.setEnvironment(level.getSolidSpriteList());

        displayZoneFrame.addKeyListener(gameEngine);
        displayZoneFrame.setVisible(true);

    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();
    }
}