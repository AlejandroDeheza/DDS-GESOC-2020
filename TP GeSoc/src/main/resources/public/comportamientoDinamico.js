var i = 0;
var maxFields = 10;

function show1(){
    document.getElementById('div1').style.display ='block';
    document.getElementById('div2').style.display ='none';
}

function show2(){
    document.getElementById('div2').style.display ='block';
    document.getElementById('div1').style.display ='none';
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

        textField.id = 'itemD ' + i;
        textField.placeholder = "Ingrese la descripcion";
        numberField.id = 'itemN ' + i;
        numberField.placeholder = "Ingrese el precio";
        deleteButton.id = 'itemB ' + i;
        deleteButton.addEventListener('click', deleteField);

        deleteButton.textContent = "-";

        container.appendChild(textField);
        container.appendChild(numberField);
        container.appendChild(deleteButton);
        container.appendChild(document.createElement("br"));
        i++;
    }
}

function deleteField() {
    var element1 = document.getElementById("itemD "  + (i-1));
    var element2 = document.getElementById("itemN " + (i-1));
    var element3 = document.getElementById("itemB " + (i-1));
    var x = document.getElementById("items").querySelectorAll("br");
    /* 	var x = document.getElementById("items").querySelectorAll("input");
          var y = document.getElementById("items").querySelectorAll("button");  */

    if (i > 0 && element1 != null) {
        element1.parentNode.removeChild(element1);
        element2.parentNode.removeChild(element2);
        element3.parentNode.removeChild(element3);
        x[i-1].parentNode.removeChild(x[i-1]);
        /* var div = document.getElementsByTagName("div")[0];
            div.innerHTML = div.innerHTML.replace(/<br>/g, ''); */
        i--;
    }
}

