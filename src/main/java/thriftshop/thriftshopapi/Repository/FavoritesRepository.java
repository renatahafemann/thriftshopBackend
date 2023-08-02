package thriftshop.thriftshopapi.Repository;

import org.springframework.data.repository.CrudRepository;

import thriftshop.thriftshopapi.Model.Favorites;

public interface FavoritesRepository extends CrudRepository<Favorites, Long>{
    
}
