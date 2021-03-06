<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>
<%@ page import="com.chequer.axboot.core.utils.RequestUtils" %>

<%
    RequestUtils requestUtils = RequestUtils.of(request);
    request.setAttribute("id", requestUtils.getString("id"));
%>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <ax:script-lang key="ax.script" var="LANG" />
        <ax:script-lang key="ax.base" var="COL" />
        <script>
            var modalParams = {id: "${id}"};
        </script>
        <script type="text/javascript" src="<c:url value='/assets/js/view/hotelproject/customerInfoByCheckOutList.js' />"></script>
    </jsp:attribute>
    <jsp:body>
        <div class='js-rsvNum'>예약번호 : <input style="border: 0; background: none; width: 100px;" data-ax-path="rsvNum" name="rsvNum" class="js-rsvNum" readonly="readonly"></div>
        <div role="page-header">
            <form name="form" class="js-form" onsubmit="return false;">
                <div data-ax-tbl class="ax-form-tbl">
                    <div data-ax-tr>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:120px;">도착일</div>
                            <div data-ax-td-wrap>
                                <div class="input-group" data-ax5picker="date">
                                    <input type="text" data-ax-path="arrDt" class="form-control js-arrDt" placeholder="yyyy-mm-dd">
                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                </div>
                                <!-- <input type="date" data-ax-path="arrDt" title="도착일" class="form-control js-arrDt" data-ax-validate="required"/> -->
                            </div>
                        </div>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:120px;">숙박수</div>
                            <div data-ax-td-wrap>
                                <input type="text" data-ax-path="nightCnt" title="숙박일" class="form-control js-nightCnt" data-ax-validate="required"/>
                            </div>
                        </div>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:120px;">출발일</div>
                            <div data-ax-td-wrap>
                                <div class="input-group" data-ax5picker="date">
                                    <input type="text" data-ax-path="depDt" class="form-control js-depDt" placeholder="yyyy-mm-dd">
                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                </div>
                                <!-- <input type="date" data-ax-path="depDt" title="출발일" class="form-control js-depDt" data-ax-validate="required"/> -->
                            </div>
                        </div>
                    </div>
                    <div data-ax-tr>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:120px;">객실타입</div>
                            <div data-ax-td-wrap>
                                <ax:common-code groupCd="ROOM_TYPE" dataPath="roomTypCd" clazz="js-roomTypCd" />
                            </div>
                        </div>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:120px;">성인수</div>
                            <div data-ax-td-wrap>
                                <input type="text" data-ax-path="adultCnt" title="성인수" class="form-control js-adultCnt" data-ax-validate="required"/>
                            </div>
                        </div>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:120px;">아동수</div>
                            <div data-ax-td-wrap>
                                <input type="text" data-ax-path="chldCnt" title="아동수" class="form-control js-chldCnt" data-ax-validate="required"/>
                            </div>
                        </div>
                    </div>

                    <div data-ax-tr>
                        <div data-ax-td style="width:100%">
                            <div data-ax-td-label style="width:120px;">투숙객<br>
                                <button type="button" class="btn btn-default" data-grid-view-01-btn="modalsearch"><i class="cqc-magnifier"></i> 검색</button>
                            </div>
                            <div data-ax-td-wrap>
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">이름</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" data-ax-path="guestNm" class="form-control js-guestNm" />
                                        </div>
                                    </div> 
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">영문</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" data-ax-path="guestNmEng" class="form-control js-guestNmEng" />
                                        </div>
                                    </div>                                                                      
                                </div>
                                
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">연락처</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" data-ax-path="guestTel" class="form-control js-guestTel" data-ax5formatter="phone"/>
                                        </div>
                                    </div> 
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">이메일</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" data-ax-path="email" class="form-control js-email" placeholder="x@x.xx"/>
                                        </div>
                                    </div>                                                                      
                                </div>
                                
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">언어</div>
                                        <div data-ax-td-wrap>
                                            <ax:common-code groupCd="PMS_LANG" dataPath="langCd" clazz="js-langCd" />
                                        </div>
                                    </div> 
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">생년월일</div>
                                        <div data-ax-td-wrap>
                                            <div class="form-inline">
                                                <div class="input-group" data-ax5picker="date">
                                                    <input type="text" data-ax-Path="brth" class="form-control js-brth" placeholder="yyyy-mm-dd">
                                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                                </div>
                                                <div class="form-group">                                            
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
                    
                    <div data-ax-tr>
                        <div data-ax-td style="width:100%">
                            <div data-ax-td-label style="width:120px;">판매/결제</div>
                            <div data-ax-td-wrap>
                                
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">판매유형</div>
                                        <div data-ax-td-wrap>
                                            <ax:common-code groupCd="PMS_SALE_TYPE" dataPath="saleTypCd" clazz="js-saleType" />
                                        </div>
                                    </div> 
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">예약경로</div>
                                        <div data-ax-td-wrap>
                                            <ax:common-code groupCd="PMS_RESERVATION_ROUTE" dataPath="srcCd" clazz="js-reservationRoute" />
                                        </div>
                                    </div>                                                                      
                                </div>
                                
                                <div data-ax-tr>
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">결제방법</div>
                                        <div data-ax-td-wrap>
                                            <ax:common-code groupCd="PMS_PAY_METHOD" dataPath="payCd" clazz="js-payMethod" />
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
                                            <input type="text" data-ax-Path="salePrc" dataPath="salePrc" class="form-control js-salePrc" data-ax5formatter="money"/>
                                        </div>
                                    </div> 
                                    <div data-ax-td style="width:50%">
                                        <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">서비스금액</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" data-ax-Path="svcPrc" dataPath="svcPrc" class="form-control js-svcPrc" data-ax5formatter="money"/>
                                        </div>
                                    </div>                                                                      
                                </div>                                
                            </div>
                        </div>
                    </div>
                    <div data-ax-tr>
                        <div data-ax-td style="width:100%; height: 231px;">
                            <div data-ax-td-label style="width:120px;">투숙메모</div>
                            <div data-ax-td-wrap>
                                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                                    <div class="left">
                                        <h2><i class="cqc-list"></i> 투숙메모 </h2>
                                    </div>
                                    <div class="right">
                                        <button type="button" class="btn btn-default" data-grid-view-01-btn="add"><i class="cqc-circle-with-plus"></i> 추가</button>
                                        <button type="button" class="btn btn-default" data-grid-view-01-btn="delete"><i class="cqc-circle-with-plus"></i> 삭제</button>
                                    </div>
                                </div>
                                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 120px; height: 179px;"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
    </jsp:body>
</ax:layout>