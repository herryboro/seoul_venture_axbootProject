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
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
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

    @Transactional
    public void updatMemo(List<CustomerInfoDto> memoList, String rsvNum) {
        for (int i = 0; i < memoList.size(); i++) {
            // 수정
            if(memoList.get(i).getDelYn() != null) {
                if (memoList.get(i).getDelYn().equals("N")) {
                    update(qCustomerInfo)
                            .set(qCustomerInfo.memoCn, memoList.get(i).getMemoCn())
                            .set(qCustomerInfo.memoDtti, memoList.get(i).getMemoDtti())
                            .where(qCustomerInfo.sno.eq(memoList.get(i).getSno()))
                            .execute();
                } else if(memoList.get(i).getDelYn().equals("Y")) {
                    delete(qCustomerInfo).where(qCustomerInfo.id.eq(memoList.get(i).getId())).execute();
                }
            } else {
                for(CustomerInfoDto memo : memoList) {
                    if(memo.getId() == null) {
                        CustomerInfo customerInfo = memo.toEntityOfCustomerInfo();
                        String memoMan = "메모자";
                        customerInfo.setMemoMan(memoMan);
                        customerInfo.setRsvNum(rsvNum);

                        int maxSno = getMaxSno();

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
        }
    }

    public List<CustomerInfo> gets(RequestParams<CustomerInfo> requestParams) {
        return findAll();
    }


}