var modalParams = modalParams || {};

var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        if (!modalParams.id) return false;
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/reservRegister/' + modalParams.id,
            callback: function (res) {        
                console.log(res);  
                caller.formView01.setData(res);

                for(var i = 0; i < res.customerInfos.length; i++) {
                    var dateString = res.customerInfos[i].memoDtti.substring(0, 10);
                    res.customerInfos[i].memoDtti = dateString;
                }
                caller.gridView01.setData(res.customerInfos);
                $(".res_nm").html(res.rsvNum);
            }
        });
    },
    PAGE_SAVE: function (caller, act, data) {
        var obj = caller.formView01.getData();
        var saveList = [].concat(caller.gridView01.getData());
        saveList = saveList.concat(caller.gridView01.getData('deleted'));
        obj.customerInfos = saveList;

        console.log(obj);

        axboot.ajax({
            type: "POST",
            url: '/api/v1/reservRegister/updateModalInfo',
            data: JSON.stringify(obj),
            callback: function (res) {
                console.log(res);
                axToast.push("저장 되었습니다");
                $(".res_nm").html(res.message);
            }
        });   
    },
    ITEM_ADD: function (caller, act, data) {
        caller.gridView01.addRow();
    },
    ITEM_DEL: function (caller, act, data) {
        caller.gridView01.delRow("selected");
        // caller.formView01.getData().customerInfos[0].delYn = 'Y';
        console.log(caller.gridView01.getData());
    },
    MODAL_IN_MODAL: function(caller, act, data) {
        data = caller.formView01.model.get() || {}; 
        caller.guestModalView.open(data);
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

var CODE = {};

// fnObj 기본 함수 스타트와 리사이즈
fnObj.pageStart = function () {

    this.pageButtonView.initView();
    this.formView01.initView();
    this.gridView01.initView();
    this.guestModalView.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageButtonView = axboot.viewExtend({
    initView: function () {
        axboot.buttonClick(this, 'data-page-btn', {
            save: function () {
                ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
            }
            // create: function () {
            //     ACTIONS.dispatch(ACTIONS.MODAL_OPEN, this.item);
            // },
        });
    },
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
    setGuest: function(data) {
        if (typeof data === 'undefined') data = this.getDefaultData();
        data = $.extend({}, data);

        this.model.setModel(data);
        this.modelFormatter.formatting();
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
        var _this = this; 

        _this.target = $('.js-form');

        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); 

        this.target.find('[data-ax5picker="date"]').ax5picker({
            direction: "auto",
            content: {
                type: 'date'
            }
        });

        this.model.onChange("nightCnt", function (e) {         
            var arrDt = $('.js-arrDt').val(); 
            var nightCnt = $('.js-nightCnt').val();
    
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
            var getArrDt = getFormatDate(arrDt, parseInt(nightCnt));     // 숙박수 숫자 변환 필수.. 에러 1시간 못잡음..
            $('.js-depDt').val(getArrDt);         
        });

        this.model.onChange("depDt", function (e) {    
            var arrDt = $('.js-arrDt').val();
            var depDt = $('.js-depDt').val();    

            if(!arrDt) {
                console.log(arrDt);   
                $('.js-nightCnt').val(0);
            } else {        
                var date1 = new Date(depDt);
                var date2 = new Date(arrDt);

                if(date1 >= date2) {
                    var date3 = (date1 - date2) / 1000 / 60 / 60 / 24;
                $('.js-nightCnt').val(date3).trigger('onChange');
                } else {
                    axDialog.alert('출발일이 도착일보다 빠릅니다.', function () {
                        $('[data-ax-path="arrDt"]').focus();
                    });
                    return false;
                }
            }
        });

        this.model.onChange("arrDt", function (e) {    
            var arrDt = $('.js-arrDt').val();
            var depDt = $('.js-depDt').val();    
            
            if(!depDt) {
                $('.js-nightCnt').val(0).trigger('onChange');
            } else {        
                var date1 = new Date(depDt);
                var date2 = new Date(arrDt);
                
                if(date1 >= date2) {
                    var date3 = (date1 - date2) / 1000 / 60 / 60 / 24;
                $('.js-nightCnt').val(date3).trigger('onChange');
                } else {
                    axDialog.alert('출발일이 도착일보다 빠릅니다.', function () {
                        $('[data-ax-path="arrDt"]').focus();
                    });
                    return false;
                }
            }
        });   
    },
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
                {
                    key: "memoDtti", 
                    label: "작성일", 
                    width: 400, 
                    align: "center", 
                    editor: {
                        type: "date", config: {}
                    }
                },
                {key: "memoCn", label: "메모", width: 900, align: "center", editor: "text"}
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
            },
            "create": function() {
                ACTIONS.dispatch(ACTIONS.MODAL_IN_MODAL);
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

/**
 * modal
 */
fnObj.guestModalView = axboot.viewExtend({
    open: function (data) {
        console.log(data);

        var _this = this;
        console.log(this);
        if (!data) data = {};

        this.modal.open({
            width: 760,
            height: 600,
            header: false,
            iframe: {
                param: 'guestNm=' + (data.guestNm || '') + '&guestTel=' + (data.guestTel || '') + '&modalView=guestModalView',
                url: 'guest-modal.jsp',
            },
        });
    },
    close: function () {
        this.modal.close();
    },
    callback: function (data) {
        console.log(data);
        fnObj.formView01.setGuest(data);
        this.modal.close();
    },
    initView: function () {
        this.modal = new ax5.ui.modal();
    },
});

