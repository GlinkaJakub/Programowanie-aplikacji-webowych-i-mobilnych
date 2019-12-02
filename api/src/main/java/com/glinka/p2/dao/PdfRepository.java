package com.glinka.p2.dao;

import com.glinka.p2.entity.PdfFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PdfRepository extends JpaRepository<PdfFile, String> {

    public List<PdfFile> findAll();
}
