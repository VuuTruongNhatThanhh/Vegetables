document.getElementById('show').onclick = function (){
    let password = document.getElementById('password');
    password.type = password.type == 'password' ? 'text' : 'password';
}
