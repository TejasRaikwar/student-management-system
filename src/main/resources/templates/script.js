/**
 * 
 */
// function changeBackground(){
//     const hexCode = '#'+Math.floor(Math.random()*16923521).toString(16);
//     const bd = document.body;
//     bd.style.backgroundColor = hexCode;

// }
// const button = document.getElementById('btn');


$(document).ready(function () {

    $("#sidebar").mCustomScrollbar({
         theme: "minimal"
    });

    $('#sidebarCollapse').on('click', function () {
        // open or close navbar
        $('#sidebar').toggleClass('active');
        // close dropdowns
        $('.collapse.in').toggleClass('in');
        // and also adjust aria-expanded attributes we use for the open/closed arrows
        // in our CSS
        $('a[aria-expanded=true]').attr('aria-expanded', 'false');
    });

});