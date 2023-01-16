package dev.alko.jpasecurity.model;


import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String slug;
    private String content;
    private String author;
    private LocalDateTime publishedOn;
    private LocalDateTime updatedAt;

//    public Post(String title, String slug, String content, String author) {
//        this.title = title;
//        this.slug = slug;
//        this.content = content;
//        this.author = author;
//        this.publishedOn = LocalDateTime.now();
//    }
}
