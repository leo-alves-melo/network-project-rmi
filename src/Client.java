//package com.javaapi.json.examples;

import java.rmi.Naming; 
import java.rmi.RemoteException; 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
 
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.*;

import java.util.Scanner;
/* 
Classname: Client 
Comment: The RMI client. 
*/
public class Client { 
	// The Hello object "obj" is the identifier that is 
	   
	// the Hello interface. 
	static Connection server = null; 
	
	private static void sendJson(JsonObject json, String ip) {
		try { 
			
			System.out.println(json.toString()); 
			server = (Connection) Naming.lookup("rmi://" + ip + "/Server"); 
			
			System.out.println(json.toString()); 
			String receivedJson = server.receiveJson(json.toString()); 
			System.out.println(json.toString()); 
			System.out.println(receivedJson); 
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
				JsonObject json = builder.add("message_number", 0).build();
				sendJson(json, ip);
				
				
			}
			else if(code.equalsIgnoreCase("2")) {
				
			}
			else if(code.equalsIgnoreCase("3")) {
				
			}
			else if(code.equalsIgnoreCase("4")) {
				
			}
			else if(code.equalsIgnoreCase("5")) {
				
			}
			else {
				System.out.println("Este não é um código válido!");
			}
			
		}
		
		
		
	}
	
	private static void professorConnection(String ip) {
		
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