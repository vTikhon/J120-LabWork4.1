package ru.avalon.vergentev.j120.labwork4a;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

public class AdderBook extends JFrame implements ActionListener {
    Book book;
    Properties data = new Properties();
    JLabel code = new JLabel("Code: ");
    JTextField codeText = new JTextField();
    JLabel isbn = new JLabel("ISBN: ");
    JTextField isbnText = new JTextField(book.getIsbn());
    JLabel title = new JLabel("Title: ");
    JTextField titleText = new JTextField(book.getTitle());
    JLabel authors = new JLabel("Authors: ");
    JTextField authorsText = new JTextField(book.getAuthors());
    JLabel year = new JLabel("Year publishing: ");
    JTextField yearText = new JTextField(book.getYear());
    JButton add = new JButton("Add");

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

    public void addButton (JButton button) {
        button.addActionListener(this);
        add(button);
    }

    public void addTextField (JTextField field) {
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

    }


}
