package thriftshop.thriftshopapi.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import thriftshop.thriftshopapi.Enum.Category;
import thriftshop.thriftshopapi.Model.Product;
import thriftshop.thriftshopapi.Repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @GetMapping
    public Iterable<Product> allProducts(){
        return this.productRepository.findAll();
    }

    @GetMapping("/{category}")
    public List<Product> findByCategory(@PathVariable("category") String category){
        String stringCategory = category.toUpperCase();
 
        Category enumCategory = null;
        for (Category productCategory : Category.values()){
            if(productCategory.name().equals(stringCategory)){
                enumCategory = productCategory;
                break;
            }
        }
       
        List<Product> productsList = this.productRepository.findByCategory(enumCategory);       
        return productsList;
        
    }
 
    @GetMapping("/details/{id}")
    public Product findProductById(@PathVariable("id") Long id){
        Optional<Product> productToFindOptional = this.productRepository.findById(id);
        Product product = productToFindOptional.get();
        
        return product;
    }
}
