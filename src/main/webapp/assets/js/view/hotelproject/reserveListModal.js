var modalParams = modalParams || {};

var fnObj = {};
var ACTIONS = axboot.actionExtend(fnObj, {
    PAGE_SEARCH: function (caller, act, data) {
        if (!modalParams.id) return false;
        axboot.ajax({
            type: 'GET',
            url: '/api/v1/reservRegister/' + modalParams.id,
            callback: function (res) {
                for(var i = 0; i < res.customerInfos.length; i++) {
                  console.log(res.customerInfos[i].memoDtti.subString(0, 10));
                }
                
                caller.formView01.setData(res);
                caller.gridView01.setData(res.customerInfos);
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

    // this.pageButtonView.initView();
    this.formView01.initView();
    this.gridView01.initView();

    ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

// fnObj.pageButtonView = axboot.viewExtend({
//     initView: function () {
//         axboot.buttonClick(this, 'data-page-btn', {
//             save: function () {
//                 ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
//             },
//             close: function () {
//                 ACTIONS.dispatch(ACTIONS.PAGE_CLOSE);
//             },
//         });
//     },
// });

/**
 * formView
 */
fnObj.formView01 = axboot.viewExtend(axboot.formView, {
    getDefaultData: function () {
        return { useYn: 'Y' };
    },
    getData: function () {
        var data = this.modelFormatter.getClearData(this.model.get()); // 모델의 값을 포멧팅 전 값으로 치환.
        console.log(data);
        return $.extend({}, data);
    },
    setData: function (data) {
        console.log(data);
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
        var _this = this; 

        _this.target = $('.js-form');

        this.model = new ax5.ui.binder();
        this.model.setModel(this.getDefaultData(), this.target);
        this.modelFormatter = new axboot.modelFormatter(this.model); 
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

