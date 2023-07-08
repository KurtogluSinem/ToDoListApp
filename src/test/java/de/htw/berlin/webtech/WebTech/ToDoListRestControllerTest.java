package de.htw.berlin.webtech.WebTech;

import de.htw.berlin.webtech.WebTech.service.ToDoListService;
import de.htw.berlin.webtech.WebTech.web.api.ToDoList;
import de.htw.berlin.webtech.WebTech.web.api.ToDoListManipulationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ToDoListRestController.class)
public class ToDoListRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ToDoListService toDoListService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void fetchToDoList_shouldReturnToDoList() throws Exception {
        // Arrange
        List<ToDoList> expectedToDoList = List.of(
                new ToDoList(1L, "Web-tech", "Backendtests erstellen", new Date(),false, "HOCH")
        );
        doReturn(expectedToDoList).when(toDoListService).findAll();

        // Act & Assert
        mockMvc.perform(get("/api/v1/todolist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(expectedToDoList.size()))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].aufgabentitel").value("Web-tech"))
                .andExpect(jsonPath("$[0].aufgabe").value("Backendtests erstellen"))
                .andExpect(jsonPath("$[0].erledigt").value(false))
                .andExpect(jsonPath("$[0].dringlichkeit").value("HOCH"));

        verify(toDoListService).findAll();
    }

    @Test
    void fetchToDoListById_withExistingId_shouldReturnToDoList() throws Exception {
        // Arrange
        Long id = 1L;
        ToDoList expectedToDoList = new ToDoList(id, "Web-tech", "Backendtests erstellen",  new Date(),false, "HOCH");
        doReturn(expectedToDoList).when(toDoListService).findById(id);

        // Act & Assert
        mockMvc.perform(get("/api/v1/todolist/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.aufgabentitel").value("Web-tech"))
                .andExpect(jsonPath("$.aufgabe").value("Backendtests erstellen"))
                .andExpect(jsonPath("$.erledigt").value(false))
                .andExpect(jsonPath("$.dringlichkeit").value("HOCH"));

        verify(toDoListService).findById(id);
    }

    @Test
    void fetchToDoListById_withNonExistingId_shouldReturnNotFound() throws Exception {
        // Arrange
        Long id = 1L;
        doReturn(null).when(toDoListService).findById(id);

        // Act & Assert
        mockMvc.perform(get("/api/v1/todolist/{id}", id))
                .andExpect(status().isNotFound());

        verify(toDoListService).findById(id);
    }

    @Test
    void createToDoList_withValidRequest_shouldReturnCreated() throws Exception {
        // Arrange
        String createToDoAsJson = "{\"aufgabentitel\":\"Web-tech\", \"aufgabe\":\"Backendtests erstellen\", \"erledigt\":false , \"datum\":\"2023-07-07\", \"dringlichkeit\":\"HOCH\"}";
        ToDoList createdToDoList = new ToDoList(1L, "Web-tech", "Backendtests erstellen",  new Date(),false, "HOCH");
        doReturn(createdToDoList).when(toDoListService).create(any());

        // Act & Assert
        mockMvc.perform(
                        post("/api/v1/todolist")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(createToDoAsJson)
                )
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"))
                .andExpect(header().string("Location", "/api/v1/todolist/1"));

        verify(toDoListService).create(any());
    }

    @Test
    void updateToDoListe_withExistingIdAndValidRequest_shouldReturnToDoList() throws Exception {
        // Arrange
        Long id = 1L;
        String updateToDoAsJson = "{\"aufgabentitel\":\"Updated Task\", \"aufgabe\":\"Updated Description\", \"erledigt\":true , \"datum\":\"2023-07-07\", \"dringlichkeit\":\"HOCH\"}";
        ToDoList updatedToDoList = new ToDoList(id, "Updated Task", "Updated Description", new Date(),true,"HOCH");
        doReturn(updatedToDoList).when(toDoListService).update(anyLong(), any());

        // Act & Assert
        mockMvc.perform(
                        put("/api/v1/todolist/{id}", id)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(updateToDoAsJson)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.aufgabentitel").value("Updated Task"))
                .andExpect(jsonPath("$.aufgabe").value("Updated Description"))
                .andExpect(jsonPath("$.erledigt").value(true))
                .andExpect(jsonPath("$.dringlichkeit").value("HOCH"));

        verify(toDoListService).update(anyLong(), any());
    }

    @Test
    @DisplayName("should delete a todo by ID")
    void should_delete_todo_by_id() throws Exception {
        // Arrange
        Long id = 1L;
        doReturn(true).when(toDoListService).deleteById(id);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/todolist/{id}", id))
                .andExpect(status().isOk());

        verify(toDoListService).deleteById(id);
    }

    @Test
    @DisplayName("should return 404 if unable to delete a todo by ID")
    void should_return_404_if_unable_to_delete_todo_by_id() throws Exception {
        // Arrange
        Long id = 1L;
        doReturn(false).when(toDoListService).deleteById(id);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/todolist/{id}", id))
                .andExpect(status().isNotFound());

        verify(toDoListService).deleteById(id);
    }

    @Test
    @DisplayName("should delete all todos")
    void should_delete_all_todos() throws Exception {
        // Arrange
        doReturn(true).when(toDoListService).deleteAll();

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/todolist/deleteAll"))
                .andExpect(status().isOk());

        verify(toDoListService).deleteAll();
    }
}
