package edu.axboot.domain.reservRegister;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservRegisterRepository extends AXBootJPAQueryDSLRepository<ReservRegister, Long> {
}
