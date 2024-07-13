package co.edu.uniquindio.services;

import co.edu.uniquindio.dtos.ProductDTO;
import co.edu.uniquindio.entities.Product;
import co.edu.uniquindio.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepo;

    public ProductServiceImpl(ProductRepository productRepo) {
        this.productRepo = productRepo;
    }


    @Override
    public Product agregarProducto(ProductDTO productoDTO) throws Exception {

        Optional<Product> optional = productRepo.findByNombre(productoDTO.nombre().toLowerCase());

        if (optional.isPresent()) {
            throw new Exception("El producto ya existe");
        }
        Product product = Product.builder()
                .nombre(productoDTO.nombre().toLowerCase())
                .precio(productoDTO.precio())
                .cantidad(productoDTO.cantidad())
                .build();
        productRepo.save(product);
        return product;
    }

    @Override
    public List<ProductDTO> listarProductos() throws Exception {

        List<Product> productList = productRepo.findAll();

        return productList.stream().map(p -> new ProductDTO(
                p.getCodigo(),
                p.getNombre(),
                p.getPrecio(),
                p.getCantidad()
        )).toList();
    }

    @Override
    public void eliminarProducto(int codigo) throws Exception {

        Optional<Product> optional = productRepo.findById(codigo);
        if (optional.isEmpty()) {
            throw new Exception("No existe el producto");
        }
        productRepo.deleteById(codigo);
    }

    @Override
    public Product modificarProducto(int codigo, ProductDTO productDTO) throws Exception {


        Optional<Product> optionalProduct = productRepo.findByCodigo(codigo);

        if (optionalProduct.isEmpty()) {
            throw new Exception("El producto no existe");
        }

        Product product = optionalProduct.get();
        product.setNombre(productDTO.nombre().toLowerCase());
        product.setPrecio(productDTO.precio());
        product.setCantidad(productDTO.cantidad());
        productRepo.save(product);
        return product;
    }

    @Override
    public ProductDTO mostrarInformacionProducto(int codigo) throws Exception {

        Optional<Product> optional = productRepo.findById(codigo);
        if (optional.isEmpty()) {
            throw new Exception("No existe el producto");
        }
        Product product = optional.get();
        ProductDTO productDTO = new ProductDTO(
                product.getCodigo(),
                product.getNombre(),
                product.getPrecio(),
                product.getCantidad()
        );
        return productDTO;
    }


    @Override
    public Double calcularValorTotal(int codigo, int cantidad) throws Exception {

        Optional<Product> optional = productRepo.findById(codigo);
        if (optional.isEmpty()) {
            throw new Exception("No existe el producto");
        }
        Product product = optional.get();
        Double valor = product.getPrecio() * cantidad;
        return valor;
    }

    @Override
    public Double calcularTotalInventario() throws Exception {

        List<Product> productList = productRepo.findAll();
        Double valor = 0.0;

        for (Product product : productList) {
            valor = valor + product.getPrecio() * product.getCantidad();
        }

        return valor;
    }

}
