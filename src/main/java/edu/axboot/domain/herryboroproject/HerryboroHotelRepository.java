package edu.axboot.domain.herryboroproject;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HerryboroHotelRepository extends AXBootJPAQueryDSLRepository<HerryboroHotel, Long> {
}
