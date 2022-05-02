package ru.avalon.vergentev.j120.labwork4a;

public class BookParameters {
    private String isbn;
    private String title;
    private String authors;
    private String year;

    public BookParameters() {}

    public BookParameters(String isbn, String title, String year) {
        setIsbn(isbn);
        setTitle(title);
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
                ", title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", year='" + year + '\'' +
                '}';
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
