package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.controllers.dto.*;
import edu.axboot.domain.customerinfo.CustomerInfo;
import edu.axboot.domain.customerinfo.CustomerInfoService;
import edu.axboot.domain.education.EducationTeach;
import edu.axboot.domain.education.EducationTeachService;
import org.apache.commons.lang.StringUtils;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public Responses.PageResponse frontList(RequestParams<ReserveStatusDto> requestParams, Pageable pageable) {
        Page<ReserveStatusDto> reserveList = reservRegisterService.getFrontList(requestParams, pageable);

        return Responses.PageResponse.of(reserveList);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public ResponseFindGuestByIdDto findGuestById(@PathVariable Long id) {
        return reservRegisterService.findGuestById(id);
    }

    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody ReservRegisterDto reservRegisterDto) {
        ReservRegister reservRegister = reservRegisterService.saveReserveRegisgter(reservRegisterDto);
        customerInfoService.saveMemo(reservRegisterDto.getMemoList(), reservRegister.getRsvNum());

        return ok(reservRegister.getRsvNum());
    }

    @RequestMapping(value = "/updateModalInfo", method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse updateInModal(@RequestBody ReservRegisterDto reservRegisterDto) {
        List<ReservRegister> reservRegister = reservRegisterService.updateInModal(reservRegisterDto);
        customerInfoService.updatMemo(reservRegisterDto.getMemoList(), reservRegister.get(0).getRsvNum());

        return ok(reservRegister.get(0).getRsvNum());
    }
}