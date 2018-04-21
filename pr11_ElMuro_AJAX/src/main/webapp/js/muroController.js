/** ControladorJS del Muro 
*/

$(() => {
    //Create and initialize controller    
    let muroCtrl= new MuroController();    
    muroCtrl.init();
});

//Muro Controller
class MuroController {
    constructor () {
        this.srvUrl= 'webservice/muro';
        //view-model
        this.identificador= "Desconocido";
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
        
        $('#idIdentificador').text(this.identificador);
        $('[name=identificador]').focus();
        this.cargaMensajes();
    }
    cambiaIdentificador () {        
        this.identificador=$('[name=identificador]').val();
        $('#idIdentificador').html(this.identificador);
        $('[name=mensaje]').focus();
    }
    cargaMensajes () {        
        fetch(this.srvUrl)
            .then( response => response.json() )
            .then( mensajes => {
                this.visualizaMensajes(mensajes);
            })
            .catch( jqxhr => {
                //Network error
                console.log('Error al recuperar los mensajes');
                console.log(jqxhr);                    
            });
    }
    visualizaMensajes (mensajes) {
        let filas="";
        mensajes.forEach( m => {
            filas += `<li> ${m.identificador} : ${m.mensaje}</li>`;	
        });
        $('#idMensajes').html(filas);        
    }
    enviaMensaje() {   
        let objMensaje={};
        objMensaje.mensaje=$('[name=mensaje]').val();
        objMensaje.identificador=this.identificador;

        fetch(this.srvUrl, {
                method: 'POST',
                body: JSON.stringify(objMensaje),
                headers: {
                    'Content-type': 'application/JSON',
                    'accept': 'application/JSON' 
                }
            })
            .then( response  => {
                if (response.ok) {
                    let $iMensaje=$('[name=mensaje]');
                    $iMensaje.val("");
                    $iMensaje.focus();
                    $('#idErrores').html(""); //clean previous error message
                    this.cargaMensajes();
                    return;
                }  
                // Get bean-validation errors
                return response.json();                    
             })
            .then( errores => {
                //show bean-validation errors
                if (errores) {
                    console.log(errores);
                    $('#idErrores').html(errores[0].message);
                }
            })
            .catch ( err => {
                //Network error
                console.log(err);
            });
    }// enviaMensajes()
}


