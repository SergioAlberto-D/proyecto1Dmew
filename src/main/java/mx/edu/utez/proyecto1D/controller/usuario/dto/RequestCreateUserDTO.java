package mx.edu.utez.proyecto1D.controller.usuario.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateUserDTO {

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 6, max = 20, message = "El nombre debe contener entre 6  y 20 caracteres")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, incluir mayúscula, minúscula, número y un carácter especial (@$!%*?&)"
    )
    private String password;

    private String descripcion;

    @NotBlank(message = "El rol es obligatorio")
    @Pattern(
            regexp = "^(ADMIN|PROFESOR|ESTUDIANTE)$",
            message = "El rol debe ser únicamente: ADMIN, PROFESOR o ESTUDIANTE"
    )
    private String rol;

    //Datos de la persona

    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String correo;
    private String curp;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;


}
