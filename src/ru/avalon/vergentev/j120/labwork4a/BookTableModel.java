package ru.avalon.vergentev.j120.labwork4a;
import javax.swing.event.*;
import javax.swing.table.TableModel;
import java.util.*;

public class BookTableModel implements TableModel {
    private static final String[] COLUMN_HEADERS = new String[]{"CODE", "ISBN", "TITLE", "AUTHORS", "YEAR"};
    private final Set<TableModelListener> modelListeners = new HashSet<>();

//    public Properties getObjectInRow (int rowIndex) {
//
//        return BookParameters.getBooks().get(rowIndex);
//    }

    public void insertObjectInNewRow () {
        int rowIndex = BookParameters.getBooks().size() - 1;
        fireTableModelEvent(rowIndex, TableModelEvent.INSERT);
    }

    public void changeObjectInRow (int index) {
        fireTableModelEvent(index, TableModelEvent.UPDATE);
    }

    public void deleteObjectInRow (int index) {
        BookParameters.getBooks().remove(index);
        fireTableModelEvent(index, TableModelEvent.DELETE);
    }

    private void fireTableModelEvent(int rowIndex, int eventType) {
        TableModelEvent tableModelEvent = new TableModelEvent(this, rowIndex, rowIndex, TableModelEvent.ALL_COLUMNS, eventType);
        for (TableModelListener listener : modelListeners) {listener.tableChanged(tableModelEvent);}
    }

    @Override
    public int getColumnCount() {return COLUMN_HEADERS.length;}
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4: return String.class;
        }
        throw new IllegalArgumentException("unknown columnIndex");
    }
    @Override
    public String getColumnName(int columnIndex) {return COLUMN_HEADERS[columnIndex];}
    @Override
    public int getRowCount() {return BookParameters.getBooks().size();}
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[][] arrayData = new String[BookParameters.getBooks().size()][];
        int k = 0;
        for (Object i : BookParameters.getBooks().keySet()) {
            BookParameters book = new BookParameters();
            String [] eachBookParameter = ((String)BookParameters.getBooks().get(i)).split("'");
            for (int j = 1; j < eachBookParameter.length; j = j + 2) {
                if      (j == 1) book.setIsbn(eachBookParameter[j]);
                else if (j == 3) book.setBookTitle(eachBookParameter[j]);
                else if (j == 5) book.setAuthors(eachBookParameter[j]);
                else if (j == 7) book.setYear(eachBookParameter[j]);
            }
            arrayData[k] = new String[]{(String) i, book.getIsbn(), book.getBookTitle(), book.getAuthors(), book.getYear()};
            k++;
        }
        switch (columnIndex) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4: return arrayData[rowIndex][columnIndex];
        }
        throw new IllegalArgumentException("unknown columnIndex");
    }
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {return false;}
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {/* Nothing to do, since isCellEditable is always false. */}
    @Override
    public void addTableModelListener(TableModelListener listener) {modelListeners.add(listener);}
    @Override
    public void removeTableModelListener(TableModelListener listener) {modelListeners.remove(listener);}
}
