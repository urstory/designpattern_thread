import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import searcher.Display;
import searcher.Searcher;
import searcher.SearcherFactory;

public class MyFrame extends JFrame implements Display, ActionListener {
    private final JTextField textfield = new JTextField("word", 10);
    private final JButton button = new JButton("Search");
    private final JTextArea textarea = new JTextArea(20, 30);
    private final Searcher searcher = SearcherFactory.createSearcher();
    private final static String NEWLINE = System.getProperty("line.separator");

    public MyFrame() {
        super("ActiveObject Sample");
        getContentPane().setLayout(new BorderLayout());

        // North
        JPanel north = new JPanel();
        north.add(new JLabel("Search:"));
        north.add(textfield);
        north.add(button);
        button.addActionListener(this);

        // Center
        JScrollPane center = new JScrollPane(textarea);

        // Layout
        getContentPane().add(north, BorderLayout.NORTH);
        getContentPane().add(center, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // Search�ܥ��󤬲����줿�Ȥ�
    public void actionPerformed(ActionEvent e) {
        searchWord(textfield.getText());
    }

    // ɽ��
    private void println(String line) {
        textarea.append(line + NEWLINE);
    }

    // ����
    private void searchWord(String word) {
        // �����θƤӽФ�
        searcher.search(word, this);
        println("Searching " + word + "...");
    }

    // ɽ��
    public void display(final String line) {
        // ���٥�ȡ��ǥ����ѥå��󥰡�����åɤ�ɽ�������
        SwingUtilities.invokeLater(
            new Runnable() {
                public void run() {
                    MyFrame.this.println(line);
                }
            }
        );
    }
}
