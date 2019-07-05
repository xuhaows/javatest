function show(){
	var params = {
		"poolId" : $("#poolId").val()
			}
	$.ajax( {
						type : "post",
						dataType : "jsonp",
						data : params,
						jsonp : "iFishery_jsonpCallback",
						url : $("#iFisheryPath").val() + "/wxAction/getPoolInfoListAction.action", //请求的url
						success : function(result) {
							var str ="<thead>"
                               	  str +=" <tr class=\"table-header \">";
                                  str +="<th scope=\"col\">监测类别</th>";
                                  str +="<th scope=\"col\">监测值</th>";
                                  str +="<th scope=\"col\">取值时间</th>";
                                  str +="<th scope=\"col\">参考值</th>";
                                  str +="<th scope=\"col\">操作</th>";
                                  str +="</tr>";
                              	  str +="</thead>";
                              	  str +="<tbody>"; 
							if(result.operationResult==0){
								var poolName=$("#poolName").val();
								var poolId=$("#poolId").val();
								var plantName=$("#plantName").val();
								for(var i=0;i<result.poolInfoList.length;i++){
								  var oprValue=result.poolInfoList[i].oprValue;
								  str +="<tr>";
                                  str +="<td>"+result.poolInfoList[i].testType+"</td>";
                                  var cas="";
                                  if(result.poolInfoList[i].status!=0){
                                	  cas="class=\"fontred\"";
                                  }else{
                                	  cas="class=\"fontgreen\"";
                                  }
                                  str +="<td "+cas+">"+formatStr(result.poolInfoList[i].testVal)+"</td>";
                                  str +="<td>"+formatStr(result.poolInfoList[i].detectTime)+"</td>";
                                  str +="<td>"+result.poolInfoList[i].alarmValue+"</td>";
                                  if(result.poolInfoList[i].status!=-99){
                                  	str +="<td><A <A href=\"javascript:\" onclick='goDetectLogAction(\""+poolName+"\",\""+poolId+"\",\""+plantName+"\",\""+oprValue+"\")'>详情</A></td>";
                                  }
                               	  str +="</tr>";
								}
							}
							str +="</tbody>"; 
							$("#tablemeeting").html(str);
						},error:function(){
							addError("网络错误，连接失败");
						}
					})
};

function goDetectLogAction(poolName,poolId,plantName,oprValue){
    var turnForm = document.createElement("form");   
    document.body.appendChild(turnForm);
    turnForm.method = 'post';
    turnForm.action = $("#farm").val()+"/goDetectLogAction";

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
        
        var oprValueElement = document.createElement("input");
        oprValueElement.setAttribute("type","hidden");
        oprValueElement.name = "oprValue";
        oprValueElement.id = "oprValue";
        oprValueElement.value = oprValue;        
        turnForm.appendChild(oprValueElement);
    turnForm.submit();
}