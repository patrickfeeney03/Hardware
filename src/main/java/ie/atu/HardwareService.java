package ie.atu;

import org.hibernate.ResourceClosedException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HardwareService {
    // Used for interfacing with the hardware database ie hardwareRepository.save(CPU)
    // CRUD
    // Create, Read, Update, Delete
        // => save, get, delete, update
    private final CPURepository cpuRepository;
    private final GPURepository gpuRepository;
    // HardwareR hardwareR

    public HardwareService(CPURepository cpuRepository, GPURepository gpuRepository) {
        this.cpuRepository = cpuRepository;
        this.gpuRepository = gpuRepository;
    }

    // Retrieve a CPU by its brand
    public List<CPU> getCPUByBrand(String brand) {
        return cpuRepository.findByBrand(brand);
    }
    public List<CPU> getCPUByName(String name) {
        return cpuRepository.findByName(name);
    }
    public List<CPU> getCPUByPrice(Float price) {
        return cpuRepository.findByPrice(price);
    }

    public List<CPU> findAll() {
        return cpuRepository.findAll();
    }

    public CPU saveCPU(CPU cpu) {
        return cpuRepository.save(cpu);
    }

    // deleteById returns void
    public void deleteCPU(Long id) {
        cpuRepository.deleteById(id);
    }

    public CPU updateCPU(Long id, CPU updatedCpu) {
        CPU temporaryCPU = cpuRepository
                .findById(id)
                .orElseThrow(() -> new ResourceClosedException("CPU not  found for this id: " + id));
        temporaryCPU.setName(updatedCpu.getName());
        temporaryCPU.setPrice(updatedCpu.getPrice());
        temporaryCPU.setSocket(updatedCpu.getSocket());
        temporaryCPU.setFrequency(updatedCpu.getFrequency());
        temporaryCPU.setBrand(updatedCpu.getBrand());
        temporaryCPU.setEbaylink(updatedCpu.getEbaylink());

        return cpuRepository.save(temporaryCPU);
    }


    public List<GPU> getGPUByBrand(String brand) {
        return gpuRepository.findByBrand(brand);
    }

    public List<GPU> getGPUByName(String name) {
        return gpuRepository.findByName(name);
    }

    public List<GPU> getGPUByPrice(Float price) {
        return gpuRepository.findByPrice(price);
    }

    public GPU saveGPU(GPU gpu) {
        return gpuRepository.save(gpu);
    }

    public void deleteGPU(Long id) {
        gpuRepository.deleteById(id);
    }

    public GPU updateGPU(Long id, GPU updatedGpu) {
        GPU temporaryGPU = gpuRepository
                .findById(id)
                .orElseThrow(() -> new ResourceClosedException("GPU not  found for this id: " + id));
        temporaryGPU.setName(updatedGpu.getName());
        temporaryGPU.setPrice(updatedGpu.getPrice());
        temporaryGPU.setBrand(updatedGpu.getBrand());
        temporaryGPU.setEbaylink(updatedGpu.getEbaylink());

        return gpuRepository.save(temporaryGPU);
    }
}
