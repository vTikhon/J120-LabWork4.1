package ru.avalon.vergentev.j120.labwork4a;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class BooksAccounting extends JFrame implements ActionListener, WindowListener {
    FileReader fileReader;
    FileWriter fileWriter;
    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;
    JButton addBook = new JButton("Add Book");
    JButton removeBook = new JButton("Remove Book");
    JButton showBooks = new JButton("Show Books");
    File file = new File("books.dat");
    AdderBook adderBook = new AdderBook();
    RemoverBook removerBook = new RemoverBook();
    TableOfBooks tableOfBooks = new TableOfBooks();
    Book book = new Book();


    public BooksAccounting() {
        super("Books accounting");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new FlowLayout());
        addWindowListener(this);
        addButton(addBook);
        addButton(removeBook);
        addButton(showBooks);
    }

    //METHODS
    private void addButton (JButton button) {
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
        if (!adderBook.isActive()) {
            adderBook.setVisible(true);
        } else {
            adderBook.dispose();
        }
    }

    private void algorithmIfRemoveBookButtonIsPushed () {
        if (!removerBook.isActive()) {
            removerBook.setVisible(true);
        } else {
            removerBook.dispose();
        }
    }

    private void algorithmIfShowBooksButtonIsPushed () {
        if (!tableOfBooks.isActive()) {
            tableOfBooks.setVisible(true);
        } else {
            tableOfBooks.dispose();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {loadData(file);}

    //метод читающий файл и возвращающий данные в память компьютера
    public Properties loadData (File file) {
        System.out.println(adderBook.data.toString());
        try {
            if (!file.exists()) file.createNewFile();
            fileReader = new FileReader(file);
            adderBook.data.load(fileReader);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return adderBook.data;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        storeData(file);
        adderBook.dispose();
        removerBook.dispose();
        tableOfBooks.dispose();
    }

    public void storeData (File file) {
        System.out.println(adderBook.data.toString());
        try {
            fileWriter = new FileWriter(file, false);
            adderBook.data.store(fileWriter, "j120 task4.1 books");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {}
    @Override
    public void windowIconified(WindowEvent e) {}
    @Override
    public void windowDeiconified(WindowEvent e) {}
    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}
}
