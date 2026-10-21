package mx.edu.utez.proyecto1D.controller.persona;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1D.controller.persona.dto.RequestUpdatePersonaDTO;
import mx.edu.utez.proyecto1D.model.persona.Persona;
import mx.edu.utez.proyecto1D.service.persona.PersonaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/persona")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Persona> update(@PathVariable("id") Long id, @RequestBody @Valid RequestUpdatePersonaDTO payload ) {
        return ResponseEntity.status(200).body(personaService.updatePersona(id, payload));
    }
}