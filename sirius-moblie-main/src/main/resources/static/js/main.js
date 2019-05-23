/**
 * main js
 */

$(function () {

    if (typeof $.fn.slimScroll !== 'undefined') {
        $('.wrapper').slimScroll({ height: '100%' });
    }

    //$(document).ajaxStop($.unblockUI);
});

var $$ = function () {

    return {
        /* json ajax */
        ajax: function (url, type, ctype, p1, p2, p3) {
            var params = {
                url: url,
                type: type,
                dataType: 'json',
                cache: false,
                contentType: ctype
            };
            if (typeof p1 === 'object' || typeof p1 === 'string') {
                params.data = p1;
            } else {
                p3 = p2;
                p2 = p1;
            }
            (typeof p2 === 'function') && (params.success = p2);
            (typeof p3 === 'function') && (params.error = p3);
            var old = params.success || function () {};
            params.success = function (r) {
                $$.UI.unblock();
                var now = new Date();
                console.log('%d:%d:%d.%d [%s]%s\n%o\n%o',
                    now.getHours(), now.getMinutes(), now.getSeconds(), now.getMilliseconds(),
                    params.type, params.url, params.data, r);
                if (r.code !== 0) {
                    alert(r.msg);
                    return;
                }
                old(r);
            };
            params.error = function (xhr, status, error) {
                $$.UI.unblock();
                var now = new Date();
                console.error('%d:%d:%d.%d [%s]%s\n%o\n%o %o',
                    now.getHours(), now.getMinutes(), now.getSeconds(), now.getMilliseconds(),
                    params.type, params.url, params.data, error, status);
                //alert(error);
                $$.Modal.error(xhr.responseText);
            };
            $.ajax(params);
        },
        get: function (url, p1, p2, p3) {
            $$.ajax(url, 'GET', 'application/x-www-form-urlencoded; charset=UTF-8', p1, p2, p3);
        },
        post: function (url, p1, p2, p3) {
            $$.ajax(url, 'POST', 'application/x-www-form-urlencoded; charset=UTF-8', p1, p2, p3);
        },
        postJson: function (url, p1, p2, p3) {
            $$.ajax(url, 'POST', 'application/json; charset=UTF-8', p1, p2, p3);
        },
        load: function (url, selector, callback) {
            (typeof selector === 'object') || (selector = $(selector));
            if (typeof selector[0] === 'undefined') {
                console.error("selector error: %o", selector);
                callback();
            } else {
                selector.load(url, callback);
            }
        },
        /* \json ajax */
        compareArrayIgnoreOrder: function (a, b, compareKey) {
            if (a.length !== b.length) {
                return false;
            }
            a = a.sort(compareKey);
            b = b.sort(compareKey);
            for (var i = 0; i < a.length; ++i) {
                for (var k in a[i]) {
                    if (k in b[i]) {
                        if (a[i][k] != b[i][k]) {
                            console.log(a[i][k], b[i][k]);
                            return false;
                        }
                    } else {
                        console.log(a[i], b[i]);
                        return false;
                    }
                }
            }
            return true;
        },
        talog: function (tag, log) {
            console.log("%c " + tag + " %c " + log + " %c",
                "background:#35495e; padding: 1px; border-radius: 3px 0 0 3px; color: #fff",
                "background:#3c8dbc; padding: 1px; border-radius: 0 3px 3px 0; color: #fff",
                "background:transparent");
        }
    };
}();

$$.UI = function () {
    return {
        blockWith: function (iconcls, textcls, text) {
            $.blockUI({
                message: '<i class="' + iconcls + '"></i>&nbsp;' + text,
                css: {
                    border: 'none',
                    padding: '15px',
                    backgroundColor: '#000',
                    '-webkit-border-radius': '10px',
                    '-moz-border-radius': '10px',
                    opacity: .75,
                    color: '#fff'
                }
            });
        },
        blockSpin: function (text) {
            this.blockWith('fa fa-spin fa-spinner', 'text-info', text);
        },
        blockLoading: function () {
            this.blockSpin('加载中...');
        },
        unblock: function () {
            // if (typeof $.fn.unblockUI !== 'undefined') {
                $.unblockUI();
            // }
        }
    };
}();

$$.Event = function () {
    var hub = {};
    var register = function (event, callback) {
        var events = [], t = event.split(" ");
        for (var i in t) {
            var z = t[i].split(',');
            var p = z[0].substr(0, z[0].lastIndexOf('.') + 1);
            events.push(z[0]);
            for (var j = 1; j < z.length; ++j) {
                events.push(p + z[j]);
            }
        }
        // console.log(events);

        for (var k in events) {
            if (!(events[k] in hub)) {
                hub[events[k]] = [];
            }
            if (typeof callback !== 'function') {
                throw new Error('Register callback must be a function.')
            }
            hub[events[k]].push(callback);
        }
    };
    var dispatch = function (event) {
        if (!(event in hub)) {
            return;
        }
        for (var i in hub[event]) {
            hub[event][i].apply(this, [].slice.call(arguments, 1));
        }
    };
    return {
        register: register,
        dispatch: dispatch,
        on: register,
        emit: dispatch
    };
}();

$$.Modal = function () {
    return {
        error: function (content) {
            var $modal = $('#modal-error');
            if ($modal.length > 0) {
                $modal.find('iframe').contents().find('body').html(content);
                $modal.modal('show');
            }
        },
        delete: function (callback) {
            var $modal = $('#modal-delete');
            var $modalTxtDelete = $modal.find('input[name=txt-delete]');
            var $modalBtnDelete = $modal.find('.btn-delete');
            $modalBtnDelete.off('click').on('click', function () {
                callback();
            });
            $modalTxtDelete.off('input propertychange').on('input propertychange', function () {
                if ($(this).val().toLowerCase() === 'delete') {
                    $modalBtnDelete.removeAttr('disabled');
                } else {
                    $modalBtnDelete.attr('disabled', 'disabled');
                }
            });
            $modal.on('show.bs.modal', function () {
                $modalTxtDelete.val('');
                // $modalTxtDelete[0].focus();
            });
            $modal.modal();
            // $modalTxtDelete[0].focus();
        }
    };
}();

$$.Utils = function () {
    return {
        buildArrayByField: function (array, field) {
            var result = [];
            for (var i in array) {
                result.push(array[i][field]);
            }
            return result;
        }
    };
}();