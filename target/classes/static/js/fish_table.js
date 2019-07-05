function show(){
	var userId=$("#userId").val();
	if(userId==null||userId==''){
		alertInfo("用户ID不能为空");
		return false;
	}
	var params = {
			"userId" : $("#userId").val()
		};
	$.ajax( {
						type : "post",
						dataType : "jsonp",
						data : params,
						jsonp : "iFishery_jsonpCallback",
						url : $("#iFisheryPath").val() + "/wxAction/getPoolStatusListAction.action", //请求的url
						success : function(result) {
							var str ="";
							if(result.operationResult==0){
								for(var i=0;i<result.plantFormats.length;i++){
									var plantName=result.plantFormats[i].plantName;
									str+="<div class='fishtable'>";
									str+="<h3 class='mleft20'>渔场名称："+plantName+"</h3>";
									str+="<p class=\"right\" style=\"  \"><b>气温:</b>&nbsp;&nbsp;<lable class=\"fontgreen\">"+formatStr(result.plantFormats[i].h)+"(℃)</lable>&nbsp;&nbsp;&nbsp;&nbsp;<b>湿度:</b>&nbsp;&nbsp;<lable class=\"fontgreen\">"+formatStr(result.plantFormats[i].at)+"(%)</lable>&nbsp;&nbsp;&nbsp;&nbsp;<b>气压:</b>&nbsp;&nbsp;<lable class=\"fontgreen\">"+formatStr(result.plantFormats[i].lt)+"(hPa)</lable>&nbsp;&nbsp;&nbsp;&nbsp;<b>光强:</b>&nbsp;&nbsp;<lable class=\"fontgreen\">"+formatStr(result.plantFormats[i].chl)+"(lx)</lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>";
									str+="<table id='tablemeeting' class='table table-hover'>";
									str+="<thead>";
									str+="<tr class='table-header'>";
									str+="<th scope='col' width='20%'>鱼池名称</th>";
									str+="<th scope='col' width='20%'>水质</th>";
									str+="<th scope='col' width='15%'>溶氧</th>";
									str+="<th scope='col' width='15%'>增氧设备</th>";
									str+="<th scope='col' width='15%'>投饵设备</th>";
									str+="<th scope='col' width='15%'>电源</th>";
									str+="</tr></thead>";
									str+="<tbody>";
									for(var j=0;j<result.plantFormats[i].poolFormats.length;j++){
										var poolName=result.plantFormats[i].poolFormats[j].poolName;
										var poolId=result.plantFormats[i].poolFormats[j].poolId;
										var k=j+1;
										var img_1="<A href='#'><img src='"+$("#farm").val()+"/pcClient/img/ic_set_"+result.plantFormats[i].poolFormats[j].control1Status+".png' width='35' height='35' /></A>";
										var img_2="<A href='#'><img src='"+$("#farm").val()+"/pcClient/img/ic_set_"+result.plantFormats[i].poolFormats[j].control2Status+".png' width='35' height='35' /></A>";
										if(result.plantFormats[i].poolFormats[j].control1Status==-2){
											img_1="/";
										}
										if(result.plantFormats[i].poolFormats[j].control2Status==-2){
											img_2="/";
										}
										str+="<tr>";
										str+="<td width='20%'>"+poolName+"</td>";
										str+="<td width='20%'><A href=\"javascript:\" onclick='goParameterTable(\""+poolName+"\",\""+poolId+"\",\""+plantName+"\")'><img src='"+$("#farm").val()+"/pcClient/img/ic_fish_"+result.plantFormats[i].poolFormats[j].waterStatus+".png' width='48' height='35' /></A></td>";
										str+="<td width='15%'><A href=\"javascript:\" onclick='goParameterTable(\""+poolName+"\",\""+poolId+"\",\""+plantName+"\")'><img src='"+$("#farm").val()+"/pcClient/img/ic_fish_"+result.plantFormats[i].poolFormats[j].oxStatus+".png' width='48' height='35' /></A></td>";
										str+="<td width='15%'>"+img_1+"</td>";
										str+="<td width='15%'>"+img_2+"</td>";
										str+="<td width='15%'><img src='"+$("#farm").val()+"/pcClient/img/ic_power_"+result.plantFormats[i].poolFormats[j].powerStatus+".png' width='35' height='35' /></td>";
										str+="</tr>";
									}
									str+="</tbody></table>";
									str+="</div> ";
								}
							}
							$("#plantInfo").html(str);
						},error:function(){
							addError("网络错误，连接失败");
						}
					})
};

function goParameterTable(poolName,poolId,plantName){
    
    var turnForm = document.createElement("form");   
    document.body.appendChild(turnForm);
    turnForm.method = 'post';
    turnForm.action = $("#farm").val()+"/goParameterTable";

    var newElement = document.createElement("input");
        newElement.setAttribute("type","hidden");
        newElement.name = "poolName";
        newElement.id = "poolName";
        newElement.value = poolName;        
        turnForm.appendChild(newElement);
        
        var plantNameElement = document.createElement("input");
        plantNameElement.setAttribute("type","hidden");
        plantNameElement.name = "plantName";
        plantNameElement.id = "plantName";
        plantNameElement.value = plantName;        
        turnForm.appendChild(plantNameElement);
        
        var poolIdElement = document.createElement("input");
        poolIdElement.setAttribute("type","hidden");
        poolIdElement.name = "poolId";
        poolIdElement.id = "poolId";
        poolIdElement.value = poolId;        
        turnForm.appendChild(poolIdElement);
        
    turnForm.submit();
}