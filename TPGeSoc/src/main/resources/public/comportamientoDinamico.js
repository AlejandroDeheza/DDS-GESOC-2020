function show1(){
    document.getElementById('div1').style.display ='block';
    document.getElementById('div2').style.display ='none';
}

function show2(){
    document.getElementById('div2').style.display ='block';
    document.getElementById('div1').style.display ='none';
}

function showPresupuestosMinimos(){
    document.getElementById('presupuestosMinimos').style.display ='block';
}
function hidePresupuestosMinimos(){
    document.getElementById('presupuestosMinimos').style.display ='none';
}

function showLoader(){
    document.getElementById('main').style.display = 'none';
    document.getElementById('loader').style.display = 'block';

}

function checkAddress(checkbox)
{
    if (checkbox.checked)
       showPresupuestosMinimos();
    else
        hidePresupuestosMinimos();
}

var i = 0;
var maxFields = 10;

function crearItem() {
    var container = document.getElementById('items');
    var textField = document.createElement("input");
    var numberField = document.createElement("input");
    var deleteButton = document.createElement("button");

    if (i < maxFields) {
        textField.type = "text";
        numberField.type = "number";
        deleteButton.type = "button";
        textField.classList.add("nombre-campo");
        numberField.classList.add("nombre-campo");
        deleteButton.classList.add("delete");
        textField.required = true;
        numberField.required = true;

        textField.id = 'itemD ' + i;
        textField.setAttribute('name', ('itemD' + i));
        textField.placeholder = "Ingrese la descripcion";
        numberField.id = 'itemN ' + i;
        numberField.setAttribute('name', ('itemN' + i));
        numberField.placeholder = "Ingrese el precio";
        deleteButton.id = 'itemB ' + i;
        deleteButton.setAttribute('onclick', "deleteField(this)");
        deleteButton.textContent = "-";
        var bro = document.createElement("br");
        bro.id = 'itemBro '+i;

        container.appendChild(textField);
        container.appendChild(numberField);
        container.appendChild(deleteButton);
        container.appendChild(bro);
        i++;
    }
}

function deleteField(html) {
    var objectId = html.id;
    var id = parseInt(objectId.replace("itemB ", ""), 10);
    // console.log(id);

    var element1 = document.getElementById("itemD " + id);
    var element2 = document.getElementById("itemN " + id);
    var element3 = document.getElementById("itemB " + id);
    var element4 = document.getElementById("itemBro " + id);

    if (i > 0 && element1 != null) {
        element1.parentNode.removeChild(element1);
        element2.parentNode.removeChild(element2);
        element3.parentNode.removeChild(element3);
        element4.parentNode.removeChild(element4);
        i--;
    }
}

