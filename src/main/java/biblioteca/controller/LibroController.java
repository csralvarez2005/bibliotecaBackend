package biblioteca.controller;

import biblioteca.dto.LibroDTO;
import biblioteca.model.Libro;
import biblioteca.service.LibroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/libros")
@RequiredArgsConstructor
public class LibroController {
    private final LibroService libroService;

    @PostMapping
    public ResponseEntity<Libro> crear(@ModelAttribute @Valid LibroDTO dto) {
        return new ResponseEntity<>(libroService.crearLibro(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Libro> listar() {
        return libroService.listarLibros();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Libro> obtener(@PathVariable String isbn) {
        return ResponseEntity.ok(libroService.obtenerLibro(isbn));
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<Libro> actualizar(@PathVariable String isbn, @ModelAttribute @Valid LibroDTO dto) {
        return ResponseEntity.ok(libroService.actualizarLibro(isbn, dto));
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> eliminar(@PathVariable String isbn) {
        libroService.eliminarLibro(isbn);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/paginado")
    public ResponseEntity<Page<Libro>> listarPaginado(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Libro> librosPaginados = libroService.listarLibrosPaginado(pageable);
        return ResponseEntity.ok(librosPaginados);
    }
}
