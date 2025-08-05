package com.example.signimus.personal_blog.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "article")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

//    Many articles can have one author
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDateTime dateOfPublication;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.dateOfPublication = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dateOfPublication = LocalDateTime.now();
    }
}
