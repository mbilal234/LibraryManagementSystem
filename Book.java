package lms;

import java.awt.*;

public class Book {
    private Image cover;
    private String title, isbn, author, publisher, genre;
    private double damage_level;
    private String issued_by;

    public Book(Image cover, String title, String isbn, String author, String publisher, String genre, double damage_level, String issued_by) {
        this.cover = cover;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
        this.damage_level = damage_level;
        this.issued_by = issued_by;
    }

    public Image getCover() {
        return cover;
    }

    public void setCover(Image cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getDamage_level() {
        return damage_level;
    }

    public void setDamage_level(double damage_level) {
        this.damage_level = damage_level;
    }

    public String getIssued_by() {
        return issued_by;
    }

    public void setIssued_by(String issued_by) {
        this.issued_by = issued_by;
    }
}
