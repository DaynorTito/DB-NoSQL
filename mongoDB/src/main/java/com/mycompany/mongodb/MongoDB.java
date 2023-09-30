
package com.mycompany.mongodb;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class MongoDB {
    public static MongoClient mongoClient;
    public static MongoDatabase db;
    
    public static void main(String[] args) {
        connectionMongo();
        eliminarEstudiante("123456789");
        consultarEstudiantes();
        
    }
    public static void eliminarEstudiante(String identificadorEstudiante) {
        MongoCollection<Document> estudianteCollection = db.getCollection("estudiante");
        try {
            Document filtro = new Document("numero_identificacion", identificadorEstudiante);
            estudianteCollection.deleteOne(filtro);
            System.out.println("Se elimino el estudiante con identificador "+identificadorEstudiante+" correctamente");
        } catch (MongoException e) {
            System.out.println("Error: "+e.getMessage());
        }
        
    }
    public static void actualizarSemestreEstudiante(int newSem) {
        MongoCollection<Document> estudianteCollection = db.getCollection("estudiante");
        try {
            Document filtro = new Document("numero_identificacion", "123456789");
            Document actualizacion = new Document("$set", new Document("semestre_actual", newSem));
            estudianteCollection.updateOne(filtro, actualizacion);
            System.out.println("Se actualizo el semestre a "+newSem+" semestre, correctamente");
        } catch (MongoException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    public static void consultarEstudiantes() {
        MongoCollection<Document> estudianteCollection = db.getCollection("estudiante");
        try {
            estudianteCollection.find().forEach(document -> {
            System.out.println(document.toJson());
        });
        } catch (MongoException e) {
            System.out.println("Error: "+e.getMessage());
        }
    }
    
    public static void insertarEstudiante(String nombre, String apellido, String numId, String direccion, String fechaNac, String program, int sem) {
        MongoCollection<Document> estudianteCollection = db.getCollection("estudiante");
        
        try {
            Document nuevoEstudiante = new Document("nombre", nombre)
                    .append("apellido", apellido)
                    .append("numero_identificacion", numId)
                    .append("direccion", direccion)
                    .append("fecha_nacimiento", fechaNac)
                    .append("programa_academico", program)
                    .append("semestre_actual", sem);
            estudianteCollection.insertOne(nuevoEstudiante);
            System.out.println("Se agrego el estudiante correctamente");
        } catch (MongoException me) {
            System.out.println("");
        }
    }
    public static void connectionMongo() {
        try {
            mongoClient = MongoClients.create("mongodb://localhost:27017");
            db = mongoClient.getDatabase("universidad");
            System.out.println("Se conecto: "+db.getName());
        } catch (MongoException e) {
            System.out.println(e.getMessage());
        }
        
    }
}
