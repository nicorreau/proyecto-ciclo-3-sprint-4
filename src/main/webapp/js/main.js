var username = new URL(location.href).searchParams.get("username");
var user;

$(document).ready(function () {

    $(function () {
        $('[data-toggle="tooltip"]').tooltip();
    });

    getUsuario().then(function () {
        
        $("#mi-perfil-btn").attr("href","profile.html?username=" + username);
        
        $("#user-saldo").html(user.saldo.toFixed(2) + "$");

        getMotocicletas(false, "ASC");

        $("#ordenar-marca").click(ordenarMotocicletas);
    });
});
async function getUsuario() {

    await $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletUsuarioPedir",
        data: $.param({
            username: username
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                user = parsedResult;
            } else {
                console.log("Error recuperando los datos del usuario");
            }
        }
    });

}

function getMotocicletas(ordenar, orden) {

    $.ajax({
        type: "GET",
        dataType: "html",
        url: "./ServletMotocicletaListar",
        data: $.param({
            ordenar: ordenar,
            orden: orden
        }),
        success: function (result) {
            let parsedResult = JSON.parse(result);

            if (parsedResult != false) {
                mostrarMotocicletas(parsedResult);
            } else {
                console.log("Error recuperando los datos de las peliculas");
            }
        }
    });
}
function mostrarMotocicletas(motocicletas) {

    let contenido = "";

    $.each(motocicletas, function (index, motocicleta) {

        motocicleta = JSON.parse(motocicleta);
        let precio;

        if (motocicleta.disponibles > 0) {

            if (user.premium) {

                if (motocicleta.novedad) {
                    precio = (2 - (2 * 0.1));
                } else {
                    precio = (1 - (1 * 0.1));
                }
            } else {
                if (motocicleta.novedad) {
                    precio = 2;
                } else {
                    precio = 1;
                }
            }

            contenido += '<tr><th scope="row">' + motocicleta.id + '</th>' +
                    '<td>' + motocicleta.marca + '</td>' +
                    '<td>' + motocicleta.cilindraje + '</td>' +
                    '<td>' + motocicleta.modelo + '</td>' +
                    '<td>' + motocicleta.disponibles + '</td>' +
                    '<td><input type="checkbox" name="novedad" id="novedad' + motocicleta.id + '" disabled ';
            if (motocicleta.novedad) {
                contenido += 'checked';
            }
            contenido += '></td>' +
                    '<td>' + precio + '</td>' +
                    '<td><button onclick="alquilarMotocicleta(' + motocicleta.id + ',' + precio + ');" class="btn btn-success" ';
            if (user.saldo < precio) {
                contenido += ' disabled ';
            }

            contenido += '>Reservar</button></td></tr>'

        }
    });
    $("#motocicletas-tbody").html(contenido);
}
