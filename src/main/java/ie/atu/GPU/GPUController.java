package ie.atu.GPU;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/gpus")
@RestController
public class GPUController {
    private final GPUService gpuService;

    public GPUController(GPUService gpuService) {
        this.gpuService = gpuService;
    }

    @GetMapping
    public ResponseEntity<?> getGPU(@RequestParam(name = "brand", required = false) String brand,
                                    @RequestParam(name = "name", required = false) String name,
                                    @RequestParam(name = "price", required = false) Float price) {
        List<GPU> gpus = gpuService.getGPU(brand, name, price);
        return ResponseEntity.ok(gpus);
    }

    @PostMapping
    public ResponseEntity<?> createGPU(@RequestBody GPU gpu) {
        gpuService.saveGPU(gpu);
        return ResponseEntity.ok("GPU created successfully");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteGPU(@PathVariable("id") Long id) {
        gpuService.deleteGPU(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateGPU(@PathVariable("id") Long id, @RequestBody GPU modifiedGPU) {
        GPU updatedGPU = gpuService.updateGPU(id, modifiedGPU);
        return ResponseEntity.ok(updatedGPU);
    }
}
