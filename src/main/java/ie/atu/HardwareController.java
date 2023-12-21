package ie.atu;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/hardware")
@RestController
public class HardwareController {
    // This has the mappings
    private final HardwareService hardwareService;

    public HardwareController(HardwareService hardwareService) {
        this.hardwareService = hardwareService;
    }

    /*
    @GetMapping("/getCPUByBrand")
    public ResponseEntity<?> getCPUByBrand(@RequestParam("brand") String brand) {
        return ResponseEntity.ok(hardwareService.getCPUByBrand(brand));
    }

    @GetMapping("cpu")
    public ResponseEntity<?> getCPUByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(hardwareService.getCPUByName(name));
    }
     */

    @GetMapping("/cpus")
    public ResponseEntity<?> getCPUs(@RequestParam(name = "brand", required = false) String brand,
                                     @RequestParam(name = "name", required = false) String name) {
        if (brand != null) {
            return ResponseEntity.ok(hardwareService.getCPUByBrand(brand));
        } else if (name != null) {
            return ResponseEntity.ok(hardwareService.getCPUByName(name));
        } else {
            return ResponseEntity.ok("No parameters provided");
        }
    }

    // @GetMapping("/getCPUByName") PRICE

    @PostMapping("/createCPU")
    public ResponseEntity<String> createCPU(@RequestBody CPU cpu) {
        hardwareService.saveCPU(cpu);
        return ResponseEntity.ok("CPU created successfully");
    }



}
