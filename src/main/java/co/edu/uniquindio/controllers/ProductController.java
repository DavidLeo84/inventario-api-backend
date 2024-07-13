package co.edu.uniquindio.controllers;


import co.edu.uniquindio.dtos.MessageDTO;
import co.edu.uniquindio.dtos.ProductDTO;
import co.edu.uniquindio.entities.Product;
import co.edu.uniquindio.services.ProductServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {

    private final ProductServiceImpl productService;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/create")
    public ResponseEntity<MessageDTO<Product>> agregarProducto(@Valid @RequestBody ProductDTO productDTO) throws Exception {

        Product product = productService.agregarProducto(productDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, product));
    }

    @GetMapping("/all-products")
    public ResponseEntity<MessageDTO<List<ProductDTO>>> mostrarTodos() throws Exception {

        List<ProductDTO> list = productService.listarProductos();
        return ResponseEntity.ok().body(new MessageDTO<>(false, list));
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/delete/{codigo}")
    public ResponseEntity<MessageDTO<String>> eliminarProducto(@PathVariable int codigo) throws Exception {

        productService.eliminarProducto(codigo);
        return ResponseEntity.ok().body(new MessageDTO<>(true, "Producto eliminado satisfactoriamente"));
    }

    @PutMapping("/update/{codigo}")
    public ResponseEntity<MessageDTO<String>> modificarProducto(@PathVariable int codigo,
                                                                @Valid @RequestBody ProductDTO productDTO) throws Exception {
        productService.modificarProducto(codigo, productDTO);
        return ResponseEntity.ok().body(new MessageDTO<>(false, "Producto actualizado satisfactoriamente"));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/get-product/{codigo}")
    public ResponseEntity<MessageDTO<ProductDTO>> mostrarInformacionProducto(@PathVariable int codigo) throws Exception {

        ProductDTO productDTO = productService.mostrarInformacionProducto(codigo);
        return ResponseEntity.ok().body(new MessageDTO<>(false, productDTO));
    }

    @GetMapping("/price/{codigo}/{cantidad}")
    public ResponseEntity<MessageDTO<Double>> calcularPrecio(@PathVariable int codigo,
                                                             @PathVariable int cantidad) throws Exception {

        Double precio = productService.calcularValorTotal(codigo, cantidad);
        return ResponseEntity.ok().body(new MessageDTO<>(false, precio));
    }

    @GetMapping("/total-price")
    public ResponseEntity<MessageDTO<Double>> calcularValorTotal() throws Exception {

        Double total = productService.calcularTotalInventario();
        return ResponseEntity.ok().body(new MessageDTO<>(false, total));
    }

}
