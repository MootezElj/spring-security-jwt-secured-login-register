package com.project.doc.controllers;


import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
public class SimpleTestResource {

    private final Logger log = LoggerFactory.getLogger(DocumentResource.class);

    @GetMapping("/heyDoc/")
    public ResponseEntity<String> helloDoctor(){
        log("Request to say hey to a doctor: ");
        return new ResponseEntity<String>("Hello doctor !!", HttpStatus.OK);
    }

    @GetMapping("/heyPatient/")
    public ResponseEntity<String> helloPatient(){
        log("Request to say hey to a patient: ");
        return new ResponseEntity<String>("Hello patient !!", HttpStatus.OK);
    }

    private void log(String msg){
        log.debug(msg);
    }


}
