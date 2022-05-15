package ru.avalon.vergentev.j120.labwork4a;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class BookParameters {
    private final File file = new File("books.dat");
    private static final Properties books = new Properties();
    private String isbn;
    private String titleBook;
    private String authors;
    private String year;

    public BookParameters() {}

    public BookParameters(String isbn, String title, String year) {
        setIsbn(isbn);
        setBookTitle(title);
        setYear(year);
    }

    public BookParameters(String isbn, String title, String authors, String year) {
        this(isbn, title, year);
        setAuthors(authors);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", title='" + titleBook + '\'' +
                ", authors='" + authors + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    //метод читающий файл и возвращающий данные в память компьютера в виде Properties
    public Properties loadData () {
        try {
            if (!file.exists()) file.createNewFile();
            FileReader fileReader = new FileReader(file);
            BookParameters.getBooks().load(fileReader);
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return BookParameters.getBooks();
    }

    //метод записывающий файл из Properties
    public void storeData () {
        try {
            FileWriter fileWriter = new FileWriter(file, false);
            BookParameters.getBooks().store(fileWriter, null);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getIsbn() {return isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}
    public String getBookTitle() {return titleBook;}
    public void setBookTitle(String title) {this.titleBook = title;}
    public String getAuthors() {return authors;}
    public void setAuthors(String authors) {this.authors = authors;}
    public String getYear() {return year;}
    public void setYear(String year) {this.year = year;}

    public static Properties getBooks() {return books;}
//    public void setBooks(Properties books) {this.books = books;}
//
//    public int getBooksCount() {return books.size();}
//    public BookParameters getBookInfo(int index) {return (BookParameters) books.get(index);}
//    public static BookList getBook() {return book;}
}
