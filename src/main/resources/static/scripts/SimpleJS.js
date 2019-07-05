/*左侧菜单控制*/
$(function(){
	var $link=$(".nav-list>li");
	$link.click(function(){
		if($(this).next("ul").css("display") == "none"){
			$(this).next("ul").css("display","block");
			 $(this).addClass("changePic");
			 $(this).removeClass("changePic2");
			}
			else{
				$(this).next("ul").css("display","none")
				$(this).addClass("changePic2");
				 $(this).removeClass("changePic");
				}
			
		});
})


/*表格鼠标经过效果*/	  	 
$(function(){
	  $("#tablemeeting tr:nth-child(even)").addClass("white-two");
   })
  

/*表单获得焦点效果*/
$(document).ready(function(){
  $("input").focus(function(){
    $(this).css("border-color","#669fc7");
  });
  $("input").blur(function(){
    $(this).css("border-color","#ccc");
  });
});

 

