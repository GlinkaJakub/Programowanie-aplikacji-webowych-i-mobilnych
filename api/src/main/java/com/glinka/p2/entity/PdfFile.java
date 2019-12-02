package com.glinka.p2.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pdffile")
public class PdfFile {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Lob
    @Column(name = "pdf", columnDefinition = "BLOB")
    private byte[] data;
//    @Column(name = "owner", columnDefinition = "INT")
//    private Long owner;
    private String fileName;
    private String fileType;

    public PdfFile() {
    }

    public PdfFile(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }
}
