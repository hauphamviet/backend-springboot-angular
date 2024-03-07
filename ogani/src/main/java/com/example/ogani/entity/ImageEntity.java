package com.example.ogani.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", length = 250)
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    private long size;

    @Lob
    @Column(unique = false, nullable = false, length = 100000)
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "uploaded_by")
    private UserEntity uploadedBy;

}
