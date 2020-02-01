package com.team.alpha.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.team.alpha.entity.Publisher;
import com.team.alpha.service.PublisherService;

@RestController
@RequestMapping(path="/administrator")
public class AdminPublisherController {
	
	@Autowired
	private PublisherService publisherservice;

	@GetMapping(value="/publisher/", produces="application/json")
	public ResponseEntity<List<Publisher>> getAllPublishers(){
		List<Publisher> publishers = publisherservice.getAllPublishers();
		if (publishers.isEmpty()) {
            return new ResponseEntity<List<Publisher>>(HttpStatus.NO_CONTENT);
		} 
		return new ResponseEntity<List<Publisher>>(publishers, HttpStatus.OK);		
	}

	@PostMapping(value="/publisher/", consumes="application/json")
	 public ResponseEntity<Publisher> createPublisher(@RequestBody Publisher publisher, UriComponentsBuilder ucBuilder) {
        if (publisherservice.getPublisherById(publisher.getPublisherId())!=null) {
            return new ResponseEntity<Publisher>(HttpStatus.CONFLICT);
        }
        publisherservice.addPublisher(publisher);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/administrator/publisher/{id}").buildAndExpand(publisher.getPublisherId()).toUri());
        return new ResponseEntity<Publisher>(headers, HttpStatus.CREATED);	
	}
	
	@DeleteMapping(value="/publisher/{id}", consumes="application/json")
    public ResponseEntity<Publisher> deletePublisher(@PathVariable("id") int id) {    
        if (publisherservice.getPublisherById(id)==null) {
            return new ResponseEntity<Publisher>(HttpStatus.NOT_FOUND);
        }
        publisherservice.deletePublisher(id);
        return new ResponseEntity<Publisher>(HttpStatus.NO_CONTENT);
    }
	

	@PutMapping(value="/publisher/{id}", consumes="application/json")
	public ResponseEntity<Publisher> updatePublisher(@PathVariable("id") int id, @RequestBody Publisher publisher) {
        if (publisherservice.getPublisherById(id)==null) {
            return new ResponseEntity<Publisher>(HttpStatus.NOT_FOUND);
        }    
	    publisherservice.updatePublisher(publisher);
	    return new ResponseEntity<Publisher>(publisher, HttpStatus.OK);
	}
}
