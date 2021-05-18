package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.controllers.dto.HotelCustomerDto;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import edu.axboot.domain.hotelcustomer.HotelCustomer;
import edu.axboot.domain.hotelcustomer.HotelCustomerService;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/hotelCustomer")
public class HotelCustomerController extends BaseController {

    @Inject
    private HotelCustomerService hotelCustomerService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(@RequestParam(value = "guestNm") String guestNm,
                                       @RequestParam(value = "guestTel") String guestTel,
                                       @RequestParam(value = "email") String email) {
        List<HotelCustomer> list = hotelCustomerService.list(guestNm, guestTel, email);

        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody HotelCustomerDto request) {
        hotelCustomerService.save(request);

        return ok();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public HotelCustomerDto findGuest(@PathVariable long id) {
        return hotelCustomerService.findGuest(id);
    }
}