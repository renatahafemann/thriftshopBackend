package thriftshop.thriftshopapi.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import thriftshop.thriftshopapi.Enum.Category;
import thriftshop.thriftshopapi.Model.Product;

public interface ProductRepository extends CrudRepository<Product, Long>{
    List<Product> findByCategory(Category category);
}
