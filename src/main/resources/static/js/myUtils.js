function formatStr(str){
	if(str==null||str==''||str=='null'){
		str = '--';
	}
	return str;
}

function formStrLen(str,length){
	if(str==null||str==''||str=='null'){
		str = '--';
	}else{
		var strLen=str.length;
		str=str.substring(0,length);
		if(length<strLen){
			str+='...';
		}
	}
	return str;
}

function clearThis(obj){
	obj.value='';
}

function submit(){
	$("#form").submit();
}

function selectInfo(obj){
	window.location.href=obj.value;
}

function closeWindow(){
	window.close();
}

function addSuccess(){
	var txt=  "添加成功";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
						onOk:function(){
							window.opener.location.href=window.opener.location.href;
            				window.close();
						}
					});
	
}

function addSuccessAndClick(clickId){
	var txt=  "添加/修改成功";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
						onOk:function(){
							$("#"+clickId,window.opener.document).trigger('click');
            				window.close();
						}
					});
}

function addSuccessAndReq(path){
	var txt=  "添加/修改成功";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
						onOk:function(){
							window.location.href=path;
						}
					});
}

function addError(){
	var txt=  "添加失败";
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
}

function alertInfo(txt){
		window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.info);

}

function addError(txt){
	window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
}

function checkAll(){
	var check=$("#checkAll").prop("checked");
	$("input[name='subBox']").prop("checked", check);
}

function checkValue(){
	var result=true;
	$(".noNull").each(function(){     
		var name = $(this).attr("name");    
		if($(this).val()==""||$(this).val()==null){     
			alertInfo($(this).attr('notNull')+"不能为空");
			$(this).focus(); 
			result= false;
			return result;
		}
		if($(this).attr("type")=="radio"){
			if ($("input[name='"+name+"']:checked").size() < 1){
				alertInfo($(this).attr('notNull')+"不能为空!");
				$(this).focus(); 
				result= false;
				return result;
			}
		}
		if($(this).attr("type")=="checkbox"){
			if ($('input[name="'+name+'"]:checked').size() < 1){
				alertInfo($(this).attr('notNull')+"不能为空!");
				$(this).focus(); 
				result= false;
				return result;
				}      
			}
		})
	return result;
}

function formatAlarmValue(alarmValueLow,alarmValueHight){
	var str="--";
	if(alarmValueLow==null){
		str="<"+alarmValueHight;
	}else if(alarmValueHight==null){
		str=">"+alarmValueLow;
	}else{
		str=alarmValueLow+"--"+alarmValueHight;
	}
	return str;
}

function openwin()
    {
     window.open("add_weiyang.jsp", '喂养情况新增', 'height=550, width=560, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
 function openwin2()
    {
     window.open("add_yongyao.jsp", '动保情况新增', 'height=650, width=560, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
 function openwin3()
    {
     window.open("add_putLog.jsp", '放养新增', 'height=800, width=560, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
 
 function openwin4()
    {
     window.open("add_suyuan.jsp", '鱼批次新增', 'height=800, width=580, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
 
   function openwin6()
    {
     window.open($("#farm").val() + "/go/goAddWenzhenAction.action", '问诊记录新增', 'height=900, width=1200, top=0, left=100, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
   function openwin7()
    {
     window.open("add_catchLog.jsp", '起捕新增', 'height=800, width=560, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
   function openwin8()
    {
     window.open("add_clearPool.jsp", '清塘新增', 'height=800, width=560, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
    
   function openwin9(path,title)
    {
     window.open(path, title, 'height=700, width=1200, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
     
   function openwin10()
    {
     window.open("add_other.jsp", '其他新增', 'height=800, width=560, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
   
   function openwin12()
    {
     window.open("add_fishDisease.jsp", '鱼病新增', 'height=900, width=1200, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
   
    function openwin13()
    {
     window.open("add_growthRecord.jsp", '生长新增', 'height=800, width=560, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
    
     function openwin14()
    {
     window.open("add_waterDesc.jsp", '水色新增', 'height=800, width=560, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
     
     function openwin15()
    {
     window.open("add_fishDisease_2.jsp", '问诊新增', 'height=900, width=1200, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
     
     function openraty(path)
    {
     window.open(path, '评分', 'height=800, width=560, top=100, left=400, toolbar=no, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') 
    }
 