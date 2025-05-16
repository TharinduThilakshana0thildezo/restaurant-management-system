package com.example.restaurantmanagementsystem.Service;

import com.example.restaurantmanagementsystem.model.Vendor;
import com.example.restaurantmanagementsystem.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id).orElse(null);
    }

    public Vendor addVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public Vendor updateVendor(Long id, Vendor updatedVendor) {
        Optional<Vendor> optionalVendor = vendorRepository.findById(id);
        if (optionalVendor.isPresent()) {
            Vendor existingVendor = optionalVendor.get();
            existingVendor.setName(updatedVendor.getName());
            existingVendor.setContactDetails(updatedVendor.getContactDetails());
            return vendorRepository.save(existingVendor);
        } else {
            return null;
        }
    }

    public void deleteVendor(Long id) {
        vendorRepository.deleteById(id);
    }
}
