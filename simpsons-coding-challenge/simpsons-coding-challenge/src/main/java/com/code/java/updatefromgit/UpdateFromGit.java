package com.code.java.updatefromgit;

import java.nio.file.Paths;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;



public class UpdateFromGit {
	public static void main(String[] args) {
		String repoUrl = "https://github.com/in28minutes/spring-boot-examples.git";
		
		String cloneDirectoryPath = "C:\\Users\\vmaji\\wORKING\\test1"; // Ex.in windows c:\\gitProjects\SpringBootMongoDbCRUD\
		try {
		    System.out.println("Cloning "+repoUrl+" into "+repoUrl);
		    Git.cloneRepository()
		        .setURI(repoUrl)
		        .setDirectory(Paths.get(cloneDirectoryPath).toFile())
		        .call();
		    System.out.println("Completed Cloning");
		} catch (GitAPIException e) {
		    System.out.println("Exception occurred while cloning repo");
		    e.printStackTrace();
		}
	}

}
