package thriftshop.thriftshopapi.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import thriftshop.thriftshopapi.Enum.Category;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    private Long id;
    private String name;
    private String description;
    private Double price;
    @Enumerated(EnumType.STRING)
    private Category category;
    
} 