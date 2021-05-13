package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.hotelcustomer.HotelCustomer;
import edu.axboot.domain.hotelcustomer.HotelCustomerService;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/hotelcustomer")
public class HotelCustomerController extends BaseController {

    @Inject
    private HotelCustomerService hotelCustomerService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<HotelCustomer> requestParams) {
        List<HotelCustomer> list = hotelCustomerService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<HotelCustomer> request) {
        hotelCustomerService.save(request);
        return ok();
    }
}