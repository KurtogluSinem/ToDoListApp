package de.htw.berlin.webtech.WebTech;

import de.htw.berlin.webtech.WebTech.persistence.Dringlichkeit;
import de.htw.berlin.webtech.WebTech.persistence.ToDoListEntity;
import de.htw.berlin.webtech.WebTech.service.ToDoListService;
import de.htw.berlin.webtech.WebTech.persistence.ToDoListRepository;
import de.htw.berlin.webtech.WebTech.service.ToDoListTransformer;
import de.htw.berlin.webtech.WebTech.web.api.ToDoListManipulationRequest;
import de.htw.berlin.webtech.WebTech.web.api.ToDoList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ToDoListServiceTest {

    @Mock
    private ToDoListRepository toDoListRepository;

    @Mock
    private ToDoListTransformer toDoListTransformer;

    @InjectMocks
    private ToDoListService toDoListService;

    @Test
    @DisplayName("should return true if delete was successful")
    void should_return_true_if_delete_was_successful() {
        // given
        Long givenId = 111L;
        doReturn(true).when(toDoListRepository).existsById(givenId);

        // when
        boolean result = toDoListService.deleteById(givenId);

        // then
        verify(toDoListRepository).deleteById(givenId);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("should return false if ToDoList to delete does not exist")
    void should_return_false_if_todo_to_delete_does_not_exist() {
        // given
        Long givenId = 111L;
        when(toDoListRepository.existsById(givenId)).thenReturn(false);

        // when
        boolean result = toDoListService.deleteById(givenId);

        // then
        verify(toDoListRepository).existsById(givenId);
        verifyNoMoreInteractions(toDoListRepository);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("should create ToDoList with valid request")
    void should_create_todo_list_with_valid_request() {
        // given
        ToDoListManipulationRequest request = new ToDoListManipulationRequest();
        request.setAufgabentitel("Test Task");
        request.setAufgabe("Test Description");
        request.setErledigt(false);
        LocalDate date = LocalDate.of(2023, 7, 25);
        request.setDatum(java.sql.Date.valueOf(date)); // Convert LocalDate to java.sql.Date
        request.setDringlichkeit("HOCH");

        ToDoListEntity savedEntity = new ToDoListEntity();
        savedEntity.setId(1L);
        savedEntity.setAufgabentitel("Test Task");
        savedEntity.setAufgabe("Test Description");
        savedEntity.setErledigt(false);
        savedEntity.setDatum(java.sql.Date.valueOf(date)); // Convert LocalDate to java.sql.Date
        savedEntity.setDringlichkeit(Dringlichkeit.HOCH);
        when(toDoListRepository.save(any())).thenReturn(savedEntity);

        ToDoList transformedEntity = new ToDoList(1L, "Test Task", "Test Description", java.sql.Date.valueOf(date), false, "HOCH");
        when(toDoListTransformer.transformEntity(any())).thenReturn(transformedEntity);

        // when
        ToDoList result = toDoListService.create(request);

        // then
        verify(toDoListRepository).save(any());
        verify(toDoListTransformer).transformEntity(any());
        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getAufgabentitel()).isEqualTo("Test Task");
        assertThat(result.getAufgabe()).isEqualTo("Test Description");
        assertThat(result.isErledigt());
    }
}
