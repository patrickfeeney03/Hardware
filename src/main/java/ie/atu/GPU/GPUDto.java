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
    private Float price;
    private String brand;
    private Float vram;
    private String ebaylink;
}
