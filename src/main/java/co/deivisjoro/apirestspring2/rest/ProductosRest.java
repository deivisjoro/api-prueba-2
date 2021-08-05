package co.deivisjoro.apirestspring2.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.deivisjoro.apirestspring2.dao.ProductoDAO;
import co.deivisjoro.apirestspring2.entidad.Producto;

@RestController
@RequestMapping("/productos")
public class ProductosRest {
	
	@Autowired
	private ProductoDAO productoDAO;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Producto>> getProductos(){
		
		List<Producto> productos = productoDAO.findAll();
		return ResponseEntity.ok(productos);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Producto> getProducto(@PathVariable("id") Long id){
		
		Optional<Producto> producto = productoDAO.findById(id);
		if(producto.isPresent()) {
			return ResponseEntity.ok(producto.get());
		}
		else{
			return ResponseEntity.noContent().build();			
		}		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Producto> setProducto(@RequestBody Producto producto){
		Producto newProducto = productoDAO.save(producto);	
		return ResponseEntity.ok(newProducto);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProducto(@PathVariable("id") Long id){
		productoDAO.deleteById(id);	
		return ResponseEntity.ok(null);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<Producto> updateProducto(@RequestBody Producto producto){
		Optional<Producto> optionalProducto = productoDAO.findById(producto.getId());	
		if(optionalProducto.isPresent()) {
			Producto p = optionalProducto.get();
			p.setNombre(producto.getNombre());
			productoDAO.save(p);
			return ResponseEntity.ok(p);
		}
		else{
			return ResponseEntity.notFound().build();			
		}	
	}
	
	/*@RequestMapping(value="hello", method = RequestMethod.GET)	
	public String hola(){
		return "hola";
	}*/

}
