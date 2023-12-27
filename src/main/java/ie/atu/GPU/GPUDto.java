package ie.atu.GPU;

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
public class GPUDto {
    private Long id;
    private String name;
    private float price;
    private String brand;
    private float vram;
    private String ebaylink;
}
