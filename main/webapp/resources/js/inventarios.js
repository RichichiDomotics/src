$(document).ready(function(){
	  $(".buscainventariosRep").click(function(){
		consecutivo = $("#consecutivo").val();
		camara = $("#camara").val();
		idCliente = $("#idCliente").val();
		claveProducto = $("#claveProducto").val();
		tunel = $("#tunel").val();
		if(idCliente=="" && consecutivo=="" && camara=="" && tunel==""&& claveProducto==""){
			$("#buscainventario").html("");
			alert("Favor de seleccionar un parametro de busqueda.");
			$("#consecutivo").focus();
			return false;
		}
		$.ajax("inventariosReporte", {
 		   "type": "POST", 
 		  "beforeSend": function() {
 			   $("#buscainventario").css("font-size","27px");
 			   $("#buscainventario").html('Cargando ...');
 		   },
 		  "success": function(result){
 		       $("#buscainventario").css("font-size","14px");
			   $("#buscainventario").html(result);
			   return false;
		   },
 		   "error": function(result) {
 		     alert("Error al recuperar la información", result);
 		     //showAlert("Error al recuperar la información", result);
 		   },
 		   "data": { consecutivo: consecutivo,camara : camara, idCliente : idCliente, 
 			   		 claveProducto : claveProducto,tunel:tunel},
 		   "async": true
 		});
		
		return false;
	});
	
});
