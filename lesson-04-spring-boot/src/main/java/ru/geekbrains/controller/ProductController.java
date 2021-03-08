package ru.geekbrains.controller;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.data.domain.Page;
        import org.springframework.http.HttpStatus;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.servlet.ModelAndView;
        import ru.geekbrains.persist.Product;
        import ru.geekbrains.service.ProductService;

        import javax.validation.Valid;
        import java.math.BigDecimal;
        import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listPage(Model model,
                           @RequestParam("titleFilter") Optional<String> titleFilter,
                           @RequestParam("minPrice") Optional<BigDecimal> minPrice,
                           @RequestParam("maxPrice") Optional<BigDecimal> maxPrice,
                           @RequestParam("page") Optional<Integer> page,
                           @RequestParam("size") Optional<Integer> size,
                           @RequestParam("sortField") Optional<String> sortField,
                           @RequestParam(name = "sortOrder") Optional<String> sortOrder) {
        logger.info("List page requested");

        model.addAttribute("products", productService.findWithFilter(titleFilter, minPrice, maxPrice,
                page, size, sortField, sortOrder));
          return "product";
    }

    @GetMapping("/{id}")
    public String editProduct(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("product", productService.findById(id)
                .orElseThrow(NotFoundException::new));
        return "product_form";
    }

    @PostMapping("/update")
    public String updateProduct(@Valid @ModelAttribute("product") Product product, BindingResult result) {
        logger.info("Update endpoint requested");

        if (result.hasErrors()) {
            return "product_form";
        }
        logger.info("Updating product with id {}", product.getId());
        productService.save(product);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String newProduct(Model model) {
        logger.info("Create new product request");

        model.addAttribute("product");
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        logger.info("Product delete request");

        productService.deleteById(id);
        return "redirect:/product";
    }
    @ExceptionHandler
    public ModelAndView notFoundExceptionHandler(NotFoundException ex) {
        ModelAndView mav = new ModelAndView("not_found");
        mav.setStatus(HttpStatus.NOT_FOUND);
        return mav;
    }
}
