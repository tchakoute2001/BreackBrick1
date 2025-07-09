package tchakoute;

import java.awt.Rectangle;

//creazione di una classe per i mattoni per stockarla in una tabella oppur una lista

public class Racchetta {
    public int x = GameConstants.WINDOW_WIDTH / 2 - GameConstants.PADDLE_WIDTH / 2;
    public final int y = GameConstants.WINDOW_HEIGHT - 50;
    public final int width = GameConstants.PADDLE_WIDTH;
    public final int height = GameConstants.PADDLE_HEIGHT;

    public void moveLeft() {
        if (x > 0) x -= 20;
    }

    public void moveRight() {
        if (x < GameConstants.WINDOW_WIDTH - width) x += 20;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}
