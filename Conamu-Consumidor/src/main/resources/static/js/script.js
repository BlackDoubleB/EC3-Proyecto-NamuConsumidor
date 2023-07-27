$(document).ready(function() {
	habilitarHamburgerSideBar()
	
	habilitarCerrarSesion()
});

function habilitarHamburgerSideBar(){
	
	$(".hamburger").click(function(){
	    document.querySelector('body').classList.toggle('active');
	})
}

function habilitarCerrarSesion(){
	$("#cerrar-sesion").click(function(e){
		procesarCierreSesion(e)
	})
}

function procesarCierreSesion(e){
	e.preventDefault()
	
	localStorage.removeItem("at")
	
	$.ajax({
		type: 'POST',
		url: '/api/usuario/logout',
		contentType: "application/json",
		data: {},
		success: function() {
			location.href = "/"
		},
		error: function(jqXHR) {
			console.log(jqXHR)
			//alert('Error: ' + jqXHR.status)
			toastr["error"](jqXHR.status, "Error" )
		}
	});
}