package ru.avalon.vergentev.j120.labwork4a;

public class Book {
    private String isbn;
    private String title;
    private String authors;
    private Integer year;

    public Book() {
    }

    public Book(String isbn, String title, Integer year) {
        setIsbn(isbn);
        setTitle(title);
        setYear(year);
    }

    public Book(String isbn, String title, String authors, Integer year) {
        this(isbn, title, year);
        setAuthors(authors);
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
