import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

import javax.swing.*;

public class CRUDMongoDB {

    private MongoDBManager mongoDBManager;

    public CRUDMongoDB(MongoDBManager mongoDBManager) {
        this.mongoDBManager = mongoDBManager;
    }

    public void insertarDocumento(String nombre, String precio, String cantidad, String Productos) {
        MongoCollection<Document> collection = mongoDBManager.getCollection(Productos);
        Document document = new Document("nombre", nombre)
                .append("precio", precio)
                .append("cantidad",cantidad);
        collection.insertOne(document);
        JOptionPane.showMessageDialog(null, "Documento insertado: " + document.toJson());
    }

    public void actualizarDocumento(String nombreActualizar, String precioNuevo, String Productos) {
        MongoCollection<Document> collection = mongoDBManager.getCollection(Productos);
        collection.updateOne(new Document("nombre", nombreActualizar),
                new Document("$set", new Document("precio", precioNuevo)));
        JOptionPane.showMessageDialog(null, "Documento actualizado");
    }

    public void mostrarDocumentos(String collectionName) {
        MongoCollection<Document> collection = mongoDBManager.getCollection(collectionName);
        StringBuilder sb = new StringBuilder();
        MongoCursor<Document> cursor = collection.find().iterator();
        while (cursor.hasNext()) {
            Document doc = cursor.next();
            sb.append(doc.toJson()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void eliminarDocumento(String nombreEliminar, String Productos) {
        MongoCollection<Document> collection = mongoDBManager.getCollection(Productos);
        collection.deleteOne(new Document("nombre", nombreEliminar));
        JOptionPane.showMessageDialog(null, "Documento eliminado");
    }
}