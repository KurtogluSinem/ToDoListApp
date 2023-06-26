package de.htw.berlin.webtech.WebTech;

import de.htw.berlin.webtech.WebTech.service.ToDoListService;
import de.htw.berlin.webtech.WebTech.web.api.ToDoList;
import de.htw.berlin.webtech.WebTech.web.api.ToDoListManipulationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ToDoListRestController {

    private final ToDoListService toDoListService;

    public ToDoListRestController(ToDoListService toDoListService) {
        this.toDoListService = toDoListService;
    }

    @GetMapping(path = "/api/v1/todolist")
    public ResponseEntity<List<ToDoList>> fetchToDoList(){
        return ResponseEntity.ok(toDoListService.findAll());
    }

    @GetMapping(path = "/api/v1/todolist/{id}")
    public ResponseEntity<ToDoList> fetchToDoListById(@PathVariable Long id){
        var toDoList = toDoListService.findById(id);
        return toDoList != null? ResponseEntity.ok(toDoList) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/todolist")
    public ResponseEntity<Void> createToDoList(@RequestBody ToDoListManipulationRequest request) throws URISyntaxException {
       var toDoList = toDoListService.create(request);
       URI uri = new URI("/api/v1/todolist/" + toDoList.getId());
       return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/todolist/{id}")
    public ResponseEntity<ToDoList> updateToDoList(@PathVariable Long id, @RequestBody ToDoListManipulationRequest request){
       var toDoList = toDoListService.update(id, request);
       return toDoList != null? ResponseEntity.ok(toDoList) : ResponseEntity.notFound().build();
    }

    @PutMapping(path = "/api/v1/todolist/{id}/erledigt")
    public ResponseEntity<ToDoList> doneToDos(@PathVariable Long id, @RequestBody ToDoListManipulationRequest request) {
        var toDoList= toDoListService.isFinished(id, request);
        return toDoList != null ? ResponseEntity.ok(toDoList) : ResponseEntity.notFound().build();
    }
    @DeleteMapping(path = "/api/v1/todolist/{id}")
    public ResponseEntity<Void> deleteToDoList(@PathVariable Long id){
        boolean successful = toDoListService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/todolist/deleteall")
    public ResponseEntity<Void> deleteAll() {
        boolean successful = toDoListService.deleteAll();
        return successful ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

