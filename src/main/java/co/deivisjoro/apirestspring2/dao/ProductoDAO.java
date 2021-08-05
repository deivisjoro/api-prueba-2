package co.deivisjoro.apirestspring2.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import co.deivisjoro.apirestspring2.entidad.Producto;

public interface ProductoDAO extends JpaRepository<Producto, Long>{
	
	
	
}
