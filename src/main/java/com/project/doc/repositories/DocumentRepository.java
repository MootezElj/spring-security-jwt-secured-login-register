package com.project.doc.repositories;

import com.project.doc.entities.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    public List<Document> findAllByPatientId(Long patientId);

}
