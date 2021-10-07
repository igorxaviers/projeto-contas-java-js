fornecedores = {
    init: () => {
        fornecedores.listaFornecedores();
    },
    listaFornecedores: () => {
        HTTPClient.get('/Fornecedor?acao=listar')
        .then(fornecedores => {
            return fornecedores.json();
        })
        .then(fornecedores => {
            const tabela = document.getElementById("fornecedores-table");
            let valores = '';
            console.log(fornecedores)
            fornecedores.forEach(fornecedor => {
                valores += `
                <tr>
                    <td>${fornecedor.cnpj}</td> 
                    <td>${fornecedor.categoria.nome}</td> 
                    <td>${fornecedor.fantasia}</td>
                    <td>${fornecedor.razao}</td>
                    <td>${fornecedor.inscricao_estadual}</td> 
                    <td>${fornecedor.endereco}</td> 
                    <td>${fornecedor.bairro}</td> 
                    <td>${fornecedor.email}</td>
                    <td>${fornecedor.cep}</td> 
                    <td>${fornecedor.cidade}</td> 
                    
                    <td class="text-center"><i class="fas fa-edit" onclick="fornecedores.mostraForm('alterar',${fornecedor.cnpj})"></i></td>
                    <td class="text-center"><i class="fas fa-trash-alt" onclick="fornecedores.excluir('${fornecedor.cnpj}', '${fornecedor.razao}')"></i></td>
                </tr>`;
            });
            tabela.innerHTML = valores;
            console.log(fornecedores);
        })
        .catch(e => {
            console.log('deu ruim', e);
        })
    },

    cadastrarFornecedor: () => {
        const form = document.getElementById("form-fornecedor").elements;
        const form2 = document.getElementById("form-fornecedor");
        const dados = {
            cnpj: removeCaracters(form['cnpj'].value),
            razao: form['razao'].value,
            fantasia: form['fantasia'].value,
            endereco: form['endereco'].value,
            bairro: form['bairro'].value,
            email: form['email'].value,
            inscricao_estadual: form['inscricao_estadual'].value,
            cep: form['cep'].value,
            cidade: form['cidade'].value,
            id_categoria: form['categoria'].value,
            acao: "add"
        }
        console.log(dados);
        if(Validations.isValid())
        {
            HTTPClient.post('/Fornecedor', dados)
            .then(resp => {
                return resp.text();
            })
            .then(resp => {
                form2.classList.toggle("d-none");
                form2.reset();
                fornecedores.listaFornecedores();
                if(resp.toUpperCase().includes('ERRO'))
                    ohSnap(resp, {color: 'red'});
                else
                    ohSnap(resp, {color: 'green'});
            })
            .catch(e => {
                ohSnap(e, {color: 'red'});
                console.log(e);
            });
        }
        else
            ohSnap('Corrija os campos inválidos', {color: 'red'});
    },

    alterarFornecedor: () => {
        const form = document.getElementById("form-fornecedor").elements;
        const form2 = document.getElementById("form-fornecedor");
        const dados = {
            cnpj: removeCaracters(form['cnpj'].value),
            razao: form['razao'].value,
            fantasia: form['fantasia'].value,
            endereco: form['endereco'].value,
            bairro: form['bairro'].value,
            email: form['email'].value,
            inscricao_estadual: form['inscricao_estadual'].value,
            cep: form['cep'].value,
            cidade: form['cidade'].value,
            id_categoria: form['categoria'].value,
            acao: "alterar"
        }
        console.log(dados);

        if(Validations.isValid())
        {
            HTTPClient.post('/Fornecedor', dados)
            .then(fornecedores => {
                console.log(fornecedores)
                return fornecedores.text();
            })
            .then(resp => {
                form2.classList.toggle("d-none");
                form2.reset();
                fornecedores.listaFornecedores();
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

    excluir: (cnpj, nome) => {
        const dados = {
            cnpj,
            acao: "excluir"
        }
        let resposta = confirm("Deseja mesmo excluir o fornecedor "+nome+" ?");
        if(resposta)
        {
            HTTPClient.post('/Fornecedor', dados)
            .then(fornecedores => {
                console.log(fornecedores)
                return fornecedores.text();
            })
            .then(resp => {
                fornecedores.listaFornecedores();
                ohSnap(resp, {color: 'green'});
            })
            .catch(e => {
                ohSnap(e, {color: 'red'});
                console.log(e);
            })
        }
    },

    listaCategorias: () => {
        HTTPClient.get('/Categoria')
        .then(categorias => {
            return categorias.json();
        })
        .then(categorias => {
            console.log(categorias);
            const select = document.getElementById("categorias");
            let opt;
            select.innerHTML = '<option selected hidden disabled>-- Selecione uma categoria --</option>';
            
            categorias.forEach(categoria => {
                opt = document.createElement('option');
                opt.value = categoria.id;
                opt.innerHTML = categoria.nome;
                select.appendChild(opt);
            });
        })
        .catch(e => {
            ohSnap(e, {color: 'red'});
        })
    },

    mostraForm: (acao, cnpj='') => {
        fornecedores.listaCategorias();
        const form = document.getElementById("form-fornecedor");
        Validations.eventValidations(form.elements);
        form.classList.toggle("d-none");
        fornecedores.addMaskCNPJ(form.elements['cnpj']);
        if(acao == 'cadastrar')
        {
            document.getElementById("bt-cadastrar").classList.remove("d-none");
            document.getElementById("bt-alterar").classList.add("d-none");
            const cep = form.elements['cep'];
            cep.addEventListener('keyup', () => {
                if(cep.value.length == 8) {
                    form.elements['cidade'].disabled = true;
                    form.elements['endereco'].disabled = true;
                    form.elements['bairro'].disabled = true;
                    HTTPClient.getURL(`https://viacep.com.br/ws/${cep.value}/json/`)
                    .then(resp => {
                        return resp.json();
                    })
                    .then(resp => {
                        console.log(resp);
                        if(!resp.erro)
                        {
                            form.elements['cidade'].value = resp.localidade;
                            form.elements['endereco'].value = resp.logradouro;
                            form.elements['bairro'].value = resp.bairro;
                        }
                    })
                    .catch(e => {
                        console.log(e);
                    })
                    .finally(() => {
                        form.elements['cidade'].disabled = false;
                        form.elements['bairro'].disabled = false;
                        form.elements['endereco'].disabled = false;
                    })
                }
            })
        }
        else
        {
            document.getElementById("cnpj-fornecedor").value = cnpj;
            HTTPClient.get(`/Fornecedor?acao=buscar&cnpj=${cnpj}`)
            .then(resp => {
                return resp.json();
            })
            .then(fornecedor => {
                console.log(fornecedor);
                form['cnpj'].value = fornecedor.cnpj;
                form['razao'].value = fornecedor.razao;
                form['fantasia'].value = fornecedor.fantasia;
                form['endereco'].value = fornecedor.endereco;
                form['bairro'].value = fornecedor.bairro;
                form['email'].value = fornecedor.email;
                form['inscricao_estadual'].value = fornecedor.inscricao_estadual;
                form['cep'].value = fornecedor.cep;
                form['cidade'].value = fornecedor.cidade;
                form['categoria'].value = fornecedor.categoria.id;
            })
            .catch(e => {
                alert('Erro: ', e);
            });
            document.getElementById("bt-alterar").classList.remove("d-none");
            document.getElementById("bt-cadastrar").classList.add("d-none");
        }
    },

    addMaskCNPJ: (cnpj) => {
        //47.893.822/0001-02
        console.log(cnpj)
        let cnpjValue;
        cnpj.addEventListener('keyup', () => {
            cnpjValue = removeCaracters(cnpj.value);
            if(!isNaN(cnpjValue))
            {
                if(cnpj.value.length == 2 || cnpj.value.length == 6)
                    cnpj.value += '.';
                if(cnpj.value.length == 10)
                    cnpj.value += '/';
                if(cnpj.value.length == 15)
                    cnpj.value += '-';
            }
            else{
                console.log(cnpjValue)
                cnpj.value = '';
            }
        })
    },

    fechar: () => {
        const form = document.getElementById("form-fornecedor");
        form.classList.toggle("d-none");
        form.reset();
    }
}

fornecedores.init();

function removeCaracters(valor) {
    let valorCorreto = valor;
    valorCorreto = valorCorreto.replaceAll('.', '');
    valorCorreto = valorCorreto.replace('/', '');
    valorCorreto = valorCorreto.replace('-', '');
    return valorCorreto;
}