package ie.atu.Motherboard;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MotherboardDto {
    private Long id;
    private String name;
    private float price;
    private String brand;
    private String socket;
    private String chipset;
    private List<String> compatibleRAMTypes;
    private List<String> compatibleStorageTypes;
    private String ebaylink;
}
