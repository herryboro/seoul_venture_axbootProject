package edu.axboot.domain.hotelcustomer;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.HotelCustomerDto;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public HotelCustomer save(HotelCustomerDto request) {
        HotelCustomer save = hotelCustomerRepository.save(request.toEntity());
        return save;
    }

    public List<HotelCustomer> list(String guestNm, String guestTel, String email) {
        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(guestNm)) {
            builder.and(qHotelCustomer.guestNm.like("%" + guestNm + "%"));
        }
        if (isNotEmpty(guestTel)) {
            builder.and(qHotelCustomer.guestTel.like("%" + guestTel + "%"));
        }
        if (isNotEmpty(email)) {
            builder.and(qHotelCustomer.email.like("%" + email + "%"));
        }

        List<HotelCustomer> hotelCutomerList = select().from(qHotelCustomer)
                .where(builder)
                .orderBy(qHotelCustomer.id.asc())
                .fetch();

        return hotelCutomerList;
    }

    public HotelCustomerDto findGuest(long id) {
        HotelCustomer guest = hotelCustomerRepository.findOne(id);
        HotelCustomerDto hotelCustomerDto = new HotelCustomerDto(guest);
        return hotelCustomerDto;
    }
}