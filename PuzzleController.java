import javax.swing.*;

public class PuzzleController {

    private final GameMenu gameMenu;
    private final PuzzleFrame puzzleFrame;
    private final Leaderboard leaderboard;
    private final SlidePuzzleBoard slidePuzzleBoard;
    private final Time timer;
    private final StartButton startButton;

    public PuzzleController() {
        // Initialize all components
        gameMenu = new GameMenu();
        leaderboard = new Leaderboard();
        slidePuzzleBoard = new SlidePuzzleBoard();
        puzzleFrame = new PuzzleFrame(slidePuzzleBoard, leaderboard);
        timer = new Time((StartButton) puzzleFrame.getStartButton());
        startButton = new StartButton(slidePuzzleBoard, puzzleFrame);
        // Show the GameMenu first
        gameMenu.setVisible(true);
        puzzleFrame.setVisible(false); // Hide the game frame initially
    }

    }

