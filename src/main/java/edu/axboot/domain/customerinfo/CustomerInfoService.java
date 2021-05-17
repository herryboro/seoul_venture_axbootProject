package edu.axboot.domain.customerinfo;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.CustomerInfoDto;
import edu.axboot.controllers.dto.ReservRegisterDto;
import edu.axboot.domain.reservRegister.ReservRegister;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerInfoService extends BaseService<CustomerInfo, Long> {
    private CustomerInfoRepository customerInfoRepository;

    @Inject
    public CustomerInfoService(CustomerInfoRepository customerInfoRepository) {
        super(customerInfoRepository);
        this.customerInfoRepository = customerInfoRepository;
    }

    // 일렬번호 최대값 조회
    public int getMaxSno() {
        List<CustomerInfo> reservRegister = select().from(qCustomerInfo).fetch();

        if(reservRegister.size() != 0) {
            int[] arr = new int[reservRegister.size()];

            for(int i = 0; i < reservRegister.size(); i++) {
                int val = reservRegister.get(i).getSno().intValue();
                arr[i] += val;
            }
            int asInt = Arrays.stream(arr).max().getAsInt();

            return asInt;
        }
        return 0;
    }

    public void saveMemo(List<CustomerInfoDto> reservRegisterDto, String rsvNm) {
        int maxSno = getMaxSno();

        if(reservRegisterDto.size() != 0) {
            for (int i = 0; i < reservRegisterDto.size(); i++) {
                CustomerInfo customerInfo = reservRegisterDto.get(i).toEntityOfCustomerInfo();
                customerInfo.setRsvNum(rsvNm);

                String memoMan = "메모자";
                customerInfo.setMemoMan(memoMan);

                if(maxSno == 0) {
                    customerInfo.setSno(100);
                    customerInfoRepository.save(customerInfo);
                } else {
                    int plusSno = ++maxSno;
                    customerInfo.setSno(plusSno);
                    customerInfoRepository.save(customerInfo);
                }
            }
        }
    }

    public List<CustomerInfo> gets(RequestParams<CustomerInfo> requestParams) {
        return findAll();
    }
}