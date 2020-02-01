package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.dao.PublisherDao;
import com.team.alpha.entity.Publisher;

@Service
public class PublisherService {

	@Autowired
	PublisherDao publisherdao;
	
	public List<Publisher> getAllPublishers(){
		return publisherdao.getAllObjects();
	}
	
	public Publisher getPublisherById(Integer id){
		Publisher tempPublisher = publisherdao.getPublisherById(id);
		return tempPublisher;
	}
	
	public void addPublisher(Publisher publisher) {
		publisherdao.createObject(publisher);
	}
	
	public void deletePublisher(int id) {
		publisherdao.deleteObject(id);
	}
	
	public void updatePublisher(Publisher publisher) {
		publisherdao.updateObject(publisher);
	}
}
