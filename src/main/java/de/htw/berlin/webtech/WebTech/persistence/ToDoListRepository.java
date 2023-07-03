package de.htw.berlin.webtech.WebTech.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoListRepository extends JpaRepository<ToDoListEntity, Long> {
 List<ToDoListEntity> findAllById(long id);
}


