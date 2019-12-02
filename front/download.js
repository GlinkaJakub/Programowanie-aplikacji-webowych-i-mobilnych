$.ajax({
    url: 'http://localhost:8282/allFile',
    type: 'GET',
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token'),
        "accept": "application/json",
        "Access-Control-Allow-Origin":"*"
    },
    success: function(data) {
            showfiles(data);
    },
    error: function(data) {
        alert('Sign in again');
        window.location.href = "login.html";
    }
});

function showfiles(data) {
    var table = "<table> <tr> <th> File </th> <th> Download </th> </tr>";
    for(i=0;i<data.length;i++){
        table += "<tr> <td> " + data[i].fileName + " </td> <td> <button type=\"button\" class=\"loginbtn\" id=\"loginbutton\" value=\"Download\" onclick=\"downloadFile(\'" + data[i].id + "\')\">Download</button> </td> </tr>";
    }
    table += "</table>";
    document.getElementById("view").innerHTML = table;
}

function downloadFile1(name) {
    window.location.href = "http://localhost:8282/downloadFile/" + name;
}

function downloadFile(name){

    const url = 'http://localhost:8282/isLog';

    let h = new Headers();
    h.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
    console.log(localStorage.getItem('token'));

    let req = new Request(url, {
        method: 'GET',
        headers: h,
        mode: "no-cors"
    });

    h.forEach(key => console.log(key));

    fetch(req)
        .then((response) => {
            window.location.href = "http://localhost:8282/downloadFile/" + name;
            console.log(response.statusText);
        })
        .catch((err) => {
            console.log('ERROR:', err.message);
        });
}

