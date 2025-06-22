package biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Libro {
    @Id
    @NotBlank
    private String isbn;

    @NotBlank
    private String titulo;

    @NotBlank
    private String editorial;

    @Min(1000)
    private int anioPublicacion;

    @NotBlank
    private String categoria;

    @Min(1)
    private int numeroEjemplares;

    @NotBlank
    private String ubicacion;

    private String caratula; // URL de Cloudinary
}
