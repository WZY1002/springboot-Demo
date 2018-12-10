package printer.print;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vive on 2014/11/2.
 */

public class windowstyle extends JFrame implements ActionListener {
    private JButton chooseBtn = new JButton("选择文本文件");
    private JButton pressBtn = new JButton("打印");

    private JLabel label = new JLabel("打印程序");
    private JTextArea textArea = new JTextArea(20, 80);
    JFileChooser chooser;
    List<String> textList = new ArrayList<String>();
    List<String> account = new ArrayList<String>(), password = new ArrayList<String>();

    private class WindowCloser extends WindowAdapter {

        public void windowClosing(WindowEvent we) {
            System.exit(0);
        }
    }


    public windowstyle() {
        super("打印");
        Container content = getContentPane();
        content.setLayout(new FlowLayout());
        content.add(label);
        content.add(chooseBtn);
        content.add(pressBtn);
        JScrollPane textScroll = new JScrollPane(textArea);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        content.add(textScroll);

        //添加按钮监听事件
        pressBtn.addActionListener(this);
        chooseBtn.addActionListener(this);
        chooser = new JFileChooser(".");

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(this);
            pack();
        } catch (Exception e) {
            System.err.println("Exception: " + e);
        }
        validate();
        pack();
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == chooseBtn) {
            int result = chooser.showDialog(this, "");
            if (result == JFileChooser.APPROVE_OPTION) {

                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(
                            chooser.getSelectedFile().getPath()));
                    textList.clear();
                    if (!textList.isEmpty())
                        textList.removeAll(textList);
                    textArea.setText("需要打印的列表");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                String s;
                try {
                    while ((s = br.readLine()) != null) {
                        s = s.replaceAll("^\\s+", "");
                        if (s.length() == 0)
                            continue;
                        textList.add(s);
                        textArea.setText(textArea.getText() + "\n" + s);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if (ae.getSource() == pressBtn) {
            account.clear();
            password.clear();
            if (textList != null && textList.size() != 0) {
                for (String item : textList) {
                    String[] array = item.split("\\s+");
                    account.add(array[0]);
                    password.add(array[1]);
                }

                textArea.setText("开始打印");
                PrintTest.printInterface(textArea, account, password);
            }
        }
    }

    public static void main(String args[]) {
//        windowstyle style = new windowstyle();
//        PrintTest p=new PrintTest(account, password);

    }
}

