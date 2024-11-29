import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GamePanel {
    int fps = 60;
    Camera camera = new Camera(0,0);
    PlaygroundManager pgManager = new PlaygroundManager(camera);
    GameEngine gameEngine = new GameEngine();
    Player hero = new Player(ImageIO.read(new File("./img/heroTileSheetLowRes.png")),31*64,28*64,48, 50,camera,gameEngine,pgManager);
    RenderEngine renderEngine = new RenderEngine(pgManager);
    PhysicsEngine physicEngine = new PhysicsEngine(pgManager);

    public GamePanel() throws IOException {
        // Créer la JFrame
        JFrame frame = new JFrame("Jeu");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setLocationRelativeTo(null); //met l'écran au milieu

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
        //titleScreen.add(titleLabel, BorderLayout.WEST);

        JButton startButton = new JButton("Start");
        titleScreen.add(startButton, BorderLayout.CENTER);

        // Créer le deuxième écran (Écran de jeu)
        JPanel gameScreen = new JPanel();

        JLabel fulllife = new JLabel();
        fulllife.setIcon(new ImageIcon("./img/fulllife.png"));
        fulllife.setOpaque(false);

        JLabel threequarterlife = new JLabel();
        threequarterlife.setIcon(new ImageIcon("./img/3life.png"));
        threequarterlife.setOpaque(false);

        JLabel midlife = new JLabel();
        midlife.setIcon(new ImageIcon("./img/midlife.png"));
        midlife.setOpaque(false);

        JLabel onequarterlife = new JLabel();
        onequarterlife.setIcon(new ImageIcon("./img/1life.png"));
        onequarterlife.setOpaque(false);



        JLayeredPane layers = new JLayeredPane();
        layers.setPreferredSize(new Dimension(1920, 1080));

        renderEngine.setBounds(0, 0, 1920, 1080);
        fulllife.setBounds(50, 20, 200, 20);
        threequarterlife.setBounds(50, 20, 200, 20);
        midlife.setBounds(50, 20, 200, 20);
        onequarterlife.setBounds(50, 20, 200, 20);

        layers.add(renderEngine, JLayeredPane.DEFAULT_LAYER);

        switch (hero.getLife()){
            case 4 :
                layers.add(fulllife, JLayeredPane.PALETTE_LAYER);
                break;
            case 3 :
                layers.add(threequarterlife, JLayeredPane.PALETTE_LAYER);
                break;
            case 2 :
                layers.add(midlife, JLayeredPane.PALETTE_LAYER);
                break;
            case 1 :
                layers.add(onequarterlife, JLayeredPane.PALETTE_LAYER);
                break;
        }

        gameScreen.setLayout(new BorderLayout());
        gameScreen.add(layers, BorderLayout.CENTER);


        for (int i = 0; i < pgManager.playgroundList.size(); i++) {
            renderEngine.addToRenderList(hero,i);
        }
        physicEngine.addToMovingSpriteList(hero);
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
