package de.htw.berlin.webtech.WebTech.service;

import de.htw.berlin.webtech.WebTech.persistence.ToDoListEntity;
import de.htw.berlin.webtech.WebTech.persistence.ToDoListRepository;
import de.htw.berlin.webtech.WebTech.web.api.ToDoList;
import de.htw.berlin.webtech.WebTech.web.api.ToDoListManipulationRequest;
import org.jboss.logging.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

    @Service
    public class ToDoListService {

        private final ToDoListRepository toDoListRepository;

        public ToDoListService(ToDoListRepository toDoListRepository) {
            this.toDoListRepository = toDoListRepository;
        }

        public List<ToDoList> findAll() {
            List<ToDoListEntity> toDoLists = toDoListRepository.findAll();
            return toDoLists.stream()
                    .map(this::transformEntity)
                    .collect(Collectors.toList());

        }

        public ToDoList findById(Long id){
            var toDoListEntity = toDoListRepository.findById(id);
            return toDoListEntity.map(this::transformEntity).orElse(null);
        }

        public ToDoList create(ToDoListManipulationRequest request) {
            var toDoListEntity = new ToDoListEntity(request.getÜberschrift(),
                    request.getAufgabe(),
                    request.isErledigt(),
                    request.getDatum());
            toDoListEntity = toDoListRepository.save(toDoListEntity);
            return transformEntity(toDoListEntity);
        }

        public ToDoList update(Long id, ToDoListManipulationRequest request){
            var toDoListEntityOptional = toDoListRepository.findById(id);
            if (toDoListEntityOptional.isEmpty()){
                return null;
            }

            var toDoListEntity = toDoListEntityOptional.get();
            toDoListEntity.setÜberschrift(request.getÜberschrift());
            toDoListEntity.setAufgabe(request.getAufgabe());
            toDoListEntity.setErledigt(request.isErledigt());
            toDoListEntity.setDatum(request.getDatum());

            toDoListEntity = toDoListRepository.save(toDoListEntity);
            return transformEntity(toDoListEntity);
        }


        public boolean deleteById(Long Id){

            toDoListRepository.deleteById(Id);
            return true;
        }


        public ToDoList transformEntity(ToDoListEntity toDoListEntity) {
            return new ToDoList(
                                toDoListEntity.getId(),
                                toDoListEntity.getÜberschrift(),
                                toDoListEntity.getAufgabe(),
                                toDoListEntity.getDatum(),
                                toDoListEntity.getErledigt());

        }
    }

