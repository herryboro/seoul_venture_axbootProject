package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import edu.axboot.controllers.dto.CustomerInfoDto;
import edu.axboot.controllers.dto.ReservRegisterDto;
import edu.axboot.domain.customerinfo.CustomerInfo;
import edu.axboot.domain.customerinfo.CustomerInfoService;
import edu.axboot.domain.education.EducationTeachService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping(method = {RequestMethod.POST}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody ReservRegisterDto reservRegisterDto) {
        ReservRegister reservRegister = reservRegisterService.saveReserveRegisgter(reservRegisterDto);

        customerInfoService.saveMemo(reservRegisterDto.getMemoList(), reservRegister.getRsvNum());

        return ok(reservRegister.getRsvNum());
    }
}