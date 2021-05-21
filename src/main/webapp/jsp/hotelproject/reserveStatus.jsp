<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/hotelproject/reserveStatus.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons></ax:page-buttons>


        <div role="page-header">
            <form name="form" class="js-form">
                <div class="H5"></div>
                <div data-ax-tbl class="ax-form-tbl" style="width:1669px">
                    <div data-ax-tr>
                        <div data-ax-td style="width:20%">
                            <div data-ax-td-label style="width:80px;">검색어</div>
                            <div data-ax-td-wrap style="width:90px;">
                                <input type="text" name="guestNm" data-ax-path="guestNm" class="js-guestNm form-control" style="width:120px;">
                            </div>
                        </div>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:80px;">예약번호</div>
                            <div data-ax-td-wrap>
                                <input type="text" name="rsvNum" data-ax-path="rsvNum" class="js-rsvNum form-control" style="width:120px;">
                            </div>
                        </div>
                        <div data-ax-td style="width:50%">
                            <div data-ax-td-label style="width:120px;">예약일</div>
                            <div data-ax-td-wrap>
                                <div class="input-group" data-ax5picker="date" >
                                    <input type="text" name="rsvDt1" data-ax-path="rsvDt1" class="form-control js-rsvDt1" placeholder="yyyy/mm/dd"/>
                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span> 
                                </div>
                            </div>
                            <div data-ax-td-wrap>
                                <div class="input-group" data-ax5picker="date" >
                                    <input type="text" name="rsvDt2" data-ax-path="rsvDt2" class="form-control js-rsvDt2" placeholder="yyyy/mm/dd"/>
                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div data-ax-tr>
                        <div data-ax-td style="width:20%">
                            <div data-ax-td-label style="width:80px;">객실타입</div>
                            <div data-ax-td-wrap style="width:90px;">
                                <ax:common-code groupCd="PMS_ROOM_TYPE" clazz="js-roomTypCd" dataPath="roomTypCd" emptyText="전체" />
                            </div>
                        </div>
                        <div data-ax-td style="width:30%">
                            <div data-ax-td-label style="width:80px;">도착일</div>
                            <div data-ax-td-wrap>
                                <div class="input-group" data-ax5picker="date" >
                                    <input type="text" name="depDt1" data-ax-path="depDt1" class="form-control js-depDt1" placeholder="yyyy/mm/dd"/>
                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span> 
                                </div>
                            </div>
                            <div data-ax-td-wrap>
                                <div class="input-group" data-ax5picker="date" >
                                    <input type="text" name="depDt2" data-ax-path="depDt2" class="form-control js-depDt2" placeholder="yyyy/mm/dd"/>
                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                </div>
                            </div>
                        </div>
                        <div data-ax-td style="width:50%">
                            <div data-ax-td-label style="width:120px;">출발일</div>
                            <div data-ax-td-wrap>
                                <div class="input-group" data-ax5picker="date" >
                                    <input type="text" name="arrDt1" data-ax-path="arrDt1" class="form-control js-arrDt1" placeholder="yyyy/mm/dd"/>
                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span> 
                                </div>
                            </div>
                            <div data-ax-td-wrap>
                                <div class="input-group" data-ax5picker="date" >
                                    <input type="text" name="arrDt2" data-ax-path="arrDt2" class="form-control js-arrDt2" placeholder="yyyy/mm/dd"/>
                                    <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div data-ax-tr>
                        <div data-ax-td>
                            <div data-ax-td-label style="width:157px;">상태</div>
                            <div data-ax-td-wrap style="width:700px;">
                                <div>
                                    <label style="margin-right: 5px;"><input type="radio" class="js-sttusCd" name="sttusCd" data-ax-path="sttusCd" value="" checked>전체</label>
                                    <label style="margin-right: 5px;"><input type="radio" class="js-sttusCd" name="sttusCd" data-ax-path="sttusCd" value="예약"> 예약</label>
                                    <label style="margin-right: 5px;"><input type="radio" class="js-sttusCd" name="sttusCd" data-ax-path="sttusCd" value="예약대기"> 예약대기</label>
                                    <label style="margin-right: 5px;"><input type="radio" class="js-sttusCd" name="sttusCd" data-ax-path="sttusCd" value="예약취소"> 예약취소</label>
                                    <label style="margin-right: 5px;"><input type="radio" class="js-sttusCd" name="sttusCd" data-ax-path="sttusCd" value="예약확정"> 예약확정</label>
                                    <label style="margin-right: 5px;"><input type="radio" class="js-sttusCd" name="sttusCd" data-ax-path="sttusCd" value="노쇼"> 노쇼</label>
                                    <label style="margin-right: 5px;"><input type="radio" class="js-sttusCd" name="sttusCd" data-ax-path="sttusCd" value="체크인"> 체크인</label>
                                    <label style="margin-right: 5px;"><input type="radio" class="js-sttusCd" name="sttusCd" data-ax-path="sttusCd" value="체크아웃"> 체크아웃</label>
                                    <label style="margin-right: 5px;"><input type="radio" class="js-sttusCd" name="sttusCd" data-ax-path="sttusCd" value="체크인취소"> 체크인취소</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class="H10"></div>
        </div>

        <ax:split-layout name="ax1" orientation="horizontal">
            <ax:split-panel width="*" style="">

                <!-- 목록 -->
                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i>
                            예약 목록 </h2>
                    </div>
                    <div class="right">
                        <button type="button" class="btn btn-default" data-grid-view-01-btn=""></i> 상태 변경</button>
                    </div>
                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>

            </ax:split-panel>
        </ax:split-layout>

    </jsp:body>
</ax:layout>