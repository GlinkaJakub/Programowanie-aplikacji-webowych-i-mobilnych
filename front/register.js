var $password2 = $("#password2");
var $password = $("#password");
var $login = $("#login");
var $name = $("#name");
var $surname = $("#surname");
var $email = $("#email");
var $submit = $("#submit");
var $loginspan = $("#loginspan");
var $passwordspan = $("#passwordspan");
var $confirmpasswordspan = $("#confirmpasswordspan");

$("form span").hide();

function isPasswordValid() {
    return $password.val().length > 7;
}

function arePasswordsMatching() {
    return $password.val() === $password2.val();
}

function canSubmit() {
    return isPasswordValid() && arePasswordsMatching() && !areEmpty();
}

function areEmpty() {
    if($("#name").val().length == 0 ||
        $("#surname").val().length == 0 ||
        $("#email").val().length == 0 ||
        $("#password").val().length == 0)
        return true;
    else
        return false;
}

function passwordEvent() {
    if (isPasswordValid()) {
        $password.next().hide();
    } else {
        $password.next().show();
        $passwordspan.show();
    }
}

function confirmPasswordEvent() {
    if (arePasswordsMatching()) {
        $password2.next().hide();
    } else {
        $password2.next().show();
        $confirmpasswordspan.show();
    }
}

function enableSubmitEvent() {
    $submit.prop("disabled", !canSubmit());
}


$password.focus(passwordEvent).keyup(passwordEvent).keyup(confirmPasswordEvent).keyup(enableSubmitEvent);
$password2.focus(confirmPasswordEvent).keyup(confirmPasswordEvent).keyup(enableSubmitEvent);
// $login.focus(userEvent).keyup(userEvent).keyup(enableSubmitEvent());
$name.focus(areEmpty).keyup(areEmpty).keyup(enableSubmitEvent());
$surname .focus(areEmpty).keyup(areEmpty).keyup(enableSubmitEvent());
$email.focus(areEmpty).keyup(areEmpty).keyup(enableSubmitEvent());

enableSubmitEvent();


$submit.click(function () {
    var mydata = { name: $name.val(), surname: $surname.val(), email: $email.val(), login: $login.val(), password: $password.val()};
    console.log(JSON.stringify(mydata));

    $.ajax({
        url: 'http://localhost:8282/users/save',
        type: 'POST',
        data: JSON.stringify(mydata),
        headers: {
            "accept": "application/json",
            "Access-Control-Allow-Origin":"*"
        },
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        success: function() {
            alert("Great!");
            window.location.href = "login.html"
        },
        failure: function () {
            alert("Mistakes were made");
        }
    });
});
