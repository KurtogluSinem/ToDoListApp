package de.htw.berlin.webtech.WebTech;

import de.htw.berlin.webtech.WebTech.service.ToDoListService;
import de.htw.berlin.webtech.WebTech.web.api.ToDoList;
import de.htw.berlin.webtech.WebTech.web.api.ToDoListManipulationRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todolist")
public class ToDoListRestController {

    private final ToDoListService toDoListService;


    public ToDoListRestController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @GetMapping
    public ResponseEntity<List<ToDoList>> fetchToDoList() {
        List<ToDoList> toDoList = toDoListService.findAll();
        return ResponseEntity.ok(toDoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDoList> fetchToDoListById(@PathVariable Long id) {
        ToDoList toDoList = toDoListService.findById(id);
        return toDoList != null ? ResponseEntity.ok(toDoList) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createToDoList(@Valid @RequestBody ToDoListManipulationRequest request) throws URISyntaxException {
        ToDoList toDoList = toDoListService.create(request);
        URI uri = new URI("/api/v1/todolist/" + toDoList.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDoList> updateToDoListe(@PathVariable Long id, @Valid @RequestBody ToDoListManipulationRequest request) {
        ToDoList toDoList = toDoListService.update(id, request);
        if (toDoList != null) {
            return ResponseEntity.ok(toDoList);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/erledigt")
    public ResponseEntity<ToDoList> toggleDone(@PathVariable Long id, @Valid @RequestBody ToDoListManipulationRequest request) {
        ToDoList toDoList = toDoListService.toggleDone(id, request);
        return toDoList != null ? ResponseEntity.ok(toDoList) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToDoListe(@PathVariable Long id) {
        boolean successful = toDoListService.deleteById(id);
        return successful ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAll() {
        boolean successful = toDoListService.deleteAll();
        return successful ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
