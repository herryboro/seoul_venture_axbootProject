var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        var obj = $.extend({}, caller.searchView.getData(), data);
        console.log(obj);

        axboot.ajax({
            type: "GET",
            url: '/api/v1/reservRegister',
            data: obj,
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
    MODAL_OPEN: function (caller, act, data) {
        console.log(data.id);

        axboot.modal.open({
            width: 1300,
            height: 750,
            iframe: {
                param: 'id=' + data.id,
                url: 'reserveListModal.jsp',
            },
            header: { title: '예약조회' },
            callback: function (data) {
                console.log(data);
                if (data && data.dirty) {
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                }
                this.close();
            },
        });
    },
    UPDATE_STTUS: function(caller, act, data) {
        axDialog.confirm({ msg: '선택한 항목의 상태를 변경하시겠습니까?' }, function () {
            var items = caller.gridView01.getData('selected');
            console.log(items);

            if (!items.length) {
                axDialog.alert('변경할 데이터가 없습니다.');
                return false;
            } else {
                items.forEach(function (val) {
                    val.sttusCd = $('.js-sttusCdB').val();
                });                    
            }
            // console.log(items); return false;

            axboot.ajax({
                type: 'PUT',
                url: '/api/v1/reservRegister',
                data: JSON.stringify(items),
                callback: function (res) {
                    axDialog.alert('변경 되었습니다');
                    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
                },
            });
        });
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
            "clear": function () {
                location.reload();
            },
            "excel": function () {

            },
            "saveSttus" : function() {
                ACTIONS.dispatch(ACTIONS.UPDATE_STTUS);
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
        this.target = $(document["form"]);
        this.target.attr("onsubmit", "return ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);");
        this.guestNm = $('.js-guestNm');
        this.rsvNum = $('.js-rsvNum');
        this.rsvDt1 = $('.js-rsvDt1');
        this.rsvDt2 = $('.js-rsvDt2');
        this.roomTypCd = $('.js-roomTypCd');
        this.depDt1 = $('.js-depDt1');
        this.depDt2 = $('.js-depDt2');
        this.arrDt1 = $('.js-arrDt1');
        this.arrDt2 = $('.js-arrDt2');
        
        this.target.find('[data-ax5picker="date"]').ax5picker({
            direction: "auto",
            content: {
                type: 'date'
            }
        });
    },
    getData: function () {
        return {
            pageNumber: this.pageNumber || 0,
            pageSize: this.pageSize || 10,
            guestNm: this.guestNm.val(),
            rsvNum: this.rsvNum.val(),
            rsvDt1: this.rsvDt1.val(),
            rsvDt2: this.rsvDt2.val(),
            roomTypCd: this.roomTypCd.val(),
            depDt1: this.depDt1.val(),
            depDt2: this.depDt2.val(),
            arrDt1: this.arrDt1.val(),
            arrDt2: this.arrDt2.val(),
            sttusCd: $('input[name="sttusCd"]:checked').val()
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
            showRowSelector: true,
            frozenColumnIndex: 0,
            multipleSelect: true,
            target: $('[data-ax5grid="grid-view-01"]'),
            columns: [
                {key: "rsvNum", label: "예약번호", width: 120, align: "center"},
                {key: "rsvDt", label: "예약일", width: 120, align: "center"},
                {key: "guestNm", label: "투숙객", width: 100, align: "center"},
                {key: "roomTypCd", label: "객실타입", width: 100, align: "center"},
                {key: "roomNum", label: "객실번호", width: 100, align: "center"},
                {key: "depDt", label: "도착일", width: 150, align: "center"},
                {key: "arrDt", label: "출발일", width: 150, align: "center"},
                {key: "srcCd", label: "예약경로", width: 100, align: "center"},
                {key: "saleTypCd", label: "판매유형", width: 100, align: "center"},
                {key: "sttusCd", label: "상태", width: 100, align: "center"}
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

        // axboot.buttonClick(this, "data-grid-view-01-btn", {
        //     "saveSttus": function () {
        //         ACTIONS.dispatch(ACTIONS.UPDATE_STTUS);
        //     }
        // });
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
        var _this = this; 

        _this.target = $('.js-form');
        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); // 모델 포메터 시작
    }
});