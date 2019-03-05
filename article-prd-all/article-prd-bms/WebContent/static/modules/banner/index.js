var getDataList;
$(function () {
    var $tableForm = $('#tableForm');
    var $smallTableForm = $('#smallTableForm');
    var prame = {};
    // 列表
    var fnData = function (obj, pageIndex, recordsNum, pages) {
        var htmls = "";
        for (var i = 0; i < obj.length; i++) {
            var record = obj[i];
            htmls += [
                '<div class="tableContent tableCtHover mt5">',
                '<div class="flexWrap">',
                '<div class="flexWrap flexAgCen" style="width: 20%; text-align:center;  min-height: 70px;""> <div class="flexCon"> '
                + isUndefined(record.imgUrl) + '</div></div>',

                '<div class="flexWrap flexAgCen" style="width: 20%; text-align:center; "> <div class="flexCon"> '
                + isUndefined(record.linkUrl) + '</div></div>',

                '<div class="flexWrap flexAgCen"  style="width: 15%; text-align:center;"> <div class="flexCon"> '
                + isUndefined(record.imgAlt) + '</div></div>',

                '<div class="flexWrap flexAgCen"  style="width: 25%; text-align:center; "> <div class="flexCon">'
                + isUndefined(record.description) + '</div></div>',

                '<div class="flexWrap flexAgCen"  style="width: 5%; text-align:center; "> <div class="flexCon">'
                + isUndefined(record.sort) + '</div></div>',
                
                '<div class="flexWrap flexAgCen"  style="width: 5%; text-align:center; "> <div class="flexCon">'
                + (record.status==1?'正常':'停用') + '</div></div>',

                '<div class="flexWrap flexAgCen"  style="width: 15%; text-align:center; "> <div class="flexCon">'
                + op(record.id) + '</div></div>',

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
            "ajaxUrl": g_requestContextPath + "/banner/bannerList",
            "fnData": fnData,
            "postData": prame,
            "headtype": 1
        });
    };
    getDataList();

    //查询客户
    $('#searchBtn').on('click', function () {
        getSearchForm();
        getDataList();
        closePopForm('#searchWin');
        return false;
    });

    //打开查询客户窗口
    $('.searchBtn').on('click', function () {
        openPopForm('#searchWin');
    });

    //打开添加客户窗口
    $('.addBtn').on('click', function () {
        openPopForm('#addWin');
        $('.liveTitle').html("添加banner");
        $('#id').val('');
        $('#linkUrl').val('');
        $('#imgAlt').val('');
        $('#sort').val('');
    });

    /**
     * 增加客户表单提交
     */
    $tableForm.validate({
        submitHandler: function () {
            formSubmit("#submitBtn", true, "保存中...");
            var options = {
                success: function (data) {
                    if (data.code == '1000') {
                        window.location.href = g_requestContextPath + "/banner";
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

//如果时间为undefined 设置为 -
function isUndefined(lastLoginTime) {
    if (typeof(lastLoginTime) == "undefined") {
        return "-";
    } else {
        return lastLoginTime;
    }
}

function op(id) {
    var op = '<a class="a_style" data-id="' + id + '" onclick="deleteBanner(this)">删除 </a>';
    op += ' <a class="a_style" data-id="' + id + '"  onclick="editBannerWin(this)">编辑</a>';
    op += ' <a class="a_style" data-id="' + id + '"  onclick="disableBanner(this)">停用</a>';
    return op;
}

//修改管理用户
function editBannerWin(obj) {
    $.ajax({
        url: g_requestContextPath + '/banner/getBanner',
        data: {
            'bannerId': $(obj).data("id")
        },
        success: function (data) {
            if (data.code == 1000) {
                openPopForm('#addWin');
               $('.liveTitle').html("编辑banner");
               $('#id').val(data.data.id);
               $('#linkUrl').val(data.data.linkUrl);
               $('#imgAlt').val(data.data.imgAlt);
               $('#sort').val(data.data.sort);
            } else {
                popLayer(data.message);
            }
        }
    });
}




/**
 * 删除管理用户
 * @param obj
 */
function deleteBanner(obj) {
    var d = dialog({
        title: '提示',
        content: '是否确认删除',
        okValue: '确 定',
        ok: function () {
            $.ajax({
                url: g_requestContextPath + '/banner/deleteBanner',
                data: {
                    'id': $(obj).data("id")
                },
                success: function (data) {
                    if (data.code == 1000) {
                        getDataList();
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


function disableBanner(obj) {
    var d = dialog({
        title: '提示',
        content: '是否确认停用',
        okValue: '确 定',
        ok: function () {
            $.ajax({
                url: g_requestContextPath + '/banner/disableBanner',
                data: {
                    'bannerId': $(obj).data("id")
                },
                success: function (data) {
                    if (data.code == 1000) {
                        getDataList();
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

