package ru.geekbrains.controller;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.validation.BindingResult;
        import org.springframework.web.bind.annotation.*;
        import ru.geekbrains.service.ProductRepr;
        import ru.geekbrains.service.ProductService;

        import javax.validation.Valid;
        import java.util.List;
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
                           @RequestParam("titleFilter") Optional<String> titleFilter) {
        logger.info("List page requested");

        List<ProductRepr> products;
        if (titleFilter.isPresent() && !titleFilter.get().isBlank()) {
            products = productService.findWithFilter(titleFilter.get());
        } else {
            products = productService.findAll();
        }
        model.addAttribute("products", products);
        return "product";
    }

    @GetMapping("/{id}")
    public String editPage(@PathVariable("id") Long id, Model model) {
        logger.info("Edit page for id {} requested", id);

        model.addAttribute("productRepr", productService.findById(id)
                .orElseThrow(NotFoundException::new));
        return "product_form";
    }

    @PostMapping("/update")
    public String update(@Valid ProductRepr productRepr, BindingResult result, Model model) {
        logger.info("Update endpoint requested");

        if (result.hasErrors()) {
            return "product_form";
        }
        logger.info("Updating product with id {}", productRepr.getId());
        productService.save(productRepr);
        return "redirect:/product";
    }

    @GetMapping("/new")
    public String create(Model model) {
        logger.info("Create new product request");

        model.addAttribute("productRepr", new ProductRepr());
        return "product_form";
    }

    @DeleteMapping("/{id}")
    public String remove(@PathVariable("id") Long id) {
        logger.info("Product delete request");

        productService.delete(id);
        return "redirect:/product";
    }
}
