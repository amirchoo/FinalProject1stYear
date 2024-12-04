import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameMenu extends JFrame{
    private JButton startButton;
    private JButton leaderboardButton;
    private SlidePuzzleBoard board;
    private Leaderboard leaderboard;

    public GameMenu() {
        board = new SlidePuzzleBoard();
        leaderboard = new Leaderboard();

        setTitle("Puzzle Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create main panel with buttons
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        // Title label
        JLabel titleLabel = new JLabel("Welcome to Puzzle Game!", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 20));

        // Start button
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Serif", Font.PLAIN, 18));

        // Leaderboard button
        leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.setFont(new Font("Serif", Font.PLAIN, 18));

        // Add components to panel
        panel.add(titleLabel);
        panel.add(startButton);
        panel.add(leaderboardButton);

        // Add the panel to the JFrame
        add(panel, BorderLayout.CENTER);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PuzzleFrame frame = new PuzzleFrame(board, leaderboard);
                frame.setVisible(true);
                setVisible(false);
            }
        });

        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Leaderboard board = new Leaderboard();
                board.setVisible(true);
                setVisible(false);

            }
        });
    }


    public JButton getStartButton() {
        return startButton;
    }

    public JButton getLeaderboardButton() {
        return leaderboardButton;
    }
}
