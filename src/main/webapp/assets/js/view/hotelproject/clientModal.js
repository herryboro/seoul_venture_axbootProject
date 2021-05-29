var modalParams = modalParams || {};
var fnObj = {};

var ACTIONS = axboot.actionExtend(fnObj, {
  PAGE_SEARCH: function(caller, act, data) {
    axboot.ajax({
      type: 'GET',
      url: '/api/v1/hotelCustomer/',
      data: modalParams,
      callback: function(res) {
        caller.gridView01.setData(res);
      }
    });
  },
  PAGE_SAVE: function(caller, act, data) {
    var formViewObj = caller.formView01.getData();

    axboot.ajax({
      type: 'POST',
      url: '/api/v1/subcontractor/',
      data: JSON.stringify(formViewObj),
      callback: function(res) {
        axDialog.alert('저장 되었습니다', function() {
          if(parent && parent.axboot && parent.axboot.modal) {
            parent.axboot.modal.callback({ dirty: true });
          }
        });
      }
    });
  },
  ITEM_CLICK: function (caller, act, data) {
    var id = data.id;

    axboot.ajax({
        type:'GET',
        url: '/api/v1/hotelCustomer/' + id,
        callback: function(res) {
            caller.formView01.setData(res);
        }
    });
  },
  SEND_DATA: function(caller, act, data) {
    var data = caller.formView01.getData();
    console.log(data);

    axboot.ajax({
      type: 'GET',
      url: '/api/v1/hotelCustomer/' + data.id,
      callback: function (res) {
          parent.axboot.modal.callback(res);
      }
  });
  },
  MODAL_EXIT: function (caller, act, data) {
    caller.gridView01.addRow();
  },
  dispatch: function (caller, act, data) {
    var result = ACTIONS.exec(caller, act, data);
    if (result != 'error') {
        return result;
    } else {
        // 직접코딩
        return false;
    }
  }
});

fnObj.pageStart = function () {
  var _this = this;
  this.gridView01.initView();
  this.formView01.initView();
  _this.pageButtonView.initView();
  ACTIONS.dispatch(ACTIONS.PAGE_SEARCH);
};

fnObj.pageButtonView = axboot.viewExtend({
  initView: function () {
      axboot.buttonClick(this, 'data-page-btn', {
          save: function () {
              ACTIONS.dispatch(ACTIONS.PAGE_SAVE);
          },
          close: function () {
              ACTIONS.dispatch(ACTIONS.PAGE_CLOSE);
          },
      });
  },
}); 


/*
  **
  ** gridView
  **
*/
fnObj.gridView01 = axboot.viewExtend(axboot.gridView, {
  initView: function () {
      var _this = this;

      this.target = axboot.gridBuilder({
          showLineNumber: true,
          showRowSelector: false,
          frozenColumnIndex: 0,
          multipleSelect: true,              
          target: $('[data-ax5grid="grid-view-01"]'),
          columns: [
              {key: "guestNm", label: "이름", width: 100, align: "center", editor: "text"},
              {key: "guestTel", label: "연락처", width: 150, align: "center", editor: "text", formatter: "phone"},
              {key: "email", label: "이메일", width: 150, align: "center", editor: "text"},
              {key: "gender", label: "성별", width: 80, align: "center", editor: "text"},
              {key: "brth", label: "생년월일", width: 150, align: "center", editor: "text"},
          ],
          body: {
              onClick: function () {
                  this.self.select(this.dindex, {selectedClear: true});
                  ACTIONS.dispatch(ACTIONS.ITEM_CLICK, this.item);
              }
          }
      });

      axboot.buttonClick(this, "data-grid-view-01-btn", {
          "select": function () {
              ACTIONS.dispatch(ACTIONS.SEND_DATA, this.item);
          },
          "close": function () {
              ACTIONS.dispatch(ACTIONS.ITEM_ADD, this.item);
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


/*
  **
  ** formView
  **
*/
fnObj.formView01 = axboot.viewExtend(axboot.formView, {
  getDefaultData: function() {
    return {};
  },
  getData: function() {
    var data = this.modelFormatter.getClearData(this.model.get());
    return $.extend({}, data);
  },
  setData: function(res) {
    var setData = $.extend({}, res);
    this.model.setModel(setData);
    this.modelFormatter.formatting();
  },
  initView: function() {
    this.target = $('.js-form');
    this.model = new ax5.ui.binder();
    this.model.setModel(this.getDefaultData(), this.target);
    this.modelFormatter = new axboot.modelFormatter(this.model);
  }
});