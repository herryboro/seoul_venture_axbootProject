var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        var obj = $.extend({}, caller.searchView.getData(), data, { pageSize: 5 });
        var url;
    
        if (caller.searchView.pageUse.is(':checked')) {
            url = '/api/v1/education/teachGridForm/page';
        } else {
            url = '/api/v1/education/teachGridForm/';
        }

        axboot.ajax({
            type: 'GET',
            url: url,
            data: obj,
            callback: function (res) {
                caller.gridView01.setData(res);
                caller.formView01.initView();
            },
            options: {
                // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
                onError: function (err) {
                    console.log(err);
                },
            },
        });

        return false;
    },
    PAGE_SAVE: function (caller, act, data) {
        // var item = {};
        // caller.formView01.target.find('input, select').each(function (i, element) {
        //     var $element = $(element);
        //     var name = $element.data('axPath');
        //     var value = $element.val() || '';
        //     console.log(name, value);
        //     item[name] = value;
        // });
        // console.log(item);
        // var item = caller.formView01.getData();
        var obj = $.extend({}, caller.formView01.getData());
        console.log(obj);

        axboot.ajax({
            type: 'POST',
            url: '/api/v1/education/teachGridForm',
            data: JSON.stringify(obj),
            callback: function (res) {
                console.log(res);
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                axToast.push('저장 되었습니다');
            }
        });
    },
    PAGE_DELETE: function(caller, act, data) {
        var id = caller.searchView.getData().id;

        axboot.ajax({
            type: 'DELETE',
            url: '/api/v1/education/teachGridForm/' + id,
            // data: id,
            callback: function (res) {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                axToast.push('삭제 되었습니다');
            }
        });
    },
    ITEM_CLICK: function (caller, act, data) {},
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
        caller.formView01.initView();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow('selected');
    },
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

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {
    this.pageButtonView.initView();
    this.searchView.initView();
    this.gridView01.initView();
    this.formView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageResize = function () {};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            search: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            },
            excel: function () {},
            fn1: function() {
                ACTIONS.dispatch(ACTIONS.PAGE_DELETE);
            }
        });
    },
});

//== view 시작
/**
 * searchView
 */
fnObj.searchView = axboot.viewExtend(axboot.searchView, {
    initView: function () {
        this.target = $(document['searchView0']);
        this.target.attr('onsubmit', 'return ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);');
        this.filter = $('#filter');
        this.companyNm = $('.js-companyNm');
        this.ceo = $('.js-ceo');
        this.bizno = $('.js-bizno');
        this.pageUse = $('.js-isPage');
        this.id = $('.js-id');
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber || 0,
            pageSize: this.pageSize || 0,
            filter: this.filter.val(),
            companyNm: this.companyNm.val(),
            ceo: this.ceo.val(),
            bizno: this.bizno.val(),
            id: this.id.val()
        };
    },
});

/**
 * gridView
 */
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
    initView: function () {
        var _this = this;
        console.log(this);

        this.target = axboot.gridBuilder({
            onPageChange: function (pageNumber) {
                console.log(pageNumber);
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH, { pageNumber: pageNumber });
            },
            showRowSelector: true,
            frozenColumnIndex: 0,
            multipleSelect: false,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                { key: 'companyNm', label: COL('company.name'), width: 120, align: 'left', editor: 'text' },
                { key: 'ceo', label: COL('company.ceo'), width: 80, align: 'center', editor: 'text' },
                { key: 'bizno', label: COL('company.bizno'), width: 100, align: 'center', editor: 'text' }
            ],
            body: {
                onClick: function () {
                    console.log(this);
                    this.self.select(this.dindex);
                    fnObj.formView01.setData(this.item);
                },
            },
        });

        axboot.buttonClick(this, 'data-grid-view-01-btn', {
            add: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_ADD);
            },
            delete: function () {
                ACTIONS.dispatch(ACTIONS.ITEM_DEL);
            },
        });
    },
    getData: function (_type) {
        var list = [];
        var _list = this.target.getList(_type);

        if (_type == 'modified' || _type == 'deleted') {
            list = ax5.util.filter(_list, function () {
                //                delete this.deleted;
                //                return this.key;
                return this.id;
            });
        } else {
            list = _list;
        }
        return list;
    },
    addRow: function () {
        this.target.addRow({ __created__: true, useYn: 'Y' }, 'last');
    },
});

/*
    formView
*/
fnObj.formView01 = axboot.viewExtend(axboot.formView, {
    getData: function () {
        var item = {};
        this.target.find('input, select').each(function (i, elem) {
            //var $elem = $(elem);
            var $elem = $(this);
            var name = $elem.data('axPath');
            var value = $elem.val() || '';
            item[name] = value;
        });
        return item;
    },
    setData: function (item) {
        if(item.__selected__ === true) {
            var value;
            for (var prop in item) {
                value = item[prop] || '';
                $('[data-ax-path="' + prop + '"]').val(value);
            }
        } else {
            for (var prop in item) {
                value = item[prop] || '';
                $('[data-ax-path="' + prop + '"]').val(undefined);
            }
        }
    },
    initView: function () {
        var _this = this; // fnOjb.formview01

        _this.target = $('.js-form');
        _this.model = new ax5.ui.binder();
        _this.model.setModel({}, _this.target);

        // console.log(_this.model.get());
    },
});
