package ie.atu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Storage {
    private String name;
    private float price;
    private int capacity;
    private String eBayLink;
}
