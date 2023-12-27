package ie.atu.Storage;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StorageDto {
    private Long id;
    private String name;
    private float price;
    private int capacity;
    private String brand;
    private String storageType;
    private String ebaylink;
}
