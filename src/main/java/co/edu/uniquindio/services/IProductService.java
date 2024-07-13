package co.edu.uniquindio.services;

import co.edu.uniquindio.dtos.ProductDTO;
import co.edu.uniquindio.entities.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {

    Product agregarProducto(ProductDTO producto) throws Exception;

    List<ProductDTO> listarProductos() throws Exception;

    void eliminarProducto(int id) throws Exception;

    Product modificarProducto(int id, ProductDTO producto) throws Exception;

    ProductDTO mostrarInformacionProducto(int id) throws Exception;

    Double calcularValorTotal(int id, int cantidad) throws Exception;

    Double calcularTotalInventario() throws Exception;


}
