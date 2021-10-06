contas = {
    init: () => {
        contas.listaContas();
    },
    listaContas: () => {
        HTTPClient.get('/Contas')
        .then(contas => {
            console.log(contas)
            return contas.json();
        })
        .then(contas => {
            const tabela = document.getElementById("contas-table");
            let valores = '';
            let tipo = '';
            contas.forEach(conta => {
                tipo = conta.tipo == 0 ? 'Pagar' : 'Receber';
                valores += `
                <tr>
                    <td>${conta.data}</td> 
                    <td>${conta.data_vencimento}</td> 
                    <td>${conta.usuario.nome}</td> 
                    <td>${conta.descricao}</td> 
                    <td>${conta.status.nome}</td> 
                    <td>R$${conta.valor}</td> 
                    <td>${tipo}</td> 
                    <td class="text-center text-warning " onclick="contas.mostraForm('alterar',${conta.id})"><i class="fas fa-edit"></i></td>
                    <td class="text-center text-danger" onclick="contas.excluir(${conta.id})"><i class="fas fa-trash-alt"></i></td>
                </tr>`;
            });
            tabela.innerHTML = valores;
            console.log(contas);
        })
        .catch(e => {
            ohSnap(e, {color: 'red'});
        })
    },
    cadastrarConta: () => {
        const form = document.getElementById("form-conta").elements;
        const form2 = document.getElementById("form-conta");
        const conta = {
            cont_data: form['data'].value,
            cont_data_vencimento: form['data_vencimento'].value,
            cont_desc: form['descricao'].value,
            cont_valor: form['valor'].value,
            cont_tipo: form['tipo_conta'].value,
            acao: "add"
        }
        if(Validations.isValid())
        {
            HTTPClient.post('/Contas', conta)
            .then(resp => {
                return resp.text();
            })
            .then(resp => {
                form2.classList.toggle("d-none");
                form2.reset();
                contas.listaContas();
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
    alterarConta: () => {
        const form = document.getElementById("form-conta").elements;
        const form2 = document.getElementById("form-conta");
        const conta = {
            cont_id: form['id-conta'].value,
            cont_data: form['data'].value,
            cont_data_vencimento: form['data_vencimento'].value,
            cont_desc: form['descricao'].value,
            cont_valor: form['valor'].value,
            cont_tipo: form['tipo_conta'].value,
            cont_status: form['status'].value,
            acao: "alterar"
        };
        console.log(form['valor'].value);
        HTTPClient.post('/Contas', conta)
        .then(contas => {
            return contas.text();
        })
        .then(resp => {
            form2.classList.toggle("d-none");
            form2.reset();
            contas.listaContas();
            if(resp.includes('bloqueada'))
                ohSnap(resp, {color: 'red'});
            else
                ohSnap(resp, {color: 'green'});
        })
        .catch(e => {
            ohSnap(e, {color: 'red'});
            console.log(e);
        });
        
    },
    excluir: (id) => {
        const conta = {
            cont_id: id,
            acao: "excluir"
        }
        let resposta = confirm("Deseja mesmo excluir a conta?");
        if(resposta)
        {
            HTTPClient.post('/Contas', conta)
            .then(contas => {
                return contas.text();
            })
            .then(resp => {
                contas.listaContas();
                if(resp.includes('erro'))
                    ohSnap(resp, {color: 'red'});
                else
                    ohSnap( resp, {color: 'green'});
            })
            .catch(e => {
                ohSnap('Erro ao excluir a conta', {color: 'red'});
                console.log(e);
            })
        }
    },
    mostraForm: (acao, id=0) => {
        const form = document.getElementById("form-conta");
        Validations.eventValidations(form.elements);
        if(acao == 'cadastrar')
        {
            form.classList.toggle("d-none");
            document.getElementById("status-conta").classList.add("d-none");
            document.getElementById("bt-cadastrar").classList.remove("d-none");
            document.getElementById("bt-alterar").classList.add("d-none");
        }
        else
        {
            document.getElementById("status-conta").classList.remove("d-none");
            document.getElementById("id-conta").value = id;
            HTTPClient.get(`/Contas?acao=buscar&id=${id}`)
            .then(resp => {
                return resp.json();
            })
            .then(conta => {
                form.classList.toggle("d-none");
                form['tipo_conta'].disabled = true;
                form['data'].value = conta.data;
                form['data_vencimento'].value = conta.data_vencimento;
                form['descricao'].value = conta.descricao;
                form['valor'].value = conta.valor;
                form['tipo_conta'].value = conta.tipo;
                form['status'].value = conta.status.nome;
            })
            .catch(e => {
                ohSnap('Erro: ', e);
            });
            document.getElementById("bt-alterar").classList.remove("d-none");
            document.getElementById("bt-cadastrar").classList.add("d-none");
        }
    },
    fechar: () => {
        const form = document.getElementById("form-conta");
        form.classList.toggle("d-none");
        form.reset();
    }

}

contas.init();





