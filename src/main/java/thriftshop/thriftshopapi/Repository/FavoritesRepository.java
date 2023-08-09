package thriftshop.thriftshopapi.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import thriftshop.thriftshopapi.Model.Client;
import thriftshop.thriftshopapi.Model.Favorites;
import thriftshop.thriftshopapi.Model.Product;


public interface FavoritesRepository extends CrudRepository<Favorites, Long>{
  List<Favorites> findByClient(Client client);
  Optional<Favorites> findByClientAndFavoritesProducts(Client client, Product product);
        
    
}

