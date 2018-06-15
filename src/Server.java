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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonReader;
import javax.json.JsonValue;
/* 
Classname: Server 
Purpose: The RMI server. 
*/
public class Server extends UnicastRemoteObject implements Connection { 
	public Server() throws RemoteException { 
		super(); 
	} 
	
	private static JsonObject database = null;
	
	public String receiveJson(String json) { 
		System.out.print("Recebi a mensagem: "); 
		
		System.out.println(json); 
		
		JsonReader jsonReader = Json.createReader(new StringReader(json));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		
		int code = jsonObject.getInt("message_number");

		try {
			
			JsonBuilderFactory factory = Json.createBuilderFactory(null);
			
			
	        
	        JsonArray disciplinas = database.getJsonArray("disciplinas");
	        
	        if(code == 1) {
	        	
			}
	        else if(code == 2) {
	        	System.out.print("Recebi a mensagem: "); 
	        	Iterator<JsonValue> iterator = disciplinas.iterator();
				while(iterator.hasNext()) {
					JsonObject information = (JsonObject) iterator.next();
					if(information.getString("codigo").equalsIgnoreCase(jsonObject.getJsonObject("content").getString("codigo"))) {
						
						JsonObjectBuilder builder = factory.createObjectBuilder();
						JsonObject jsonReturn = builder.add("message_number", code)
								.add("content", factory.createObjectBuilder()
										.add("ementa", information.getString("ementa")).build()
										)
								.build();
						
						System.out.print(jsonReturn.toString()); 
						
						return jsonReturn.toString();
					}
				}
			}
	        else if(code == 3) {
	        	System.out.print("Recebi a mensagem: "); 
	        	Iterator<JsonValue> iterator = disciplinas.iterator();
				while(iterator.hasNext()) {
					JsonObject information = (JsonObject) iterator.next();
					if(information.getString("codigo").equalsIgnoreCase(jsonObject.getJsonObject("content").getString("codigo"))) {
						
						JsonObjectBuilder builder = factory.createObjectBuilder();
						JsonObject jsonReturn = builder.add("message_number", code)
								.add("content", factory.createObjectBuilder()
										.add("nome", information.getString("nome"))
										.add("codigo", information.getString("codigo"))
										.add("ementa", information.getString("ementa"))
										.add("sala", information.getString("sala"))
										.add("horario", information.getString("horario"))
										.add("comentario", information.getString("comentario"))
										.build()
										)
								.build();
						
						System.out.print(jsonReturn.toString()); 
						
						return jsonReturn.toString();
					}
				}
			}
	        else if(code == 4) {
				
			}
	        else if(code == 5) {
				
			}
	        else if(code == 6) {
				
			}

	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
		
		
		
		JsonBuilderFactory factory = Json.createBuilderFactory(null);
		JsonObjectBuilder builder = factory.createObjectBuilder();
		JsonObject returnedJson = builder.add("content", "A minha mensagem").build();
		
		return returnedJson.toString(); 
	} 

	public static void main(String args[]) { 
		try { 
			
			//Lendo o Banco de dados			
			byte[] encoded = Files.readAllBytes(Paths.get("src/data.json"));
			String data = new String(encoded, StandardCharsets.UTF_8);
			JsonReader jsonReader = Json.createReader(new StringReader(data));
			database =  jsonReader.readObject();
			jsonReader.close();

			Server server = new Server(); 

			Naming.rebind("Server", server); 
			System.out.println("Ligado no registro"); 
		} 
		catch (Exception ex) { 
			System.out.println("error: " + ex.getMessage()); 
			ex.printStackTrace(); 
		} 
	}
}