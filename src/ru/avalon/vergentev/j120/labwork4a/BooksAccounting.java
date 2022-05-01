package ru.avalon.vergentev.j120.labwork4a;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BooksAccounting extends JFrame implements ActionListener, KeyListener {
    JButton addBook = new JButton("Add Book");
    JButton removeBook = new JButton("Remove Book");
    JButton showBooks = new JButton("Show Books");
    String pole1String = "";
    JLabel textLabel1 = new JLabel(pole1String);


    public BooksAccounting() throws HeadlessException {
        super("Books accounting");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3,1));

        setButtonInterface(addBook);
        setButtonInterface(removeBook);
        setButtonInterface(showBooks);


    }

    //METHODS
    //method which preset the appearance of the buttons (for constructor)
    public void setButtonInterface (JButton button) {
        button.addActionListener(this);
        add(button);
    }

    //method which preset the appearance of the text label (for constructor)
    public void setLabelInterface (JPanel jPanel, JLabel label,
                                   GridBagConstraints labelPosition) {
        add(label, labelPosition);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if      (e.getSource() == addBook) algorithmIfAddBookButtonIsPushed();
        else if (e.getSource() == removeBook) algorithmIfRemoveBookButtonIsPushed();
        else if (e.getSource() == showBooks) algorithmIfShowBooksButtonIsPushed();
    }

    public void algorithmIfAddBookButtonIsPushed () {

    }

    public void algorithmIfRemoveBookButtonIsPushed () {

    }

    public void algorithmIfShowBooksButtonIsPushed () {

    }





    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
