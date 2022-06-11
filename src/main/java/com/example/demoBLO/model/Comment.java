package com.example.demoBLO.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "comments")
@SequenceGenerator(name = "comment_seq_gen", sequenceName = "comment_seq", initialValue = 10, allocationSize=1)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "comment_seq_gen")
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "creation_date", nullable = false, updatable = false)
    private Date creationDate;

    @NotNull
    @ManyToOne
    private Post post;

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", creationDate=" + creationDate +
                ", post_id=" + post.getId() +
                '}';
    }
}