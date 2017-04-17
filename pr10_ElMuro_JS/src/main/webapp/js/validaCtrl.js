$(function () {
      ctrl.init();
  });

var ctrl={
      config: {
          idni:"[name=dni]",
          btDNI:"#btDNI",                   
          salida:"#mensaje",
          ERdni:/^\d{7,8}(-?[a-z])?$/i
      },
      init: function () {
          var self=this; //that=this
          $(this.config.btDNI).on('click',function () {
              self.validaDNI();
          });                  
          console.log("controlador inicializado");
      },
      validaDNI: function () {
              var dni=$(this.config.idni).val().trim();
              var mensaje="Tu dni "+dni;
              if (this.config.ERdni.test(dni)) {

                  mensaje+= " es correcto";
                  console.log("Se ha aceptado el dni %s",dni);
              }else {
                  mensaje+= " es incorrecto";
              }
              $(this.config.salida).text(mensaje);                   
      }

  }


