var modalParams = modalParams || {};
console.log(modalParams);

var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        var paramObj = $.extend(caller.searchView.getData(), data);

        axboot.ajax({
            type: 'GET',
            url: '/api/v1/herryboroHotel',
            data: paramObj,
            callback: function (res) {
                caller.gridView01.clear();
                caller.gridView01.setData(res);
            },
        });

        return false;
    },
    PAGE_CLOSE: function (caller, act, data) {
        var modal = fnObj.getModal();
        if (modal) modal.close();
        if (opener) window.close();
    },
    PAGE_CHOICE: function (caller, act, data) {
        console.log(data);
        if (!data) {
            var list = caller.gridView01.getData('selected');
            if (list.length > 0) data = list[0];
        }
        if (data) {
            console.log(data.roomNum);
            var modal = fnObj.getModal();
            if (modal) modal.callback(data);
            if (opener) window.close();
        } else {
            alert(LANG('ax.script.requireselect'));
        }
    },
    // ITEM_CLICK: function (caller, act, data) {
    //     caller.formView01.setData(data || {});
    // },
    dispatch: function (caller, act, data) {
        var result = ACTIONS.exec(caller, act, data);
        if (result != 'error') {
            return result;
        } else {
            // 직접코딩
            return false;
        }
    },
});

fnObj.getModal = function () {
    var modalView;
    if (parent && modalParams.modalView && (modalView = parent[axboot.def.pageFunctionName][modalParams.modalView])) {
        return modalView;
    } else if (opener && modalParams.modalView && (modalView = opener[axboot.def.pageFunctionName][modalParams.modalView])) {
        return modalView;
    } else if (parent && parent.axboot && parent.axboot.modal) {
        return parent.axboot.modal;
    }
};

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.searchView.initView();
    this.gridView01.initView();
    this.formView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};



fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        $('[data-page-btn="excel"]').text('엑셀 다운로드');
        axboot.buttonClick(this, 'data-page-btn', {
            search: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            choice: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_CHOICE);
            },
            close: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_CLOSE);
            },
        });
    },
});

//== view 시작
/**
 * searchView
 */
fnObj.searchView = axboot.viewExtend(axboot.searchView, {
    initView: function () {
        this.target = $('.js-search-form');
        this.target.attr('onsubmit', 'return false;');
        this.target.on('keydown.search', 'input, .form-control', function (e) {
            if (e.keyCode === 13) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            }
        });
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber || 0,
            pageSize: this.pageSize || 5
        };
    },
});

/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        this.target = axboot.gridBuilder({
            onPageChange: function (pageNumber) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, { pageNumber: pageNumber });
            },
            showRowSelector: false,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                { key: 'roomNum', label: '객실번호', width: 100, align: 'center' },
                { key: 'roomTypCd', label: '객실타입', width: 100, align: 'center' },
                { key: 'roomSttusCd', label: '객실상태', width: 100, align: 'center' },
                { key: 'clnSttusCd', label: '청소상태', width: 150, align: 'center' },
                { key: 'svcSttusCd', label: '서비스상태', width: 150, align: 'center' }
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, { selectedClear: true });
                    // ACTIONS.dispatch(ACTIONS.ITEM_CLICK, this.item);
                    ACTIONS.dispatch(ACTIONS.PAGE_CHOICE, this.item);
                },
                onDBLClick: function () {
                    ACTIONS.dispatch(ACTIONS.PAGE_CHOICE, this.item);
                },
            },
        });
    },
});

/**
 * formView
 */
fnObj.formView01 = axboot.viewExtend(axboot.formView, {
    getDefaultData: function () {
        return {};
    },
    getData: function () {
        var data = this.modelFormatter.getClearData(this.model.get()); // 모델의 값을 포멧팅 전 값으로 치환.
        return $.extend({}, data);
    },
    setData: function (data) {
        if (typeof data === 'undefined') data = this.getDefaultData();
        data = $.extend({}, data);

        this.model.setModel(data);
        this.modelFormatter.formatting(); // 입력된 값을 포메팅 된 값으로 변경
    },
    initView: function () {
        var _this = this; // fnObj.formView01

        _this.target = $('.js-form');

        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작

        new ax5.ui.picker().bind({
            target: $('[data-ax5picker="brth"]'),
            direction: 'top',
            content: {
                width: 270,
                margin: 10,
                type: 'date',
                config: {
                    lang: {
                        yearTmpl: '%s년',
                        months: ['01', '02', '03', '04', '05', '06', '07', '08', '09', '10', '11', '12'],
                        dayTmpl: '%s',
                    },
                },
                formatter: {
                    pattern: 'date',
                },
            },
            onStateChanged: function () {
                if (this.state == 'open') {
                    //console.log(this.item);
                    var selectedValue = this.self.getContentValue(this.item['$target']);
                    if (!selectedValue) {
                        this.item.pickerCalendar[0].ax5uiInstance.setSelection([ax5.util.date(new Date(), { add: { d: 1 } })]);
                    }
                }
            },
        });
    },
});
