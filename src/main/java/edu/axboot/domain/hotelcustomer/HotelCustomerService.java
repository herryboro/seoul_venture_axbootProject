package edu.axboot.domain.hotelcustomer;

import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;

@Service
public class HotelCustomerService extends BaseService<HotelCustomer, Long> {
    private HotelCustomerRepository hotelCustomerRepository;

    @Inject
    public HotelCustomerService(HotelCustomerRepository hotelCustomerRepository) {
        super(hotelCustomerRepository);
        this.hotelCustomerRepository = hotelCustomerRepository;
    }

    public List<HotelCustomer> gets(RequestParams<HotelCustomer> requestParams) {
        return findAll();
    }
}