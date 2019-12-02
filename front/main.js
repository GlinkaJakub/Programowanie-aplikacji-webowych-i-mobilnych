$uploadbtn = $("#uploadbtn");
$downloadbtn = $("#downloadbtn");
$logoutn = $("#logoutbtn");

function logout(){
    window.location.href = 'login.html';
    localStorage.setItem('token', '');
}
