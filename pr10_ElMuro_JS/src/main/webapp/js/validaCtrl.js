/*View controller with old literal object pattern */


$(function () {
      ctrl.init(); //attach view-event handlers
  });

var ctrl={
      vm: { //view-model
          idni:"[name=dni]",
          btDNI:"#btDNI",                   
          salida:"#mensaje",
          ERdni:/^\d{7,8}(-?[a-z])?$/i
      },
      init: function () {
          var self=this; //that=this
          $(this.vm.btDNI).on('click',function () {
              self.validaDNI();
          });                  
          console.log("controlador inicializado");
      },
      validaDNI: function () {
              var dni=$(this.vm.idni).val().trim();
              var mensaje="Tu dni "+dni;
              if (this.vm.ERdni.test(dni)) {

                  mensaje+= " es correcto";
                  console.log("Se ha aceptado el dni %s",dni);
              }else {
                  mensaje+= " es incorrecto";
              }
              $(this.vm.salida).text(mensaje);                   
      }

  }


