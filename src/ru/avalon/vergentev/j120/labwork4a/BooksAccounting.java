package ru.avalon.vergentev.j120.labwork4a;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BooksAccounting extends JFrame implements ActionListener {
    JButton addBook = new JButton("Add Book");
    JButton removeBook = new JButton("Remove Book");
    JButton showBooks = new JButton("Show Books");
    AdderBook addButton = new AdderBook();
    RemoverBook removeButton = new RemoverBook();
    TableOfBooks showsBookButton = new TableOfBooks();



    public BooksAccounting() throws HeadlessException {
        super("Books accounting");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());

        addButton(addBook);
        addButton(removeBook);
        addButton(showBooks);
    }

    //METHODS
    public void addButton (JButton button) {
        button.addActionListener(this);
        add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if      (e.getSource() == addBook) algorithmIfAddBookButtonIsPushed();
        else if (e.getSource() == removeBook) algorithmIfRemoveBookButtonIsPushed();
        else if (e.getSource() == showBooks) algorithmIfShowBooksButtonIsPushed();
    }

    private void algorithmIfAddBookButtonIsPushed () {
        if (!addButton.isActive()) {
            addButton.setVisible(true);
//            System.out.println("aaa");
        } else {
            addButton.dispose();
//            System.out.println("bbb");
        }
    }

    private void algorithmIfRemoveBookButtonIsPushed () {
        if (!removeButton.isActive()) {
            removeButton.setVisible(true);
        } else {
            removeButton.dispose();
        }
    }

    private void algorithmIfShowBooksButtonIsPushed () {
        if (!showsBookButton.isActive()) {
            showsBookButton.setVisible(true);
        } else {
            showsBookButton.dispose();
        }
    }


}
