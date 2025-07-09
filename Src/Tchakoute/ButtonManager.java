package tchakoute;

import javax.swing.JButton;

public class ButtonManager {
    
	public JButton pauseButton;
	public JButton replayButton;
	public JButton exitButton;
	public JButton nextButton;
	public JButton playButton;
	public JButton returnButton;
	
    public ButtonManager() {
        pauseButton = createButton("");
        replayButton = createButton("Replay");
        exitButton = createButton("Exit");
        nextButton = createButton("Next");
        playButton = createButton("Play");
        returnButton = createButton("Return");
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        // style and actions can be defined here
        return button;
    }
}
