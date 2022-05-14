package ru.avalon.vergentev.j120.labwork4a;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;

public class BookAccounting extends JFrame implements WindowListener {
    private final File file = new File("books.dat");
    public static final Properties books = new Properties();
    private final BookDialog bookDialog = new BookDialog(this);
    private final BookTableModel booksTableModel = new BookTableModel();
    private JTable table;

    public BookAccounting() {
        loadData();
        setTitle("Books accounting");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setResizable(true);
        setLocationRelativeTo(null);
        addWindowListener(this);
        initTable();
        initMenu();
    }

    private void initTable () {
        table = new JTable(booksTableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu operations = new JMenu("Operations");
        operations.setMnemonic('O');
        menuBar.add(operations);
        addMenuItemTo(operations, "Add", 'A',
                KeyStroke.getKeyStroke('A', InputEvent.ALT_DOWN_MASK),
                e -> algorithmIfAddBookButtonIsPushed());
        addMenuItemTo(operations, "Change", 'C',
                KeyStroke.getKeyStroke('C', InputEvent.ALT_DOWN_MASK),
                e -> algorithmIfChangeBookButtonIsPushed());
        addMenuItemTo(operations, "Delete", 'D',
                KeyStroke.getKeyStroke('D', InputEvent.ALT_DOWN_MASK),
                e -> algorithmIfRemoveBookButtonIsPushed());
        setJMenuBar(menuBar);
    }

    private void addMenuItemTo(JMenu parent, String text, char mnemonic, KeyStroke accelerator, ActionListener al) {
        JMenuItem menuItem = new JMenuItem(text, mnemonic);
        menuItem.setAccelerator(accelerator);
        menuItem.addActionListener(al);
        parent.add(menuItem);
    }

    private void algorithmIfAddBookButtonIsPushed() {
        if (bookDialog.isFrameOpened()) {
            BookParameters book = new BookParameters();
            bookDialog.getCodeFromTextField();
            book.setIsbn(bookDialog.getIsbnFromTextField().replaceAll("'", ""));
            book.setBookTitle(bookDialog.getTitleFromTextField().replaceAll("'", ""));
            book.setAuthors(bookDialog.getAuthorsFromTextField().replaceAll("'", ""));
            book.setYear(bookDialog.getYearFromTextField().replaceAll("'", ""));
            if (!books.containsKey(bookDialog.getCodeFromTextField())) {
                books.setProperty(bookDialog.getCodeFromTextField(), String.valueOf(book));
                booksTableModel.insertObjectInNewRow();
                bookDialog.setCodeForTextField("");
                bookDialog.setIsbnForTextField("");
                bookDialog.setTitleForTextField("");
                bookDialog.setAuthorsForTextField("");
                bookDialog.setYearForTextField("");
            } else {
                JOptionPane.showMessageDialog(null, "That code has another book", "WARNING", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    private void algorithmIfChangeBookButtonIsPushed() {
        if (table.getSelectedRow() == -1) return;
        bookDialog.prepareForChange(table);
        if (bookDialog.isFrameOpened()) {
            BookParameters book = new BookParameters();
            bookDialog.getCodeFromTextField();
            book.setIsbn(bookDialog.getIsbnFromTextField().replaceAll("'", ""));
            book.setBookTitle(bookDialog.getTitleFromTextField().replaceAll("'", ""));
            book.setAuthors(bookDialog.getAuthorsFromTextField().replaceAll("'", ""));
            book.setYear(bookDialog.getYearFromTextField().replaceAll("'", ""));
            books.setProperty(bookDialog.getCodeFromTextField(), String.valueOf(book));
            booksTableModel.changeObjectInRow(table.getSelectedRow());
        }
    }

    private void algorithmIfRemoveBookButtonIsPushed() {
        if (table.getSelectedRow() == -1) return;
        if (JOptionPane.showConfirmDialog(this,"Are you sure you want to delete book\n" + "with code " + booksTableModel.getValueAt(table.getSelectedRow(), 0) + "?", "Delete confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            books.remove(booksTableModel.getValueAt(table.getSelectedRow(), 0));
            booksTableModel.deleteObjectInRow(table.getSelectedRow());
        }
    }

    //метод читающий файл и возвращающий данные в память компьютера в виде Properties
    public Properties loadData () {
        try {
            if (!file.exists()) file.createNewFile();
            FileReader fileReader = new FileReader(file);
            books.load(fileReader);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    //метод записывающий файл из Properties
    public void storeData () {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            books.store(fileWriter, null);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {storeData();}
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
