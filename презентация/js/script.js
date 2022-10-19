const C = document.querySelector("canvas"),
  $ = C.getContext("2d"),
  W = (C.width = 1920),
  H = (C.height = 1080);

//const str = "А+Б0В-Г1Д=Е2Ё Ж3З И4Й К5Л М6Н О7П Р8С Т9У Ф!Х Ц?Ч Ш.ЩЪ,Ы Ь:ЭЮ;Я",
// + "1234567890<>?&|\\/:;}{]['.,!@#$%^*()_+=-~`" 
const str = "社は現在、世界中の主要な金融センターに事務所をЌえており、お客様には24時間体制で、世界40ヶ所を越える市場での取引にアクセスいただけます" 
  matrix = str.split("");

let font = 8, // font size
  col = W / font,
  arr = [];

for (let i = 0; i < col; i++) arr[i] = 1;

function draw() {
  $.fillStyle = "rgba(0, 0, 15,.07)";
  $.fillRect(0, 0, W, H);
  $.fillStyle = "#76ff21e0";
  $.font = font + "px system-ui";
  for (let i = 0; i < arr.length; i++) {
    let txt = matrix[Math.floor(Math.random() * matrix.length)];
    $.fillText(txt, i * font, arr[i] * font);
    if (arr[i] * font > H && Math.random() > 0.975) arr[i] = 0;
    arr[i]++;
  }
}

setInterval(draw, 75); // speed

window.addEventListener("resize", () => location.reload());