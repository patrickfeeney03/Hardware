package ie.atu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GPU {
    private String name;
    private float price;
    private String brand;
    private int vram;
    private String ebaylink;
}
