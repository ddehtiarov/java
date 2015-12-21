function validate_form() {

	var re = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;

	if (document.getElementById("register_form").email.value == ""
			|| !re.test(document.getElementById("register_form").email.value)) {
		alert("Input email correctly!");
		return false;
	}

	if (document.getElementById("register_form").password.value == ""
			|| document.getElementById("register_form").password2.value == ""
			|| document.getElementById("register_form").password2.value != document
					.getElementById("register_form").password.value) {
		alert("Input password!");
		return false;
	}

	return true;
}
