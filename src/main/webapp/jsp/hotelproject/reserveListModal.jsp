<%@ page import="com.chequer.axboot.core.utils.RequestUtils" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<%
    RequestUtils requestUtils = RequestUtils.of(request);
    request.setAttribute("id", requestUtils.getString("id"));
%>
<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>
<ax:set key="axbody_class" value="baseStyle"/>

<ax:layout name="modal">
    <jsp:attribute name="script">
        <ax:script-lang key="ax.script" var="LANG" />
        <script>
            var modalParams = {id: "${id}"};
        </script>
        <script type="text/javascript" src="<c:url value='/assets/js/view/hotelproject/reserveListModal.js'/>"></script>
    </jsp:attribute>
    <jsp:body>
        <ax:split-layout name="ax1" orientation="horizontal">
            <ax:split-panel width="*" style="">
                <ax:split-panel width="*" style="padding-left: 10px;">
                    
                        <form name="form" class="js-form" onsubmit="return false;">
                            <div data-ax-tr>
                                <div data-ax-td style="display: flex;"> 
                                    <div data-ax-td>
                                        <div style="display: inline;"> 예약 번호: </div>
                                        <div style="display: inline;" class="res_nm"></div>
                                    </div>
                                    <div data-ax-td style="margin-left: 10px; margin-right: 10px;">
                                        <ax:common-code groupCd="PMS_STAY_STATUS" dataPath="sttusCd" clazz="js-room-type" emptyText="전체" />
                                    </div>
                                    <button type="button" class="js-save" data-page-btn="save" style="margin-right: 3px;"><i class="cqc-save"></i> 저장</button>
                                    <button type="button" class="js-closed" data-page-btn="reload"><i class="cqc-save"></i> 닫기</button>
                                </div>
                            </div>
                            <div data-ax-tbl class="ax-form-tbl">
                                <div data-ax-tr>
                                    <div data-ax-td style="width:30%" style="display: flex;">
                                        <div data-ax-td-label style="width:120px;" rowspan="3">
                                            <div style="display: inline; color: red;">* </div>도착일
                                        </div>
                                        <div data-ax-td-wrap>
                                            <div class="input-group" data-ax5picker="date">
                                                <input type="text" name="arrDt" data-ax-path="arrDt" class="form-control js-arrDt" placeholder="yyyy/mm/dd"/>
                                                <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div data-ax-td style="width:30%">
                                        <div data-ax-td-label style="width:120px;">
                                            <div style="display: inline; color: red;">* </div>숙박일 수
                                        </div>
                                        <div data-ax-td-wrap>
                                            <input type="text" class="form-control js-nightCnt" name="nightCnt" data-ax-path="nightCnt" data-ax-validate="required" />
                                        </div>
                                    </div>
                                    <div data-ax-td style="width:40%">
                                        <div data-ax-td-label style="width:120px;"">
                                            <div style="display: inline; color: red;">* </div>출발일
                                        </div>
                                        <div data-ax-td-wrap>
                                            <div class="input-group" data-ax5picker="date">
                                                <input type="text" class="form-control js-depDt" name="depDt" data-ax-path="depDt" placeholder="yyyy/mm/dd"/>
                                                <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
        
                                <div data-ax-tr>
                                    <div data-ax-td style="width:30%">
                                        <div data-ax-td-label style="width:120px;">
                                            <div style="display: inline; color: red;">* </div>객실 타입
                                        </div>
                                        <div data-ax-td-wrap>
                                            <ax:common-code groupCd="PMS_ROOM_TYPE" dataPath="roomTypCd" clazz="js-room-type" emptyText="전체" />
                                        </div>
                                    </div>
                                    <div data-ax-td style="width:30%">
                                        <div data-ax-td-label style="width:120px;">
                                            <div style="display: inline; color: red;">* </div>성인수
                                        </div>
                                        <div data-ax-td-wrap>
                                            <input type="text" name="adultCnt" data-ax-path="adultCnt" class="form-control" data-ax-validate="required" />
                                        </div>
                                    </div>
                                    <div data-ax-td style="width:40%">
                                        <div data-ax-td-label style="width:120px;">
                                            <div style="display: inline; color: red;">* </div>아동수
                                        </div>
                                        <div data-ax-td-wrap>
                                            <input type="text" name="chldCnt" data-ax-path="chldCnt" class="form-control" data-ax-validate="required" />
                                        </div>
                                    </div>
                                </div>

                                <div data-ax-tr>
                                    <div data-ax-td style="width:100%">
                                        <div data-ax-td-label style="width:120px;">
                                            <div data-ax-td style="width:100%">투숙객</div> 
                                            <button type="button" class="btn btn-default" data-grid-view-01-btn="create">
                                                <i class="cqc-circle-with-plus"></i> <ax:lang id="ax.admin.search"/>
                                            </button>
                                        </div>
                                        
                                        <div data-ax-td-wrap>
                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">이름</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" name="guestNm" data-ax-path="guestNm" class="js-guestNm form-control" />
                                                    </div>
                                                </div> 
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">영문</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" name="guestNmEng" data-ax-path="guestNmEng" class="form-control" />
                                                    </div>
                                                </div>         
                                            </div>

                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">연락처</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" name="guestTel" data-ax-path="guestTel" class="js-guestTel form-control" />
                                                    </div>
                                                </div> 
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">이메일</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" name="email" data-ax-path="email" class="js-email form-control" data-ax-validate="required"/>
                                                    </div>
                                                </div>         
                                            </div>

                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">언어</div>
                                                    <div data-ax-td-wrap>
                                                        <ax:common-code groupCd="PMS_LANG" clazz="js-pay-method" dataPath="langCd" emptyText="전체" />
                                                    </div>
                                                </div> 
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">생년월일</div>
                                                    <div data-ax-td-wrap>
                                                        <div data-ax-td-wrap>                                                           
                                                            <div data-ax-td>
                                                                <div class="input-group" data-ax5picker="date">
                                                                    <input type="text" name="brth" data-ax-path="brth" class="form-control" placeholder="yyyy/mm/dd"/>
                                                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                                                </div>
                                                                <div data-ax-td style="width:50%">
                                                                    <input type="radio" id="male" name="gender" data-ax-path="gender" value="남"> 남
                                                                    <input type="radio" id="female" name="gender" data-ax-path="gender" value="여"> 여
                                                                </div>
                                                            </div>                                                                                                                                                  
                                                        </div>                                                       
                                                    </div>
                                                </div>         
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div data-ax-tr>
                                    <div data-ax-td style="width:100%">
                                        <div data-ax-td-label style="width:120px;">판매 / 결제</div>
                                        <div data-ax-td-wrap>
                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">
                                                        <div style="display: inline; color: red;">* </div>판매유형
                                                    </div>
                                                    <div data-ax-td-wrap>
                                                        <ax:common-code groupCd="PMS_SALE_TYPE" dataPath="saleTypCd" clazz="js-pay-method" emptyText="전체" />
                                                    </div>
                                                </div> 
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">
                                                        <div style="display: inline; color: red;">* </div>예약경로
                                                    </div>   
                                                    <div data-ax-td-wrap>
                                                        <ax:common-code groupCd="PMS_RESERVATION_ROUTE" dataPath="srcCd" clazz="js-pay-method" emptyText="전체" />
                                                    </div>
                                                </div>         
                                            </div>

                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">결제방법</div>
                                                    <div data-ax-td-wrap>
                                                        <ax:common-code groupCd="PMS_PAY_METHOD" dataPath="payCd" clazz="js-pay-method" emptyText="전체" />
                                                    </div>
                                                </div> 
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">선수금 여부</div>
                                                    <div data-ax-td-wrap>                                        
                                                        <input type="checkbox" name="advnYn" data-ax-path="advnYn" value="Y">                                                            
                                                    </div>
                                                </div>         
                                            </div>

                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">결제금액</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" class="form-control" name="salePrc" data-ax-path="salePrc" data-ax5formatter="money"/>
                                                    </div>
                                                </div> 
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">서비스 금액</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" class="form-control" name="svcPrc" data-ax-path="svcPrc" data-ax5formatter="money"/>
                                                    </div>
                                                </div>         
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div data-ax-tr style="height: 1px;">
                                    <div data-ax-td style="width:100%">
                                        <div data-ax-td-label style="width:120px;">투숙 메모</div>
                                        <div data-ax-td-wrap>
                                            <div data-ax-tr>
                                                <div data-ax-td-wrap>
                                                    <div data-ax-td style="width:30%; margin-right: 300px;">
                                                        <div data-ax-td-label style="width:120px;">투숙 메모</div>                        
                                                    </div>    
                                                    <div data-ax-td style="margin-left: 7px;">
                                                        <button type="button" class="btn btn-default" data-grid-view-01-btn="add">
                                                            <i class="cqc-circle-with-plus"></i> 
                                                            <ax:lang id="ax.admin.add"/>
                                                        </button>
                                                        <button type="button" class="btn btn-default" data-grid-view-01-btn="delete">
                                                            <i class="cqc-circle-with-plus"></i> 
                                                            <ax:lang id="ax.admin.delete"/>
                                                        </button>             
                                                    </div>                                                                                            
                                                </div>                                                        
                                            </div>
                                            <div data-ax-tr>
                                                <div data-ax-td style="width:100%">
                                                    <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>                 
                                                </div>       
                                            </div>
                                        </div>                       
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </ax:split-panel>
            </ax:split-panel>
        </ax:split-layout>

    </jsp:body>
</ax:layout>