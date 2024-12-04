import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButton extends JButton{
    private SlidePuzzleBoard board;
    private PuzzleFrame frame;
    private Time time;

    public StartButton(SlidePuzzleBoard b, PuzzleFrame f) {
        super("Start");
        board = b;
        frame = f;
        time = new Time(this);

        setFont(new Font("Arial", Font.BOLD, 25));

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.createPuzzleBoard();
                frame.update();
                setText("Reshuffle");
            }
        });

    }
}
