package biblioteca.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LibroDTO {
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

    private MultipartFile caratula;
}
