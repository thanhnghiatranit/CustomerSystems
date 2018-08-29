function LoginError() {
	var x = document.getElementById("txtUserID").value;
	var y = document.getElementById("txtPassword").value;

	if (x == "") {
		document.getElementById("lblErrorMessage").innerHTML = "Error Message1";
		return false;
	} else if (y == "") {
		document.getElementById("lblErrorMessage").innerHTML = "Error Message2";
		return false;
	}
	document.getElementById("mode").value = "login";
	document.getElementById('myFormLogin').submit();
	return true;
}

function LoginClear() {
	document.getElementById("lblErrorMessage").innerHTML = "";
	document.getElementById("txtUserID").value = "";
	document.getElementById("txtPassword").value = "";

}
function Addnew() {
	var x = document.getElementById("btnAddnew").value;
	document.getElementById("mode").value = "addnew";
	document.getElementById('myForm1').submit();

}

function SearchName() {
	var x = document.getElementById("txtsearchName").value;
	var y = document.getElementById("llbSex").value;
	var z1= document.getElementById("txtBirthdayFrom").value;

	if(x=="" && y=="" && z1==""){
		alert('Input search');
	}
	document.getElementById("mode").value = "searchname";
	document.getElementById('myformSearch').submit();
	return true;
}
function toggle(source) {
	var checkboxes = document.querySelectorAll('input[type="checkbox"]');
	for ( var i = 0; i < checkboxes.length; i++) {
		if (checkboxes[i] != source)
			checkboxes[i].checked = source.checked;
	}
}
// checkbox only
function checkedFunction(arg) {
	var x = document.getElementById("customerId");
	if (arg.checked == true) {
		x.value = x.value + ",";
		x.value = x.value + arg.id;
	}
	if (arg.checked == false) {
		x.value = x.value.replace("," + arg.id, "");
	}
}

function ErrorDelete(){

	alert("Ban co chac chan xoa!");
}
function EditClear() {
	document.getElementById("lblErrorMessage").innerHTML = "";
	document.getElementById("txtCustomer_Id").value = "";
	document.getElementById("txtCustomer_name").value = "";
	document.getElementById("txtSex").value = "";
	document.getElementById("txtBirthday").value = "";
	document.getElementById("txtEmail").value = "";
	document.getElementById("txtAddress").value = "";

}
