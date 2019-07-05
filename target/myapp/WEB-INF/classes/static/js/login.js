
function bindwx() {
	var path = $("#iFisheryPath").val()
			+ "/wxAction/checkAccountRelationAction.action";
	var params = {
		"userId" : $("#userId").val(),
		"password" : $("#password").val(),
		"wxAccount" : $("#wxAccount").val()
	};
		$.ajax({
			type : "post",
			dataType : "jsonp",
			jsonp : "iFishery_jsonpCallback",// 服务端用于接收callback调用的function名的参数
			data : params,
			url : path, // 请求的url
			success : function(result) {
				if (result.operationResult == 0) {
					var alertStr = "";
					alertStr += "userId:" + result.userId + ",";
					alertStr += "phoneNum:" + result.phoneNum + ",";
					alertStr += "wxAccount:" + result.wxAccount + ",";
					alertStr += "type:" + result.type + ",";
					alertStr += "status:" + result.status;
					var param = {
							"userId" : result.userId,
							"phoneNum" : result.phoneNum,
							"type" : result.type,
							"status" : result.status
						};
					$.ajax({
						type : "post",
						data : param,
						url : $("#iFisheryWeiXin").val()+"/kunyuwx/wxAuthLogin/checkLogin", // 将其改为自己的域名
						success : function(result) {
							if(result=="0")	window.location.href = $("#iFisheryWeiXin").val()+"/kunyuwx/goFishIndex"; // 将其改为自己的域名
							else	alertInfo("请求超时或没有在公众号内进行绑定");
						},
						error : function() {
							alertInfo("网络故障，绑定失败");
						}
					});
				}else if(result.operationResult == 1){
					console.log(result);
					alertInfo("用户信息验证错误");
				}else{
					alertInfo("用户信息验证失败");
				}
			},
			error:function(){
				addError("网络错误，绑定失败");
			}
		});
}
