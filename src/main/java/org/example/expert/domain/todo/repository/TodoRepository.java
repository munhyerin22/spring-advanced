package org.example.expert.domain.todo.repository;

import org.example.expert.domain.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    //fetch join으로 구현되어있는 코드를 @EntityGraph로 변경 N+1문제
    @EntityGraph(attributePaths = {"user"})
//    @Query("SELECT t FROM Toodo t LEFT JOIN FETCH t.user u ORDER BY t.modifiedAt DESC")
    Page<Todo> findAllByOrderByModifiedAtDesc(Pageable pageable);

    //fetch join으로 구현되어있는 코드를 @EntityGraph로 변경 N+1문제
    @EntityGraph(attributePaths = {"user"})
//    @Query("SELECT t FROM Toodo t " +
//            "LEFT JOIN FETCH t.user " +
//            "WHERE t.id = :todoId")
    @NonNull
    Optional<Todo> findById(@Param("todoId") @NonNull Long todoId);

    int countById(Long todoId);
}
