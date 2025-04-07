package com.edu.uceva.example.facultadservice.controllers;

import com.edu.uceva.example.facultadservice.models.entities.Facultad;
import com.edu.uceva.example.facultadservice.models.services.IFacultadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/facultad-service")
public class FacultadRestController {

    // Declaramos como final el servicio para mejorar la inmutabilidad
    private final IFacultadService facultadService;

    private static final String ERROR = "error";
    private static final String MENSAJE = "mensaje";
    private static final String FACULTAD = "facultad";
    private static final String FACULTADES = "facultades";


    // Inyección de dependencia del servicio que proporciona servicios de CRUD
    public FacultadRestController(IFacultadService facultadService) {
        this.facultadService = facultadService;
    }

    /**
     * Listar todos las facultades.
     */
    @GetMapping("/facultades")
    public ResponseEntity<Map<String, Object>> getFacultades() {
        Map<String, Object> response = new HashMap<>();

        try {
            List<Facultad> facultades = facultadService.findAll();

            if (facultades.isEmpty()) {
                response.put(MENSAJE, "No hay facultades en la base de datos.");
                response.put(FACULTADES, facultades); // para que sea siempre el mismo campo
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }

            response.put(FACULTADES, facultades);
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

     /**
     * Listar facultades con paginación.
     */
    @GetMapping("/facultad/page/{page}")
    public ResponseEntity<Object> index(@PathVariable Integer page) {
        Map<String, Object> response = new HashMap<>();
        Pageable pageable = PageRequest.of(page, 4);

        try {
            Page<Facultad> productos = facultadService.findAll(pageable);

            if (productos.isEmpty()) {
                response.put(MENSAJE, "No hay facultades en la página solicitada.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            return ResponseEntity.ok(productos);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        } catch (IllegalArgumentException e) {
            response.put(MENSAJE, "Número de página inválido.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    /**
     * Crear una nueva facultad pasando el objeto en el cuerpo de la petición.
     */
    @PostMapping("/facultades")
    public ResponseEntity<Map<String, Object>> save(@RequestBody Facultad facultad) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Guardar la facultad en la base de datos
            Facultad nuevaFacultad = facultadService.save(facultad);

            response.put(MENSAJE, "La facultad ha sido creado con éxito!");
            response.put(FACULTAD, nuevaFacultad);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al insertar la facultad en la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Eliminar una facultad pasando el objeto en el cuerpo de la petición.
     */
    @DeleteMapping("/facultades")
    public ResponseEntity<Map<String, Object>> delete(@RequestBody Facultad facultad) {
        Map<String, Object> response = new HashMap<>();
        try {
            Facultad facultadExistente = facultadService.findById(facultad.getId());
            if (facultadExistente == null) {
                response.put(MENSAJE, "La facultad ID: " + facultad.getId() + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            facultadService.delete(facultad);
            response.put(MENSAJE, "La facultad ha sido eliminado con éxito!");
            return ResponseEntity.ok(response);
        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al eliminar la facultad de la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Actualizar una facultad pasando el objeto en el cuerpo de la petición.
     * @param facultad: Objeto Facultad que se va a actualizar
     */
    @PutMapping("/facultades")
    public ResponseEntity<Map<String, Object>> update(@RequestBody Facultad facultad) {
        Map<String, Object> response = new HashMap<>();

        try {
            // Verificar si el producto existe antes de actualizar
            if (facultadService.findById(facultad.getId()) == null) {
                response.put(MENSAJE, "Error: No se pudo editar, la facultad ID: " + facultad.getId() + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            // Guardar directamente la facultad actualizada en la base de datos
            Facultad facultadActualizada = facultadService.save(facultad);

            response.put(MENSAJE, "La facultad ha sido actualizado con éxito!");
            response.put(FACULTAD, facultadActualizada);
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al actualizar la facultad en la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * Obtener un facultad por su ID.
     */
    @GetMapping("/facultades/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        try {
            Facultad facultad = facultadService.findById(id);

            if (facultad == null) {
                response.put(MENSAJE, "La facultad ID: " + id + " no existe en la base de datos.");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }

            response.put(MENSAJE, "La facultad ha sido actualizada con éxito!");
            response.put(FACULTAD, facultad);
            return ResponseEntity.ok(response);

        } catch (DataAccessException e) {
            response.put(MENSAJE, "Error al consultar la base de datos.");
            response.put(ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

}


