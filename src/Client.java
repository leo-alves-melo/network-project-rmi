//package com.javaapi.json.examples;

import java.rmi.Naming; 
import java.rmi.RemoteException; 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.StringReader;

import javax.json.*;

import java.util.Iterator;
import java.util.Scanner;
/* 
Classname: Client 
Comment: The RMI client. 
*/
public class Client { 
	// The Hello object "obj" is the identifier that is 
	   
	// the Hello interface. 
	static Connection server = null; 
	
	private static void parseJson(JsonObject json) {
		try { 
			
			int number = json.getInt("message_number");
			//JsonObject content = json.getJsonObject("content");
			
			if(number == 1) {
				JsonArray content = json.getJsonArray("content");
				
				Iterator<JsonValue> iterator = content.iterator();
				while(iterator.hasNext()) {
					JsonObject information = (JsonObject) iterator.next();
				
					System.out.println("Título: " + information.getString("nome"));
					System.out.println("Código: " + information.getString("codigo") + "\n");
	
				}
			}
			else if(number == 2) {
				JsonObject content = json.getJsonObject("content");

				System.out.println("Ementa: " + content.getString("ementa") + "\n");

			}
			else if(number == 3) {
				JsonObject content = json.getJsonObject("content");

				System.out.println("Título: " + content.getString("nome"));
				System.out.println("Código: " + content.getString("codigo") );
				System.out.println("Ementa: " + content.getString("ementa"));
				System.out.println("Sala: " + content.getString("sala"));
				System.out.println("Horário: " + content.getString("horario"));
				System.out.println("Comentário: " + content.getString("comentario") + "\n");
				
			}
			else if(number == 4) {
				JsonArray content = json.getJsonArray("content");
				
				Iterator<JsonValue> iterator = content.iterator();
				while(iterator.hasNext()) {
					JsonObject information = (JsonObject) iterator.next();
					
					System.out.println("Título: " + information.getString("nome"));
					System.out.println("Código: " + information.getString("codigo") );
					System.out.println("Ementa: " + information.getString("ementa"));
					System.out.println("Sala: " + information.getString("sala"));
					System.out.println("Horário: " + information.getString("horario"));
					System.out.println("Comentário: " + information.getString("comentario") + "\n");
					
					
				}
			}
			else if(number == 5) {
				System.out.println("Comentário escrito com sucesso!");
			}
			else if(number == 6) {
				JsonObject content = json.getJsonObject("content");

				System.out.println("Comentário: " + content.getString("comentario") + "\n");
			}
			
			
			
			
		
			
		} 
		catch (Exception e) { 
			System.out.println("HelloClient exception: " + e.getMessage()); 
			e.printStackTrace(); 
		} 
	}
	
	private static void sendJson(JsonObject json, String ip) {
		try { 
			
			for(int i = 0; i < 60; i++) {
				long millis1 = System.nanoTime();
				server = (Connection) Naming.lookup("rmi://" + ip + "/Server"); 
				String receivedJson = server.receiveJson(json.toString()); 
				long millis2 = System.nanoTime();
				
				System.out.println(millis2 - millis1); 
			}
			
			
			
			//JsonReader jsonReader = Json.createReader(new StringReader(receivedJson));
			//JsonObject jsonObject = jsonReader.readObject();
			//127.0.0.1jsonReader.close();
			
			//parseJson(jsonObject);
			
		} 
		catch (Exception e) { 
			System.out.println("HelloClient exception: " + e.getMessage()); 
			e.printStackTrace(); 
		} 
	}
	
	private static void studentConnection(String ip) {
		
		System.out.println("Bem-vindo, aluno!");
		
		while(true) {
			System.out.println("\nO que você gostaria de consultar? Digite o número correspondente:");
			System.out.println("[1] listar todos os códigos de disciplinas com seus respectivos títulos");
			System.out.println("[2] dado o código de uma disciplina, retornar a ementa");
			System.out.println("[3] dado o código de uma disciplina, retornar todas as informações desta disciplina");
			System.out.println("[4] listar todas as informações de todas as disciplinas");
			System.out.println("[5] dado o código de uma disciplina, retornar o texto de comentário sobre a próxima aula\n");
			
			Scanner reader = new Scanner(System.in); 
			String code = reader.next();
			
			JsonBuilderFactory factory = Json.createBuilderFactory(null);
			JsonObjectBuilder builder = factory.createObjectBuilder();
			
			if(code.equalsIgnoreCase("1")) {
				JsonObject json = builder.add("message_number", 1).build();
				sendJson(json, ip);
				
				
			}
			else if(code.equalsIgnoreCase("2")) {
				System.out.println("Escreva o nome da disciplina:");
				String disciplina = reader.next();
				
				JsonObject json = builder.add("message_number", 2)
						.add("content", factory.createObjectBuilder()
								.add("codigo", disciplina))
						.build();
				sendJson(json, ip);
				
				
			}
			else if(code.equalsIgnoreCase("3")) {
				System.out.println("Escreva o nome da disciplina:");
				String disciplina = reader.next();
				
				JsonObject json = builder.add("message_number", 3)
						.add("content", factory.createObjectBuilder()
								.add("codigo", disciplina))
						.build();
				sendJson(json, ip);
				
			}
			else if(code.equalsIgnoreCase("4")) {
				JsonObject json = builder.add("message_number", 4).build();
				sendJson(json, ip);
				
			}
			else if(code.equalsIgnoreCase("5")) {
				System.out.println("Escreva o nome da disciplina:");
				String disciplina = reader.next();
				
				JsonObject json = builder.add("message_number", 6)
						.add("content", factory.createObjectBuilder()
								.add("codigo", disciplina))
						.build();
				sendJson(json, ip);
			}
			else {
				System.out.println("Este não é um código válido!");
			}
			
		}
		
		
		
	}
	
	private static void professorConnection(String ip) {
System.out.println("Bem-vindo, professor!");
		
		while(true) {
			System.out.println("\nO que você gostaria de consultar? Digite o número correspondente:");
			System.out.println("[1] listar todos os códigos de disciplinas com seus respectivos títulos");
			System.out.println("[2] dado o código de uma disciplina, retornar a ementa");
			System.out.println("[3] dado o código de uma disciplina, retornar todas as informações desta disciplina");
			System.out.println("[4] listar todas as informações de todas as disciplinas");
			System.out.println("[5] escrever um texto de comentário sobre a próxima aula de uma disciplina");
			System.out.println("[6] dado o código de uma disciplina, retornar o texto de comentário sobre a próxima aula\n");
			
			Scanner reader = new Scanner(System.in); 
			String code = reader.next();
			
			JsonBuilderFactory factory = Json.createBuilderFactory(null);
			JsonObjectBuilder builder = factory.createObjectBuilder();
			
			if(code.equalsIgnoreCase("1")) {
				JsonObject json = builder.add("message_number", 1).build();
				sendJson(json, ip);
				
				
			}
			else if(code.equalsIgnoreCase("2")) {
				System.out.println("Escreva o nome da disciplina:");
				String disciplina = reader.next();
				
				JsonObject json = builder.add("message_number", 2)
						.add("content", factory.createObjectBuilder()
								.add("codigo", disciplina))
						.build();
				sendJson(json, ip);
				
				
			}
			else if(code.equalsIgnoreCase("3")) {
				System.out.println("Escreva o nome da disciplina:");
				String disciplina = reader.next();
				
				JsonObject json = builder.add("message_number", 3)
						.add("content", factory.createObjectBuilder()
								.add("codigo", disciplina))
						.build();
				sendJson(json, ip);
				
			}
			else if(code.equalsIgnoreCase("4")) {
				JsonObject json = builder.add("message_number", 4).build();
				sendJson(json, ip);
				
			}
			else if(code.equalsIgnoreCase("5")) {
				System.out.println("Escreva o nome da disciplina:");
				String disciplina = reader.next();
				System.out.println("Escreva o comentário:");
				reader.nextLine();
				String comentario = reader.nextLine();
				
				
				
				JsonObject json = builder.add("message_number", 5)
						.add("content", factory.createObjectBuilder()
								.add("codigo", disciplina)
								.add("comentario", comentario)
								)
						.build();
				sendJson(json, ip);
			}
			else if(code.equalsIgnoreCase("6")) {
				System.out.println("Escreva o nome da disciplina:");
				String disciplina = reader.next();
				
				JsonObject json = builder.add("message_number", 6)
						.add("content", factory.createObjectBuilder()
								.add("codigo", disciplina))
						.build();
				sendJson(json, ip);
			}
			else {
				System.out.println("Este não é um código válido!");
			}
			
		}
		
	}
	
	
	public static void main(String args[]) { 
		
		Scanner reader = new Scanner(System.in); 
		System.out.println("Informe o IP do servidor:");
		String ip = reader.next();
		System.out.println("Bem vindo ao sistema de comunicação Alunos-Professor! Digite [A] se você for aluno ou [P] se você for professor:");
		String type = reader.next();
		
		while(true) {
			if(type.equalsIgnoreCase("A")) {
				studentConnection(ip);
			}
			else if(type.equalsIgnoreCase("P")) {
				professorConnection(ip);
			}
			else {
				System.out.println("Não entendi, digite [A] se você for aluno ou [P] se você for professor:");
			}
		}
		
		
		
		
		
	} 
}