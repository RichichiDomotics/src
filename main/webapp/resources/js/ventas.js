/**
 * Created by Richard on 07/07/2015.
 */
$(document).ready(function(){
    $(".consultaVentas").click(function(){
        fecini = $("#fecini").val();
        fecfin = $("#fecfin").val();
        if(fecini==""){
            alert("Favor de ingresar una fecha de inicio");
            $("#fecini").focus();
            return false;
        }

        if(fecfin==""){
            alert("Favor de ingresar una fecha final");
            $("#fecfin").focus();
            return false;
        }
        $.ajax("getVentas", {
            "type": "POST",
            "beforeSend": function() {
                $("#buscaretemes").css("font-size","0.875em");
                $("#buscaretemes").html('<img src="resources/img/loader.GIF"/>');
            },
            "success": function(result){
                //$("#buscaretemes").css("font-size","0.875em");
                $("#buscaretemes").html(result);
                return false;
            },
            "error": function(result) {
                alert("Error al recuperar la información", result);
            },
            "data": {fecini:fecini, fecfin:fecfin},
            "async": true
        });
    });
    $(".consultaVentasIdEjecutivo").click(function(){
        fecini = $("#fecini").val();
        fecfin = $("#fecfin").val();
        idEjecutivo=$("#idEjecutivo").val();
        if(fecini==""){
            alert("Favor de ingresar una fecha de inicio");
            $("#fecini").focus();
            return false;
        }

        if(fecfin==""){
            alert("Favor de ingresar una fecha final");
            $("#fecfin").focus();
            return false;
        }
        $.ajax("getVentasIdEjecutivo", {
            "type": "POST",
            "beforeSend": function() {
                $("#buscaretemes").css("font-size","0.875em");
                $("#buscaretemes").html('<img src="resources/img/loader.GIF"/>');
            },
            "success": function(result){
                //$("#buscaretemes").css("font-size","0.875em");
                $("#buscaretemes").html(result);
                return false;
            },
            "error": function(result) {
                alert("Error al recuperar la información", result);
            },
            "data": {fecini:fecini, fecfin:fecfin, idEjecutivo:idEjecutivo},
            "async": true
        });
    });
});