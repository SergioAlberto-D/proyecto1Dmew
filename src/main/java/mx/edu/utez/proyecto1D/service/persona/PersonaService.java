package mx.edu.utez.proyecto1D.service.persona;

import mx.edu.utez.proyecto1D.controller.persona.dto.RequestUpdatePersonaDTO;
import mx.edu.utez.proyecto1D.model.persona.Persona;
import mx.edu.utez.proyecto1D.repository.persona.PersonaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Transactional
    public Persona updatePersona(Long id, RequestUpdatePersonaDTO payload) {
        Persona personaExistente = personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la persona con ID: " + id));

        personaExistente.setNombre(payload.getNombre());
        personaExistente.setPrimerApellido(payload.getPrimerApellido());
        personaExistente.setSegundoApellido(payload.getSegundoApellido());
        personaExistente.setFechaNacimiento(payload.getFechaNacimiento());
        personaExistente.setCurp(payload.getCurp());
        personaExistente.setCorreo(payload.getCorreo());


        return personaRepository.save(personaExistente);
    }
}
