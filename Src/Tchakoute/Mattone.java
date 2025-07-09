package tchakoute;
//creazione di una classe per i mattoni per stockarla in una tabella oppur una lista


import java.awt.Rectangle;

public class Mattone {
    public static final boolean speciale = false;
	public static final boolean indistruttibile = false;
	public int x, y, width, height;
    public boolean destroyed = false;
    public BrickType type;

    public enum BrickType {
        NORMAL, INDESTRUCTIBLE, POWER
    }
 // Costruttore per mattoni normali o indistruttibili
    public Mattone(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    } 

    public Mattone(int x, int y, int width, int height, BrickType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}


