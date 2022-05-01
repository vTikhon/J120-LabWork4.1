package ru.avalon.vergentev.j120.labwork4a;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RemoverBook extends JFrame implements ActionListener {
    JLabel code = new JLabel("Code: ");
    JTextField codeText = new JTextField();
    JButton remove = new JButton("Remove");

    public RemoverBook() {
        super("Remove book by NUMBER");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5,1));

        add(code);
        addTextField(codeText);
        add(remove);
    }

    public void addTextField (JTextField field) {
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
            remove();
            this.dispose();
        } else {
            this.dispose();
        }
    }

    private void remove() {

    }



}
