function changeStations() {
	var st1 = document.getElementById("station1");
	var st2 = document.getElementById("station2");
	var temp = document.getElementById("station2").value;
	st2.value = st1.value;
	st1.value = temp;
}