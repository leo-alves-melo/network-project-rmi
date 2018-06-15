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
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileWriter;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
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
		
		long millis1 = System.nanoTime();
		
		//System.out.print("Recebi a mensagem: "); 
		
		//System.out.println(json); 
		
		JsonReader jsonReader = Json.createReader(new StringReader(json));
		JsonObject jsonObject = jsonReader.readObject();
		jsonReader.close();
		
		int code = jsonObject.getInt("message_number");

		try {
			
			JsonBuilderFactory factory = Json.createBuilderFactory(null);
			
			
	        
	        JsonArray disciplinas = database.getJsonArray("disciplinas");
	        
	        if(code == 1) {
	        	JsonArrayBuilder arrayBuilder = factory.createArrayBuilder();
	        	
	        	
	        	
	        	
	        	Iterator<JsonValue> iterator = disciplinas.iterator();
				while(iterator.hasNext()) {
					JsonObject information = (JsonObject) iterator.next();
					
					JsonObject atualJson = factory.createObjectBuilder()	
							.add("nome", information.getString("nome"))
							.add("codigo", information.getString("codigo"))
							.build();
					
					arrayBuilder.add(atualJson);		
				}
				
				JsonArray jsonArray = arrayBuilder.build();
				
				JsonObjectBuilder builder = factory.createObjectBuilder();
	        	JsonObject jsonReturn = builder.add("message_number", code)
	        			.add("content", jsonArray).build();
				
	        	long millis2 = System.nanoTime();
	        	
	        	System.out.println(millis2 - millis1);
				return jsonReturn.toString();
			}
	        else if(code == 2) {
	        	
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
						
						long millis2 = System.nanoTime();
			        	
			        	System.out.println(millis2 - millis1);
						return jsonReturn.toString();
					}
				}
			}
	        else if(code == 3) {
	        	
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
						
						long millis2 = System.nanoTime();
			        	
			        	System.out.println(millis2 - millis1);
						return jsonReturn.toString();
					}
				}
			}
	        else if(code == 4) {
	        	
	        	JsonArrayBuilder arrayBuilder = factory.createArrayBuilder();
	        	
	        	
	        	
	        	
	        	Iterator<JsonValue> iterator = disciplinas.iterator();
				while(iterator.hasNext()) {
					JsonObject information = (JsonObject) iterator.next();
					
					JsonObject atualJson = factory.createObjectBuilder()	
							.add("nome", information.getString("nome"))
							.add("codigo", information.getString("codigo"))
							.add("ementa", information.getString("ementa"))
							.add("sala", information.getString("sala"))
							.add("horario", information.getString("horario"))
							.add("comentario", information.getString("comentario"))
							.build();
					
					arrayBuilder.add(atualJson);		
				}
				
				JsonArray jsonArray = arrayBuilder.build();
				
				JsonObjectBuilder builder = factory.createObjectBuilder();
	        	JsonObject jsonReturn = builder.add("message_number", code)
	        			.add("content", jsonArray).build();
				
	        	long millis2 = System.nanoTime();
	        	
	        	System.out.println(millis2 - millis1);
				return jsonReturn.toString();
			}
	        else if(code == 5) {
	        	Iterator<JsonValue> iterator = disciplinas.iterator();
	        	JsonObjectBuilder builder = factory.createObjectBuilder();
	        	JsonArrayBuilder arrayBuilder = factory.createArrayBuilder();
	        	
	        	
	        	String comentarioNovo = jsonObject.getJsonObject("content")
	        			.getString("comentario");
	        	
	        	
				while(iterator.hasNext()) {
					JsonObject information = (JsonObject) iterator.next();
					if(information.getString("codigo").equalsIgnoreCase(jsonObject.getJsonObject("content").getString("codigo"))) {
						
						JsonObjectBuilder actualBuilder = factory.createObjectBuilder();
						JsonObject actual = actualBuilder
								.add("nome", information.getString("nome"))
								.add("codigo", information.getString("codigo"))
								.add("ementa", information.getString("ementa"))
								.add("sala", information.getString("sala"))
								.add("horario", information.getString("horario"))
								.add("comentario", comentarioNovo)
								.build();
						
						arrayBuilder.add(actual);
						
						
					}
					else {
						JsonObjectBuilder actualBuilder = factory.createObjectBuilder();
						JsonObject actual = actualBuilder
								.add("nome", information.getString("nome"))
								.add("codigo", information.getString("codigo"))
								.add("ementa", information.getString("ementa"))
								.add("sala", information.getString("sala"))
								.add("horario", information.getString("horario"))
								.add("comentario", information.getString("comentario"))
								.build();
						
						arrayBuilder.add(actual);
					}
				}
				
				database = builder.add("disciplinas", arrayBuilder.build()).build();
				JsonObjectBuilder actualBuilder = factory.createObjectBuilder();
				JsonObject actual = actualBuilder.add("message_number", 5).build();
				
				try (PrintWriter out = new PrintWriter("src/data.json")) {
				    out.println(database.toString());
				}
				
				long millis2 = System.nanoTime();
	        	
	        	System.out.println(millis2 - millis1);
				return actual.toString();
			}
	        else if(code == 6) {
	        	
	        	Iterator<JsonValue> iterator = disciplinas.iterator();
				while(iterator.hasNext()) {
					JsonObject information = (JsonObject) iterator.next();
					if(information.getString("codigo").equalsIgnoreCase(jsonObject.getJsonObject("content").getString("codigo"))) {
						
						JsonObjectBuilder builder = factory.createObjectBuilder();
						JsonObject jsonReturn = builder.add("message_number", code)
								.add("content", factory.createObjectBuilder()
										.add("comentario", information.getString("comentario")).build()
										)
								.build();
						
						long millis2 = System.nanoTime();
			        	
			        	System.out.println(millis2 - millis1);
						return jsonReturn.toString();
					}
				}
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