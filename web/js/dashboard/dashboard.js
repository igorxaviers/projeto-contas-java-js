dashboard = {
    init: () => {
        dashboard.getInformacoes();
        setTimeout(()=>{
            dashboard.showPie();
        }, 500)
    },
    getInformacoes: () => {
        HTTPClient.get('/Dashboard')
        .then(info => {
            console.log(info)
            return info.json();
        })
        .then(info => {
            let valor_caixa = info[0].saldo;    
            let contas = info[1];
            let valores = '';
            let tipo = '';
            let totalPagar = 0;
            let totalReceber = 0;

            console.log(info);

            document.getElementById("valor-caixa").innerHTML = valor_caixa;   
            contas.forEach(conta => {
                tipo = conta.tipo == 0 ? 'Pagar' : 'Receber';
                valores += `
                <tr>
                    <td>${conta.data_vencimento}</td> 
                    <td>${(conta.fornecedor.fantasia ? conta.fornecedor.fantasia : '---')}</td> 
                    <td>${conta.descricao}</td> 
                    <td>${conta.status.nome} <div class="status ${conta.status.nome}"></div></td> 
                    <td>R$${conta.valor}</td> 
                    <td>${tipo}</td> 
                </tr>`;
            });
            document.getElementById("contas-table").innerHTML = valores;
            document.getElementById("total-pagar").innerHTML = info[2];
            document.getElementById("total-receber").innerHTML = info[3];

            dashboard.totalPagar = info[4];
            dashboard.totalReceber = info[5];

        })
        .catch(e => {
            ohSnap(e, {color: 'red'});
        })
    },
    showPie: () => {
        Chart.defaults.global.defaultFontFamily = 'Poppins';
        Chart.defaults.global.defaultFontColor = '#262626';
        var ctx = document.getElementById("myPieChart");
        var myPieChart = new Chart(ctx, {
        type: 'doughnut',
        data: {
            labels: [
                "A PAGAR: R$",
                "A RECEBER: R$"
            ],
            datasets: [{
                data: [dashboard.totalPagar, dashboard.totalReceber],
                backgroundColor: ['#e74a3b', '#1cc88a'],
                hoverBackgroundColor: ['#e74a3b', '#17a673'],
                hoverBorderColor: "rgba(234, 236, 244, 1)",
            }],
        },
        options: {
            maintainAspectRatio: false,
            tooltips: {
            backgroundColor: "rgb(255,255,255)",
            bodyFontColor: "#858796",
            borderColor: '#dddfeb',
            borderWidth: 1,
            xPadding: 15,
            yPadding: 15,
            displayColors: true,
            caretPadding: 10,
            },
            legend: {
            display: true
            },
            cutoutPercentage: 80,
        },
        });
    },  
    totalPagar: 0,
    totalReceber: 0


}

dashboard.init();

