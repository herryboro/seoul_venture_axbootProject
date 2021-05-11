<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/hotelproject/reservRegister.js' />"></script>
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons></ax:page-buttons>

        <ax:split-layout name="ax1" orientation="horizontal">
            <ax:split-panel width="*" style="">
                <ax:split-panel width="*" style="padding-left: 10px;">
                    <div data-fit-height-aside="form-view-01">
                        
                        <form name="form" class="js-form" onsubmit="return false;">
                            <div data-ax-tbl class="ax-form-tbl">
        
                                <div data-ax-tr>
                                    <div data-ax-td style="width:30%">
                                        <div data-ax-td-label style="width:120px;" rowspan="3">도착일</div>
                                        <div data-ax-td-wrap>
                                            <div class="input-group" data-ax5picker="date">
                                                <input type="text" class="form-control" placeholder="yyyy/mm/dd"/>
                                                <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                    <div data-ax-td style="width:30%">
                                        <div data-ax-td-label style="width:120px;">숙박일 수</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" name="companyNm" data-ax-path="companyNm" title="회사명" class="form-control" data-ax-validate="required" />
                                        </div>
                                    </div>
                                    <div data-ax-td style="width:40%">
                                        <div data-ax-td-label style="width:120px;"">출발일</div>
                                        <div data-ax-td-wrap>
                                            <div class="input-group" data-ax5picker="date">
                                                <input type="text" class="form-control" placeholder="yyyy/mm/dd"/>
                                                <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
        
                                <div data-ax-tr>
                                    <div data-ax-td style="width:30%">
                                        <div data-ax-td-label style="width:120px;">객실 타입</div>
                                        <div data-ax-td-wrap>
                                            <select name="useYn" data-ax-path="useYn" class="form-control">
                                                <option value="SB">SB</option>
                                                <option value="DB">DB</option>
                                                <option value="DT">DT</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div data-ax-td style="width:30%">
                                        <div data-ax-td-label style="width:120px;">성인수</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" name="companyNm" data-ax-path="companyNm" title="회사명" class="form-control" data-ax-validate="required" />
                                        </div>
                                    </div>
                                    <div data-ax-td style="width:40%">
                                        <div data-ax-td-label style="width:120px;">아동수</div>
                                        <div data-ax-td-wrap>
                                            <input type="text" name="companyNm" data-ax-path="companyNm" title="회사명" class="form-control" data-ax-validate="required" />
                                        </div>
                                    </div>
                                </div>

                                <div data-ax-tr>
                                    <div data-ax-td style="width:100%">
                                        <div data-ax-td-label style="width:120px;">투숙객</div>
                                        <div data-ax-td-wrap>
                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">이름</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" class="form-control" />
                                                    </div>
                                                </div> 
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">영문</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" class="form-control" />
                                                    </div>
                                                </div>         
                                            </div>

                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">연락처</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" class="form-control" />
                                                    </div>
                                                </div> 
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">이메일</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" class="form-control" />
                                                    </div>
                                                </div>         
                                            </div>

                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">언어</div>
                                                    <div data-ax-td-wrap>
                                                        <select name="useYn" data-ax-path="useYn" class="form-control">
                                                            <option value="korean">한국어</option>
                                                            <option value="english">영어</option>
                                                        </select>
                                                    </div>
                                                </div> 
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">생년월일</div>
                                                    <div data-ax-td-wrap>
                                                        <div class="input-group" data-ax5picker="date">
                                                            <input type="text" class="form-control" placeholder="yyyy/mm/dd"/>
                                                            <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                                                            <div class="form-control">
                                                                <input type="checkbox">남
                                                                <input type="checkbox">여
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
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">판매유형</div>
                                                    <div data-ax-td-wrap>
                                                        <select name="useYn" data-ax-path="useYn" class="form-control">
                                                            <option value="korean">부킹 예약</option>
                                                            <option value="english">워크인</option>
                                                            <option value="english">대실</option>
                                                            <option value="english">컴프</option>
                                                            <option value="english">하우스 유즈</option>
                                                        </select>
                                                    </div>
                                                </div> 
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">예약경로</div>
                                                    <div data-ax-td-wrap>
                                                        <select name="useYn" data-ax-path="useYn" class="form-control">
                                                            <option value="korean">홈페이지</option>
                                                            <option value="english">메일</option>
                                                            <option value="english">전화</option>
                                                            <option value="english">팩스</option>
                                                            <option value="english">방문</option>
                                                            <option value="english">온라인</option>
                                                            <option value="english">기타</option>
                                                        </select>
                                                    </div>
                                                </div>         
                                            </div>

                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">결제방법</div>
                                                    <div data-ax-td-wrap>
                                                        <select name="useYn" data-ax-path="useYn" class="form-control">
                                                            <option value="korean">현금</option>
                                                            <option value="english">신용카드</option>
                                                            <option value="english">계좌이체</option>
                                                            <option value="english">대외후불</option>                            
                                                        </select>
                                                    </div>
                                                </div> 
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">선수금 여부</div>
                                                    <div data-ax-td-wrap>                                        
                                                        <input type="checkbox">                                                            
                                                    </div>
                                                </div>         
                                            </div>

                                            <div data-ax-tr>
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">결제금액</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" class="form-control" />
                                                    </div>
                                                </div> 
                                                <div data-ax-td style="width:50%">
                                                    <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">서비스 금액</div>
                                                    <div data-ax-td-wrap>
                                                        <input type="text" class="form-control" />
                                                    </div>
                                                </div>         
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                
                                <div data-ax-tr>
                                    <div data-ax-td style="width:100%">
                                        <div data-ax-td-label style="width:120px;">투숙 메모</div>
                                        <div data-ax-td>
                                            <div data-ax-td style="width:50%">
                                                <div data-ax-td-label style="width:120px; background-color: #fff; background-image: none;">투숙 메모</div>
                                            </div> 
                                            <div data-ax-tr>
                                                <div data-ax-td style="width:20%">                           
                                                    <div data-ax-td-wrap>작성일</div>
                                                </div> 
                                                <div data-ax-td style="width:80%">                           
                                                    <div data-ax-td-wrap>메모</div>
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