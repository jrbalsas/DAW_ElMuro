/** ControladorJS del Muro 
*/

$(function () {
    muroCtrl.init();
});

//Muro Controller
var muroCtrl = {
    config: {
        srvUrl:  'webservices/muro'
    },
    viewModel: {
        identificador: "Desconocido"
    },
    init: function (model) {
        var self=this;
        $('[name=frmIdentificador]').on('submit',function (event) {
            event.preventDefault();
            self.cambiaIdentificador();
        });
        $('[name=frmMensaje]').on('submit',function (event) {
            event.preventDefault();
            self.enviaMensaje();
        });        
        $('#idIdentificador').text(this.viewModel.identificador);
        $('[name=identificador]').focus();
        this.cargaMensajes();
    },
    cambiaIdentificador: function () {        
        this.viewModel.identificador=$('[name=identificador]').val();
        $('#idIdentificador').html(this.viewModel.identificador);
        $('[name=mensaje]').focus();
    },
    cargaMensajes: function () {        
        var self = this;
        $.getJSON(this.config.srvUrl)
                .done(function (mensajes) {
                    self.visualizaMensajes(mensajes);
                })
                .fail(function (jqxhr) {
                    console.log('Error al recuperar los mensajes');
                    console.log(jqxhr);                    
                });
    },
    visualizaMensajes: function (mensajes) {
        var filas="";
        mensajes.forEach(function (m) {
            filas += "<li>" + m.identificador + ":" + m.mensaje +"</li>";
        });
        $('#idMensajes').html(filas);        
    },
    enviaMensaje: function () {   
            var self=this;
            var objMensaje={};
            objMensaje.mensaje=$('[name=mensaje]').val();
            objMensaje.identificador=self.viewModel.identificador;
            //Client-side validations ¿?
            $.ajax({
                url: this.config.srvUrl,                
                type: "POST",
                dataType: 'json',                //expected data type
                contentType: 'application/JSON',
                data: JSON.stringify(objMensaje)
                })
                .done(function (respuesta) {                    
                    var $iMensaje=$('[name=mensaje]');
                    $iMensaje.val("");
                    $iMensaje.focus();
                    $('#idErrores').html(""); //clean previous error message
                    self.cargaMensajes();
                })
                .fail(function (jqxhr) {                 
                    console.log(jqxhr);
                    var err=jqxhr.responseJSON;
                    $('#idErrores').html(err[0].message);
                });

    }
};

