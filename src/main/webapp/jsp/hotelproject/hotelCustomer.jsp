<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${pageRemark}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <ax:script-lang key="ax.script" />
        <script type="text/javascript" src="<c:url value='/assets/js/view/hotelproject/hotelCustomer.js'/>"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons></ax:page-buttons>

        <div role="page-header">
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label='이름' width="300px">
                            <input type="text" class="js-guestNm guestform-control" />
                        </ax:td>
                        <ax:td label='전화번호' width="300px">
                            <input type="text" class="js-guestTel form-control" />
                        </ax:td>
                        <ax:td label='이메일' width="300px">
                            <input type="text" class="js-email form-control" />
                        </ax:td>
                    </ax:tr>
                </ax:tbl>
            </ax:form>

            <form name="excelForm" class="js-form-excel" method="post">
            </form>

            <div class="H10"></div>
        </div>

        <ax:split-layout name="ax1" orientation="vertical">
            <ax:split-panel width="400" style="padding-right: 10px;">
                <!-- 목록 -->
                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i>
                            투숙객 목록
                    </div>
                    <div class="right">

                    </div>
                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>
            </ax:split-panel>
            <ax:splitter></ax:splitter>
            <ax:split-panel width="*" style="padding-left: 10px;" scroll="scroll">
                <!-- 폼 -->
                <div class="ax-button-group" role="panel-header">
                    <div class="left">
                        <h2><i class="cqc-news"></i>
                            투숙객 정보
                        </h2>
                    </div>
                </div>
                
                <form name="form" class="js-form">
                    <div class="H5"></div>
                    <div data-ax-tbl class="ax-form-tbl">
                        <div data-ax-tr>
                            <div data-ax-td style="width:50%">
                                <div data-ax-td-label style="width:120px;">이름</div>
                                <div data-ax-td-wrap>
                                    <input type="text" name="guestNm" data-ax-path="guestNm" class="form-control">
                                </div>
                            </div>
                            <div data-ax-td style="width:50%">
                                <div data-ax-td-label style="width:120px;">영문</div>
                                <div data-ax-td-wrap>
                                    <input type="text" name="guestNmEng" data-ax-path="guestNmEng" class="form-control">
                                </div>
                            </div>
                        </div>

                        <div data-ax-tr>
                            <div data-ax-td style="width:50%">
                                <div data-ax-td-label style="width:120px;">연락처</div>
                                <div data-ax-td-wrap>
                                    <input type="text" name="guestTel" data-ax-path="guestTel" class="form-control" data-ax-validate="required"/>
                                </div>
                            </div>
                            <div data-ax-td style="width:50%">
                                <div data-ax-td-label style="width:120px;">이메일</div>
                                <div data-ax-td-wrap>
                                    <input type="text" name="email" data-ax-path="email" class="form-control"/>
                                </div>
                            </div>
                        </div>

                        <div data-ax-tr>
                            <div data-ax-td style="width:50%">
                                <div data-ax-td-label style="width:120px;">언어</div>
                                <div data-ax-td-wrap>
                                    <ax:common-code groupCd="PMS_LANG" clazz="js-pay-method" dataPath="langCd" emptyText="전체" />
                                </div>
                            </div>
                            <div data-ax-td style="width:50%">
                                <div data-ax-td-label style="width:120px;">생년월일</div>
                                <div data-ax-td-wrap>
                                    <div class="input-group" data-ax5picker="date">
                                        <input type="text" name="brth" data-ax-path="brth" class="form-control js-arrDt" placeholder="yyyy/mm/dd"/>
                                        <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                    </div>
                                </div>
                                <div>
                                    <label><input type="radio" data-ax-path="gender" name="gender" value="남">남 </label>
                                    <label><input type="radio" data-ax-path="gender" name="gender" value="여">여 </label>
                                </div>
                            </div>
                        </div>

                        <div data-ax-tr>
                            <div data-ax-td style="width:100%">
                                <div data-ax-td-label style="width:120px;">비고</div>
                                <div data-ax-td-wrap>
                                    <textarea name="rmk" data-ax-path="rmk" class="form-control inline-block" style="width: 100%; height: 100px;">
                                    </textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                
                <div class="ax-button-group" role="panel-header" style="margin-top: 10px;">
                    <div class="left">
                        <h2><i class="cqc-news"></i>
                            투숙 이력
                        </h2>
                    </div>
                </div>
                <div data-ax5grid="grid-view-02" data-fit-height-content="grid-view-02" style="height: 300px;"></div>
            </ax:split-panel>
        </ax:split-layout>

    </jsp:body>
</ax:layout>