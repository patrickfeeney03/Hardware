package ie.atu;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HardwareService {
    // Used for interfacing with the hardware database ie hardwareRepository.save(CPU)
    // CRUD
    // Create, Read, Update, Delete
        // => save, get, delete, update
    private final HardwareRepository hardwareRepository;

    public HardwareService(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    // Retrieve a CPU by its brand
    public List<CPU> getCPUByBrand(String brand) {
        return hardwareRepository.findByBrand(brand);
    }

    public CPU saveCPU(CPU cpu) {
        return hardwareRepository.save(cpu);
    }
}
