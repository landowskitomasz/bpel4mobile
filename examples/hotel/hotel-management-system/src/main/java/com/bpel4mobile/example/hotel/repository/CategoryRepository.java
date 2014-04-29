package com.bpel4mobile.example.hotel.repository;

import com.bpel4mobile.example.hotel.entity.Category;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = Category.class)
public interface CategoryRepository {
}
