import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class PsychometricTest extends JFrame implements ActionListener {
    private JLabel label;
    private JRadioButton[] options;
    private JButton submit;
    private ButtonGroup group;
    private int questionIndex;
    private int score;
    private String[][] questions = {
      {"Question 1: Which of the following best describes you?", "A. I am outgoing and sociable", "B. I am reserved and introspective", "C. I am both outgoing and reserved", "D. I am neither outgoing nor reserved"},
      {"Question 2: How do you handle stress?", "A. I confront it head on", "B. I try to avoid it", "C. I seek support from others", "D. I distract myself with other activities"},
      {"Question 3: Which of the following statements do you most agree with?", "A. I am detail-oriented and precise", "B. I am creative and imaginative", "C. I am analytical and logical", "D. I am intuitive and spontaneous"},
      {"Question 4: How do you make decisions?", "A. I weigh the pros and cons carefully", "B. I go with my gut feeling", "C. I seek advice from others", "D. I avoid making decisions if possible"},
      {"Question 5: Which of the following is most important to you?", "A. Financial security", "B. Personal growth", "C. Strong relationships", "D. Adventure and excitement"},
      {"Question 6: How do you prefer to work?", "A. Alone", "B. With a team", "C. It depends on the task", "D. I don't have a preference"},
      {"Question 7: How do you deal with criticism?", "A. I take it as a learning opportunity", "B. I get defensive and upset", "C. I ignore it", "D. It depends on who is criticizing me"},
      {"Question 8: Which of the following is most important to you in a job?", "A. Job security", "B. Interesting work", "C. High salary", "D. Good work-life balance"},
      {"Question 9: How do you handle conflicts?", "A. I try to find a compromise", "B. I stand my ground and argue my point", "C. I try to avoid the conflict", "D. I seek mediation from a third party"},
      {"Question 10: Which of the following do you value most?", "A. Stability and predictability", "B. Variety and change", "C. Tradition and heritage", "D. Innovation and progress"}
};
private char[] answers = {'A', 'B', 'C', 'A', 'C', 'C', 'A', 'B', 'A', 'D'};
    public PsychometricTest() {
        setTitle(" Psychometric Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setResizable(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        
        try {
            // Set the look and feel to the system default
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(255, 204, 204));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        label = new JLabel(questions[0][0], SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 24));
        mainPanel.add(label, BorderLayout.NORTH);
        
        JPanel questionPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        questionPanel.setBackground(new Color(255, 204, 229));
        questionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        options = new JRadioButton[4];
        group = new ButtonGroup();
        for (int i = 0; i < options.length; i++) {
            options[i] = new JRadioButton(questions[questionIndex][i + 1]);
            options[i].setActionCommand(Character.toString((char) ('A' + i)));
            options[i].setFont(new Font("Arial", Font.PLAIN, 16));
            options[i].setBackground(new Color(255, 204, 204));
            options[i].setOpaque(false);
            group.add(options[i]);
            questionPanel.add(options[i]);
        }
        
        mainPanel.add(questionPanel, BorderLayout.CENTER);
        
        submit = new JButton("Submit");
        submit.setPreferredSize(new Dimension(100, 40));
        submit.setBackground(new Color(255, 102, 102));
        submit.setForeground(Color.BLACK);
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.addActionListener(this);
        submit.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        JPanel submitPanel = new JPanel(new FlowLayout());
        submitPanel.setBackground(new Color(255, 204, 204));
        submitPanel.add(Box.createHorizontalGlue());
        submitPanel.add(submit);
        submitPanel.add(Box.createHorizontalGlue());
        
        mainPanel.add(submitPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent e) {
        if (group.getSelection() == null) {
            JOptionPane.showMessageDialog(this, "Please select an option", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        char selected = group.getSelection().getActionCommand().charAt(0);
        if (selected == answers[questionIndex]) {
            score++;
        }
        
        questionIndex++;
        
        if (questionIndex < questions.length) {
            label.setText(questions[questionIndex][0]);
            for (int i = 0; i < options.length; i++) {
                options[i].setText(questions[questionIndex][i + 1]);
                options[i].setSelected(false);
            }
        } else {
            if (score >= 6 && score <= 10) {
                JOptionPane.showMessageDialog(this, "Very well done!", "Results", JOptionPane.INFORMATION_MESSAGE);
            } else if (score >= 4 && score < 6) {
                JOptionPane.showMessageDialog(this, "Some work is needed, do visit us again", "Results", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Time to reassess yourself. Best of luck!", "Results", JOptionPane.INFORMATION_MESSAGE);
            }
            System.exit(0);
        }
    }
    
    public static void main(String[] args) {
        new PsychometricTest();
    }
  }    