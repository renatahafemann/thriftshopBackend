package thriftshop.thriftshopapi.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import thriftshop.thriftshopapi.Model.Client;
import thriftshop.thriftshopapi.Model.Favorites;


public interface FavoritesRepository extends CrudRepository<Favorites, Long>{
  List<Favorites> findByClient(Client client);
        
    
}

