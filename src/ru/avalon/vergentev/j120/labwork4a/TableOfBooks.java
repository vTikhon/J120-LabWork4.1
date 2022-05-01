package ru.avalon.vergentev.j120.labwork4a;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableOfBooks extends JFrame implements ActionListener {
    JTable table = new JTable();

    public TableOfBooks() {
        super("Table of books");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);

        add(table);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource() == remove) {
//            algorithmIfRemoveBookButtonIsPushed();
//        }
    }
}
