package thriftshop.thriftshopapi.Repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import thriftshop.thriftshopapi.Model.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{
    Optional<Client> findByEmail(String email);
    
}
