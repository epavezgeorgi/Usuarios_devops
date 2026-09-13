package com.hackerfinder.backend.usuarios.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hackerfinder.backend.usuarios.model.Tarjeta;


@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {

    public Tarjeta findByIdTarjeta(Long idTarjeta);
    
    public List<Tarjeta> findByNumeroTarjeta(int numeroTarjeta);

    @Query(value = "SELECT * FROM tarjeta WHERE stock > 0 ORDER BY nombre ASC", nativeQuery = true)
    public List<Tarjeta> obtenerTarjetasDisponibles();

    @Query(value = "SELECT * FROM tarjeta ORDER BY precio DESC LIMIT 10", nativeQuery = true)
    public List<Tarjeta> obtenerTop10TarjetasPorPrecio();
}
