package ru.avalon.vergentev.j120.labwork4a;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;

public class BookAccounting extends JFrame implements ActionListener, WindowListener {
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
    JButton empty1 = new JButton("");
    JLabel codeForRemover = new JLabel("Code for remove book: ", SwingConstants.RIGHT);
    JTextField codeTextForRemover = new JTextField();
    JButton remove = new JButton("Remove");
    JButton empty2 = new JButton("");
    JButton showBooks = new JButton("Show books");
    JButton empty3 = new JButton("");

    File file = new File("books.dat");
    Properties data = new Properties();

    String[] column = {"CODE", "ISBN", "TITLE", "AUTHORS", "YEAR"};
    JFrame frameForTable = new JFrame();

    public BookAccounting() {
        setTitle("Books accounting");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(9,2));
        addWindowListener(this);

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
        add(empty1);
        addButton(add);

        add(codeForRemover);
        addTextField(codeTextForRemover);
        add(empty2);
        addButton(remove);

        add(empty3);
        addButton(showBooks);
    }

    private void addButton (JButton button) {
        button.addActionListener(this);
        add(button);
    }

    private void addTextField (JTextField field) {
        field.addActionListener(this);
        add(field);
    }

    private void addTable () {
        JTable table = new JTable(getDataFromPropertiesForTable(), column);
        JScrollPane scrollPane = new JScrollPane(table);
        frameForTable.add(scrollPane);
        frameForTable.setBounds(30, 40, 600, 600);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add) algorithmIfAddBookButtonIsPushed();
        else if (e.getSource() == remove) algorithmIfRemoveBookButtonIsPushed();
        else if (e.getSource() == showBooks) algorithmIfShowBooksButtonIsPushed();
    }

    private void algorithmIfAddBookButtonIsPushed() {
        BookParameters book = new BookParameters();
        codeText.getText();
        book.setIsbn(isbnText.getText().replaceAll("'", ""));
        book.setTitle(titleText.getText().replaceAll("'", ""));
        book.setAuthors(authorsText.getText().replaceAll("'", ""));
        book.setYear(yearText.getText().replaceAll("'", ""));
        if (!data.containsKey(codeText.getText())) {
            data.setProperty(codeText.getText(), String.valueOf(book));
            codeText.setText("");
            isbnText.setText("");
            titleText.setText("");
            authorsText.setText("");
            yearText.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "That code has another book", "WARNING", JOptionPane.INFORMATION_MESSAGE);
        }
        getDataFromPropertiesForTable();
    }

    private void algorithmIfRemoveBookButtonIsPushed() {
        int resultRemove = JOptionPane.showConfirmDialog(null, "Are you sure want to remove that book from data base ?", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (resultRemove == JOptionPane.OK_OPTION) {
            data.remove(codeTextForRemover.getText());
            codeTextForRemover.setText("");
        }
    }

    private void algorithmIfShowBooksButtonIsPushed() {
        addTable();
        frameForTable.setVisible(!frameForTable.isShowing());
    }

    private String[][] getDataFromPropertiesForTable() {
        String[][] arrayData = new String[data.size()][];
        int k = 0;
        for (Object i : data.keySet()) {
            BookParameters book = new BookParameters();
            String [] dataEachBookParameter = ((String)data.get(i)).split("'");
            for (int j = 1; j < dataEachBookParameter.length; j = j + 2) {
                if      (j == 1) book.setIsbn(dataEachBookParameter[j]);
                else if (j == 3) book.setTitle(dataEachBookParameter[j]);
                else if (j == 5) book.setAuthors(dataEachBookParameter[j]);
                else if (j == 7) book.setYear(dataEachBookParameter[j]);
            }
            arrayData[k] = new String[]{(String) i, book.getIsbn(), book.getTitle(), book.getAuthors(), book.getYear()};
            k++;
        }
        return arrayData;
    }

    @Override
    public void windowOpened(WindowEvent e) {loadData();}

    //метод читающий файл и возвращающий данные в память компьютера в виде Properties
    public Properties loadData () {
        try {
            if (!file.exists()) file.createNewFile();
            FileReader fileReader = new FileReader(file);
            data.load(fileReader);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        storeData();
        frameForTable.dispose();
    }

    public void storeData () {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            data.store(fileWriter, null);
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
