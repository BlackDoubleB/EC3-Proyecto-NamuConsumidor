$(document).ready(function() {
	habilitarBtnRegistrar()
	habilitarBtnRegresar()
	habilitarBtnPopup()
});

function habilitarBtnPopup(){
	$("#btn-popup").click(function(){
		location.href = "/ingresar"
	})
}

function habilitarBtnRegresar(){
	$("#btn-regresar").click(function(){
		window.history.back();
	})
}

function habilitarBtnRegistrar(){
	$("#btn-registrar").click(function(){
		procesarRegistroUsuario()
	})
}

function procesarRegistroUsuario(){
	let contrasena = $("#contrasena").val()
	let confirmContrasena = $("#confirm-contrasena").val()
	let nombres = $("#nombres").val()
	let dni = $("#dni").val()
	let telefono = $("#telefono").val()
	let correo = $("#correo").val()
	let rol = "2"
	let usuario = $("#usuario").val()

	if (!contrasena || !confirmContrasena) {
		//alert("Debe ingresar una contraseña.")
		toastr["warning"]("Debe ingresar una contraseña.", "Validación" )
		return;
	}

	if (!nombres) {
		//alert("Debe ingresar los Nombres del Usuario.")
		toastr["warning"]("Debe ingresar los Nombres del Usuario.", "Validación" )
		return;
	}

	if (!dni) {
		//alert("Debe ingresar el Dni del Usuario.")
		toastr["warning"]("Debe ingresar el Dni del Usuario.", "Validación" )
		return;
	}

	if (!telefono) {
		//alert("Debe ingresar el teléfono del Usuario.")
		toastr["warning"]("Debe ingresar el teléfono del Usuario.", "Validación" )
		return;
	}

	if (!correo) {
		//alert("Debe ingresar el correo del Usuario.")
		toastr["warning"]("Debe ingresar el correo del Usuario.", "Validación" )
		return;
	}

	if (!usuario) {
		//alert("Debe ingresar un Nombre de Usuario.")
		toastr["warning"]("Debe ingresar un Nombre de Usuario.", "Validación" )
		return;
	}

	if (contrasena != confirmContrasena) {
		//alert("Las contraseñas no coinciden.")
		toastr["warning"]("Las contraseñas no coinciden.", "Validación" )
		return;
	}

	let data = {
		nombreCompletos: nombres,
		dni,
		telefono,
		correo,
		rol,
		usuario,
		contrasena
	}

	$.ajax({
		type: 'POST',
		url: '/api/usuario/create',
		contentType: "application/json",
		data: JSON.stringify(data),
		success: function(response) {
			if (!response.success) {
				//alert(response.message)
				toastr["error"]("Las contraseñas no coinciden.", "Error" )
				return;
			}

			$("#modalRegistrar").modal("show")
			
		},
		error: function(jqXHR) {
			console.log(jqXHR)
			
			//alert('Error: ' + jqXHR.status)
			toastr["error"](jqXHR.status, "Error" )
		}
	});
}