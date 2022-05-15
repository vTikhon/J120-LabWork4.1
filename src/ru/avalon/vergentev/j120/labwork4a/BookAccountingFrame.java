package ru.avalon.vergentev.j120.labwork4a;
import javax.swing.*;
import java.awt.event.*;

public class BookAccountingFrame extends JFrame implements WindowListener {
    private final BookDialogFrame bookDialog = new BookDialogFrame(this);
    private final BookTableModel booksTableModel = new BookTableModel();
    private final JTable table = new JTable(booksTableModel);
    private final BookParameters bookParameters = new BookParameters();
    private final BookFileData bookFileData = new BookFileData();

    public BookAccountingFrame() {
        bookFileData.loadData();
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
            bookDialog.getCodeFromTextField();
            bookParameters.setIsbn(bookDialog.getIsbnFromTextField().replaceAll("'", ""));
            bookParameters.setBookTitle(bookDialog.getTitleFromTextField().replaceAll("'", ""));
            bookParameters.setAuthors(bookDialog.getAuthorsFromTextField().replaceAll("'", ""));
            bookParameters.setYear(bookDialog.getYearFromTextField().replaceAll("'", ""));
            if (!BookFileData.getBooks().containsKey(bookDialog.getCodeFromTextField())) {
                BookFileData.getBooks().setProperty(bookDialog.getCodeFromTextField(), String.valueOf(bookParameters));
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
            bookDialog.getCodeFromTextField();
            bookParameters.setIsbn(bookDialog.getIsbnFromTextField().replaceAll("'", ""));
            bookParameters.setBookTitle(bookDialog.getTitleFromTextField().replaceAll("'", ""));
            bookParameters.setAuthors(bookDialog.getAuthorsFromTextField().replaceAll("'", ""));
            bookParameters.setYear(bookDialog.getYearFromTextField().replaceAll("'", ""));
            BookFileData.getBooks().setProperty(bookDialog.getCodeFromTextField(), String.valueOf(bookParameters));
            booksTableModel.changeObjectInRow(table.getSelectedRow());
        }
    }

    private void algorithmIfRemoveBookButtonIsPushed() {
        if (table.getSelectedRow() == -1) return;
        if (JOptionPane.showConfirmDialog(this,"Are you sure you want to delete book\n" + "with code " + booksTableModel.getValueAt(table.getSelectedRow(), 0) + "?", "Delete confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            BookFileData.getBooks().remove(booksTableModel.getValueAt(table.getSelectedRow(), 0));
            booksTableModel.deleteObjectInRow(table.getSelectedRow());
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}
    @Override
    public void windowClosing(WindowEvent e) {bookFileData.storeData();}
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
