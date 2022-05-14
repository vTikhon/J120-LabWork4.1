package ru.avalon.vergentev.j120.labwork4a;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BookDialog extends JDialog {
    private final JTextField codeField;
    private final JTextField isbnField;
    private final JTextField titleField;
    private final JTextField authorsField;
    private final JTextField yearField;
    private boolean okPressed;
    BookTableModel bookTableModel;

    public BookDialog (JFrame owner) {
        super(owner, true);
        codeField = new JTextField(10);
        isbnField = new JTextField(10);
        titleField = new JTextField(80);
        authorsField = new JTextField(80);
        yearField = new JTextField(4);
        initControls();
        initOkCancelButtons();
        setResizable(false);
    }

    private void initControls() {
        JPanel controlsPane = new JPanel(null);
        controlsPane.setLayout(new BoxLayout(controlsPane, BoxLayout.Y_AXIS));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("Code: ");
        label.setLabelFor(codeField);
        panel.add(label);
        panel.add(codeField);
        controlsPane.add(panel);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel("ISBN: ");
        label.setLabelFor(isbnField);
        panel.add(label);
        panel.add(isbnField);
        controlsPane.add(panel);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel("Title: ");
        label.setLabelFor(titleField);
        panel.add(label);
        panel.add(titleField);
        controlsPane.add(panel);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel("Authors: ");
        label.setLabelFor(authorsField);
        panel.add(label);
        panel.add(authorsField);
        controlsPane.add(panel);

        panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        label = new JLabel("Year: ");
        label.setLabelFor(yearField);
        panel.add(label);
        panel.add(yearField);
        controlsPane.add(panel);

        add(controlsPane, BorderLayout.CENTER);
    }

    private void initOkCancelButtons() {
        JPanel panelForButtons = new JPanel();

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            okPressed = true;
            setVisible(false);
        });
        okButton.setDefaultCapable(true);
        panelForButtons.add(okButton);

        Action cancelDialogAction = new AbstractAction("Cancel") {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        };

        JButton cancelButton = new JButton(cancelDialogAction);
        panelForButtons.add(cancelButton);

        add(panelForButtons, BorderLayout.SOUTH);

        final String escape = "escape";
        getRootPane()
                .getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
                .put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), escape);
        getRootPane()
                .getActionMap()
                .put(escape, cancelDialogAction);
    }

    public boolean isFrameOpened() {
        pack();
        setLocationRelativeTo(getOwner());
        if (codeField.isEnabled())
            codeField.requestFocusInWindow();
        else
            titleField.requestFocusInWindow();
        okPressed = false;
        setVisible(true);
        return okPressed;
    }

//    public void prepareForAdd () {
//        setTitle("New book registration");
//        codeField.setText("");
//        isbnField.setText("");
//        titleField.setText("");
//        authorsField.setText("");
//        yearField.setText("");
//    }

    public void prepareForChange (JTable table) {
//        this.setTitle("Book parameters change");
        bookTableModel = new BookTableModel();
        codeField.setText(String.valueOf(bookTableModel.getValueAt(table.getSelectedRow(), 0)));
        isbnField.setText(String.valueOf(bookTableModel.getValueAt(table.getSelectedRow(), 1)));
        titleField.setText(String.valueOf(bookTableModel.getValueAt(table.getSelectedRow(), 2)));
        authorsField.setText(String.valueOf(bookTableModel.getValueAt(table.getSelectedRow(), 3)));
        yearField.setText(String.valueOf(bookTableModel.getValueAt(table.getSelectedRow(), 4)));

        codeField.setEnabled(false);
    }

    public String getCodeFromTextField() {return codeField.getText();}
    public void setCodeForTextField(String string) {codeField.setText(string);}
    public String getIsbnFromTextField() {return isbnField.getText();}
    public void setIsbnForTextField(String string) {isbnField.setText(string);}
    public String getTitleFromTextField() {return titleField.getText();}
    public void setTitleForTextField(String string) {titleField.setText(string);}
    public String getAuthorsFromTextField() {return authorsField.getText();}
    public void setAuthorsForTextField(String string) {authorsField.setText(string);}
    public String getYearFromTextField() {return yearField.getText();}
    public void setYearForTextField(String string) {yearField.setText(string);}
}