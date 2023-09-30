import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import java.util.Arrays;
import org.bson.Document;

public class ConectionMongoAtlas {
    
    public static void main(String[] args) {
        //cambiar el password por la del usuari9
        String connectionString = "mongodb+srv://daynortito:password@cluster1.ymnieiz.mongodb.net/?retryWrites=true&w=majority";
           
        String dbName = "Universidad";
        String collectionName = "Estudiantes";

        try (MongoClient mongoClient = MongoClients.create(connectionString)) {
            MongoDatabase database = mongoClient.getDatabase(dbName);
            MongoCollection<Document> estudiantesCollection = database.getCollection(collectionName);

	     // Crear un nuevo documento
            Document nuevoEstudiante = new Document()
                    .append("nombre", "Juan Pérez")
                    .append("edad", 20)
                    .append("carrera", "Ingeniería Informática")
                    .append("correo", "juan.perez@example.com")
                    .append("notas", Arrays.asList(85, 92, 78, 89))
                    .append("fecha_de_inscripción", "2023-01-15");

            // Insertar un documento
            estudiantesCollection.insertOne(nuevoEstudiante);
            System.out.println("Documento insertado: " + nuevoEstudiante.toJson());
            	
            /// Eliminar un documento
            estudiantesCollection.deleteOne(Filters.eq("nombre", "Juan Pérez"));
            System.out.println("Documento eliminado.");
            
           
           // Leer documentos (todos los documentos en la colección)
            System.out.println("Documentos en la colección:");
            estudiantesCollection.find().forEach(document -> {
                System.out.println(document.toJson());
            });

            // Actualizar un documento
            estudiantesCollection.updateOne(
                    Filters.eq("nombre", "Juan Pérez"),
                    Updates.set("edad", 21)
            );
            System.out.println("Documento actualizado.");

            // Eliminar un documento
            estudiantesCollection.deleteOne(Filters.eq("nombre", "Juan Pérez"));
            System.out.println("Documento eliminado.");
        } catch (Exception e) {
            System.err.println("Error al interactuar con MongoDB Atlas: " + e);
        }
    }
}
