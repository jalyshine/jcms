layui.define(['jquery'], function (exports) {   // 生成验证码
    "use strict";
    var $ = layui.jquery;

    var handler = {
        draw:function (canvas, codeNum) {
            var canvas_width = canvas.width();
            var canvas_height = canvas.height();
            var _canvas = document.getElementById(canvas.attr("id"));
            var context = _canvas.getContext("2d");
            _canvas.width = canvas_width;
            _canvas.height = canvas_height;
            var sCode = "A,B,C,E,F,G,H,J,K,L,M,N,P,Q,R,S,T,W,X,Y,Z,1,2,3,4,5,6,7,8,9,0";
            var aCode = sCode.split(",");
            var aLength = aCode.length;//获取到数组的长度
            var identifyCode = "";
            var show_num = [];
            for (var i = 0; i < codeNum; i++) {
                var j = Math.floor(Math.random() * aLength);//获取到随机的索引值
                var deg = Math.random() * 30 * Math.PI / 180;//产生0~30之间的随机弧度
                var txt = aCode[j];//得到随机的一个内容
                identifyCode += txt;
                show_num[i] = txt.toLowerCase();
                var x = 10 + i * 20;//文字在canvas上的x坐标
                var y = 20 + Math.random() * 8;//文字在canvas上的y坐标
                context.font = "bold 25px 微软雅黑";
                context.translate(x, y);
                context.rotate(deg);
                context.fillStyle = handler.randomColor();
                context.fillText(txt, 0, 0);
                context.rotate(-deg);
                context.translate(-x, -y);
            }
            for (var i = 0; i < codeNum; i++) { //验证码上显示线条
                context.strokeStyle = handler.randomColor();
                context.beginPath();
                context.moveTo(Math.random() * canvas_width, Math.random() * canvas_height);
                context.lineTo(Math.random() * canvas_width, Math.random() * canvas_height);
                context.stroke();
            }
            for (var i = 0; i < 5*codeNum; i++) { //验证码上显示小点
                context.strokeStyle = handler.randomColor();
                context.beginPath();
                var x = Math.random() * canvas_width;
                var y = Math.random() * canvas_height;
                context.moveTo(x, y);
                context.lineTo(x + 1, y + 1);
                context.stroke();
            }
            var result = identifyCode.toLowerCase();
            canvas.on('click',function(){
                result = handler.draw(canvas, codeNum);
            })
            return result;
        }
        , randomColor: function () {//得到随机的颜色值
            var r = Math.floor(Math.random() * 256);
            var g = Math.floor(Math.random() * 256);
            var b = Math.floor(Math.random() * 256);
            return "rgb(" + r + "," + g + "," + b + ")";
        }
    }
    exports('identify', handler);
});