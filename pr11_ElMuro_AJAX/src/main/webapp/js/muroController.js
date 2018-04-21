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
        $.getJSON(this.config.srvUrl)
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
            //Client-side validations ¿?
            $.ajax({
                url: this.config.srvUrl,                
                type: "POST",
                dataType: 'json',                //expected data type
                contentType: 'application/JSON',
                data: JSON.stringify(objMensaje)
                })
                .then( respuesta => {                    
                    var $iMensaje=$('[name=mensaje]');
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


