var getDataList;
$(function () {
    var $tableForm = $('#tableForm');
    var prame = {};
    // 列表
    var fnData = function (obj, pageIndex, recordsNum, pages) {
        var htmls = "";
        for (var i = 0; i < obj.length; i++) {
            var record = obj[i];
            htmls += [
                '<div class="tableContent tableCtHover mt5">',
                '<div class="flexWrap">',
                '<div class="flexWrap flexAgCen" style="width: 10%; text-align:center;  min-height: 70px;""> <div class="flexCon"> '
                + record.userNickName + '</div></div>',


                '<div class="flexWrap flexAgCen"  style="width: 5%; text-align:center;"> <div class="flexCon"> '
                + record.userLevel + '</div></div>',

                '<div class="flexWrap flexAgCen"  style="width: 10%; text-align:center;"> <div class="flexCon"> '
                + record.userTel + '</div></div>',


                '<div class="flexWrap flexAgCen"  style="width: 15%; text-align:center; "> <div class="flexCon">'
                + record.createTime + '</div></div>',

                '<div class="flexWrap flexAgCen"  style="width: 15%; text-align:center; "> <div class="flexCon">'
                + record.lastLoginTime + '</div></div>',

                '<div class="flexWrap flexAgCen"  style="width: 10%; text-align:center; "> <div class="flexCon">'
                + '-' + '</div></div>',

                '<div class="flexWrap flexAgCen"  style="width: 10%; text-align:center; "> <div class="flexCon">'
                + record.lookTime + '</div></div>',
                
                '<div class="flexWrap flexAgCen"  style="width: 10%; text-align:center; "> <div class="flexCon">'
                + record.createUserName + '</div></div>',

                '<div class="flexWrap flexAgCen"  style="width: 10%; text-align:center; "> <div class="flexCon">'
                + isUndefined(record.lastLoginIp) + '</div></div>',

                '<div class="flexWrap flexAgCen"  style="width: 10%; text-align:center; "> <div class="flexCon">'
                + op(record.groupId, record.userId, record.isGag, record.isBlack) + '</div></div>',

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
            "ajaxUrl": g_requestContextPath + "/user/getVipUserList",
            "fnData": fnData,
            "postData": prame,
            "headtype": 1
        });
    };
    getDataList();

    //查询vip客户
    $('#searchBtn').on('click', function () {
        getSearchForm();
        getDataList();
        closePopForm('#searchWin');
        return false;
    });

    //打开查询vip客户窗口
    $('.searchBtn').on('click', function () {
        openPopForm('#searchWin');
    });

    //打开添加vip客户窗口
    $('.addBtn').on('click', function () {
        openPopForm('#addWin');
        $('#groupId').val();
        $('.groupName').html('所有');
        $('#roomId').val();
        $('.roomName').html('所有');
        $('.userSpecialty').addClass('hide');
    });

    /**
     * 增加vip客户表单提交
     */
    $tableForm.validate({
        submitHandler: function () {
            formSubmit("#submitBtn", true, "保存中...");
            var options = {
                success: function (data) {
                    if (data.code == '1000') {
                        window.location.href = g_requestContextPath + "/user/vipUser";
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

    //刷新列表
    $('.refBtn').on('click', function () {
        prame = {};
        getDataList();
    });

    //全部游客
    $('.all').on('click', function () {
        prame = {};
        $(this).parent().addClass('cur');
        $(this).parent().siblings().removeClass('cur');
        getDataList();
    });

    //在线游客
    $('.online').on('click', function () {
        $('#isOnline').val(1);
        $('#isGag').val('');
        $('#isBlack').val('');
        $('#userNickName').val('');
        $('#userTel').val('');
        getSearchForm();
        $(this).parent().addClass('cur');
        $(this).parent().siblings().removeClass('cur');
        getDataList();
    });

    //禁言游客
    $('.gag').on('click', function () {
        $('#isOnline').val('');
        $('#isGag').val(1);
        $('#isBlack').val('');
        $('#userNickName').val('');
        $('#userTel').val('');
        getSearchForm();
        $(this).parent().addClass('cur');
        $(this).parent().siblings().removeClass('cur');
        getDataList();
    });

    //黑名单游客
    $('.black').on('click', function () {
        $('#isOnline').val('');
        $('#isGag').val('');
        $('#isBlack').val(1);
        $('#userNickName').val('');
        $('#userTel').val('');
        getSearchForm();
        $(this).parent().addClass('cur');
        $(this).parent().siblings().removeClass('cur');
        getDataList();
    });

});

function op(groupId, userId, isGag, isBlack) {
    var op = '<a class="a_style" data-id="' + userId + '" data-gid="' + groupId + '" onclick="editVipUserWin(this)">修改</a> ';
    if (isGag == 1) {
        op += '<a class="a_style" data-id="' + userId + '" data-gid="' + groupId + '" data-gag="0" onclick="setGag(this)">允许发言</a> ';
    } else {
        op += '<a class="a_style" data-id="' + userId + '" data-gid="' + groupId + '" data-gag="1" onclick="setGag(this)">禁言</a> ';
    }
    if (isBlack == 1) {
        op += '<a class="a_style" data-id="' + userId + '" data-gid="' + groupId + '" data-black="0" onclick="setBlack(this)">取消黑名单</a>';
    } else {
        op += '<a class="a_style" data-id="' + userId + '" data-gid="' + groupId + '" data-black="1" onclick="setBlack(this)">拉黑</a>';
    }
    return op;
}

//修改管理用户
function editVipUserWin(obj) {
    $.ajax({
        url: g_requestContextPath + '/user/getVipUserById',
        data: {
            'userId': $(obj).data("id")
        },
        success: function (data) {
            if (data.code == 1000) {
                openPopForm('#addWin');
                $('#userId').val(data.data.userId);
                $('#userNickName').val(data.data.userNickName);
                $('#userTel').val(data.data.userTel);
                // $('#userPass').val(data.data.userPass);
                $('#userTel').attr("readonly", true);
                $('#userRealName').val(data.data.userRealName);
                $('#userQq').val(data.data.userQq);
                $('#groupId').val(data.data.groupId);
                $('.groupName').html(data.data.groupName);
                $('#roomId').val(data.data.roomId);
                $('.roomName').html(data.data.roomName);
                $('#userLevel').val(data.data.userLevel);
                $('.userLevel').html('VIP ' + data.data.userLevel);
                $('#tempWatchTime').val(data.data.tempWatchTime);
            } else {
                popLayer(data.message);
            }
        }
    });
}

//设置黑名单
function setBlack(obj) {
    var groupId = $(obj).data("gid");
    var userId = $(obj).data("id");
    var black = $(obj).data("black");
    var content = '是否取消黑名单';
    if (black == 1) {
        content = '是否确认拉黑';
    }
    var d = dialog({
        title: '提示',
        content: content,
        okValue: '确 定',
        ok: function () {
            $.ajax({
                url: g_requestContextPath + '/user/setVipBlack',
                data: {
                    'userId': userId,
                    'isBlack': black,
                    'groupId': groupId
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

//禁言
function setGag(obj) {
    var groupId = $(obj).data("gid");
    var userId = $(obj).data("id");
    var gag = $(obj).data("gag");
    var content = '是否允许发言';
    if (gag == 1) {
        content = '是否确认禁言';
    }
    var d = dialog({
        title: '提示',
        content: content,
        okValue: '确 定',
        ok: function () {
            $.ajax({
                url: g_requestContextPath + '/user/setVipGag',
                data: {
                    'userId': userId,
                    'isGag': gag,
                    'groupId': groupId
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

function isUndefined(str) {
    if (typeof (str) == 'undefined') {
        return '-';
    } else {
        return str;
    }
}