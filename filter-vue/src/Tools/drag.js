export function drag(element) {
    element.onmousedown = function (e) {
        if (!e.target.className.includes("ant-input")) {
            let left = e.pageX - element.offsetLeft
            let top = e.pageY - element.offsetTop
            document.onmousemove = function (e) {
                element.style.left = e.pageX - left + "px"
                element.style.top = e.pageY - top + "px"
            }
            document.onmouseup = function () {
                document.onmousemove = null
            }
            return false
        }
    }
}