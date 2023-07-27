$(document).ready(function() {
	habilitarBtnBuscar()
	habilitarBtnEditarReserva()
	habilitarBtnVerReserva()
	
	configurarValoresPorDefecto()
});

function habilitarBtnVerReserva(){
	$(".btn-ver-reserva").click(function(){
		obtenerModalVerReserva(this)
	})
}

function habilitarBtnEditarReserva(){
	$(".btn-editar-reserva").click(function(){
		obtenerModalEditarReserva(this)
	})
}


function habilitarBtnBuscar(){
	$("#btn-buscar").click(function(){
		procesarBusquedaReservas()
	})
}

function habilitarBtnConfirmarEditar(){
	$("#btn-confirmar-editar").click(function(){
		procesarEditarReserva()
	})
}

function habilitarComboFechasOnChange() {
	$("#fecha-reserva-editar").change(function() {
		obtenerHorariosPorFecha()
	})
}

function obtenerHorariosPorFecha() {
	let fecha = $("#fecha-reserva-editar").val()

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

function configurarValoresPorDefecto() {
	
	let fechaInput = document.getElementById("fecha-reserva")

	fechaInput.value = dayjs().format("YYYY-MM-DD")

}

function procesarEditarReserva(){
	let idReserva = $("#id-reserva-editar").val()
	let idHorario = $("#horario").val()
	let numeroPersonas = $("#numero-personas").val()
	let comentarios = $("#comentarios").val() ?? ""
	
	if(!idReserva){
		//alert("No se encontró la Reserva a editar.")
		toastr["warning"]("No se encontró la Reserva a editar.", "Validación" )
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
		idReserva,
		idHorarioReserva: idHorario,
		numeroPersonas,
		comentarios
	}

	$.ajax({
		type: 'POST',
		url: '/api/reserva/update',
		contentType: "application/json",
		data: JSON.stringify(data),
		success: function(response) {
			if (!response.success) {
				alert(response.message)
				toastr["error"](response.message, "Error" )
				return;
			}
			
			$("#modalEditar").modal("hide")
			procesarBusquedaReservas()
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

function obtenerModalVerReserva(e){
	const idReserva = $(e).attr("data-id-reserva")
	
	const data = {
		idReserva
	}
	
	$.ajax({
		type: 'GET',
		url: '/mis-reservas/modal-ver-reserva',
		data: data,
		success: function(response) {
			if (!response) {
				return;
			}

			$(".modals").html(response)
			
			$("#modalVerReserva").modal("show")
			
			//SE DEBE HABILITAR EL BOTON PARA DESCARGAR EL TICKET
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

function obtenerModalEditarReserva(e){
	const idReserva = $(e).attr("data-id-reserva")
	
	const data = {
		idReserva
	}
	
	$.ajax({
		type: 'GET',
		url: '/mis-reservas/modal-editar',
		data: data,
		success: function(response) {
			if (!response) {
				return;
			}

			$(".modals").html(response)
			
			$("#modalEditar").modal("show")
			
			habilitarBtnConfirmarEditar()
			habilitarComboFechasOnChange()
			habilitarBtnConfirmarEditar()
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

function procesarBusquedaReservas(){
	let fecha = $("#fecha-reserva").val()
	
	const data = {
		fecha
	}
	
	$.ajax({
		type: 'GET',
		url: '/mis-reservas/table',
		data: data,
		success: function(response) {
			if (!response) {
				return;
			}

			$(".datagrid").html(response)
			
			habilitarBtnEditarReserva()
			habilitarBtnVerReserva()
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