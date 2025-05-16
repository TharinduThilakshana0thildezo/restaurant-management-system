
package com.example.restaurantmanagementsystem.Controller;

import com.example.restaurantmanagementsystem.model.Vendor;
import com.example.restaurantmanagementsystem.Service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendors")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @GetMapping("/all")
    public List<Vendor> getAllVendors() {
        return vendorService.getAllVendors();
    }

    @GetMapping("/{id}")
    public Vendor getVendorById(@PathVariable Long id) {
        return vendorService.getVendorById(id);
    }

    @PostMapping("/add")
    public Vendor addVendor(@RequestBody Vendor vendor) {
        return vendorService.addVendor(vendor);
    }

    @PutMapping("/update/{id}")
    public Vendor updateVendor(@PathVariable Long id, @RequestBody Vendor updatedVendor) {
        return vendorService.updateVendor(id, updatedVendor);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
    }
}
