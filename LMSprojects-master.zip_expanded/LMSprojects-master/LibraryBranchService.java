package com.team.alpha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.alpha.dao.LibraryBranchDao;
import com.team.alpha.entity.LibraryBranch;

@Service
public class LibraryBranchService {
	
	@Autowired
	LibraryBranchDao librarybranchdao;
	
	public List<LibraryBranch> getAllBranches() {
		return librarybranchdao.getAllObjects();
	}
	
	public LibraryBranch getBranchById(int branchId){
		LibraryBranch tempBranch = librarybranchdao.findBranchById(branchId);
		return tempBranch;
	}
	
	public void addBranch(LibraryBranch branch) {
		 librarybranchdao.createObject(branch);
	}
	
	public void deleteBranch(int id) {
		librarybranchdao.deleteObject(id);
	}
	
	public void updateBranch(LibraryBranch branch) {
		librarybranchdao.updateObject(branch);
	}
}