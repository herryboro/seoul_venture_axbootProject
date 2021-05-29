var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        axboot.ajax({
            type: "GET",
            url: '/api/v1/reservRegister/inHouse',
            data: caller.searchView.getData(),
            callback: function (res) {
                caller.gridView01.setData(res);
            },
            options: {
                // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
                onError: function (err) {
                    console.log(err);
                }
            }
        });

        return false;
    },
    MODAL_OPEN: function(caller, act, data) {
        axboot.modal.open({
            width: 1300,
            height: 750,
            iframe: {
                param: 'id=' + data.id,
                url: 'customerInfoByInHouse.jsp',
            },
            callback: function (data) {
                console.log(data);
                if (data && data.dirty) {
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                }
                this.close();
            },
        });
    },
    ITEM_CLICK: function (caller, act, data) {

    },
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow("selected");
    },
    dispatch: function (caller, act, data) {
        var result = ACTIONS.exec(caller, act, data);
        if (result != "error") {
            return result;
        } else {
            // 직접코딩
            return false;
        }
    }
});

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.searchView.initView();
    this.gridView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {

};


fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, "data-page-btn", {
            "search": function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            "save": function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
            "excel": function () {

            }
        });
    }
});

//== view 시작
/**
 * searchView
 */
fnObj.searchView = axboot.viewExtend(axboot.searchView, {
    initView: function () {
        this.target = $(document["searchView0"]);
        this.target.attr("onsubmit", "return ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);");
        this.filter = $(".js-filter");
        this.rsvNum = $('.js-rsvNum');
        this.rsvDtStart = $('.js-rsvDtStart');
        this.rsvDtEnd = $('.js-rsvDtEnd');
        this.roomTypCd = $('.js-roomTypCd');
        this.arrDtStart = $('.js-arrDtStart');
        this.arrDtEnd = $('.js-arrDtEnd');
        this.depDtStart = $('.js-depDtStart');
        this.depDtEnd = $('.js-depDtEnd');
        
        this.target.find('[data-ax5picker]').ax5picker({
            direction: "auto",
            content: {
                type: 'date'
            }
        });
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber || 0,
            pageSize: this.pageSize || 5,
            filter: this.filter.val(),
            rsvNum: this.rsvNum.val(),
            rsvDtStart: this.rsvDtStart.val(),
            rsvDtEnd: this.rsvDtEnd.val(),
            roomTypCd: this.roomTypCd.val(),
            arrDtStart: this.arrDtStart.val(),
            arrDtEnd: this.arrDtEnd.val(),
            depDtStart: this.depDtStart.val(),
            depDtEnd: this.depDtEnd.val()
        }
    }
});


/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;

        this.target = axboot.gridBuilder({
            onPageChange: function (pageNumber) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, { pageNumber: pageNumber });
            },
            showRowSelector: false,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                {key: "rsvNum", label: "예약번호", width: 100, align: "center"},
                {key: "rsvDt", label: "예약일", width: 100, align: "center"},
                {key: "guestNm", label: "투숙객", width: 100, align: "center"},
                {key: "roomTypCd", label: "객실타입", width: 100, align: "center"},
                {key: "roomNum", label: "객실번호", width: 100, align: "center"},
                {key: "depDt", label: "도착일", width: 100, align: "center"},
                {key: "arrDt", label: "출발일", width: 100, align: "center"},
                {key: "srcCd", label: "예약경로", width: 100, align: "center"},
                {key: "saleTypCd", label: "판매유형", width: 100, align: "center"},
                {key: "sttusCd", label: "상태", width: 100, align: "center"},
                {key: "salePrc", label: "결제금액", width: 100, align: "center"},
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, {selectedClear: true});
                },
                onDBLClick: function () {
                    ACTIONS.dispatch(ACTIONS.MODAL_OPEN, this.item);
                },
            }
        });

        axboot.buttonClick(this, "data-grid-view-01-btn", {
            "add": function () {
                ACTIONS.dispatch(ACTIONS.ITEM_ADD);
            },
            "delete": function () {
                ACTIONS.dispatch(ACTIONS.ITEM_DEL);
            }
        });
    },
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == "modified" || _type == "deleted") {
            list = ax5.util.filter(_list, function () {
                return this.id;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        this.target.addRow({__created__: true}, "last");
    }
});