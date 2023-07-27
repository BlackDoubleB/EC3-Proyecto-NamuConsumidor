$(document).ready(function() {
	habilitarComboFechasOnChange()
	habilitarBtnLimpiar()
	habilitarBtnEnviarReserva()
	habilitarBtnVerTicket()

	configurarValoresPorDefecto()
});

function habilitarBtnVerTicket(){
	$("#btn-ver-ticket").click(function(){
		obtenerModalVerTicket(this)
	})
}

function habilitarBtnEnviarReserva(){
	$("#btn-enviar").click(function(){
		procesarEnvioReserva()
	})
}

function habilitarBtnLimpiar(){
	$("#btn-limpiar").click(function (){
		location.reload()
	})
}

function obtenerModalVerTicket(e){
	let idReserva = $(e).attr("data-id-reserva")
	
	let data = {
		idReserva
	}

	$.ajax({
		type: 'GET',
		url: '/crear-reserva/modal-ver-ticket',
		data: data,
		success: function(response) {
			if (!response) {
				return;
			}
			
			$(".modals").html(response)
			$("#modalReservaRealizada").modal("hide")			
			$("#modalVerTicket").modal("show")
		},
		error: function(jqXHR) {
			console.log(jqXHR)

			if (jqXHR.status == 401 || jqXHR.status == 403) {
				toastr["error"]( "No esta autorizado a realizar esta acción","Error"  )
				return
			}
			
			toastr["error"]( jqXHR.status, "Error"  )
		}
	});
}

function configurarValoresPorDefecto() {

 	$("#numero-personas").val(1)
	
	let fechaInput = document.getElementById("fecha-reserva")

	fechaInput.value = ""
	fechaInput.min = dayjs().format("YYYY-MM-DD")
	
	obtenerHorariosPorFecha()
}

function procesarEnvioReserva(){
	let idConsumidor = $("#id-consumidor").val()
	let idHorario = $("#horario").val()
	let numeroPersonas = $("#numero-personas").val()
	let comentarios = $("#comentarios").val() ?? ""
	
	if(!idConsumidor){
		//alert("No se encontró el Usuario de registro.")
		toastr["warning"]("No se encontró el Usuario de registro.", "Validación" )
		return
	}
	
	if(!idHorario){
		//alert("Debe seleccionar un Horario.")
		toastr["warning"]("Debe seleccionar un Horario.", "Validación" )
		return
	}
	
	if(!numeroPersonas || Number(numeroPersonas) < 1 ){
		//alert("Debe ingresar un número de personas.")
		toastr["warning"]("Debe ingresar un número de personas.", "Validación" )
		return
	}
	
	let data = {
		idConsumidor,
		idHorarioReserva: idHorario,
		numeroPersonas,
		comentarios
	}

	$.ajax({
		type: 'POST',
		url: '/api/reserva/create',
		contentType: "application/json",
		data: JSON.stringify(data),
		success: function(response) {
			if (!response.success) {
				//alert(response.message)
				toastr["error"](response.message, "Error" )
				return;
			}
			
			$("#btn-ver-ticket").attr("data-id-reserva", response.message)

			$("#modalReservaRealizada").modal("show")
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

function habilitarComboFechasOnChange() {
	$("#fecha-reserva").change(function() {
		obtenerHorariosPorFecha()
	})
}

function obtenerHorariosPorFecha() {
	let fecha = $("#fecha-reserva").val()

	let data = {
		fecha: fecha
	}

	$.ajax({
		type: 'GET',
		url: '/crear-reserva/combo-horario',
		data: data,
		success: function(response) {
			if (!response) {
				return;
			}

			$(".combo-horarios").html(response)
		},
		error: function(jqXHR) {
			console.log(jqXHR)

			if (jqXHR.status == 401 || jqXHR.status == 403) {
				toastr["error"]( "No esta autorizado a realizar esta acción","Error"  )
				return
			}
			
			toastr["error"]( jqXHR.status, "Error"  )
		}
	});
}