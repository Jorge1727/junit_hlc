package ies.vdm.crud;

import java.util.ArrayList;
import java.util.List;

import ies.vdm.model.Persona;

public class PersonaCRUD {
    private List<Persona> personas;

    public PersonaCRUD() {
        this.personas = new ArrayList<>();
    }

    //create
    public void agregarPersona(Persona persona) {
        personas.add(persona);
    }

    //read
    public Persona obtenerPersonaPorId(int id) {
        for (Persona persona : personas) {
            if (persona.getId() == id) {
                return persona;
            }
        }
        return null;
    }

    //update
    public void actualizarPersona(Persona personaActualizada) {
        for (int i = 0; i < personas.size(); i++) {
            if (personas.get(i).getId() == personaActualizada.getId()) {
                personas.set(i, personaActualizada);
                return;
            }
        }
    }

    //delete
    public void eliminarPersona(int id) {
        personas.removeIf(persona -> persona.getId() == id);
    }
}
