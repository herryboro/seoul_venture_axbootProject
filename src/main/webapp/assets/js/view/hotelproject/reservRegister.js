var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SAVE: function (caller, act, data) {
        if(caller.formView01.validate()) {
            var sendObj = $.extend({}, caller.formView01.getData(), {"customerInfos" : caller.gridView01.getData()});
            console.log(sendObj);

            axboot.ajax({
                type: "POST",
                url: '/api/v1/reservRegister',
                data: JSON.stringify(sendObj),
                callback: function (res) {
                    console.log(res);
                    axToast.push("저장 되었습니다");
                    $(".res_nm").html(res.message);
                }
            });
        }       
    },
    MODAL_OPEN: function (caller, act, data) {
        var guestInFo = caller.searchView.getData();

        axboot.modal.open({
            width: 750,
            height: 400,
            iframe: {
                param: {
                    guestNm: guestInFo.guestNm,
                    email: guestInFo.email,
                    guestTel:guestInFo.guestTel
                },
                url: 'searchClientModal.jsp',
            },
            header: {tilte: '협력사 등록'},
            callback: function(data) {
                console.log(data);
                caller.formView01.setData(data);
                this.close();
            }
        });
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
        this.guestNm = $('.js-guestNm');
        this.guestTel = $('.js-guestTel');
        this.email = $('.js-email');

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
            guestNm: this.guestNm.val(),
            guestTel: this.guestTel.val(),
            email: this.email.val()
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
                {
                    key: "memoDtti", 
                    label: "작성일", 
                    width: 500, 
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
            "create": function () {
                ACTIONS.dispatch(ACTIONS.MODAL_OPEN, this.item);
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
        return {};
    },
    getData: function () {
        var data = this.modelFormatter.getClearData(this.model.get()); 
        return $.extend({}, data);
    },
    setData: function (data) {
        if (typeof data === 'undefined') data = this.getDefaultData();
        data = $.extend({}, data);

        this.model.setModel(data);
        this.modelFormatter.formatting(); // 입력된 값을 포메팅 된 값으로 변경
    },
    validate: function () {
        var item = this.model.get();

        var rs = this.model.validate();
        // if (rs.error) {
        //     axDialog.alert(LANG('ax.script.form.validate', rs.error[0].jquery.attr('title')), function () {
        //         rs.error[0].jquery.focus();
        //     });
        //     return false;
        // }

        // required 이외 벨리데이션 정의
        var pattern;
        if (item.email) {
            pattern = /^[A-Za-z0-9]([-_.]?[A-Za-z0-9])*@[A-Za-z0-9]([-_.]?[A-Za-z0-9])*\.(?:[A-Za-z0-9]{2,}?)$/i;
            if (!pattern.test(item.email)) {
                axDialog.alert('이메일 형식을 확인하세요.', function () {
                    $('[data-ax-path="email"]').focus();
                });
                return false;
            }
        }

        return true;
    },
    
    initView: function () {
        var _this = this; // fnObj.formView01

        _this.target = $('.js-form');
        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작

        // 날짜 계산 코드들
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
        
        // 모달 버튼

    },
});
