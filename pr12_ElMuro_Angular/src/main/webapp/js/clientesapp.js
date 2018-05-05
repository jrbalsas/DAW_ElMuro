class ClientesController {
    constructor() {
        this.listaclientes = [
            {nombre: 'Carlos', dni: '12345678T', socio: true},
            {nombre: 'María', dni: '87654321G', socio: true},
            {nombre: 'Juan', dni: '12121212H', socio: false}
        ];
        this.cliente = {};
        this.nuevocliente={};
    }
    visualiza(cliente) {
        this.cliente = cliente;
    }
    enviaCliente() {
        //añadimos el nuevo cliente a la lista de clientes
        this.listaclientes.push(this.nuevoCliente);
        this.nuevoCliente = {};
    }

}

angular.module('clientesApp', [])
        .controller('ClientesController', [ClientesController]);
