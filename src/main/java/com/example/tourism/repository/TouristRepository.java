
package com.example.tourism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.tourism.entity.Tourist;

public interface TouristRepository
        extends JpaRepository<Tourist, Integer> {

}
