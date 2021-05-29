<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/hotelproject/report.js' />"></script>
    </jsp:attribute>
    <jsp:body>
        <div data-page-buttons>
            <div class="button-warp">
                <button type="button" class="btn btn-info" data-page-btn="search"><i class="fas fa-search"></i> 검색</button>
                <button type="button" class="btn btn-info" data-page-btn="excel"><i class="far fa-file-excel"></i> 엑셀다운로드</button>
            </div>
        </div>

        <div role="page-header">
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label="조회날짜" width="100%">
                            <div style="display: flex;">
                                <div>
                                    <button type="button" class="btn btn-info js-today" value="" data-page-btn="today">오늘</button>
                                    <button type="button" class="btn btn-info js-yesterday" value="" data-page-btn="yesterday">어제</button>
                                    <button type="button" class="btn btn-info js-threeDaysAgo" value="" data-page-btn="">3일</button>
                                    <button type="button" class="btn btn-info js-aWeekAgo" value="" data-page-btn="">7일</button>
                                    <button type="button" class="btn btn-info js-aMonthAgo" value="" data-page-btn="">1개월</button>
                                    <button type="button" class="btn btn-info js-threeMonthAgo" value="" data-page-btn="">3개월</button>
                                    <button type="button" class="btn btn-info js-sixMonthAgo" value="" data-page-btn="">6개월</button>
                                    <button type="button" class="btn btn-info js-aYearAgo" value="" data-page-btn="" style="margin-right: 20px;">1년</button>  
                                </div>
                                <div>
                                    <div data-ax5picker="search-period">
                                        <div class="input-group">
                                            <input
                                                type="text" name="start"
                                                class="js-start form-control"
                                                data-ax-path="start"
                                                data-ax5formatter="date"
                                                placeholder="yyyy-mm-dd"/>
                                            <span class="input-group-addon">~</span>
                                            <input
                                                type="text" name="end"
                                                class="js-end form-control"
                                                data-ax-path="end"
                                                data-ax5formatter="date"
                                                placeholder="yyyy-mm-dd"/>
                                            <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </ax:td>
                    </ax:tr>
                </ax:tbl>
            </ax:form>
            <div class="H10"></div>
        </div>
        <ax:split-layout name="ax1" orientation="horizontal">
            <ax:split-panel width="*">
                <!-- 목록 -->
                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i>
                            보고서 </h2>
                    </div>
                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>        
                
            </ax:split-panel>
        </ax:split-layout>

    </jsp:body>
</ax:layout>