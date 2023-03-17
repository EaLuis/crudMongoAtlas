import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import javax.swing.JOptionPane;

public class ConexionMongoDB {

    public static void main(String[] args) {
      String connectionString = "mongodb+srv://user:1234@practica1.rqcdusa.mongodb.net/?retryWrites=true&w=majority";
        String dbName = "Actividad1_502";
        String collectionName = "Productos";

        MongoDBManager mongoDBManager = new MongoDBManager();
        mongoDBManager.connect(connectionString, dbName);

        CRUDMongoDB crud = new CRUDMongoDB(mongoDBManager);
        
        

        while (true) {
            String opcion = JOptionPane.showInputDialog(null, "Seleccione una opción: \n"
                    + "1. Insertar documento \n"
                    + "2. Actualizar documento \n"
                    + "3. Mostrar documentos \n"
                    + "4. Eliminar documento \n"
                    + "5. Salir");

            if (opcion.equals("1")) {
                String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre:");
                String precio = JOptionPane.showInputDialog(null, "Ingrese el precio:");
                String cantidad = JOptionPane.showInputDialog(null, "Ingrese la cantidad:");
                crud.insertarDocumento(nombre, precio, cantidad, collectionName);
            } else if (opcion.equals("2")) {
                String nombreActualizar = JOptionPane.showInputDialog(null, "Ingrese el nombre del documento a actualizar:");
                String precioNuevo = JOptionPane.showInputDialog(null, "Ingrese el nuevo precio:");
                crud.actualizarDocumento(nombreActualizar, precioNuevo, collectionName);
            } else if (opcion.equals("3")) {
                crud.mostrarDocumentos(collectionName);
            } else if (opcion.equals("4")) {
                String nombreEliminar = JOptionPane.showInputDialog(null, "Ingrese el nombre del documento a eliminar:");
                crud.eliminarDocumento(nombreEliminar, collectionName);
            } else if (opcion.equals("5")) {
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Opción no válida");
            }
        }

        mongoDBManager.close();
    }
}
    
    
    
