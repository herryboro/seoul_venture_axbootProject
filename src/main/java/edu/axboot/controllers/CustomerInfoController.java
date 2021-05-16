package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.customerinfo.CustomerInfo;
import edu.axboot.domain.customerinfo.CustomerInfoService;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/customerinfo")
public class CustomerInfoController extends BaseController {

    @Inject
    private CustomerInfoService customerInfoService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<CustomerInfo> requestParams) {
        List<CustomerInfo> list = customerInfoService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

//    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
//    public ApiResponse save(@RequestBody List<CustomerInfo> request) {
//        customerInfoService.save(request);
//        return ok();
//    }
}