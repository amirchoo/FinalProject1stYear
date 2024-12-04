import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Leaderboard extends JFrame {
    private JTextArea leaderboardTextArea;
    private List<String> leaderboardEntries;
    private JButton mainMenuButton;

    public Leaderboard() {
        setTitle("Leaderboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        leaderboardTextArea = new JTextArea();
        leaderboardTextArea.setEditable(false);
        add(new JScrollPane(leaderboardTextArea), BorderLayout.CENTER);

        leaderboardEntries = new ArrayList<>();

        // Load existing leaderboard entries from file
        loadLeaderboard();

        // Sort and display leaderboard
        sortLeaderboard();
        displayLeaderboard();

        // Create and add "Main Menu" button
        mainMenuButton = new JButton("Main Menu");
        mainMenuButton.setFont(new Font("Serif", Font.PLAIN, 18));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(mainMenuButton);
        add(buttonPanel, BorderLayout.SOUTH);

        mainMenuButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameMenu menu = new GameMenu();
                menu.setVisible(true);
                dispose();
            }
        });
    }

    public void addScore(String name, String time) {
        leaderboardEntries.add(name + "\t" + time);
        sortLeaderboard(); // Sort after adding the new score
        saveLeaderboard();
        displayLeaderboard();
    }

    // Load leaderboard from the file
    public void loadLeaderboard() {
        File file = new File("ranking.txt");
        try {
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    leaderboardEntries.add(line);
                }
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save leaderboard to the file
    private void saveLeaderboard() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("ranking.txt"));
            for (String entry : leaderboardEntries) {
                writer.write(entry);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Sort the leaderboard based on time
    private void sortLeaderboard() {
        Collections.sort(leaderboardEntries, new Comparator<String>() {
            @Override
            public int compare(String entry1, String entry2) {
                // Split entries into name and time parts
                String[] parts1 = entry1.split("\t");
                String[] parts2 = entry2.split("\t");

                // Parse times
                String time1 = parts1[1];
                String time2 = parts2[1];

                // Assuming the format is "mm:ss" (minutes:seconds)
                int totalSeconds1 = parseTimeToSeconds(time1);
                int totalSeconds2 = parseTimeToSeconds(time2);

                // Compare times
                return Integer.compare(totalSeconds1, totalSeconds2);
            }
        });
    }

    // Helper method to convert "mm:ss" format to total seconds
    private int parseTimeToSeconds(String time) {
        String[] parts = time.split(":");
        int minutes = Integer.parseInt(parts[0]);
        int seconds = Integer.parseInt(parts[1]);
        return minutes * 60 + seconds;
    }

    // Display the leaderboard in the text area
    public void displayLeaderboard() {
        leaderboardTextArea.setText("");
        for (String entry : leaderboardEntries) {
            leaderboardTextArea.append(entry + "\n");
        }
    }
}
