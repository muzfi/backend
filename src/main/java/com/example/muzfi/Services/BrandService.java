package com.example.muzfi.Services;

import com.example.muzfi.Model.Brand;
import java.util.List;

public interface BrandService {
    List<Brand> getAllBrands();
    Brand saveBrand(Brand brand);
}
