package com.yigit.publish.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "publishes")
public class Publish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    private Integer edition;

    public Publish() {
    }

    public Publish(Author author, Book book, LocalDate publishDate, Integer edition) {
        this.author = author;
        this.book = book;
        this.publishDate = publishDate;
        this.edition = edition;
    }

    public Long getId() {
        return id;
    }

    public Author getAuthor() {
        return author;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public Integer getEdition() {
        return edition;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public void setEdition(Integer edition) {
        this.edition = edition;
    }
}