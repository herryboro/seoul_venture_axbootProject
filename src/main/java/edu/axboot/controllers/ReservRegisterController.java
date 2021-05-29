package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.controllers.dto.*;
import edu.axboot.domain.customerinfo.CustomerInfoService;
import edu.axboot.domain.education.EducationTeachService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.*;
import edu.axboot.domain.reservRegister.ReservRegister;
import edu.axboot.domain.reservRegister.ReservRegisterService;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/reservRegister")
public class ReservRegisterController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(EducationTeachService.class);

    @Inject
    private ReservRegisterService reservRegisterService;

    @Inject
    private CustomerInfoService customerInfoService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse list(RequestParams<ReserveStatusDto> requestParams, Pageable pageable) {
        Page<ReserveStatusDto> reserveList = reservRegisterService.getReserveList(requestParams, pageable);

        return Responses.PageResponse.of(reserveList);
    }

    @RequestMapping(value = "/frontList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse frontList(RequestParams<CheckInRequestDto> requestParams, Pageable pageable) {
        Page<CheckInRequestDto> reserveList = reservRegisterService.getFrontList(requestParams, pageable);

        return Responses.PageResponse.of(reserveList);
    }

    @RequestMapping(value = "/inHouse", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse inHouse(@RequestParam(value = "filter", required = false) String filter,
                                          @RequestParam(value = "rsvNum", required = false) String rsvNum,
                                          @RequestParam(value = "roomTypCd", required = false) String roomTypCd,
                                          @RequestParam(value = "rsvDtStart", required = false) String rsvSttDate,
                                          @RequestParam(value = "rsvDtEnd", required = false) String rsvEndDate,
                                          @RequestParam(value = "arrDtStart", required = false) String arrSttDate,
                                          @RequestParam(value = "arrDtEnd", required = false) String arrEndDate,
                                          @RequestParam(value = "depDtStart", required = false) String depSttDate,
                                          @RequestParam(value = "depDtEnd", required = false) String depEndDate, Pageable pageable) {

        Page<HouseRequestDto> customerListForInHouse = reservRegisterService.getCustomerListForInHouse(filter, rsvNum, roomTypCd, rsvSttDate, rsvEndDate, arrSttDate,
                arrEndDate, depSttDate, depEndDate, pageable);

        return Responses.PageResponse.of(customerListForInHouse);
    }

    @RequestMapping(value = "/checkOut", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.PageResponse checkOut(@RequestParam(value = "filter", required = false) String filter,
                                          @RequestParam(value = "rsvNum", required = false) String rsvNum,
                                          @RequestParam(value = "roomTypCd", required = false) String roomTypCd,
                                          @RequestParam(value = "rsvDtStart", required = false) String rsvSttDate,
                                          @RequestParam(value = "rsvDtEnd", required = false) String rsvEndDate,
                                          @RequestParam(value = "arrDtStart", required = false) String arrSttDate,
                                          @RequestParam(value = "arrDtEnd", required = false) String arrEndDate,
                                          @RequestParam(value = "depDtStart", required = false) String depSttDate,
                                          @RequestParam(value = "depDtEnd", required = false) String depEndDate, Pageable pageable) {

        Page<HouseRequestDto> customerListForInHouse = reservRegisterService.getCustomerListForCheckOutStatus(filter, rsvNum, roomTypCd, rsvSttDate, rsvEndDate, arrSttDate,
                arrEndDate, depSttDate, depEndDate, pageable);

        return Responses.PageResponse.of(customerListForInHouse);
    }

    @RequestMapping(value = "/report", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse report(@RequestParam(value = "start", required = false) String start,
                                         @RequestParam(value = "end", required = false) String end) {

        List<SalesSumResponseDto> reportInformation = reservRegisterService.getReportInformation(start, end);
        return Responses.ListResponse.of(reportInformation);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public ResponseFindGuestByIdDto findGuestById(@PathVariable Long id) {
        return reservRegisterService.findGuestById(id);
    }

    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody ReservRegisterDto reservRegisterDto) {
        ReservRegister reservRegister = reservRegisterService.saveReserveRegisgter(reservRegisterDto);
        customerInfoService.saveMemo(reservRegisterDto.getCustomerInfos(), reservRegister.getRsvNum());

        return ok(reservRegister.getRsvNum());
    }

    @RequestMapping(value = "/updateModalInfo", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse updateInModal(@RequestBody ReservRegisterDto reservRegisterDto) {
        List<ReservRegister> reservRegister = reservRegisterService.updateInModal(reservRegisterDto);
        List<CustomerInfoDto> memoList = reservRegisterDto.getCustomerInfos();

        customerInfoService.saveMemoInReserveStaus(memoList, reservRegisterDto.getRsvNum());

        return ok(reservRegister.get(0).getRsvNum());
    }

}