$(document).ready(function() {
	habilitarBtnIngresar()
});

function habilitarBtnIngresar(){
	$("#btn-ingresar").click(function(e){
		procesarIngreso(e)
	})
}

function procesarIngreso(e){
	e.preventDefault()
	
	let username = $("#username").val()
	let password = $("#password").val()
	
	const data = {
		username,
		password
	}
	
	$.ajax({
		type: 'POST',
		url: '/login',
		contentType: "application/json",
		data: JSON.stringify(data),
		success: function(e, s, request) {
			let token = request.getResponseHeader("authorization")
			
			if (!token || !token.startsWith("Bearer ")){
				//alert("No ha sido posible autenticar al usuario ingresado.")
				toastr["error"]( "No ha sido posible autenticar al usuario ingresado.","Error"  )
				return;
			}
			
			if(!localStorage){
				//alert("El navegador no soporta LocalStorage.")
				toastr["error"]( "El navegador no soporta LocalStorage.","Error"  )
				return;
			}
			
			localStorage.setItem("at", token.replace("Bearer ", ""))
			
			location.href = "/crear-reserva";
		},
		error: function(jqXHR) {
			console.log(jqXHR)
			
			if(jqXHR.status == 401 || jqXHR.status == 403){
				toastr["error"]( "No esta autorizado a realizar esta acción","Error"  )
				return
			}
			
			toastr["error"]( jqXHR.status, "Error"  )
		}
	});
}
