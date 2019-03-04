var getDataList;
$(function () {
    var $tableForm = $('#tableForm');
    // 列表
    var fnData = function (obj, pageIndex, recordsNum, pages) {
        var htmls = "";
        for (var i = 0; i < obj.length; i++) {
            var record = obj[i];
            htmls += [
                '<div class="tableContent tableCtHover mt5">',
                '<div class="flexWrap">',
                '<div class="flexWrap flexAgCen" style="width: 33%; text-align:center;  min-height: 70px;""> <div class="flexCon"> '
                + record.className + '</div></div>',

                '<div class="flexWrap flexAgCen" style="width: 33%; text-align:center; "> <div class="flexCon"> '
                + record.createTime + '</div></div>',

                '<div class="flexWrap flexAgCen"  style="width: 33%; text-align:center; "> <div class="flexCon">'
                + op(record.classId) + '</div></div>',

                '</div>', '</div>'].join('');
        }
        return htmls;
    };


    // 列表数据
    getDataList = function () {
        $.ajaxGetData({
            "ajaxUrl": g_requestContextPath + "/live/getLiveRoomClassList",
            "fnData": fnData,
            "postData": {
                'classId': $('#classId').val()
            },
            "headtype": 1
        });
    };
    getDataList();

    /**
     * 增加直播间文件表单提交
     */
    $tableForm.validate({
        submitHandler: function () {
            formSubmit("#submitBtn", true, "保存中...");
            var options = {
                success: function (data) {
                    if (data.code == '1000') {
                        window.location.href = g_requestContextPath + "/live/toClassPage?roomId=" + $('#roomId').val();
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

    //打开上传窗口
    $('.uploadBtn').on('click', function () {
        openPopForm('#uploadWin');
    })

});

//操作
function op(classId) {
    return '<a class="a_style" data-id="' + classId + '" onclick="editFileWin(this)">修改</a> ' +
        '<a class="a_style" data-id="' + classId + '" onclick="deleteFile(this)">删除</a>';
}

//编辑课程
function editFileWin(obj) {
    $.ajax({
        url: g_requestContextPath + '/live/getLiveRoomClassById',
        data: {
            'classId': $(obj).data("id")
        },
        success: function (data) {
            if (data.code == 1000) {
                openPopForm('#uploadWin');
                $('#classId').val(data.data.classId);
                $('#className').val(data.data.className);
                $('#roomId').val(data.data.roomId);
            } else {
                popLayer(data.message);
            }
        }
    });
}

/**
 * 删除课程
 * @param obj
 */
function deleteFile(obj) {
    var d = dialog({
        title: '提示',
        content: '是否确认删除',
        okValue: '确 定',
        ok: function () {
            $.ajax({
                url: g_requestContextPath + '/live/deleteClass',
                data: {
                    'classId': $(obj).data("id")
                },
                success: function (data) {
                    if (data.code == 1000) {
                        window.location.href = g_requestContextPath + "/live/toClassPage?roomId=" + $('#roomId').val();
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
