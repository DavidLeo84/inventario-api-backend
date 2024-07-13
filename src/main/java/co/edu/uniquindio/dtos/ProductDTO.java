package co.edu.uniquindio.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

public record ProductDTO(

        int codigo,
        @NotBlank
        @Length(max = 40)
        String nombre,

        @Positive
        @Min(1)
        Double precio,

        @Positive
        @Min(1)
        @Max(1000)
        int cantidad
) {
}
