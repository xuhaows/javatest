$().ready(function(){
	$("#beginTime").jeDate({
	    isinitVal:false,
		skinCell:"jedategreen",
	    //festival:true,
	    ishmsVal:false,
	    minDate: '2016-06-16 23:59:59',
	    maxDate: $.nowDate({DD:0}),
	    format:"YYYY-MM-DD hh:mm:ss",
	    zIndex:3000,
	})

	$("#endTime").jeDate({
	    isinitVal:false,
		skinCell:"jedategreen",
	    //festival:true,
	    ishmsVal:false,
	    minDate: '2016-06-16 23:59:59',
	    maxDate: $.nowDate({DD:0}),
	    format:"YYYY-MM-DD hh:mm:ss",
	    zIndex:3000,
	})
});


function show(page){
	var str="";
	var params={
		"wxAccount":$("#wxAccount").val(),
		"beginTime":$("#beginTime").val(),
		"endTime":$("#endTime").val(),
		"perPage":$("#items_per_page").val(),
		"page":page*$("#items_per_page").val()
	};
		$.ajax( {
						type : "post",
						dataType : "json",
						data : params,
						url : $("#farm").val() + "/weixin/getMsgSendLogList.action", //请求的url
						success : function(result) {
							    str = "<table id=\"tablemeeting\" class=\"table table-hover\"><thead>";
								str += "<tr class=\"table-header \">";
								str += "<th scope=\"col\">序号</th>";
								str += "<th scope=\"col\">微信帐号</th>";
								str += "<th scope=\"col\">收发类型</th>";
								str += "<th scope=\"col\">消息类型</th>";
								str += "<th scope=\"col\">发送时间</th>";
								str += "<th scope=\"col\">消息内容</th>";
								str += "</tr>";
								str += "</thead>";
								str += "<tfoot>";
								str += "<tr>";
							
								str += "</tr>";
								str += "</tfoot>";
								str +="<tbody>";
								if(result.length==0){
									str += "<tr><td colspan='6'>无记录！</td></tr>";
								}
							for(var i=0;i<result.length;i++){
								var k=i+1;
				   			  str += "<tr>";
				                 str += "<td>"+k+"</td>";
				                                  str += "<td>"+formatStr(result[i].wxAccount)+"</td>";
				                                  str += "<td>"+formatStr(result[i].type)+"</td>";
				                                  str += "<td>"+formatStr(result[i].msgType)+"</td>";
				                                  str += "<td>"+formatStr(result[i].sendTime)+"</td>";
				                                  str += "<td title='"+result[i].msg+"'>"+formStrLen(result[i].msg,25)+"</td>";
				   			  str += "</tr>";
				   		  }
							str +="</tbody>";
							str +="</table>";
							$("#tableDiv").html(str);
						}
				});
}

function show2(){
	var params ={
		"wxAccount":$("#wxAccount").val(),
		"beginTime":$("#beginTime").val(),
		"endTime":$("#endTime").val()
		};
	$.ajax( {
						type : "post",
						dataType : "json",
						data : params,
						url : $("#farm").val() + "/weixin/getMsgSendLogPage.action", //请求的url
						success : function(result) {
								var perPage=parseInt($("#items_per_page").val());
								var allPage=parseInt(result.COUNT)/perPage;
								allPage=Math.ceil(allPage);
								$("#Pagination").pagination(result.COUNT, {
									prev_text: '<< 上一页',
                   					next_text: '下一页 >>',
									num_edge_entries : 2,
									num_display_entries : 4,
									current_page: 0,
									callback : show,
									items_per_page : perPage
								});
								$("#searchPage").html('');
								$("#searchPage").append("<span class=\"page-sum\">共<strong class=\"allPage\" id=\"apTest\">"+allPage+"</strong>页</span>");
							}
					})
	show(0);
};