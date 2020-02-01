package com.team.alpha.dao;

import java.util.List;

public interface ObjectDao <T>{
	
	List<T> getAllObjects();
    
    void createObject(T t);
    
    void deleteObject(int id);
    
    void updateObject(T t);
    
}
