package com.project.doc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Document {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String link;
    @Lob
    private byte[] file;
    @ManyToOne
    private AppUser patient;

    public Document(String name, String link) {
        this.name = name;
        this.link = link;
    }
}