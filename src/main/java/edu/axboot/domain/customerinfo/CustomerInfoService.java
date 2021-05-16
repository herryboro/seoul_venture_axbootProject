package edu.axboot.domain.customerinfo;

import com.querydsl.core.BooleanBuilder;
import edu.axboot.controllers.dto.CustomerInfoDto;
import org.springframework.stereotype.Service;
import edu.axboot.domain.BaseService;
import javax.inject.Inject;
import com.chequer.axboot.core.parameter.RequestParams;
import java.util.List;

@Service
public class CustomerInfoService extends BaseService<CustomerInfo, Long> {
    private CustomerInfoRepository customerInfoRepository;

    @Inject
    public CustomerInfoService(CustomerInfoRepository customerInfoRepository) {
        super(customerInfoRepository);
        this.customerInfoRepository = customerInfoRepository;
    }

    public List<CustomerInfo> gets(RequestParams<CustomerInfo> requestParams) {
        return findAll();
    }
}