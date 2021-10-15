package com.project.doc.services;

import com.project.doc.entities.Document;
import com.project.doc.repositories.DocumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;

    public List<Document> findDocuments(){
        return documentRepository.findAll();
    }

    public Document findDocumentById(Long DocumentId){
        return documentRepository.findById(DocumentId).orElseThrow(() -> new IllegalArgumentException("Document not found"));
    }

    public List<Document> findDocumentsByPatientId(Long vicId){
        return documentRepository.findAllByPatientId(vicId);
    }

    public Document createDocument(Document Document){
        return documentRepository.save(Document);
    }


    public void deleteDocumentById(Long id){
        documentRepository.deleteById(id);
    }

    public Document updateDocument(Document document){
        return documentRepository.save(document);
    }
}
