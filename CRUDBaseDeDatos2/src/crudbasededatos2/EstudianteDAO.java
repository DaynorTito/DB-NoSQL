package crudbasededatos2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;

public class EstudianteDAO {

    private Connection connection;

    public EstudianteDAO(Connection connection) {
        this.connection = connection;
    }

    public void crearEstudiante(Estudiante estudiante) {
        String sql = "INSERT INTO Estudiante (nombre, fecha_nacimiento, direccion, paterno, materno) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, estudiante.getNombre());
            java.util.Date fechaNacimiento = estudiante.getFechaNacimiento();
            java.sql.Date fechaNacimientoSQL = new java.sql.Date(fechaNacimiento.getTime());
            statement.setDate(2, fechaNacimientoSQL);
            statement.setString(3, estudiante.getDireccion());
            statement.setString(4, estudiante.getPaterno());
            statement.setString(5, estudiante.getMaterno());
            statement.execute();
            System.out.println("Estudiante Agregado Correctamente");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void mostrarEstudiantePorID(int idEstudiante) {
        String sql = "SELECT * FROM Estudiante WHERE idEstudiante = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idEstudiante);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
                String direccion = resultSet.getString("direccion");
                String paterno = resultSet.getString("paterno");
                String materno = resultSet.getString("materno");
                System.out.println("ID: " + idEstudiante);
                System.out.println("Nombre: " + nombre);
                System.out.println("Fecha de nacimiento: " + fechaNacimiento);
                System.out.println("Dirección: " + direccion);
                System.out.println("Apellido paterno: " + paterno);
                System.out.println("Apellido materno: " + materno);
            } else {
                System.out.println("No se encontró ningún estudiante con el ID: " + idEstudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void cambiarDireccionEstudiantePorID(int idEstudiante, String nuevaDireccion) {
        String sql = "UPDATE Estudiante SET direccion = ? WHERE idEstudiante = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nuevaDireccion);
            statement.setInt(2, idEstudiante);

            int filasActualizadas = statement.executeUpdate();

            if (filasActualizadas > 0) {
                System.out.println("Se cambió la dirección del estudiante con ID " + idEstudiante + " a: " + nuevaDireccion);
            } else {
                System.out.println("No se encontró ningún estudiante con el ID: " + idEstudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
        public void eliminarEstudiantePorID(int idEstudiante) {
        String sql = "DELETE FROM Estudiante WHERE idEstudiante = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idEstudiante);

            int filasEliminadas = statement.executeUpdate();

            if (filasEliminadas > 0) {
                System.out.println("Se eliminó el estudiante con ID " + idEstudiante + " correctamente.");
            } else {
                System.out.println("No se encontró ningún estudiante con el ID: " + idEstudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
