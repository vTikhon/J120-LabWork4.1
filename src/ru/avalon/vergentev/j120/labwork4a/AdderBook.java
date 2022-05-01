package ru.avalon.vergentev.j120.labwork4a;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AdderBook extends JFrame implements ActionListener {
    JLabel code = new JLabel("Code: ", SwingConstants.RIGHT);
    JTextField codeText = new JTextField();
    JLabel isbn = new JLabel("ISBN: ", SwingConstants.RIGHT);
    JTextField isbnText = new JTextField();
    JLabel title = new JLabel("Title: ", SwingConstants.RIGHT);
    JTextField titleText = new JTextField();
    JLabel authors = new JLabel("Authors: ", SwingConstants.RIGHT);
    JTextField authorsText = new JTextField();
    JLabel year = new JLabel("Year publishing: ", SwingConstants.RIGHT);
    JTextField yearText = new JTextField();
    JButton add = new JButton("Add");
    Book book;
    Properties data = new Properties();

    public AdderBook() throws HeadlessException {
        super("Add book with parameters");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6,2));

        add(code);
        addTextField(codeText);
        add(isbn);
        addTextField(isbnText);
        add(title);
        addTextField(titleText);
        add(authors);
        addTextField(authorsText);
        add(year);
        addTextField(yearText);
        addButton(add);
    }

    private void addButton (JButton button) {
        button.addActionListener(this);
        add(button);
    }

    private void addTextField (JTextField field) {
        field.addActionListener(this);
        add(field);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) {
            algorithmIfAddBookButtonIsPushed();
        }
    }

    private void algorithmIfAddBookButtonIsPushed() {
        book = new Book();
        codeText.getText();
        book.setIsbn(isbnText.getText());
        book.setTitle(titleText.getText());
        book.setAuthors(authorsText.getText());
        book.setYear(yearText.getText());
        if (!data.containsKey(codeText.getText())) {
            data.put(codeText.getText(), book);
            codeText.setText("");
            isbnText.setText("");
            titleText.setText("");
            authorsText.setText("");
            yearText.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "That code has another book", "WARNING", JOptionPane.INFORMATION_MESSAGE);
        }
        System.out.println("from add " + data.toString());
    }
}
