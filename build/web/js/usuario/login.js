usuario = {
    login: () => {
        const form = document.getElementById("form-login").elements;
        let dados = {
            login: form['login'].value,
            senha: form['senha'].value
        }
        console.log(dados);

        HTTPClient.post('Login', dados)
        .then(resp => {
            console.log(resp)
            return resp.text();
        })
        .then(resp => {
            window.location.href = resp;
            console.log(resp);
        })
        .catch(e => {
            console.log('deu ruim', e);
        })
    }
}