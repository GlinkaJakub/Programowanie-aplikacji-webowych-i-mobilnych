var $login = $("#login");
var $password = $("#password");
var $submit = $("#submit");
var $passwordspan = $("#passwordspan");
// var $hrefregister = $("#href-register-btn");

$("form span").hide();

function isPasswordValid() {
    return $password.val().length > 7;
}

function areEmpty() {
    if($login.val().length == 0 ||
        $password.val().length == 0)
        return true;
    else
        return false;
}

function canSubmit() {
    return isPasswordValid() && !areEmpty();
}

function passwordEvent() {
    if (isPasswordValid()) {
        $passwordspan.hide();
    } else {
        $passwordspan.show();
    }
}

function enableSubmitEvent() {
    $submit.prop("disabled", !canSubmit());
}

$login.keyup(enableSubmitEvent());
$password.focus(passwordEvent).keyup(passwordEvent).keyup(enableSubmitEvent);

// var mydata = { username: $login.val(), password: $password.val()};

$submit.click(function () {
    var mydata = {username: $login.val(), password: $password.val()};
    console.log(JSON.stringify(mydata));

    $.ajax({
        url: 'http://localhost:8282/authenticate',
        type: 'POST',
        data: JSON.stringify(mydata),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        headers: {
            "accept": "application/json",
            "Access-Control-Allow-Origin":"*"
        },
        success: function(data) {
            localStorage.setItem('token', data['token']);
            console.log(localStorage.getItem('token'));
            window.location.href = "main.html";
        },
        error: function () {
            alert("Wrong login or password");
            $login.val('');
            $password.val('');
        }
    });
});
