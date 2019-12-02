package com.glinka.p2.service;

import com.glinka.p2.dao.PdfRepository;
import com.glinka.p2.entity.PdfFile;
import com.glinka.p2.exception.FileStorageException;
import com.glinka.p2.exception.MyFileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService{

    @Autowired
    private PdfRepository pdfRepository;

    @Override
    public PdfFile storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if(fileName.contains("..")){
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            PdfFile pdfFile = new PdfFile(fileName, file.getContentType(), file.getBytes());

            return pdfRepository.save(pdfFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public PdfFile getFile(String fileId) {
        return pdfRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

    @Override
    public List<PdfFile> findAll() {
        return pdfRepository.findAll();
    }
}
