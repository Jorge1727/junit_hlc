import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import ies.vdm.crud.PersonaCRUD;
import ies.vdm.model.Persona;

public class PersonaCRUDTest {
    private PersonaCRUD personaCRUD;

    @Before
    public void setUp() {
        personaCRUD = new PersonaCRUD();
    }

    @Test
    public void testAgregarPersona() {
        Persona persona = new Persona(1, "Juan", 30);
        personaCRUD.agregarPersona(persona);
        assertEquals(persona, personaCRUD.obtenerPersonaPorId(1));
    }

    @Test
    public void testActualizarPersona() {
        Persona persona = new Persona(1, "Juan", 30);
        personaCRUD.agregarPersona(persona);
        Persona personaActualizada = new Persona(1, "Pedro", 35);
        personaCRUD.actualizarPersona(personaActualizada);
        assertEquals(personaActualizada, personaCRUD.obtenerPersonaPorId(1));
    }

    @Test
    public void testEliminarPersona() {
        Persona persona = new Persona(1, "Juan", 30);
        personaCRUD.agregarPersona(persona);
        personaCRUD.eliminarPersona(1);
        assertNull(personaCRUD.obtenerPersonaPorId(1));
    }
}
