//package com.javaapi.json.examples;

import java.rmi.Naming; 
import java.rmi.RemoteException; 
import java.rmi.RMISecurityManager; 
import java.rmi.server.UnicastRemoteObject; 
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
/* 
Classname: Server 
Purpose: The RMI server. 
*/
public class Server extends UnicastRemoteObject implements Connection { 
	public Server() throws RemoteException { 
		super(); 
	} 
	
	public String receiveJson(String json) { 
		System.out.print("Recebi a mensagem: "); 
		
		System.out.println(json); 
		
		JsonReader jsonReader = Json.createReader(new StringReader(json));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		
		int code = jsonObject.getInt("message_number");
		System.out.println(code);
		
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObjectBuilder builder = factory.createObjectBuilder();
		JsonObject returnedJson = builder.add("content", "A minha mensagem").build();
		
		return returnedJson.toString(); 
	} 

	public static void main(String args[]) { 
		try { 
			// Creates an object of the HelloServer class. 
			System.out.println("Deu bom?"); 

			//System.setSecurityManager(new RMISecurityManager());
			//System.setProperty("java.rmi.server.hostname","rmi://localhost");

			Server server = new Server(); 
			System.out.println("opa"); 

			Naming.rebind("Server", server); 
			System.out.println("Ligado no registro"); 
		} 
		catch (Exception ex) { 
			System.out.println("error: " + ex.getMessage()); 
			ex.printStackTrace(); 
		} 
	}
}