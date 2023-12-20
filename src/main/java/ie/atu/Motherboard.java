package ie.atu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Motherboard {
    private String name;
    private float price;
    private String socket;
    private String eBayLink;
}
