package tchakoute;

import java.awt.Rectangle;

public class Palla {
    public int x, y;
    public int xvelocita = 2;
    public int yVelocita = -2;
    public final int diameter;
    
    // Costruttore principale, usato per creare la prima palla collegata alla racchetta
    public Palla(Racchetta racchetta) {
        this.diameter = GameConstants.BALL_DIAMETER;
        this.x = racchetta.x + racchetta.width / 2 - diameter / 2;
        this.y = racchetta.y - diameter;
    }
    
    public Palla(int x, int y) {
        this.diameter = GameConstants.BALL_DIAMETER;
        this.x = x;
        this.y = y;
    }

    // Costruttore alternativo per le palle extra
    public Palla(int x, int y, int vx, int vy) {
        this.diameter = GameConstants.BALL_DIAMETER;
        this.x = x;
        this.y = y;
        this.xvelocita = vx;
        this.yVelocita = vy;
    }


	public void move() {
        x += xvelocita;
        y += yVelocita;

        if (x <= 0 || x >= GameConstants.WINDOW_WIDTH - diameter) {
            xvelocita = -xvelocita;
        }
        if (y <= 0) {
            yVelocita = -yVelocita;
        }
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, diameter, diameter);
    }
}

