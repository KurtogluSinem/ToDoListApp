package de.htw.berlin.webtech.WebTech.service;

import de.htw.berlin.webtech.WebTech.persistence.Dringlichkeit;
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
        private final ToDoListTransformer toDoListTransformer;

        public ToDoListService(ToDoListRepository toDoListRepository,ToDoListTransformer toDoListTransformer) {
            this.toDoListRepository = toDoListRepository;
            this.toDoListTransformer = toDoListTransformer;
        }

        public List<ToDoList> findAll() {
            List<ToDoListEntity> toDoLists = toDoListRepository.findAll();
            return toDoLists.stream()
                    .map(toDoListTransformer::transformEntity)
                    .collect(Collectors.toList());

        }

        public ToDoList findById(Long id){
            var toDoListEntity = toDoListRepository.findById(id);
            return toDoListEntity.map(toDoListTransformer::transformEntity).orElse(null);
        }

        public ToDoList create(ToDoListManipulationRequest request) {
            var dringlichkeit = Dringlichkeit.valueOf(request.getDringlichkeit());
            var toDoListEntity = new ToDoListEntity(request.getAufgabentitel(),
                    request.getAufgabe(),
                    request.isErledigt(),
                    request.getDatum(),
                    dringlichkeit);
            toDoListEntity = toDoListRepository.save(toDoListEntity);
            return toDoListTransformer.transformEntity(toDoListEntity);
        }

        public ToDoList update(Long id, ToDoListManipulationRequest request){
            var toDoListEntityOptional = toDoListRepository.findById(id);
            if (toDoListEntityOptional.isEmpty()){
                return null;
            }


            var toDoListEntity = toDoListEntityOptional.get();
            toDoListEntity.setAufgabentitel(request.getAufgabentitel());
            toDoListEntity.setAufgabe(request.getAufgabe());
            toDoListEntity.setErledigt(request.isErledigt());
            toDoListEntity.setDatum(request.getDatum());
            toDoListEntity.setDringlichkeit(Dringlichkeit.valueOf(request.getDringlichkeit()));

            toDoListEntity = toDoListRepository.save(toDoListEntity);
            return toDoListTransformer.transformEntity(toDoListEntity);
        }

        public ToDoList isDone(Long id, ToDoListManipulationRequest request){
            var toDoListEntityOptional = toDoListRepository.findById(id);
            if (toDoListEntityOptional.isEmpty()){
                return null;
            }

            var toDoListEntity = toDoListEntityOptional.get();
            if(toDoListEntityOptional.get().getErledigt().equals(true)){
                toDoListEntity.setErledigt(request.isErledigt());

            }
            else{
                toDoListEntity.setErledigt(!request.isErledigt());
            }

            toDoListEntity = toDoListRepository.save(toDoListEntity);
            return toDoListTransformer.transformEntity(toDoListEntity);
        }



        public boolean deleteById(Long Id){

            toDoListRepository.deleteById(Id);
            return true;
        }

        public boolean deleteAll(){

            toDoListRepository.deleteAll();
            return true;
        }


        public ToDoList transformEntity(ToDoListEntity toDoListEntity) {
            var dringlichkeit = toDoListEntity.getDringlichkeit() != null ? toDoListEntity.getDringlichkeit().name(): Dringlichkeit.UNKNOWN.name();
            return new ToDoList(
                                toDoListEntity.getId(),
                                toDoListEntity.getAufgabentitel(),
                                toDoListEntity.getAufgabe(),
                                toDoListEntity.getDatum(),
                                toDoListEntity.getErledigt(),
                                dringlichkeit);

        }


    }


