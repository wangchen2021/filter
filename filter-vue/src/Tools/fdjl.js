//防抖
export function fd(fn, delay) {
    var timer = null;
    return function () {
        var _this = this;
        var args = arguments;
        if (timer) {
            clearTimeout(timer);
        }
        timer = setTimeout(function () {
            fn.apply(_this, args);
        }, delay);
    };
}
//节流
export function jl(fn, delay) {
    var timer = null;
    return function () {
        var _this = this;
        var args = arguments;
        if (timer) {
            return;
        }
        timer = setTimeout(function () {
            fn.apply(_this, args);
            clearTimeout(timer);
            timer = null;
        }, delay);
    };
}