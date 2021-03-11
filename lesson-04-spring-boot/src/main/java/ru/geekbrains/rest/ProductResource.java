
package ru.geekbrains.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.controller.BadRequestException;
import ru.geekbrains.controller.NotFoundException;
import ru.geekbrains.persist.Product;
import ru.geekbrains.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
public class ProductResource {

    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/all", produces = "application/json")
    public List<Product> findAll() {return productService.findAll(); }

    @GetMapping(path = "/{id}")
    public Product findById(@PathVariable("id") Long id) {
        Product product = productService.findById(id)
                .orElseThrow(NotFoundException::new);
        product.setPrice(null);
        return product;
    }

//    @GetMapping("filter")
//    public Page<Product> listPage(
//            @RequestParam("titleFilter") Optional<String> titleFilter,
//            @RequestParam("minPrice") Optional<BigDecimal> minPrice,
//            @RequestParam("maxPrice") Optional<BigDecimal> maxPrice,
//            @Parameter(example = "1") @RequestParam("page") Optional<Integer> page,
//            @RequestParam("size") Optional<Integer> size,
//            @RequestParam("sortField") Optional<String> sortField,
//            @RequestParam("sortOrder") Optional<String> sortOrder
//    ) {
//
//        return productService.findWithFilter(
//                titleFilter.orElse(null),
//                minPrice.orElse(null),
//                maxPrice.orElse(null),
//                page.orElse(1) - 1,
//                size.orElse(3),
//                sortField.orElse(null),
//                sortOrder.orElse(null)
//        );
//    }

    @PostMapping(consumes = "application/json")
    public Product create(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new BadRequestException();
        }
        productService.save(product);
        return product;
    }

    @PutMapping(consumes = "application/json")
    public void update(@RequestBody Product product) {
        if (product.getId() == null) {
            throw new BadRequestException();
        }
        productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.deleteById(id);
    }

    @ExceptionHandler
    public ResponseEntity<String> notFoundException(NotFoundException ex) {
        return new ResponseEntity<>("Entity not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> badRequestException(BadRequestException ex) {
        return new ResponseEntity<>("Bad request", HttpStatus.NOT_FOUND);
    }
}
