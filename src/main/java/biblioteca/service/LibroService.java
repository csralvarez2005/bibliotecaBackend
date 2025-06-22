package biblioteca.service;

import biblioteca.dto.LibroDTO;
import biblioteca.model.Libro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LibroService {
    Libro crearLibro(LibroDTO dto);
    Libro obtenerLibro(String isbn);
    List<Libro> listarLibros();
    Libro actualizarLibro(String isbn, LibroDTO dto);
    void eliminarLibro(String isbn);
    Page<Libro> listarLibrosPaginado(Pageable pageable);
}
