package com.ejemplo.fibo.fibonacci_api.repository;


import com.ejemplo.fibo.fibonacci_api.model.FibonacciResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//Gestión de entidades: Administra las entidades de tipo FibonacciResult.
//Tipo de clave primaria: El tipo de la clave primaria es Long.
//Spring Data JPA: Proporciona automáticamente métodos como save(), findById(), findAll(), deleteById(), entre otros, sin necesidad de implementación adicional.
@Repository
public interface FibonacciResultRepository extends JpaRepository<FibonacciResult, Long> {// Interfaz que extiende JpaRepository para la gestión de la entidad FibonacciResult
}
// Esta interfaz permite realizar operaciones CRUD (Crear, Leer, Actualizar, Eliminar) sobre la entidad FibonacciResult en la base de datos.
