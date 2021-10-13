categorias = {
    init: () => {
        categorias.listaCategorias();
    },
    listaCategorias: () => {
        HTTPClient.get('/Categoria')
        .then(categorias => {
            console.log(categorias)
            return categorias.json();
        })
        .then(categorias => {
            const tabela = document.getElementById("categorias-table");
            let valores = '';
            categorias.forEach(categoria => {
                valores += `
                <tr>
                    <td>${categoria.nome}</td> 
                    <td class="text-center bt-action " >
                        <i class="fas fa-edit" title="Editar" onclick="categorias.mostraForm('alterar',${categoria.id})"></i>
                        <i class="fas fa-trash-alt" title="Excluir" onclick="categorias.excluir(${categoria.id}, '${categoria.nome}')"></i>
                    </td>
                </tr>`;
            });
            tabela.innerHTML = valores;
            console.log(categorias);
        })
        .catch(e => {
            ohSnap(e, {color: 'red'});
        })
    },
    cadastrarCategoria: () => {
        const form = document.getElementById("form-categoria").elements;
        const form2 = document.getElementById("form-categoria");
        const cat = {
            nome: form['nome'].value,
            acao: "add"
        }
        if(Validations.isValid())
        {
            HTTPClient.post('/Categoria', cat)
            .then(resp => {
                return resp.text();
            })
            .then(resp => {
                form2.classList.toggle("d-none");
                form2.reset();
                categorias.listaCategorias();
                if(resp.toUpperCase().includes('ERRO'))
                    ohSnap(resp, {color: 'red'});
                else
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
    alterarCategoria: () => {
        const form = document.getElementById("form-categoria").elements;
        const form2 = document.getElementById("form-categoria");
        const cat = {
            id: form['id'].value,
            nome: form['nome'].value,
            acao: "alterar"
        }
        console.log(cat);
        if(Validations.isValid())
        {
            HTTPClient.post('/Categoria', cat)
            .then(categorias => {
                console.log(categorias)
                return categorias.text();
            })
            .then(resp => {
                form2.classList.toggle("d-none");
                form2.reset();
                categorias.listaCategorias();
                if(resp.toUpperCase().includes('ERRO'))
                    ohSnap(resp, {color: 'red'});
                else
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
        const cat = {
            id,
            acao: "excluir"
        }
        let resposta = confirm("Deseja mesmo excluir a categoria "+nome+" ?");
        if(resposta)
        {
            HTTPClient.post('/Categoria', cat)
            .then(categorias => {
                console.log(categorias)
                return categorias.text();
            })
            .then(resp => {
                categorias.listaCategorias();
                ohSnap('Categoria excluída com sucesso', {color: 'green'});
            })
            .catch(e => {
                ohSnap('Erro ao excluir a categoria', {color: 'red'});
                console.log(e);
            })
        }
    },
    mostraForm: (acao, id=0) => {
        const form = document.getElementById("form-categoria");
        form.classList.toggle("d-none");
        Validations.eventValidations(form.elements);
        if(acao == 'cadastrar')
        {
            document.getElementById("bt-cadastrar").classList.remove("d-none");
            document.getElementById("bt-alterar").classList.add("d-none");
        }
        else
        {
            document.getElementById("id-categoria").value = id;
            HTTPClient.get(`/Categoria?acao=buscar&id=${id}`)
            .then(resp => {
                return resp.json();
            })
            .then(categoria => {
                form['nome'].value = categoria.nome;
            })
            .catch(e => {
                alert('Erro: ', e);
            });
            document.getElementById("bt-alterar").classList.remove("d-none");
            document.getElementById("bt-cadastrar").classList.add("d-none");
        }
    },
    fechar: () => {
        const form = document.getElementById("form-categoria");
        form.classList.toggle("d-none");
        form.reset();
    }

}

categorias.init();

