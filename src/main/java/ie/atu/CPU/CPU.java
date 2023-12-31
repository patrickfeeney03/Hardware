package ie.atu.CPU;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class CPU {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Unique Key

    private String name;
    private float price;
    private String socket;
    private String frequency;
    private String brand;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> compatibleRAMTypes;
    private String ebaylink;
}
