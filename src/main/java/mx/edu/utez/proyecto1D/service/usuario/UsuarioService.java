package mx.edu.utez.proyecto1D.service.usuario;

import mx.edu.utez.proyecto1D.controller.usuario.dto.RequestCreateUserDTO;
import mx.edu.utez.proyecto1D.controller.usuario.dto.RequestUpdateUserDTO;
import mx.edu.utez.proyecto1D.exception.customExceptions.CustomBadRequestException;
import mx.edu.utez.proyecto1D.model.persona.Persona;
import mx.edu.utez.proyecto1D.model.usuario.Roles;
import mx.edu.utez.proyecto1D.model.usuario.Usuario;
import mx.edu.utez.proyecto1D.repository.persona.PersonaRepository;
import mx.edu.utez.proyecto1D.repository.usuario.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, PersonaRepository personaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
    }

    //services
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }
    @Transactional
    public Usuario createUser(RequestCreateUserDTO payload) {
        //Validaciones
        if (usuarioRepository.findByUsername(payload.getUsername()).isPresent()) {
             throw new CustomBadRequestException("El usuario ya existe");
        }
        //Crear persona
        Persona persona = new Persona();
        persona.setNombre(payload.getNombre());
        persona.setPrimerApellido(payload.getPrimerApellido());
        persona.setSegundoApellido(payload.getSegundoApellido());
        persona.setCorreo(payload.getCorreo());
        persona.setCurp(payload.getCurp());
        persona.setFechaNacimiento(payload.getFechaNacimiento());

        persona = personaRepository.save(persona);

        //Crear la isntancia de usuario que vamos a guardar
        // a partir de los datos del dtp
        Usuario usuario = new Usuario();
        usuario.setUsername(payload.getUsername());
        usuario.setPassword(payload.getPassword());
        usuario.setDescripcion(payload.getDescripcion());
        usuario.setEnable(true);
        usuario.setRol(
                Roles.valueOf(payload.getRol())
        );
        usuario.setPersona(persona);

        return usuarioRepository.save(usuario);
    }
    @Transactional
    public Usuario desactivar_Activar(Long id){
        if (!usuarioRepository.existsById(id)) {
            throw new CustomBadRequestException("Usuario no encontrado");
        }
        Usuario usuario = usuarioRepository.findById(id).get();
        usuario.setEnable(!usuario.isEnable());
        return usuarioRepository.save(usuario);
    }
    @Transactional(readOnly = true)
    public Usuario getUsuario(Long id){
        if (!usuarioRepository.existsById(id)) {
            throw new CustomBadRequestException("Usuario no encontrado");
        }
        return usuarioRepository.findById(id).get();
    }
    @Transactional
    public Usuario update(Long id, RequestUpdateUserDTO payload) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + id));
        usuarioExistente.setUsername(payload.getUsername());
        usuarioExistente.setDescripcion(payload.getDescripcion());
        return usuarioRepository.save(usuarioExistente);
    }

}