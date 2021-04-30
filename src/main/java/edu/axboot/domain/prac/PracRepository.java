package edu.axboot.domain.prac;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracRepository extends AXBootJPAQueryDSLRepository<Prac, Long> {
}
