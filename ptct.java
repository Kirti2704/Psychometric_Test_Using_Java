import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ptct extends JFrame implements ActionListener {

    private static final String[] WORDS = { "RED", "BLUE", "GREEN", "YELLOW", "BLACK", "WHITE", "ORANGE", "PURPLE", "PINK", "BROWN" };
    private static final Color[] COLORS = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN, Color.WHITE, Color.ORANGE, new Color(128, 0, 128), Color.PINK, new Color(139, 69, 19) };
    private static final int TEST_TIME = 30; 
    private static final int NUM_QUESTIONS = 10;

    private JLabel wordLabel; 
    private JButton[] optionButtons;
    private int[] answers;
    private int questionIndex;
    private int numCorrect;
    private Timer timer;
    private int timeLeft;

    public ptct() {
        setTitle("Color Test");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBackground(Color.BLACK);
        setContentPane(contentPane);

        wordLabel = new JLabel();
        wordLabel.setFont(new Font("Arial", Font.BOLD, 40));
        wordLabel.setHorizontalAlignment(JLabel.CENTER);
        wordLabel.setVerticalAlignment(JLabel.CENTER);
        wordLabel.setForeground(Color.WHITE);
        contentPane.add(wordLabel, BorderLayout.CENTER);

        JPanel optionPanel = new JPanel(new GridLayout(1, 2));
        optionButtons = new JButton[2];
        for (int i = 0; i < 2; i++) {
            optionButtons[i] = new JButton();
            optionButtons[i].setFont(new Font("Arial", Font.BOLD, 20));
            optionButtons[i].addActionListener(this);
            optionPanel.add(optionButtons[i]);
        }
        contentPane.add(optionPanel, BorderLayout.SOUTH);

        answers = new int[NUM_QUESTIONS];
        questionIndex = 0;
        numCorrect = 0;

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                if (timeLeft == 0) {
                    timer.stop();
                    showScore();
                } else {
                    setTitle("Color Test - Time Left: " + timeLeft + "s");
                }
            }
        });
        startTest();
    }

    private void startTest() {
        questionIndex = 0;
        numCorrect = 0;
        timeLeft = TEST_TIME;
        setTitle("Color Test - Time Left: " + timeLeft + "s");
        timer.start();
        askQuestion();
    }

    private void askQuestion() {
        int wordIndex = (int) (Math.random() * WORDS.length);
        int colorIndex = (int) (Math.random() * COLORS.length);
        wordLabel.setText(WORDS[wordIndex]);
        wordLabel.setForeground(COLORS[colorIndex]);

        int correctOptionIndex = (int) (Math.random() * 2);
        int incorrectOptionIndex = (correctOptionIndex + 1) % 2;

        optionButtons[correctOptionIndex].setText(getColorName(COLORS[colorIndex]));
        optionButtons[incorrectOptionIndex].setText(WORDS[wordIndex]);

        answers[questionIndex] = correctOptionIndex; 
        questionIndex++;
    }

    private String getColorName(Color color) {
        for (int i = 0; i < COLORS.length; i++) {
            if (COLORS[i].equals(color)) {
                return WORDS[i];
            }
        }
        return "";
    }

    private void checkAnswer(int optionIndex) {
        if (optionIndex == answers[questionIndex - 1]) {
            numCorrect++;
        }
        if (questionIndex == NUM_QUESTIONS) {
            timer.stop();
            showScore();
        } else {
            askQuestion();
        }
    }

    private void showScore() {
        double score = (double) numCorrect / NUM_QUESTIONS * 100;
        JOptionPane.showMessageDialog(this, "Time's up! You got " + numCorrect + " out of " + NUM_QUESTIONS + " correct.\nScore: " + String.format("%.2f", score) + "%");
        startTest();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < optionButtons.length; i++) {
            if (e.getSource() == optionButtons[i]) {
                checkAnswer(i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ptct().setVisible(true);
        });
    }
}