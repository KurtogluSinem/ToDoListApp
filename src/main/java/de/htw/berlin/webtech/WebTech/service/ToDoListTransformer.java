package de.htw.berlin.webtech.WebTech.service;

import de.htw.berlin.webtech.WebTech.persistence.Dringlichkeit;
import de.htw.berlin.webtech.WebTech.persistence.ToDoListEntity;
import de.htw.berlin.webtech.WebTech.web.api.ToDoList;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ToDoListTransformer {

    public ToDoList transformEntity(ToDoListEntity toDoListEntity) {
        var dringlichkeit = toDoListEntity.getDringlichkeit() != null ? toDoListEntity.getDringlichkeit().name() : Dringlichkeit.UNKNOWN.name();
        return new ToDoList(
                toDoListEntity.getId(),
                toDoListEntity.getAufgabentitel(),
                toDoListEntity.getAufgabe(),
                toDoListEntity.getDatum(),
                toDoListEntity.getErledigt(),
                dringlichkeit);

    }
}
