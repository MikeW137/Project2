package com.bookstore.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="books")
public class Book {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String title;
    @Column
    private String description;

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Author> authorList;

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Genre> genreList;

    @OneToMany(mappedBy = "book", orphanRemoval = true)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Publisher> publisherList;

    //Many Books can have one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;


    public Book() {
    }

    public Book(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public List<Author> getAuthorList() { return authorList; }

    public void setAuthorList(List<Author> authorList) { this.authorList = authorList; }

    public List<Genre> getGenreList() { return genreList; }

    public void setGenreList(List<Genre> genreList) { this.genreList = genreList; }

    public List<Publisher> getPublisherList() { return publisherList; }

    public void setPublisherList(List<Publisher> publisherList) { this.publisherList = publisherList; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
