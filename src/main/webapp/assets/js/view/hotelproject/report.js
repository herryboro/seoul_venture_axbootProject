var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        console.log(caller.searchView.getData());
        axboot.ajax({
            type: "GET",
            url: '/api/v1/reservRegister/report',
            data: caller.searchView.getData(),
            callback: function (res) {
                console.log(res);
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
    PAGE_SAVE: function (caller, act, data) {
        var saveList = [].concat(caller.gridView01.getData("modified"));
        saveList = saveList.concat(caller.gridView01.getData("deleted"));

        axboot.ajax({
            type: "PUT",
            url: ["samples", "parent"],
            data: JSON.stringify(saveList),
            callback: function (res) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                axToast.push("저장 되었습니다");
            }
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
        this.target.find('[data-ax5picker]').ax5picker({
            direction: "auto",
            content: {
                type: 'date'
            }
        });

        this.today = $('.js-today').on('click', function() {
            $('.btn').val('');
            $('.js-start').val(moment().format('yyyy-MM-DD'));
            $('.js-end').val(moment().format('yyyy-MM-DD'));
            ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
        });

        this.yesterday = $('.js-yesterday').on('click', function() {
            $('.btn').val('');
            $('.js-start').val(moment().subtract("1","d").format("YYYY-MM-DD"));
            $('.js-end').val(moment().subtract("1","d").format("YYYY-MM-DD"));
            ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
        });
        
        this.threeDaysAgo = $('.js-threeDaysAgo').on('click', function() {
            $('.btn').val('');
            $('.js-start').val(moment().subtract("3","d").format("YYYY-MM-DD"));
            $('.js-end').val(moment().format('yyyy-MM-DD'));
            ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
        });

        this.aWeekAgo = $('.js-aWeekAgo').on('click', function() {
            $('.btn').val('');
            $('.js-start').val(moment().subtract("7","d").format("YYYY-MM-DD"));
            $('.js-end').val(moment().format('yyyy-MM-DD'));
            ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
        });

        this.aMonthAgo = $('.js-aMonthAgo').on('click', function() {
            $('.btn').val('');
            $('.js-start').val(moment().subtract("1","M").format("YYYY-MM-DD"));
            $('.js-end').val(moment().format('yyyy-MM-DD'));
            ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
        });

        this.threeMonthAgo = $('.js-threeMonthAgo').on('click', function() {
            $('.btn').val('');
            $('.js-start').val(moment().subtract("3","M").format("YYYY-MM-DD"));
            $('.js-end').val(moment().format('yyyy-MM-DD'));
            ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
        });

        this.sixMonthAgo = $('.js-sixMonthAgo').on('click', function() {
            $('.btn').val('');
            $('.js-start').val(moment().subtract("6","M").format("YYYY-MM-DD"));
            $('.js-end').val(moment().format('yyyy-MM-DD'));
            ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
        });

        this.aYearAgo = $('.js-aYearAgo').on('click', function() {
            $('.btn').val('');
            $('.js-start').val(moment().subtract("1","y").format("YYYY-MM-DD"));
            $('.js-end').val(moment().format('yyyy-MM-DD'));
            ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
        });
        
        this.start = $('.js-start');
        this.end = $('.js-end');
    },
    getData: function () {
        return {
            start: this.start.val(),
            end: this.end.val()
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
            showRowSelector: false,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                {key: "rsvDt", label: "날짜", width: 150, align: "center", editor: "text"},
                {key: "count", label: "투숙건수", width: 150, align: "center", editor: "text"},
                {key: "salePrc", label: "판매금액", width: 160, align: "center", editor: "text", formatter:"money"},
                {key: "svcPrc", label: "서비스 금액", width: 160, align: "center", editor: "text", formatter:"money"},
                {key: "totalPrc", label: "합계", width: 160, align: "center", editor: "text", formatter:"money"},
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, {selectedClear: true});
                }
            },
            footSum: [
                [
                    {label: "총합", align: "center"},
                    {key: "count", collector: "sum", formatter: "money", align: "right"},
                    {key: "salePrc", collector: "sum", formatter: "money", align: "right"},
                    {key: "svcPrc", collector: "sum", formatter: "money", align: "right"},
                    {key: "totalPrc", collector: "sum", formatter: "money", align: "right"}
                ]
            ]
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