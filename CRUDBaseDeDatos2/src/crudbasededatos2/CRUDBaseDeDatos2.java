package crudbasededatos2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Date;
import java.util.List;

public class CRUDBaseDeDatos2 {
    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        Connection connection = conexion.getConnection();
        
        EstudianteDAO crudEstudiante = new EstudianteDAO(connection);
        
        crudEstudiante.eliminarEstudiantePorID(5);
    }
}
