package mx.edu.utez.proyecto1D.controller.usuario;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1D.controller.usuario.dto.RequestCreateUserDTO;
import mx.edu.utez.proyecto1D.controller.usuario.dto.RequestUpdateUserDTO;
import mx.edu.utez.proyecto1D.model.usuario.Usuario;
import mx.edu.utez.proyecto1D.service.usuario.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/usuario")
public class  UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }


    @GetMapping
    public ResponseEntity<List<Usuario>> getAllUsers(){
        return ResponseEntity.status(200).body(
                usuarioService.getAllUsuarios()
        );
    }
    @PostMapping
    public ResponseEntity<Usuario> createUser (@RequestBody @Valid RequestCreateUserDTO payload){

        return ResponseEntity.status(201).body(
                usuarioService.createUser(payload)
        );
    }
    @PutMapping("/change/{id}")
    public ResponseEntity<Usuario> cambio (@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(usuarioService.desactivar_Activar(id));
    }
    @GetMapping("/Buscarid/{id}")
    public ResponseEntity<Usuario> Buscar (@PathVariable("id") Long id){
        return ResponseEntity.status(200).body(usuarioService.getUsuario(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Usuario> updateProfile(@PathVariable("id") Long id, @RequestBody @Valid RequestUpdateUserDTO payload) {
        return ResponseEntity.status(200).body(usuarioService.update(id, payload));
    }
}
