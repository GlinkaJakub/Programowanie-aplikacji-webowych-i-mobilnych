const url = 'http://localhost:8282/uploadFile';

document.addEventListener('DOMContentLoaded', init);

function init(){
    document.getElementById('submitbtn').addEventListener('click', upload);
}

function upload(ev){
    ev.preventDefault();

    let h = new Headers();
    h.append('Authorization', 'Bearer ' + localStorage.getItem('token'));
    console.log(localStorage.getItem('token'));
    h.append('Accept', 'application/json');

    let fd = new FormData();
    let myFile = document.getElementById('file').files[0];
    fd.append('file', myFile);

    let req = new Request(url, {
        method: 'POST',
        headers: h,
        body: fd
    });

    h.forEach(key => console.log(key));

    fetch(req)
        .then((response) => {
            alert("Success!");
            console.log(response.statusText);
        })
        .catch((err) => {
            console.log('ERROR:', err.message);
        });

}

