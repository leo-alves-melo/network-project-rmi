//package com.javaapi.json.examples;

import java.rmi.Remote; 
import java.rmi.RemoteException; 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
 
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
/* 
Classname: Connection
Comment: The remote interface. 
*/
public interface Connection extends Remote { 
	String receiveJson(String json) throws RemoteException; 
}