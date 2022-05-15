package ru.avalon.vergentev.j120.labwork4a;
import java.io.*;
import java.util.Properties;

public class BookFileData {
    private static final File file = new File("books.dat");
    private static final Properties books = new Properties();

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


    public static Properties getBooks() {return books;}
//    public void setBooks(Properties books) {this.books = books;}
//
//    public int getBooksCount() {return books.size();}
//    public BookParameters getBookInfo(int index) {return (BookParameters) books.get(index);}
//    public static BookList getBook() {return book;}
}
