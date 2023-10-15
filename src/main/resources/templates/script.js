/**
 * 
 */
function changeBackground(){
    const hexCode = '#'+Math.floor(Math.random()*16923521).toString(16);
    const bd = document.body;
    bd.style.backgroundColor = hexCode;

}
const button = document.getElementById('btn');