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

    @GetMapping("/getCPUByBrand")
    public ResponseEntity<?> getCPU(@RequestParam("brand") String brand) {
        return ResponseEntity.ok(hardwareService.getCPUByBrand(brand));
    }

    @PostMapping("/createCPU")
    public ResponseEntity<String> createCPU(@RequestBody CPU cpu) {
        hardwareService.saveCPU(cpu);
        return ResponseEntity.ok("CPU created successfully");
    }



}
