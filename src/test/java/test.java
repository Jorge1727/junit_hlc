import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ies.vdm.model.CajeroV1;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/cuentas_bancarias_nueva?serverTimezone=UTC";
        String USUARIO = "root";
        String CONTRASENA = "123456";
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
        public void testRetirarFondos() throws SQLException {
        String input = "1\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        CajeroV1.retirarFondos(getConnection(), "ES23456789012345678901");

        assertTrue(outContent.toString().contains("Retiro exitoso. Saldo actualizado."));
    }


    @Test
    public void testIngresarFondos() throws SQLException {
        String input = "100\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        CajeroV1.ingresarFondos(getConnection(), "ES12345678901234567890");

        assertTrue(outContent.toString().contains("Ingreso exitoso. Saldo actualizado."));
    }

    
    @Test
    public void testConsultarMovimientos() throws SQLException {
        CajeroV1.consultarMovimientos(getConnection(), "ES12345678901234567890");

        assertTrue(outContent.toString().contains("Movimientos de la cuenta"));
    }

    @Test
    public void testListarCuentasCliente() throws SQLException {
        CajeroV1.listarCuentasCliente(getConnection(), "123456789");

        assertTrue(outContent.toString().contains("Cuentas del cliente con DNI"));
    }

    @Test
    public void testConsultarInformacion() throws SQLException {
        CajeroV1.consultarInformacion(getConnection(), "123456789");

        assertTrue(outContent.toString().contains("Cliente con dni"));
    }

    
}
