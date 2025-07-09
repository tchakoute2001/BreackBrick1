package tchakoute;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

public class GamePanel extends JPanel implements KeyListener {

    private static final long serialVersionUID = 1L;
    private Gamelogica gameLogic;
    private int currentLevel = 1;
    private Timer timer;
    private BufferedImage backgroundImage;

    private enum GameState {
        PLAYING, GAME_OVER, LEVEL_COMPLETE
    }

    private GameState gameState = GameState.PLAYING;

    private JPanel overlayPanel;
    private JButton btnReplay, btnNext, btnExit;

    public GamePanel() {
        startLevel(currentLevel);
        setFocusable(true);
        addKeyListener(this);

        timer = new Timer(10, e -> {
            if (gameState == GameState.PLAYING) {
                gameLogic.update();
                if (gameLogic.isGameOver()) {
                    gameState = GameState.GAME_OVER;
                    showOverlay(false); // Non è vincitore
                } else if (gameLogic.isLevelComplete()) {
                    gameState = GameState.LEVEL_COMPLETE;
                    showOverlay(true); // È vincitore
                }
            }
            repaint();
        });
        timer.start();

        try {
            backgroundImage = ImageIO.read(getClass().getResource("image1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setupOverlayPanel();
    }

    private void setupOverlayPanel() {
        overlayPanel = new JPanel();
        overlayPanel.setOpaque(false);
        overlayPanel.setLayout(new BoxLayout(overlayPanel, BoxLayout.Y_AXIS));
        overlayPanel.setVisible(false);

        btnReplay = new JButton("Riprendere");
        btnNext = new JButton("Next");
        btnExit = new JButton("Exit");

        btnReplay.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnNext.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnReplay.addActionListener(e -> restartGame());
        btnNext.addActionListener(e -> nextLevel());
        btnExit.addActionListener(e -> System.exit(0));

        overlayPanel.add(btnReplay);
        overlayPanel.add(Box.createVerticalStrut(10));
        overlayPanel.add(btnNext);
        overlayPanel.add(Box.createVerticalStrut(10));
        overlayPanel.add(btnExit);

        setLayout(null);
        overlayPanel.setBounds(300, 200, 200, 150);
        add(overlayPanel);
    }

    private void startLevel(int level) {
        List<Mattone> bricks;
        if (level == 1) {
            bricks = Level.Livello1();
            backgroundImage = loadBackground("image1.jpg");
        } else {
            bricks = Level.Livello2();
            backgroundImage = loadBackground("image2.jpg");
        }
        gameLogic = new Gamelogica(bricks);
    }
    private BufferedImage loadBackground(String name) {
        try {
            return ImageIO.read(getClass().getResource(name));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void showOverlay(boolean isWinner) {
        overlayPanel.setVisible(true);
        btnNext.setVisible(isWinner); // Mostra 'Next' solo se si è vinto
    }

    public void restartGame() {
        gameState = GameState.PLAYING;
        overlayPanel.setVisible(false);
        startLevel(currentLevel);
    }

    public void nextLevel() {
        currentLevel++;
        gameState = GameState.PLAYING;
        overlayPanel.setVisible(false);
        startLevel(currentLevel);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }

        Racchetta racchetta = gameLogic.getRacchetta();
        Palla palla = gameLogic.getBall();

        g.setColor(Color.BLUE);
        g.fillRect(racchetta.x, racchetta.y, racchetta.width, racchetta.height);

        g.setColor(Color.WHITE);
        g.fillOval(palla.x, palla.y, palla.diameter, palla.diameter);

        for (Mattone m : gameLogic.getBricks()) {
            if (!m.destroyed) {
                g.setColor(Color.RED);
                g.fillRect(m.x, m.y, m.width, m.height);
            }
        }

        g.setColor(Color.YELLOW);
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Punteggio: " + gameLogic.getScore(), 10, 20);
        g.drawString("Vite: " + gameLogic.getLives(), 10, 40);

        if (gameState == GameState.GAME_OVER) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(Color.RED);
            g.drawString("GAME OVER", getWidth() / 2 - 100, getHeight() / 2 - 100);
        } else if (gameState == GameState.LEVEL_COMPLETE) {
            g.setFont(new Font("Arial", Font.BOLD, 40));
            g.setColor(Color.GREEN);
            g.drawString("WINNER", getWidth() / 2 - 100, getHeight() / 2 - 100);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Racchetta racchetta = gameLogic.getRacchetta();
        if (e.getKeyCode() == KeyEvent.VK_LEFT) racchetta.moveLeft();
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT) racchetta.moveRight();
        else if (e.getKeyCode() == KeyEvent.VK_R) restartGame();
        else if (e.getKeyCode() == KeyEvent.VK_N) nextLevel();
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}
