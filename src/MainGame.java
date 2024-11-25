import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class MainGame {
    RenderEngine renderEngine;
    GameEngine gameEngine;
    PhysicsEngine physicEngine;
    int fps = 60;

    public MainGame() throws IOException {
        // Créer la JFrame
        JFrame frame = new JFrame("Jeu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null); //met l'écran au milieu

        //on gère les Engines + le héro
        PlaygroundManager pgManager = new PlaygroundManager();
        gameEngine = new GameEngine();
        DynamicSprite hero = new DynamicSprite(ImageIO.read(new File("./img/heroTileSheetLowRes.png")),200, 300,  48, 50,gameEngine,pgManager);
        hero.setHitbox(10,18,27,28);
        renderEngine = new RenderEngine(pgManager);
        physicEngine = new PhysicsEngine(pgManager);

        int delay = 1000/fps;
        Timer renderTimer = new Timer(delay, (time) -> renderEngine.update());
        //Timer gameTimer = new Timer(50, (time) -> gameEngine.update());
        Timer physicTimer = new Timer(delay, (time) -> physicEngine.update());

        renderTimer.start();
        //gameTimer.start();
        physicTimer.start();

        // Créer un conteneur avec un CardLayout
        JPanel cardPanel = new JPanel(new CardLayout());

        // Créer le premier écran (Écran titre)
        JPanel titleScreen = new JPanel();
        //titleScreen.setBackground(Color.CYAN);
        titleScreen.setLayout(new BorderLayout());

        //JLabel titleLabel = new JLabel("Écran Titre", SwingConstants.CENTER);
        //titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
       // titleScreen.add(titleLabel, BorderLayout.WEST);

        JButton startButton = new JButton("Start");
        titleScreen.add(startButton, BorderLayout.CENTER);

        // Créer le deuxième écran (Écran de jeu)
        JPanel gameScreen = new JPanel();
        gameScreen.setLayout(new BorderLayout());
        gameScreen.add(renderEngine, BorderLayout.CENTER);

        gameScreen.add(renderEngine);

        //Playground level = new Playground("./data/level1.txt");
        //renderEngine.addToRenderList(level.getSpriteList());
        //renderEngine.addToRenderList(hero);
        for (int i = 0; i < pgManager.playgroundList.size(); i++) {
            renderEngine.addToRenderList(hero,i);
        }
        physicEngine.addToMovingSpriteList(hero);
        //physicEngine.setEnvironment(level.getSolidSpriteList());

        // Ajouter une barre de vie simulée
        /*
        JPanel lifePanel = new JPanel();
        JLabel lifeLabel = new JLabel("Vie : 3");
        JButton loseLifeButton = new JButton("Perdre une vie");
        lifePanel.add(lifeLabel);
        lifePanel.add(loseLifeButton);
        gameScreen.add(lifePanel, BorderLayout.SOUTH);

        // Créer le troisième écran (Game Over)
        JPanel gameOverScreen = new JPanel();
        gameOverScreen.setBackground(Color.RED);
        gameOverScreen.setLayout(new BorderLayout());

        JLabel gameOverLabel = new JLabel("Game Over", SwingConstants.CENTER);
        gameOverLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gameOverLabel.setForeground(Color.WHITE);
        gameOverScreen.add(gameOverLabel, BorderLayout.CENTER);

        JButton retryButton = new JButton("Retry");
        gameOverScreen.add(retryButton, BorderLayout.SOUTH);
        */

        // Ajouter les écrans au CardLayout
        cardPanel.add(titleScreen, "TitleScreen");
        cardPanel.add(gameScreen, "GameScreen");

        //cardPanel.add(gameOverScreen, "GameOverScreen");

        // Ajouter une action au bouton Start
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "GameScreen");
            }
        });

        // Variable pour la vie du joueur
       // final int[] playerLife = {3};

        // Ajouter une action au bouton "Perdre une vie"
        /*
        loseLifeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Réduire la vie du joueur
                playerLife[0]--;
                lifeLabel.setText("Vie : " + playerLife[0]);

                // Si la vie tombe à zéro, passer à l'écran "Game Over"
                if (playerLife[0] <= 0) {
                    CardLayout cl = (CardLayout) cardPanel.getLayout();
                    cl.show(cardPanel, "GameOverScreen");
                }
            }
        });

        // Ajouter une action au bouton Retry
        retryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Réinitialiser la vie du joueur
                playerLife[0] = 3;
                lifeLabel.setText("Vie : " + playerLife[0]);

                // Retourner à l'écran titre
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "TitleScreen");
            }
        });
        */
        frame.add(cardPanel);
        frame.setVisible(true);
        frame.addKeyListener(gameEngine);
        frame.setFocusable(true);
        frame.requestFocusInWindow();



    }
}
