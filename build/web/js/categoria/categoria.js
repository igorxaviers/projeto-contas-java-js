categorias = {
    init: () => {
        categorias.listaCategorias();
    },
    listaCategorias: () => {
        HTTPClient.get('/Categoria?acao=listar')
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
                    <td class="text-center"><i class="fas fa-edit" onclick="categorias.mostraForm('alterar',${categoria.id})"></i></td>
                    <td class="text-center"><i class="fas fa-trash-alt" onclick="categorias.excluir(${categoria.id}, '${categoria.nome}')"></i></td>
                </tr>`;
            });
            tabela.innerHTML = valores;
            console.log(categorias);
        })
        .catch(e => {
            console.log('deu ruim', e);
        })
    },
    cadastrarCategoria: () => {
        const form = document.getElementById("form-categoria").elements;
        const form2 = document.getElementById("form-categoria");
        const cat = {
            nome: form['nome'].value,
            acao: "add"
        }
        console.log(cat);

        HTTPClient.post('/Categoria', cat)
        .then(resp => {
            return resp.text();
        })
        .then(resp => {
            form2.classList.toggle("d-none");
            form2.reset();
            categorias.listaCategorias();
            alert(resp);
        })
        .catch(e => {
            alert('Erro: ', e);
        })
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

        HTTPClient.post('/Categoria', cat)
        .then(categorias => {
            console.log(categorias)
            return categorias.text();
        })
        .then(resp => {
            form2.classList.toggle("d-none");
            form2.reset();
            categorias.listaCategorias();
            alert(resp);
        })
        .catch(e => {
            alert('Erro: ', e);
        })
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
                alert(resp);
            })
            .catch(e => {
                alert('Erro: ', e);
            })
        }
    },
    mostraForm: (acao, id=0) => {
        const form = document.getElementById("form-categoria");
        form.classList.toggle("d-none");
        if(acao == 'cadastrar')
        {
            document.getElementById("bt-cadastrar").classList.remove("d-none");
            document.getElementById("bt-alterar").classList.add("d-none");
        }
        else
        {
            document.getElementById("id-categoria").value = id;
            HTTPClient.get(`/Categoria?acao=busca&id=${id}`)
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

