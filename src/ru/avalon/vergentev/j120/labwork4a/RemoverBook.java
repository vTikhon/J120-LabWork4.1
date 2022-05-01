package ru.avalon.vergentev.j120.labwork4a;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Properties;

public class RemoverBook extends JFrame implements ActionListener {
    JLabel code = new JLabel("Code: ");
    JTextField codeText = new JTextField();
    JButton remove = new JButton("Remove");
    AdderBook adderBook = new AdderBook();

    public RemoverBook() {
        super("Remove book by NUMBER");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5,1));

        add(code);
        addTextField(codeText);
        addButton(remove);
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
        if (e.getSource() == remove) {
            algorithmIfRemoveBookButtonIsPushed();
        }
    }

    private void algorithmIfRemoveBookButtonIsPushed() {
        int resultRemove = JOptionPane.showConfirmDialog(null, "Are you sure want to remove that book from data base ?", "Warning", JOptionPane.OK_CANCEL_OPTION);
        if (resultRemove == JOptionPane.OK_OPTION) {
            remove(codeText.getText());
            codeText.setText("");
        }
    }

    private void remove(String key) {
        System.out.println("from remover " + adderBook.data.toString());
//        System.out.println(key + " contains? " + adderBook.data.containsKey(key));
        adderBook.data.remove(key);
    }



}
