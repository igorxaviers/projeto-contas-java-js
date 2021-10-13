usuarios = {
    init: () => {
        usuarios.listaUsuarios();
    },
    listaUsuarios: () => {
        HTTPClient.get('/Usuario?acao=listar')
        .then(usuarios => {
            return usuarios.json();
        })
        .then(usuarios => {
            const tabela = document.getElementById("usuarios-table");
            let valores = '';
            usuarios.forEach(usuario => {
                valores += `
                <tr>
                    <td>${usuario.nome}</td> 
                    <td>${usuario.login}</td>
                    <td class="text-center">${(usuario.admin ? '<i class="fas fa-check text-success"></i>': '<i class="fas fa-times text-danger"></i>')}</td>
                    <td class="text-center">${(usuario.ativo  ? '<i class="fas fa-check text-success"></i>': '<i class="fas fa-times text-danger"></i>')}</td>
                    <td class="text-center bt-action">
                        <i class="fas fa-edit" title="Editar" onclick="usuarios.mostraForm('alterar',${usuario.id})"></i>
                        <i class="fas fa-trash-alt" title="Excluir" onclick="usuarios.excluir(${usuario.id}, '${usuario.nome}')"></i>
                    </td>
                </tr>`;
            });
            tabela.innerHTML = valores;
            console.log(usuarios);
        })
        .catch(e => {
            console.log('deu ruim', e);
        })
    },
    cadastrarUsuario: () => {
        const form = document.getElementById("form-usuario").elements;
        const form2 = document.getElementById("form-usuario");
        const usu = {
            nome: form['nome'].value,
            login: form['login'].value,
            senha: form['senha'].value,
            admin: form['admin'].value,
            ativo: form['ativo'].value,
            acao: "add"
        }
        console.log(usu);
        if(Validations.isValid())
        {
            HTTPClient.post('/Usuario', usu)
            .then(resp => {
                return resp.text();
            })
            .then(resp => {
                form2.classList.toggle("d-none");
                form2.reset();
                usuarios.listaUsuarios();
                ohSnap(resp, {color: 'green'});
            })
            .catch(e => {
                ohSnap(e, {color: 'red'});
                console.log(e);
            })
        }
        else
            ohSnap('Corrija os campos inválidos', {color: 'red'});
    },
    alterarUsuario: () => {
        const form = document.getElementById("form-usuario").elements;
        const form2 = document.getElementById("form-usuario");
        const usu = {
            id: form['id'].value,
            nome: form['nome'].value,
            login: form['login'].value,
            senha: form['senha'].value,
            admin: form['admin'].value,
            ativo: form['ativo'].value,
            acao: "alterar"
        }
        console.log(usu);

        if(Validations.isValid())
        {
            HTTPClient.post('/Usuario', usu)
            .then(usuarios => {
                console.log(usuarios)
                return usuarios.text();
            })
            .then(resp => {
                form2.classList.toggle("d-none");
                form2.reset();
                usuarios.listaUsuarios();
                ohSnap(resp, {color: 'green'});
            })
            .catch(e => {
                ohSnap(e, {color: 'red'});
                console.log(e);
            })
        }
        else
            ohSnap('Corrija os campos inválidos', {color: 'red'});
    },
    excluir: (id, nome) => {
        const usu = {
            id,
            acao: "excluir"
        }
        let resposta = confirm("Deseja mesmo excluir o usuário "+nome+" ?");
        if(resposta)
        {
            HTTPClient.post('/Usuario', usu)
            .then(usuarios => {
                console.log(usuarios)
                return usuarios.text();
            })
            .then(resp => {
                usuarios.listaUsuarios();
                ohSnap(resp, {color: 'green'});
            })
            .catch(e => {
                ohSnap(e, {color: 'red'});
                console.log(e);
            })
        }
    },
    mostraForm: (acao, id=0) => {
        const form = document.getElementById("form-usuario");
        form.classList.toggle("d-none");
        Validations.eventValidations(form.elements);
        if(acao == 'cadastrar')
        {
            document.getElementById("bt-cadastrar").classList.remove("d-none");
            document.getElementById("bt-alterar").classList.add("d-none");
        }
        else
        {
            document.getElementById("id-usuario").value = id;
            HTTPClient.get(`/Usuario?acao=busca&id=${id}`)
            .then(resp => {
                return resp.json();
            })
            .then(usuario => {
                form['nome'].value = usuario.nome;
                form['login'].value = usuario.login;
                form['senha'].value = usuario.senha;
                form['admin'].value = usuario.admin;
                form['ativo'].value = usuario.ativo;
            })
            .catch(e => {
                ohSnap(e, {color: 'red'});
                console.log(e);
            });
            document.getElementById("bt-alterar").classList.remove("d-none");
            document.getElementById("bt-cadastrar").classList.add("d-none");
        }
    },
    fechar: () => {
        const form = document.getElementById("form-usuario");
        form.classList.toggle("d-none");
        form.reset();
    }

}

usuarios.init();

