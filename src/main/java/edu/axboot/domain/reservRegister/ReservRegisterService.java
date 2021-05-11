package edu.axboot.domain.reservRegister;

import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;

@Service
public class ReservRegisterService extends BaseService<ReservRegister, Long> {
    private ReservRegisterRepository reservRegisterRepository;

    @Inject
    public ReservRegisterService(ReservRegisterRepository reservRegisterRepository) {
        super(reservRegisterRepository);
        this.reservRegisterRepository = reservRegisterRepository;
    }

    public List<ReservRegister> gets(RequestParams<ReservRegister> requestParams) {
        return findAll();
    }
}