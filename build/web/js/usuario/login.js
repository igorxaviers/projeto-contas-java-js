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
            return resp.text();
        })
        .then(resp => {
            // if(resp.ok)
            // {
            //     console.log(window.location.href+resp.url)
            //     alert(resp.mensagem);
            //     window.location.href = window.location.href+resp.url;
            // }
            // else
            // {
            //     alert(resp.mensagem);
            // }
            alert(resp);
            window.location.href = window.location.href+resp;

            console.log(resp);
        })
        .catch(e => {
            console.log('deu ruim', e);
        })
    }
}