package biblioteca.service.Impl;

import biblioteca.dto.LibroDTO;
import biblioteca.exception.RecursoNoEncontradoException;
import biblioteca.model.Libro;
import biblioteca.repository.LibroRepository;
import biblioteca.service.LibroService;
import com.cloudinary.Cloudinary;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class LibroServiceImpl implements LibroService {
    private final LibroRepository libroRepo;
    private final Cloudinary cloudinary;

    @Override
    public Libro crearLibro(LibroDTO dto) {
        Libro libro = mapearDTO(dto);

        if (dto.getCaratula() != null && !dto.getCaratula().isEmpty()) {
            try {
                Map<?, ?> result = cloudinary.uploader().upload(dto.getCaratula().getBytes(), Map.of());
                libro.setCaratula(result.get("secure_url").toString());
            } catch (IOException e) {
                throw new RuntimeException("Error al subir la carátula", e);
            }
        }

        return libroRepo.save(libro);
    }

    @Override
    public Libro obtenerLibro(String isbn) {
        return libroRepo.findById(isbn)
                .orElseThrow(() -> new RecursoNoEncontradoException("Libro no encontrado: " + isbn));
    }

    @Override
    public List<Libro> listarLibros() {
        return libroRepo.findAll();
    }

    @Override
    public Libro actualizarLibro(String isbn, LibroDTO dto) {
        Libro libro = obtenerLibro(isbn);
        Libro nuevosDatos = mapearDTO(dto);

        libro.setTitulo(nuevosDatos.getTitulo());
        libro.setEditorial(nuevosDatos.getEditorial());
        libro.setAnioPublicacion(nuevosDatos.getAnioPublicacion());
        libro.setCategoria(nuevosDatos.getCategoria());
        libro.setNumeroEjemplares(nuevosDatos.getNumeroEjemplares());
        libro.setUbicacion(nuevosDatos.getUbicacion());

        if (dto.getCaratula() != null && !dto.getCaratula().isEmpty()) {
            try {
                Map<?, ?> result = cloudinary.uploader().upload(dto.getCaratula().getBytes(), Map.of());
                libro.setCaratula(result.get("secure_url").toString());
            } catch (IOException e) {
                throw new RuntimeException("Error al subir la nueva carátula", e);
            }
        }

        return libroRepo.save(libro);
    }

    @Override
    public void eliminarLibro(String isbn) {
        Libro libro = obtenerLibro(isbn);
        libroRepo.delete(libro);
    }

    @Override
    public Page<Libro> listarLibrosPaginado(Pageable pageable) {
        return libroRepo.findAll(pageable);
    }

    private Libro mapearDTO(LibroDTO dto) {
        return Libro.builder()
                .isbn(dto.getIsbn())
                .titulo(dto.getTitulo())
                .editorial(dto.getEditorial())
                .anioPublicacion(dto.getAnioPublicacion())
                .categoria(dto.getCategoria())
                .numeroEjemplares(dto.getNumeroEjemplares())
                .ubicacion(dto.getUbicacion())
                .build();
    }
}
