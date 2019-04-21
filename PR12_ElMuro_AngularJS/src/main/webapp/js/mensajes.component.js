

export const mensajesComponent = {
    bindings: {
        datos: '<'
    },
    template: `
<ul class="list-group" id="idMensajes">
    <!-- Mensajes del Muro-->
    <li class="list-group-item" ng-repeat='m in $ctrl.datos'><strong>{{m.identificador}}</strong> : {{m.mensaje}}</li>
</ul>
    
`
}
