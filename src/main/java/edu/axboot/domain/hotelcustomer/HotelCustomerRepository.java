package edu.axboot.domain.hotelcustomer;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelCustomerRepository extends AXBootJPAQueryDSLRepository<HotelCustomer, Long> {
}
