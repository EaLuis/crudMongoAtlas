import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBManager {

    private MongoClient mongoClient;
    private MongoDatabase database;
    
    
public void connect(String connectionString, String dbName) {
    MongoClientURI uri = new MongoClientURI(connectionString);
    mongoClient = new MongoClient(uri);
    database = mongoClient.getDatabase(dbName);
    System.out.println("Conexión a la base de datos establecida");
}

    public void close() {
        mongoClient.close();
    }

    public MongoCollection<Document> getCollection(String collectionName) {
        return database.getCollection(collectionName);
    }
}