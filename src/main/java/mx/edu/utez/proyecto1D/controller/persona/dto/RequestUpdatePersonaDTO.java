package mx.edu.utez.proyecto1D.controller.persona.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestUpdatePersonaDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre es demasiado largo")
    private String nombre;

    @NotBlank(message = "El primer apellido es obligatorio")
    @Size(max = 255, message = "El primer apellido es demasiado largo")
    private String primerApellido;

    private String segundoApellido;

    private LocalDate fechaNacimiento;

    private String curp;
    private String correo;
}

