/** ControladorJS del Muro 
*/

$(() => {
    let muroCtrl= new MuroController();
    
    muroCtrl.init();
});

//Muro Controller
class MuroController {
    constructor () {
        this.config={
            srvUrl:  'webservice/muro'
        };
        this.viewModel= {
            identificador: "Desconocido"
        };
    }
    init () {
        //Attach view event-handlers
        
        $('[name=frmIdentificador]').on('submit', event=> {
            event.preventDefault();
            this.cambiaIdentificador();
        });
        $('[name=frmMensaje]').on('submit', event => {
            event.preventDefault();
            this.enviaMensaje();
        });        
        $('#idIdentificador').text(this.viewModel.identificador);
        $('[name=identificador]').focus();
        this.cargaMensajes();
    }
    cambiaIdentificador () {        
        this.viewModel.identificador=$('[name=identificador]').val();
        $('#idIdentificador').html(this.viewModel.identificador);
        $('[name=mensaje]').focus();
    }
    cargaMensajes () {        
            fetch(this.config.srvUrl)
                .then( response => response.json() )
                .then( mensajes => {
                    this.visualizaMensajes(mensajes);
                })
                .catch( jqxhr => {
                    console.log('Error al recuperar los mensajes');
                    console.log(jqxhr);                    
                });
    }
    visualizaMensajes (mensajes) {
        var filas="";
        mensajes.forEach(function (m) {
            filas += "<li>" + m.identificador + ":" + m.mensaje +"</li>";
        });
        $('#idMensajes').html(filas);        
    }
    enviaMensaje() {   
            var objMensaje={};
            objMensaje.mensaje=$('[name=mensaje]').val();
            objMensaje.identificador=this.viewModel.identificador;

            fetch(this.config.srvUrl, {
                    method: 'POST',
                    body: JSON.stringify(objMensaje),
                    headers: {
                        'Content-type': 'application/JSON',
                        'accept': 'application/JSON' 
                    }
                })
                .then( response  => {                    
                    let $iMensaje=$('[name=mensaje]');
                    $iMensaje.val("");
                    $iMensaje.focus();
                    $('#idErrores').html(""); //clean previous error message
                    this.cargaMensajes();
                })
                .catch(function (jqxhr) {                 
                    console.log(jqxhr);
                    var err=jqxhr.responseJSON;
                    $('#idErrores').html(err[0].message);
                });

    }
}


