package edu.axboot.domain.herryboroproject;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.HerryboroHotelDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HerryboroHotelService extends BaseService<HerryboroHotel, Long> {
    private HerryboroHotelRepository herryboroHotelRepository;

    @Inject
    public HerryboroHotelService(HerryboroHotelRepository herryboroHotelRepository) {
        super(herryboroHotelRepository);
        this.herryboroHotelRepository = herryboroHotelRepository;
    }

    public Page<HerryboroHotelDto> getList(String roomType, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();

        if(isNotEmpty(roomType)) {
            builder.and(qHerryboroHotel.roomTypCd.eq(roomType));
        }

        List<HerryboroHotel> list = select()
                .from(qHerryboroHotel)
                .where(builder)
                .orderBy(qHerryboroHotel.id.asc())
                .fetch();

        int totalSize = list.size();
        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > totalSize ? totalSize : (start + pageable.getPageSize());
        return new PageImpl(list.subList(start, end), pageable, totalSize);
    }
}