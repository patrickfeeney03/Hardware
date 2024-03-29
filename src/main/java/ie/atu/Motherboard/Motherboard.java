package ie.atu.Motherboard;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Motherboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Float price;
    private String brand;
    private String socket;
    private String chipset;
    @ElementCollection
    private List<String> compatibleRAMTypes;
    @ElementCollection
    private List<String> compatibleStorageTypes;
    private String ebaylink;
}
