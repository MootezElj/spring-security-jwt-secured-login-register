package com.project.doc.controllers;

import com.project.doc.entities.AppUser;
import com.project.doc.entities.Document;
import com.project.doc.security.services.AccountService;
import com.project.doc.services.DocumentService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.Locale;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class DocumentResource {

    private final DocumentService documentService;
    private final AccountService accountService;
    private final Logger log = LoggerFactory.getLogger(DocumentResource.class);


    @GetMapping("/documents/")
    public ResponseEntity<List<Document>> findAll(){
        log("Request to get all documents: ");
        return new ResponseEntity<List<Document>>(documentService.findDocuments(), HttpStatus.OK);
    }

    @GetMapping("/documents/byPatientId/{vicId}/")
    public ResponseEntity<List<Document>> findAllDocumentByVicID(@PathVariable Long vicId){
        log("Request to get all documents for vic having id: "+vicId);
        return new ResponseEntity<List<Document>>(documentService.findDocumentsByPatientId(vicId), HttpStatus.OK);
    }

    @GetMapping("/documents/currentPatient/")
    public ResponseEntity<List<Document>> findCurrentPatientsDocuments(Principal user){
        log("Request to find current patient's documents");
        AppUser currentPatient = accountService.findUserByUsername(user.getName());
        if (currentPatient.isDoctor())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {
            List<Document> documents= documentService.findDocumentsByPatientId(currentPatient.getId());
            return ResponseEntity.ok(documents);
        }
    }

    @PostMapping("/documents/currentPatient/")
    public ResponseEntity<Document> createDocumentForCurrentPatient(@RequestBody Document document, Principal user) throws URISyntaxException {
        log("Request to create document");
        AppUser currentPatient = accountService.findUserByUsername(user.getName());
        if (currentPatient.isDoctor())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else {
            document.setPatient(currentPatient);
            documentService.createDocument(document);
            return ResponseEntity.created(new URI("/api/documents")).body(document);
        }
    }

    @PostMapping("/documents/")
    public ResponseEntity<Document> createDocument(@RequestBody Document document) throws URISyntaxException {
        log("Request to create document");
        documentService.createDocument(document);
        return ResponseEntity.created(new URI("/api/documents")).body(document);
    }



    @PutMapping("/documents/")
    public ResponseEntity<Document> updateDocument(@RequestBody Document document) {
        log("Request to update document with id" + document.getId());
        return new ResponseEntity<Document>(documentService.updateDocument(document), HttpStatus.OK);
    }

    @DeleteMapping("/documents/{docId}/")
    public void deleteDocumentById(@PathVariable Long docId){
        documentService.deleteDocumentById(docId);
    }

    private void log(String msg){
        log.debug(msg);
    }

}
