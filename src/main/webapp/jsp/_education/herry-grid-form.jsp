<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${PAGE_REMARK}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="base">
    <jsp:attribute name="script">
        <script type="text/javascript" src="<c:url value='/assets/js/view/_education/herry-grid-form.js' />"></script>
        <ax:script-lang key="ax.script" var="LANG" />
        <ax:script-lang key="ax.base" var="COL" />
    </jsp:attribute>
    <jsp:body>

        <ax:page-buttons></ax:page-buttons>


        <div role="page-header">
            <ax:form name="searchView0">
                <ax:tbl clazz="ax-search-tbl" minWidth="500px">
                    <ax:tr>
                        <ax:td label='ax.base.company.name' width="200px">
                            <input type="text" name="companyNm" class="js-companyNm form-control" />
                        </ax:td>
                        <ax:td label='ax.base.company.ceo' width="200px">
                            <input type="text" name="ceo" class="js-ceo form-control" />
                        </ax:td>
                        <ax:td label='ax.base.company.bizno' width="200px">
                            <input type="text" name="bizno" class="js-bizno form-control" />
                        </ax:td>
                        <ax:td label='페이징 사용' width="200px">
                            <input type="checkbox" class="js-isPage" />
                        </ax:td>
                    </ax:tr>
                </ax:tbl>
            </ax:form>
            <div class="H10"></div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-4">
                    <!-- 목록 -->
                    <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                        <div class="left">
                            <h2><i class="cqc-list"></i>
                                프로그램 목록 </h2>
                        </div>
                    </div>
                    <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;">
                    
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="right">
                        <button type="button" class="btn btn-default" data-grid-view-01-btn="add"><i class="cqc-circle-with-plus"></i> 추가</button>
                        <!-- <button type="button" class="btn btn-default" data-grid-view-01-btn="delete"><i class="cqc-circle-with-plus"></i> 삭제</button> -->
                    </div>
                    <form name="form" class="js-form">
                        <div data-ax-tbl class="ax-form-tbl">
                            <div data-ax-tr>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:150px">ID</div>
                                    <div data-ax-td-wrap>                                  
                                        <input type="text" data-ax-path="id" class="js-id form-control" value="" readonly="readonly">                                                            
                                    </div>
                                </div>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:150px">사용여부</div>
                                    <div data-ax-td-wrap>                                  
                                        <input type="text" data-ax-path="useYn" class="form-control" value="">                                                            
                                    </div>
                                </div>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:150px">사업체명</div>
                                    <div data-ax-td-wrap>                                  
                                        <input type="text" data-ax-path="companyNm" class="form-control" value="">                                                            
                                    </div>
                                </div>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:150px">대표자</div>
                                    <div data-ax-td-wrap>                                  
                                        <input type="text" data-ax-path="ceo" class="form-control" value="">                                                            
                                    </div>
                                </div>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:150px">사업자 번호</div>
                                    <div data-ax-td-wrap>                                  
                                        <input type="text" data-ax-path="bizno" class="form-control" value="">                                                            
                                    </div>
                                </div>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:150px">전화번호</div>
                                    <div data-ax-td-wrap>                                  
                                        <input type="text" data-ax-path="tel" class="form-control" value="">                                                            
                                    </div>
                                </div>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:150px">우편번호</div>
                                    <div data-ax-td-wrap>                                  
                                        <input type="text" data-ax-path="zip" class="form-control" value="">                                                            
                                    </div>
                                </div>
                                <div data-ax-td style="width:50%">
                                    <div data-ax-td-label style="width:150px">주소</div>
                                    <div data-ax-td-wrap>                                  
                                        <input type="text" data-ax-path="address" class="form-control" value="">                                                            
                                    </div>
                                </div>
                            </div>       
                            <div data-ax-tr>
                                <div data-ax-td style="width:100%">
                                    <div data-ax-td-label="" class="" style="width:150px">주소 상세</div>            
                                        <div data-ax-td-wrap>                                  
                                            <input type="text" data-ax-path="addressDetail" class="form-control" value="" style="width:100%">                                                                   
                                        </div>                                                                                                                 
                                    </div>
                            </div>
                            <div data-ax-tr>
                                <div data-ax-td style="width:100%">
                                    <div data-ax-td-label="" class="" style="width:150px">주소 상세</div>            
                                        <div data-ax-td-wrap>                                  
                                            <input type="text" data-ax-path="remark" class="form-control" style="width:100%">                                                                           
                                        </div>                                                                                                                 
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