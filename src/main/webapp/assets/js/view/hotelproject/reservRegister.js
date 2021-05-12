var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    // PAGE_SEARCH: function (caller, act, data) {
    //     console.log(caller.formView01.getData());
    //     axboot.ajax({
    //         type: "POST",
    //         url: '/api/v1/reservRegister/list/',
    //         data: JSON.stringify(caller.formView01.getData()),
    //         callback: function (res) {
    //             console.log(res);
    //             caller.formView01.setData(res);
    //         },
    //         options: {
    //             // axboot.ajax 함수에 2번째 인자는 필수가 아닙니다. ajax의 옵션을 전달하고자 할때 사용합니다.
    //             onError: function (err) {
    //                 console.log(err);
    //             }
    //         }
    //     });

    //     return false;
    // },
    PAGE_SAVE: function (caller, act, data) {
        var sendObj = caller.formView01.getData();
        console.log(sendObj);

        axboot.ajax({
            type: "POST",
            url: '/api/v1/reservRegister',
            data: JSON.stringify(sendObj),
            callback: function (res) {
                // ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
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
    this.gridView01.initView();
    this.formView01.initView();
    this.searchView.initView();

    // ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};


fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, "data-page-btn", {
            "search": function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
            },
            "save": function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            }
        });
    }
});


/**
 * searchView
 */
fnObj.searchView = axboot.viewExtend(axboot.searchView, {
    initView: function () {
        this.target = $(document["form"]);
        this.target.attr("onsubmit", "return ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);");
        this.target.find('[data-ax5picker="date"]').ax5picker({
            direction: "auto",
            content: {
                type: 'date'
            }
        });

        axboot.buttonClick(this, "data-searchview-btn", {
            "modal": function () {
                ACTIONS.dispatch(ACTIONS.MODAL_OPEN)
            }
        });
    },
    getData: function () {
        return {
            filter: this.filter.val()
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
            showLineNumber: false,
            showRowSelector: false,
            frozenColumnIndex: 0,
            multipleSelect: false,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                {key: "key", label: "작성일", width: 500, align: "left", editor: "text"},
                {key: "value", label: "메모", width: 1013, align: "left", editor: "text"}
            ],
            body: {
                onClick: function () {
                    this.self.select(this.dindex, {selectedClear: true});
                }
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
                delete this.deleted;
                return this.key;
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

/**
 * formView
 */

fnObj.formView01 = axboot.viewExtend(axboot.formView, {
    getDefaultData: function () {
        return { useYn: 'Y' };
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
    // validate: function () {
    //     var item = this.model.get();

    //     var rs = this.model.validate();
    //     if (rs.error) {
    //         axDialog.alert(LANG('ax.script.form.validate', rs.error[0].jquery.attr('title')), function () {
    //             rs.error[0].jquery.focus();
    //         });
    //         return false;
    //     }

    //     // required 이외 벨리데이션 정의
    //     var pattern;
    //     if (item.email) {
    //         pattern = /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.(?:[A-Za-z0-9]{2,}?)$/i;
    //         if (!pattern.test(item.email)) {
    //             axDialog.alert('이메일 형식을 확인하세요.', function () {
    //                 $('[data-ax-path="email"]').focus();
    //             });
    //             return false;
    //         }
    //     }

    //     if (item.bizno && !(pattern = /^([0-9]{3})\-?([0-9]{2})\-?([0-9]{5})$/).test(item.bizno)) {
    //         axDialog.alert('사업자번호 형식을 확인하세요.'),
    //             function () {
    //                 $('[data-ax-path="bizno"]').focus();
    //             };
    //         return false;
    //     }

    //     return true;
    // },
    
    initView: function () {
        var _this = this; // fnObj.formView01

        _this.target = $('.js-form');

        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작

        this.model.onChange("nightCnt", function (e) {
            
            var getFormatDate = function(checkDate, nightCnt) {
                var date = new Date(checkDate);
                date.setDate(date.getDate() + nightCnt);

                var year = date.getFullYear();
                var month = (1 + date.getMonth());

                month = month >= 10 ? month : '0' + month;
                var day = date.getDate();
                day = day >= 10 ? day : '0' + day;

                return year + '-' + month + '-' + day;
            }

            var nightCnt = $('.js-nightCnt').val();
            var getArrDt = getFormatDate($('.js-arrDt').val(), parseInt(nightCnt)); 

            $('.js-depDt').val(getArrDt);

            // var date = new Date('2021-05-02');
            // date.setDate(date.getDate() + 2);
            // console.log(date.getDate());
            
        });
        
    },
});
