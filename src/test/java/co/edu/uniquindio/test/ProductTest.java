package co.edu.uniquindio.test;

import co.edu.uniquindio.dtos.ProductDTO;
import co.edu.uniquindio.entities.Product;
import co.edu.uniquindio.services.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ProductTest {

    @Autowired
    private ProductServiceImpl productService;

    @Test
    @Sql("classpath:dataset.sql")
    public void testAgregarProducto() throws Exception {

        ProductDTO productDTO = new ProductDTO(

                6,
                "boligrafo lamy",
                75000.00,
                3
        );

        Product producto = productService.agregarProducto(productDTO);

        assertThat(producto).isNotNull();

    }

    @Test
    @Sql("classpath:dataset.sql")
    public void testErrorAgregarProducto() throws Exception {

        ProductDTO productDTO = new ProductDTO(
                6,
                "lapicero negro",
                2000.00,
                20
        );
        assertThrows(Exception.class, () -> productService.agregarProducto(productDTO));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void testMostrarInformacionProducto() throws Exception {

        ProductDTO productDTO = productService.mostrarInformacionProducto(3);
        System.out.println("productDTO = " + productDTO);
        assertThat(productDTO).isNotNull();
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void testEliminarProducto() throws Exception {

        productService.eliminarProducto(3);
        assertThrows(Exception.class, () -> productService.mostrarInformacionProducto(3));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void testErrorEliminarProducto() throws Exception {

        assertThrows(Exception.class, () -> productService.eliminarProducto(100));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void testActualizarProducto() throws Exception {

        ProductDTO buscado = productService.mostrarInformacionProducto(3);

        ProductDTO productDTO = new ProductDTO(
                buscado.codigo(),
                buscado.nombre(),
                buscado.precio(),
                3
        );

        Product product = productService.modificarProducto(3, productDTO);
        ProductDTO modificado = productService.mostrarInformacionProducto(3);
        assertThat(modificado.cantidad()).isEqualTo(3);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void testErrorActualizarProducto() throws Exception {

        assertThrows(Exception.class, () -> productService.modificarProducto(100, new ProductDTO(100, "Tajalapiz", 2000.00, 20)));
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void testListarProductos() throws Exception {

        List<ProductDTO> lista = productService.listarProductos();
        assertThat(lista.size()).isEqualTo(5);
    }

    @Test
    @Sql("classpath:dataset.sql")
    public void testCalcularValorTotal() throws Exception {

        Double valor = productService.calcularValorTotal(1,3);
        assertThat(valor).isEqualTo(6000.00);
    }

    @Test
    @Sql("classpath:dataset.sql")
    @Transactional
    public void testErrorCalcularValorTotal() throws Exception {

        assertThrows(Exception.class, () -> productService.calcularValorTotal(100,3));
    }

    @Test
    @Sql("classpath:dataset.sql")
    @Transactional
    public void testCalcularTotalInventario() throws Exception {

        Double valor = productService.calcularTotalInventario();
        assertThat(valor).isEqualTo(1372000.00);
    }

}
