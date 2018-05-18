/*View controller with ES6 class */

$( () => {
    let muroCtrl = new MuroController();
    muroCtrl.init(); //attach view event-handlers
});

class MuroController {
    constructor() {
        this.vm = {//view model
            iMensaje: "[name=mensaje]",
            frmMensaje: "#frmMensaje",
            errMensaje: "#errMsg",
            ERmensaje: /.{2,25}/
        }
    }
    init() {
        $(this.vm.frmMensaje).on('submit', event => {
            if (!this.validaMensaje()) {
                event.preventDefault();
            } //else submit form
        });

        console.log("controlador inicializado");
    }
    validaMensaje() {
        console.log("aquí");
        var valido = false;
        var mensaje = $(this.vm.iMensaje).val().trim();
        var $errMsg = $(this.vm.errMensaje);
        if (this.vm.ERmensaje.test(mensaje)) {
            $errMsg.text("");
            valido = true;
        } else {
            $errMsg.text("La longitud del mensaje debe estar entre 2 y 25 caracteres");
        }
        return valido;
    }

};  //MuroController


