package edu.axboot.controllers;

import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Controller;
import com.chequer.axboot.core.api.response.ApiResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.axboot.domain.prac.Prac;
import edu.axboot.domain.prac.PracService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping(value = "/api/v1/prac")
public class PracController extends BaseController {

    @Inject
    private PracService pracService;

    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "companyNm", value = "회사명", dataType = "String"),
            @ApiImplicitParam(name = "ceo", value = "회장", dataType = "String"),
            @ApiImplicitParam(name = "bizno", value = "법인번호", dataType = "String"),
            @ApiImplicitParam(name = "useYn", value = "사용유무", dataType = "String")
    })
    public Responses.ListResponse list(RequestParams<Prac> requestParams) {
        List<Prac> list = pracService.gets(requestParams);
        return Responses.ListResponse.of(list);
    }

    @RequestMapping(method = {RequestMethod.PUT}, produces = APPLICATION_JSON)
    public ApiResponse save(@RequestBody List<Prac> request) {
        pracService.save(request);
        return ok();
    }
}