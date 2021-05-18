<%@ page import="com.chequer.axboot.core.utils.RequestUtils" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<!-- {id: "${id}"} 관련 -->
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
        <script type="text/javascript" src="<c:url value='/assets/js/view/hotelproject/clientModal.js'/>"></script>
    </jsp:attribute>
    <jsp:attribute name="header">
        <h1 class="title">
            <i class="cqc-browser"></i>
            투숙객 목록
        </h1>
    </jsp:attribute>
    <jsp:body>
        <ax:split-layout name="ax1" orientation="vertical">
            <ax:split-panel width="*" style="padding-right: 0px;" scroll="scroll">
                <form name="form" class="js-form" onsubmit="return false;">
                    <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 100px;"></div>
                    <div class="H5"></div>
                    <div data-ax-tbl class="ax-form-tbl">
                        <div data-ax-tr>
                            <div data-ax-td style="width:50%">
                                <div data-ax-td-label style="width:120px;">이름</div>
                                <div data-ax-td-wrap>
                                    <input type="text" name="id" data-ax-path="id" class="form-control" readonly="readonly">
                                </div>
                            </div>
                            <div data-ax-td style="width:50%">
                                <div data-ax-td-label style="width:120px;">영문</div>
                                <div data-ax-td-wrap>
                                <input type="text" name="id" data-ax-path="id" class="form-control" readonly="readonly">
                                </div>
                            </div>
                        </div>

                    <div data-ax-tr>
                        <div data-ax-td style="width:50%">
                            <div data-ax-td-label style="width:120px;">연락처</div>
                            <div data-ax-td-wrap>
                                <input type="text" name="companyNm" data-ax-path="companyNm" title="회사명" class="form-control" data-ax-validate="required" readonly="readonly"/>
                            </div>
                        </div>
                        <div data-ax-td style="width:50%">
                            <div data-ax-td-label style="width:120px;">이메일</div>
                            <div data-ax-td-wrap>
                                <input type="text" name="ceo" data-ax-path="ceo" class="form-control" readonly="readonly"/>
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
                                  <input type="text" name="arrDt" data-ax-path="arrDt" class="form-control js-arrDt" placeholder="yyyy/mm/dd"/>
                                  <span class="input-group-addon"><i class="cqc-calendar"></i></span>
                              </div>
                          </div>
                        </div>
                    </div>
  
                    <div data-ax-tr>
                        <div data-ax-td style="width:100%">
                            <div data-ax-td-label style="width:120px;">비고</div>
                            <div data-ax-td-wrap>
                                <textarea name="email" data-ax-path="email" class="form-control inline-block" style="width: 100%; height: 100px;" readonly="readonly">
                                </textarea>
                            </div>
                        </div>
                    </div>
                </div>
                <div data-ax-tbl>
                  <div data-ax-tr>
                    <div data-ax-td style="width:100%">
                      <div data-ax-td-wrap style="display: flex; justify-content: center;">
                        <button type="button" class="btn btn-default" data-grid-view-01-btn="select" style="margin: 5px;">
                          <i class="cqc-circle-with-plus"></i> <ax:lang id="ax.admin.search"/>
                        </button>
                        <button type="button" class="btn btn-default" data-grid-view-01-btn="create" style="margin: 5px;">
                          <i class="cqc-circle-with-plus"></i> <ax:lang id="ax.admin.search"/>
                        </button>
                      </div>                     
                    </div>
                  </div>
                </div>
                </form>
            </ax:split-panel>
        </ax:split-layout>

    </jsp:body>
</ax:layout>