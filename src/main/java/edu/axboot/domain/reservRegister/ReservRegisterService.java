package edu.axboot.domain.reservRegister;

import edu.axboot.controllers.dto.ReservRegisterDto;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import java.util.List;

@Service
public class ReservRegisterService extends BaseService<ReservRegister, Long> {
    private ReservRegisterRepository reservRegisterRepository;

    @Inject
    public ReservRegisterService(ReservRegisterRepository reservRegisterRepository) {
        super(reservRegisterRepository);
        this.reservRegisterRepository = reservRegisterRepository;
    }

    public List<ReservRegister> gets(ReservRegister requestParams) {
        return findAll();
    }
}