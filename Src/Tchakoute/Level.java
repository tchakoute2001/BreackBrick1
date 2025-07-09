package tchakoute;

import java.util.ArrayList;
import java.util.List;

public class Level {
    
	public static List<Mattone> bricks = new ArrayList<>();
    
	public static List<Mattone> Livello1() {
		int brickWidth = 60;
        int brickHeight = 20;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 10; col++) {
                int x = 60 + col * (brickWidth + 10);
                int y = 50 + row * (brickHeight + 10);
                bricks.add(new Mattone(x, y, brickWidth, brickHeight));
            }
        }
		return bricks;
    }
	public static List<Mattone> Livello2() {
	    List<Mattone> bricks = new ArrayList<>();
	    int brickWidth = 60;
	    int brickHeight = 20;

	    for (int row = 0; row < 3; row++) {
	        for (int col = 0; col < 10; col++) {
	            int x = 60 + col * (brickWidth + 10);
	            int y = 50 + row * (brickHeight + 10);
	            
	            if (row == 0 && col == 5) {
	                // Mattone speciale (con stella)
	                bricks.add(new Mattone(x, y, brickWidth, brickHeight, Mattone.BrickType.POWER));
	            } else if (row == 1 && (col == 2 || col == 7)) {
	                // Mattoni indistruttibili
	                bricks.add(new Mattone(x, y, brickWidth, brickHeight, Mattone.BrickType.INDESTRUCTIBLE));
	            } else {
	                // Mattoni normali
	                bricks.add(new Mattone(x, y, brickWidth, brickHeight, Mattone.BrickType.NORMAL));
	            }
	        }
	    }
	    return bricks;
	} 
}
