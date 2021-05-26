<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ax" tagdir="/WEB-INF/tags" %>
<%@ page import="com.chequer.axboot.core.utils.RequestUtils" %>
<%
    RequestUtils requestUtils = RequestUtils.of(request);
    request.setAttribute("modalView", requestUtils.getString("modalView"));
%>

<ax:set key="title" value="${pageName}"/>
<ax:set key="page_desc" value="${pageRemark}"/>
<ax:set key="page_auto_height" value="true"/>

<ax:layout name="modal">
    <jsp:attribute name="script">
        <ax:script-lang key="ax.script" />
        <script>
            var modalParams = {modalView: "${modalView}"};
        </script>
        <script type="text/javascript" src="<c:url value='/assets/js/view/hotelproject/roomTyModal.js'/>"></script>
    </jsp:attribute>
    <jsp:attribute name="header">
        <div class="ax-base-title" role="page-title">
            <h1 class="title"><i class="cqc-browser"></i> 객실 목록</h1>
        </div>
    </jsp:attribute>
    <jsp:body>
        <ax:split-layout name="ax1" orientation="vertical">
            <ax:split-panel width="400" style="padding-right: 10px;">
                <!-- 목록 -->
                <div class="ax-button-group" data-fit-height-aside="grid-view-01">
                    <div class="left">
                        <h2><i class="cqc-list"></i>
                            객실 목록
                    </div>
                </div>
                <div data-ax5grid="grid-view-01" data-fit-height-content="grid-view-01" style="height: 300px;"></div>
            </ax:split-panel>
        </ax:split-layout>

    </jsp:body>
</ax:layout>