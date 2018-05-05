/** ControladorJS del Muro 
 */

//Muro Controller
class MuroController {
    constructor($http) {
        this.$http = $http;

        this.srvUrl = 'webservice/muro';
        //view-model
        this.identificador = "Desconocido";
        this.mensajes = [];

        this.nuevoidentificador = "";
        this.textomensaje = "";
        this.errores = [];

        this.init();
    }
    init() {
        this.cargaMensajes();
    }
    cambiaIdentificador() {
        this.identificador = this.nuevoidentificador;
        this.nuevoidentificador = "";
    }
    cargaMensajes() {
        this.$http.get(this.srvUrl)
                .then( response => {
                    console.log(response);
                    this.mensajes = response.data;
                })
                .catch(response => {
                    this.errores = response.data;
                });
    }
    enviaMensaje() {
        let objMensaje = {
            identificador: this.identificador,
            mensaje: this.textomensaje
        }

        let enviado = false;

        this.$http.post(this.srvUrl, objMensaje)
                .then(response => {
                    console.log(response);
                    console.log("Mensaje enviado")
                    //Clean input and error messages
                    this.textomensaje = "";
                    this.cargaMensajes();
                })
                .catch(response => {
                    this.errores = response.data;
                })
    }// enviaMensaje
}

/** Angular module for controllers */
angular.module('muroApp.controller', [])
        .controller('MuroController', ['$http', MuroController]);

