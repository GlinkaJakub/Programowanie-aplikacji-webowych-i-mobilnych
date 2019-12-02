package com.glinka.p2.service;

import com.glinka.p2.entity.PdfFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PdfService {

    public PdfFile storeFile(MultipartFile file);
    public PdfFile getFile(String fileId);
    public List<PdfFile> findAll();
}
