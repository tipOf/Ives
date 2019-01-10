var clock = document.getElementById('clock');
var ctx = clock.getContext('2d');
var width = ctx.canvas.width;
var height = ctx.canvas.height;
var r = width / 2;
var rem =  width / 200;

function drawBackground() {
   ctx.save();
   // 1. 绘制园
   ctx.translate(r, r); // translate(x, y) 重新映射画布上的 (0,0) 位置
   ctx.beginPath(); // beginPath() 起始一条路径，或重置当前路径
   //方法创建弧/曲线（用于创建圆或部分圆） context.arc(x,y,r,sAngle,eAngle,counterclockwise);
   ctx.lineWidth = 10 * rem; // 用来定义线条的宽度
   ctx.arc(0, 0, r - ctx.lineWidth / 2, 0, 2 * Math.PI, false);
   ctx.stroke(); // stroke 绘制已制定的路径 fill填充已制定的路径

   // 2. 绘制小时数
   var hourNumbers = [3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2]
   ctx.font = 18 * rem + 'px Arial';
   ctx.textAlign = 'center';
   ctx.textBaseline = 'middle';
   hourNumbers.forEach(function(number, i) {
      // 得到弧度 2π / 12, 绘制园正好从3点开始，弧度从0算起 乘于i.
      var rad = 2 * Math.PI / 12 * i;
      var x = Math.cos(rad) * (r - 30 * rem);
      var y = Math.sin(rad) * (r - 30 * rem);

      ctx.fillText(number, x, y);
   });

   // 1. 绘制秒数点
   for(var i = 0; i < 60; i++) {
      var rad = 2 * Math.PI / 60 * i;
      var x = Math.cos(rad) * (r - 18 * rem);
      var y = Math.sin(rad) * (r - 18 * rem);

      ctx.beginPath();

      if(i % 5 == 0) {
         ctx.fillStyle = '#000';
         ctx.arc(x, y, 2 * rem, 0, 2 * Math.PI, false);
      }
      else {
         ctx.fillStyle = '#CCC';
         ctx.arc(x, y, 2 * rem, 0, 2 * Math.PI, false);
      }

      ctx.fill();
   }
}

function drawHour(hour, minute) {
   ctx.save();
   ctx.beginPath();
   var rad = 2 * Math.PI / 12 * hour;
   // 分针在时针上的偏转角度
   var mrad = 2 * Math.PI / 12 / 60 * minute;
   ctx.rotate(rad + mrad);
   ctx.lineWidth = 6 * rem;
   ctx.lineCap = 'round'; // 时针的园边
   ctx.moveTo(0, 10 * rem); // 时针的小尾巴
   ctx.lineTo(0, -r / 2) // 时针的长度
   ctx.stroke();
   ctx.restore();
}

function drawMinute(minute) {
   ctx.save();
   ctx.beginPath();
   var rad = 2 * Math.PI / 60 * minute;
   ctx.rotate(rad);
   ctx.lineWidth = 3 * rem;
   ctx.lineCap = 'round'; // 时针的园边
   ctx.moveTo(0, 10 * rem); // 时针的小尾巴
   ctx.lineTo(0, -r + 30 * rem) // 时针的长度
   ctx.stroke();
   ctx.restore();
}

function drawSecond(second) {
   ctx.save();
   ctx.beginPath();
   ctx.fillStyle = '#C14543';
   var rad = 2 * Math.PI / 60 * second;
   ctx.rotate(rad);
   //绘制秒针的从宽到细的画布
   ctx.moveTo(-2 * rem, 20 * rem); // 时针的小尾巴
   ctx.lineTo(2 * rem, 20 * rem);
   ctx.lineTo(1 * rem, -r + 18 * rem);
   ctx.lineTo(-1 * rem, -r + 18 * rem);
   ctx.fill();
   ctx.restore();
}

function drawDot() {
   ctx.beginPath();
   ctx.fillStyle = '#FFF';
   ctx.arc(0, 0, 3 * rem, 0, 2 * Math.PI, false);
   ctx.fill();
}

function draw() {
   ctx.clearRect(0, 0, width, height);
   var now = new Date();
   var hour = now.getHours();
   var minute = now.getMinutes();
   var second = now.getSeconds();

   drawBackground();
   drawHour(hour, minute);
   drawMinute(minute);
   drawSecond(second);
   drawDot();
   ctx.restore();
}

draw();

setInterval(draw, 1000);