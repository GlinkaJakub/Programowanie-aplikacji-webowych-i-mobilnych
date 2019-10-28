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
var userAllowed = false;

$("form span").hide();

function isPasswordValid() {
    return $password.val().length > 7;
}

function arePasswordsMatching() {
    return $password.val() === $password2.val();
}

function canSubmit() {
    return isPasswordValid() && arePasswordsMatching() && isUserAllowed() && !areEmpty();
}

function areEmpty() {
    if($name.val().length == 0 ||
        $surname.val().length == 0 ||
        $email.val().length == 0 ||
        $password.val().length == 0)
        return true;
    else
        return false;
}

function passwordEvent() {
    if (isPasswordValid()) {
        $passwordspan.hide();
    } else {
        $passwordspan.show();
    }
}

function confirmPasswordEvent() {
    if (arePasswordsMatching()) {
        $confirmpasswordspan.hide();
    } else {
        $confirmpasswordspan.show();
    }
}

function userEvent() {
    if (isUserAllowed()) {
        $loginspan.hide();

    } else {
        $loginspan.show();
    }
}

function isUserAllowed() {
    userRequest();
    return userAllowed;
}
function userRequest() {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        var DONE = 4;
        var OK = 404;
        if (request.readyState == DONE) {
            if (request.status == OK) {
                userAllowed = true;
            }
            else {
                userAllowed = false;
            }
        }
    }

    link = 'http://localhost:5000/user/' + $login.val();
    request.open('GET', link, false);
    request.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    request.send();
}
function enableSubmitEvent() {
    $submit.prop("disabled", !canSubmit());
}


$password.focus(passwordEvent).keyup(passwordEvent).keyup(confirmPasswordEvent).keyup(enableSubmitEvent);
$password2.focus(confirmPasswordEvent).keyup(confirmPasswordEvent).keyup(enableSubmitEvent);
$login.focus(userEvent).keyup(userEvent).keyup(enableSubmitEvent());
$name.focus(areEmpty).keyup(areEmpty).keyup(enableSubmitEvent());
$surname .focus(areEmpty).keyup(areEmpty).keyup(enableSubmitEvent());
$email.focus(areEmpty).keyup(areEmpty).keyup(enableSubmitEvent());

enableSubmitEvent();

$submit.click(function () {
    var mydata = { firstname: $name.val(), lastname: $surname.val(), login: $login.val(), password: $password.val()};

    $.ajax({
        url: 'http://localhost:5000/register',
        type: 'POST',
        data: JSON.stringify(mydata),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        async: false,
        success: function() {
            alert("Signed Up Succesfully");
        },
        failure: function () {
            alert("Mistakes were made");
        }
    });
})
