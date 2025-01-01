package com.springbootlearn.journalApp;

import com.springbootlearn.journalApp.entity_2.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement//  it search for all @Transactional method
public class JournalApplication {
	public static void main(String[] args) {
		SpringApplication.run(JournalApplication.class, args);
	}
	@Bean
	public PlatformTransactionManager add(MongoDatabaseFactory dbFactory) // we can use any name
	{
		return new MongoTransactionManager(dbFactory);
	}
}


//REST API (Representational State Transfer Application Programming Interface) is a set of rules and conventions used to create web services that enable communication between client and server over HTTP.
//Creating a REST API involves defining an HTTP verb and associating it with a URL on the server.
//
//
//A URL in a REST API consists of the server address, port number, and the specific endpoint. i.e URL = server:port/endpoint eg :-(localhost:8080/api/users)
//The four main HTTP verbs used in REST APIs are:
//		1. GET: Retrieve data from the server.
//		2. POST: Send data to the server to create a new resource.
//		3. PUT: Update an existing resource on the server.
//		4. DELETE: Remove a resource from the server.
//
//using @RestController automatically converts the returned data into JSON format.

//Atlas :
//username:-rohit_atlas
//Password:-Rohitatlas2004#$@

//NEW
//    rohit20040121
//	  v8vQIH5EvoGEAMl2

//mongodb+srv://rohit20040121:v8vQIH5EvoGEAMl2@cluster0.rhfh6.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0