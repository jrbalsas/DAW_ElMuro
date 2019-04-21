import { mensajesComponent } from './mensajes.component.js';

class MuroController {
    constructor($http) {
        this.$http = $http;
        this.serviceUrl='webservice/muro';

        //view-model
        //sample messages
        this.mensajes = [
            {identificador: 'Pepe', mensaje: 'Hola'},
            {identificador: 'María', mensaje: '¿Cómo estáis?'},
            {identificador: 'Carlos', mensaje: 'Haciendo las prácticas de DAW'}
        ];
        this.mensaje = {};
        this.identificador = "";
        this.nuevoIdentificador="";
        this.errorMsgs=[];

        this.cargaMensajes();
    }
    $onInit() {
    }
    cambiaIdentificador () {
        this.identificador=this.nuevoIdentificador;
    }
    enviaMensaje() {
        this.mensaje.identificador=this.identificador;
        //this.mensajes.push(this.mensaje);

        let enviado = false;

        this.$http.post(this.serviceUrl,this.mensaje)
        .then(response => {
                console.log("Mensaje enviado")
                this.mensaje={};
                this.errorMsgs=[];
        })
        .then( () => //Reload messages
                this.cargaMensajes()
        )
        .catch(response => {
                this.errorMsgs=response.data;
        })
    }// enviaMensajes()
    cargaMensajes() {
        this.$http.get(this.serviceUrl)
            .then( response => {
                this.mensajes=response.data;
            })
            .catch( () => {
                let msg='Error en conexion';
                this.errorMsgs=[{message: msg}];
                console.error(msg);
            });
    }

}
//Dependencies declaration
MuroController.$inject = ['$http'];

const appMuroComponent = {
    templateUrl: './js/app-muro.template.html',
    controller: MuroController
};

//export
const AppMuroModule = angular
        .module("muro.app", [])
        .component("appMuro", appMuroComponent)
        .component("mensajes", mensajesComponent)
        .name;

