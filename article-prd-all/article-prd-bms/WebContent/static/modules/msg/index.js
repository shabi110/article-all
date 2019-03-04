var getDataList;
$(function () {
    var $tableForm = $('#tableForm'),
    	$uploadForm = $('#uploadForm'),
    	$settingForm = $('#settingForm');
    var prame = {};
    // 列表
    var fnData = function (obj, pageIndex, recordsNum, pages) {
        var htmls = "";
        for (var i = 0; i < obj.length; i++) {
            var record = obj[i];
            htmls += [
                '<div class="tableContent tableCtHover mt5">',
                '<div class="flexWrap">',
                '<div class="flexWrap flexAgCen" style="width: 16%; text-align:center; "> <div class="flexCon"> '
                + (i+1) + '</div></div>',
                '<div class="flexWrap flexAgCen" style="width: 50%; text-align:center;  min-height: 70px;""> <div class="flexCon"> '
                + record.msgContent  + '</div></div>',


                '<div class="flexWrap flexAgCen"  style="width: 16%; text-align:center; "> <div class="flexCon">'
                + op(record.msgId) + '</div></div>',

                '</div>', '</div>'].join('');
        }
        return htmls;
    };

    var getSearchForm = function () {
        prame = getJsonParam("searchBox");
        console.log(prame);
    }

    // 列表数据
    getDataList = function () {
        $.ajaxGetData({
            "ajaxUrl": g_requestContextPath + "/live/getMsgList",
            "fnData": fnData,
            "postData": prame,
            "headtype": 1
        });
    };
    getDataList();



    //打开添加直播间窗口
    $('.addBtn').on('click', function () {
        openPopForm('#addWin');
    });
    
    $('.settingBtn').on('click', function () {
        openPopForm('#settingWin');
    });
  
    $('.userBtn').on('click',function(){
    	window.location.href=g_requestContextPath+'/live/autoUser';
    });
    /**
     * 增加直播间表单提交
     */
    $settingForm.validate({
        submitHandler: function () {
            formSubmit("#submitSetBtn", true, "保存中...");
            var options = {
                success: function (data) {
                    if (data.code == '1000') {
                        window.location.href = g_requestContextPath + "/live/autoMsg";
                    } else {
                        popLayer(data.message);
                    }
                    formSubmit("#submitSetBtn", false, "保存");
                },
                error: function (data) {
                    popLayer(stringMsg.serverErr);
                    formSubmit("#submitSetBtn", false, "保存");
                }
            };
            $settingForm.ajaxSubmit(options);
        }
    });

    /**
     * 增加直播间表单提交
     */
    $tableForm.validate({
        submitHandler: function () {
            formSubmit("#submitBtn", true, "保存中...");
            var options = {
                success: function (data) {
                    if (data.code == '1000') {
                        window.location.href = g_requestContextPath + "/live/autoMsg";
                    } else {
                        popLayer(data.message);
                    }
                    formSubmit("#submitBtn", false, "保存");
                },
                error: function (data) {
                    popLayer(stringMsg.serverErr);
                    formSubmit("#submitBtn", false, "保存");
                }
            };
            $tableForm.ajaxSubmit(options);
        }
    });
  
    $('.refBtn').on('click', function () {
        prame = {};
        getDataList();
    });

});

//操作
function op(roomId) {
    return '<a class="a_style" data-id="' + roomId + '" onclick="editLiveRoomWin(this)">修改</a> ' +
        '<a class="a_style" data-id="' + roomId + '" onclick="deleteLiveRoom(this)">删除</a>';
}

//编辑直播间
function editLiveRoomWin(obj) {
    $.ajax({
        url: g_requestContextPath + '/live/getAutoMsg',
        data: {
            'msgId': $(obj).data("id")
        },
        success: function (data) {
            if (data.code == 1000) {
                openPopForm('#addWin');
                $('#msgId').val(data.data.msgId);
                $('#msgContent').val(data.data.msgContent);
            } else {
                popLayer(data.message);
            }
        }
    });
}

/**
 * 删除直播间
 * @param obj
 */
function deleteLiveRoom(obj) {
    var d = dialog({
        title: '提示',
        content: '是否确认删除',
        okValue: '确 定',
        ok: function () {
            $.ajax({
                url: g_requestContextPath + '/live/removeMsg',
                data: {
                    'msgId': $(obj).data("id")
                },
                success: function (data) {
                    if (data.code == 1000) {
                    	window.location.href = g_requestContextPath + "/live/autoMsg";
                    } else {
                        popLayer(data.message);
                    }
                }
            });
        },
        cancelValue: '取消',
        cancel: function () {
        }
    });
    d.show();
}

//获取状态
function getStatus(status) {
    if (status != 3) {
        return '未开始'
    } else {
        return '播放中'
    }
}


//如果直播间idzhi为undefined 设置为 -
function isUndefined(roomUrl) {
    if (typeof(roomUrl) == "undefined") {
        return "-";
    } else {
        return roomUrl;
    }
}

//获取状态中文
function getRoomType(roomType) {
    if (roomType == 0) {
        return '免费直播';
    } else {
        return 'VIP直播';
    }
}

//直播等级中文
function getRoomLiveLevel(liveLevel) {
    if (liveLevel == 0) {
        return '入门';
    } else {
        return '进阶';
    }
}

//是否可见中文
function getIsShow(isShow) {
    if (isShow == 0) {
        return '可见';
    } else {
        return '不可见';
    }
}

//直播间详情
function toLiveRoomDetail(obj) {
    window.location.href = g_requestContextPath + "/live/toDetailPage?roomId=" + $(obj).data("id");
}