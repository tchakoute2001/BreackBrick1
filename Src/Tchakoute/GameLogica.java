	package tchakoute;
	
	import java.util.ArrayList;
	import java.util.List;
	
	public class Gamelogica {
	    private List<Mattone> bricks;
	    private Palla ball;
	    private Racchetta racchetta;
	    private int score = 0;
	    private int lives = 3;
	    private Sounds audioPlayer;
	
	    public Gamelogica(List<Mattone> bricks) {
	        this.bricks = bricks;
	        this.racchetta = new Racchetta();
	        this.ball = new Palla(racchetta);
	        
	        this.audioPlayer = new Sounds();
	        audioPlayer.playBackgroundMusic("allgioco.wav");
	    }
	
	    private List<Palla> balls = new ArrayList<>();
	
	    public Gamelogica(List<Mattone> bricks, Racchetta racchetta) {
	        this.bricks = bricks;
	        this.racchetta = racchetta;
	        this.audioPlayer = new Sounds();
	        balls.add(new Palla(racchetta));
	        audioPlayer.playBackgroundMusic("allgioco.wav");
	    }
	
	    public void update() {
	        List<Palla> ballsToRemove = new ArrayList<>();
	        for (Palla ball : balls) {
	            ball.move();
	            if (ball.getBounds().intersects(racchetta.getBounds())) {
	                ball.yVelocita = -Math.abs(ball.yVelocita);
	            }
	
	            for (Mattone m : bricks) {
	                if (!m.destroyed && ball.getBounds().intersects(m.getBounds())) {
	                    if (m.indistruttibile) {
	                        ball.yVelocita = -ball.yVelocita;
	                    } else {
	                        m.destroyed = true;
	                        score += 100;
	                        ball.yVelocita = -ball.yVelocita;
	                        audioPlayer.playSoundEffect("collision.wav");
	                        if (m.speciale) {
	                            balls.add(new Palla(ball.x, ball.y));
	                            balls.add(new Palla(ball.x, ball.y));
	                        }
	                    }
	                    break;
	                }
	            }
	
	            if (ball.y > GameConstants.WINDOW_HEIGHT) {
	                ballsToRemove.add(ball);
	            }
	        }
	
	        balls.removeAll(ballsToRemove);
	        if (balls.isEmpty()) {
	            lives--;
	            if (lives > 0) {
	                balls.add(new Palla(racchetta));
	            }
	        }
	    }
	
	    private void resetBallAndPaddle() {
	        this.racchetta = new Racchetta();
	        this.ball = new Palla(racchetta);
	    }
	
	    public boolean isGameOver() {
	        return lives <= 0;
	    }
	
	    public int getScore() {
	        return score;
	    }
	
	    public int getLives() {
	        return lives;
	    }
	
	    public List<Mattone> getBricks() {
	        return bricks;
	    }
	
	    public Racchetta getRacchetta() {
	        return racchetta;
	    }
	
	    public Palla getBall() {
	        return ball;
	    }
	
	    public boolean isLevelComplete() {
	        for (Mattone m : bricks) {
	            if (!m.destroyed) return false;
	        }
	        return true;
	    }
	}
