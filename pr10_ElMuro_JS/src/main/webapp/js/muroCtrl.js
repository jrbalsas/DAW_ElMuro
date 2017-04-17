$(function () {
      muroCtrl.init();
  });

var muroCtrl={
      config: {
          iMensaje:"[name=mensaje]",
          frmMensaje:"#frmMensaje",
          errMensaje:"#errMsg",
          ERmensaje:/.{2,25}/
      },
      init: function () {
          var self=this;
          $(this.config.frmMensaje).on('submit',function (event) {              
              if (!self.validaMensaje()) {
                  event.preventDefault();
              } //else submit form
          });
          
          console.log("controlador inicializado");
      },
      validaMensaje: function () {
          console.log("aquí");
            var valido=false;
            var mensaje=$(this.config.iMensaje).val().trim();
            var $errMsg=$(this.config.errMensaje);
            if (this.config.ERmensaje.test(mensaje)) {
                  $errMsg.text("");
                  valido=true;
              }else {
                  $errMsg.text("La longitud del mensaje debe estar entre 2 y 25 caracteres");
              }              
              return valido;
      }

  };


