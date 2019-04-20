/** ControladorJS del Muro 
 */

$(() => {
    //Create and initialize controller    
    let muroCtrl = new MuroController();
    muroCtrl.init();
});

//Muro Controller
class MuroController {
    constructor() {
        this.srvUrl = 'webservice/muro';
        //view-model
        this.identificador = "Desconocido";
    }
    init() {
        //Attach view event-handlers

        $('[name=frmIdentificador]').on('submit', event => {
            event.preventDefault();
            this.cambiaIdentificador();
        });
        $('[name=frmMensaje]').on('submit', event => {
            event.preventDefault();
            this.enviaMensaje();
        });
        //Show initial information
        $('#idIdentificador').text(this.identificador);
        $('[name=identificador]').focus();
        this.cargaMensajes();
    }
    cambiaIdentificador() {
        this.identificador = $('[name=identificador]').val();
        $('#idIdentificador').html(this.identificador);
        $('[name=mensaje]').focus();
    }
    cargaMensajes() {
        fetch(this.srvUrl)
                .then(response => response.json())
                .then( mensajes => {
                    this.visualizaMensajes(mensajes);
                })
                .catch( () => {
                    //Network error
                    $('#idErrores').html("Error en conexión");
                    console.error("Error en conexión");
                });
    }
    visualizaMensajes(mensajes) {
        let filas = "";
        mensajes.forEach(m => {
            filas += `<li class="list-group-item"> ${m.identificador} : ${m.mensaje}</li>`;
        });
        $('#idMensajes').html(filas);
    }

    enviaMensaje() {
        let objMensaje = {
            mensaje: $('[name=mensaje]').val(),
            identificador: this.identificador
        }

        let enviado = false;

        fetch(this.srvUrl, {
            method: 'POST',
            body: JSON.stringify(objMensaje),
            headers: {
                'Content-type': 'application/json',
                'accept': 'application/json'
            }
        })
        .then( response => {
            if (response.ok) {
                enviado = true;
            }
            return response.json()
        })
        .then( msgs => {
            if (enviado == true) {
                console.log("Mensaje enviado")
                //Clean input and error messages
                let $iMensaje = $('[name=mensaje]');
                $iMensaje.val("");
                $iMensaje.focus();
                $('#idErrores').html("");
                //Reload messages
                this.cargaMensajes();
            } else {
                //show bean-validation errors
                console.warn(msgs)
                $('#idErrores').html(msgs[0].message);
            }
        })
        .catch( () => {
            //Network error
            $('#idErrores').html("Error en conexión");
            console.error("Error en conexión");
        })
    }// enviaMensajes()
}


