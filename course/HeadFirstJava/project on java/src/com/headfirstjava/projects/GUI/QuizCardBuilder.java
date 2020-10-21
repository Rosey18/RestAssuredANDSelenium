/*import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class QuizCardBuilder {
    private JTextArea question;
    private JTextArea answer;
    private ArrayList<QuizCard> cardList;
    private JFrame frame;

    public static void main (String[] args) {
        QuizCardBuilder builder = new QuizCardBuilder();
        builder.go();
    }
    public void go() {
        frame = new JFrame("Quiz Card Builder");
        JPanel mainPanel = new JPanel();
        Font bigFont = new Font("sanserif", Font.BOLD, 24);
        question = new JTextArea(6, 20); //появившаяся окно будет иметь размер вместимостью с 6 строк за раз, 20 - это столбцы
        question.setLineWrap(true); //включаем перенос строки
        question.setWrapStyleWord(true);
        question.setFont(bigFont);

        JScrollPane qScroller = new JScrollPane(question); //создаем объект JScrollPane и присваеиваем ему текстовую область, которую он будет прокручивать
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        answer = new JTextArea(6,20);
        answer.setLineWrap(true);
        answer.setWrapStyleWord(true);
        answer.setFont(bigFont);

        JScrollPane aScroller = new JScrollPane(answer); //создаем объект JScrollPane и присваеиваем ему текстовую область, которую он будет прокручивать
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        JButton nextButton = new JButton(" Next Card");
        cardList = new ArrayList<QuizCard>();
        JLabel qLabel = new JLabel("Question");
        JLabel aLabel = new JLabel("Answer");

        mainPanel.add(qLabel);
        mainPanel.add(qScroller);
        mainPanel.add(aLabel);
        mainPanel.add(aScroller);
        mainPanel.add(nextButton);
        nextButton.addActionListener( new NextCardListener());
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem newMenuItem = new JCheckBoxMenuItem("New");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        newMenuItem.addActionListener(new NewMenuListener());

        saveMenuItem.addActionListener(new SaveMenuListener());
        menu.add(newMenuItem);
        menu.add(saveMenuItem);
        menuBar.add(menu);
        frame.setJMenuBar(menuBar);
        frame.add(BorderLayout.CENTER, mainPanel);
        frame.setSize(500, 600);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public class NextCardListener implements ActionListener {
        public void ActionPerformed (ActionEvent ev) {
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);
            clearCard();
        }
    }
    public class SaveMenuListener implements ActionListener {
        public void ActionPerformed (ActionEvent ev) {
            QuizCard card = new QuizCard(question.getText(), answer.getText());
            cardList.add(card);

            JFileChooser fileSave = new JFileChooser(); //вызывается диологовое окно, и программа останавливается на жтой строке, пока пользователь не выберет меню Save
            fileSave.showSaveDialog(frame);             //всю навигацию, выбор файла и т.д. за вас выполняет класс JFileChooser
            saveFile(fileSave.getSelectedFile());       //saveFile - это метод, посмотри на 98 строку
        }
    }
    public class NewMenuListener implements ActionListener {
        public void ActionPerformed (ActionEvent ev) {
            cardList.clear();
            clearCard();
        }
    }
    private void clearCard() {
        question.setText("");
        answer.setText("");
        question.requestFocus();
    }
    private void saveFile (File file) {
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));

            for(QuizCard:cardList){
                bw.write(card.getQuestion() + "/");
                bw.write(card.getAmswer() + " \n");
            }
            bw.close();
        }catch (IOException ex) {
            System.out.println("couldn't write the cardList out");
            ex.printStackTrace();
        }
    }
}*/
