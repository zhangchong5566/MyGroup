function getDate() {
	var data = new Date();
	var year = data.getFullYear(); // 获取年
	var month = data.getMonth() + 1; // 获取月
	var day = data.getDate(); // 获取日
	var hours = data.getHours();
	var minutes = data.getMinutes();
	return year + "/" + month + "/" + day + "/" + " " + hours + ":" + minutes;
}