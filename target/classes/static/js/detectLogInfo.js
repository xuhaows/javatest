function show(){
	var params = {
		"poolId" : $("#poolId").val(),
		"oprVal" : $("#oprVal").val(),
		"testDay":$("#testDay").val()
			}
	$.ajax( {
						type : "post",
						dataType : "jsonp",
						data : params,
						jsonp : "iFishery_jsonpCallback",
						url : $("#iFisheryPath").val() + "/wxAction/getDetectTypeInfoAction.action", //请求的url
						success : function(result) {
							var str ="";
							if(result.operationResult==0){
								view(result);
								writeInfo(result);
							}
						},error:function(){
							addError("网络错误，连接失败");
						}
					})
};

$(document).ready(function() {
	var params = {
		"poolId" : $("#poolId").val(),
		"oprVal" : $("#oprVal").val()
	}
	$.ajax( {
		type : "post",
		dataType : "jsonp",
		data : params,
		jsonp : "iFishery_jsonpCallback",
		url : $("#iFisheryPath").val() + "/wxAction/genTestInfoOptionAction.action", //请求的url
		success : function(result) {
			var str = "";
			if (result.operationResult == 0) {
				$("#testInfo").append(result.optionStr);
			}
		},error:function(){
			addError("网络错误，连接失败");
		}
	})
}); 


function view(result){
	var myChart = echarts.init(document.getElementById('main'), 'vintage');
	option = {
			title : {
				text : result.titleText
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : result.titles
			},
			toolbox : {
				feature : {
					saveAsImage : {}
				}
			},
			grid : {
				left : '3%',
				right : '4%',
				bottom : '3%',
				containLabel : true
			},
			xAxis : [ {
				type : 'category',
				boundaryGap : false,
				data : result.detectTime
			} ],
			yAxis : [ {
				 type: 'value', 
                scale: true, 
                precision: 2, 
                splitNumber: 9, 
                splitArea: { show: true } 
			} ],
			series : [ {
				name : result.titles[0],
				type : 'line',
				data : result.value
			}, {
				name : result.titles[1],
				type : 'line',
				data : result.alarmValueLow,
				itemStyle : {  
                        normal : {  
                            color : '#f00',  
                            lineStyle : {  
                                width : 2,
                                type : 'dashed'
                            }  
                        }  
                    } 
			}, {
				name : result.titles[2],
				type : 'line',
				data : result.alarmValueHight,
				itemStyle : {  
                        normal : {  
                            color : '#f00',  
                            lineStyle : {  
                                width : 2,
                                type : 'dashed'
                            }  
                        }  
                    } 
			} ]
		};
		myChart.setOption(option);
};

function writeInfo(result){
	var str="<div style=\"width:900px; height:400px; overflow:scroll;\">";
	str+="<table id=\"tablemeeting\" class=\"table-hover  \" style=\"width: 900px;\">";
	str+="<thead>";
	str+="<tr class=\"table-header \">";
	str+="<th scope=\"col\">序号</th>";
	str+="<th scope=\"col\">时间</th>";
	str+="<th scope=\"col\">测试值</th>";
	str+="<th scope=\"col\">参考值</th>";
	str+="</tr>";
	str+="</thead>";
	str+="<tfoot> <tr>";
	str+="</tr> </tfoot>";
	var j=1;
	
	for(var i=result.value.length;i>0;i--){
		str+="<tr>";
        str+="<td>"+parseInt(j++)+"</td>";
        str+="<td>"+result.detectTime[i-1]+"</td>";
        str+="<td>"+result.value[i-1]+"</td>";
        str+="<td>"+formatAlarmValue(result.alarmValueLow[i-1],result.alarmValueHight[i-1])+"</td>";
        str+="</tr>";            
	}
	str+="<tbody>";
	str+="</tbody>";
	str+="</table>";
	str+="</div>";
	$("#detectInfo").html(str);
};

function setOprVal(){
	var info=$("#testInfo").val();
	$("#oprVal").val(info);
	show();
};

function setTestDay(day){
	$("#testDay").val(day);
	show();
}