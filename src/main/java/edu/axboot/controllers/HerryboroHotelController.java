package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import edu.axboot.controllers.dto.HerryboroHotelDto;
import edu.axboot.domain.herryboroproject.HerryboroHotel;
import edu.axboot.domain.herryboroproject.HerryboroHotelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/herryboroHotel")
public class HerryboroHotelController extends BaseController {

    @Inject
    private HerryboroHotelService herryboroHotelService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roomTypCd", value = "객실 타입", dataType = "String", paramType = "query")
    })
    public Responses.PageResponse list(@RequestParam(value = "roomTypCd", required = false) String roomType , Pageable pageable) {
        Page<HerryboroHotelDto> list = herryboroHotelService.getList(roomType, pageable);

        return Responses.PageResponse.of(list);
    }

    @RequestMapping(method = RequestMethod.PUT, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<HerryboroHotel> request) {
        herryboroHotelService.save(request);
        return ok();
    }
}