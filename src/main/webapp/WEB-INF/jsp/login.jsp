<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/common/taglibs.jsp"%>
<head>
<%@include file="/common/meta.jsp"%>
<title>鲲语智慧水产系统</title>
<link rel="stylesheet" href="${farm}/styles/xcConfirm.css" type="text/css" />
<script type="text/javascript" src="${farm}/scripts/jquery.min.js"></script>
<script type="text/javascript" src="${farm}/scripts/xcConfirm.js"></script>
<script type="text/javascript" src="${farm}/js/myUtils.js"></script>
<script type="text/javascript" src="${farm}/js/login.js"></script>
<style>
body{ margin:0; padding:0; color:#666; background:#008272;}
.form_login{ max-width:640px; margin:auto; text-align:center; padding-top:100px;}
.form-group{ width:355px; margin:0 auto; height:50px; margin-bottom:20px;}
.form-group .fa{ display:block; width:50px; height:50px; float:left;}
.form-group .form-control{ display:block; width:300px; height:48px; float:left; border:1px solid #ccc; padding:0; margin-left:0; text-indent:1em; themeColor: #00a988;}
.form-group .form-control:hover{ border:1px solid #0160A0;}
.form-group .checkfont{ color:#666;}
.form-group .btn{ width:350px; height:50px; background-color:#F90; border:0px; color:#fff; font-size:14px;}
.fa-user{ background:url(${farm}/images/user.png) no-repeat center;}
.fa-key{ background:url(${farm}/images/pw.png) no-repeat center;}
.form_footer{ margin-top:100px; font-size:12px; color:#FFFFFF;}
.btn-orange {
	background-color:#F90;
    border-color: #F60;
}
</style>
</head>

<body>
<input type="hidden" value="wxAccount" id="wxAccount"/>
<div class="form_login">
<div class="form_logo"><img src="${farm}/images/logo.png" /></div>
<form method="post" role="form" id="form_login">

    <div class="form-group">
    	<i class="fa fa-user"></i>
		<input type="text" class="form-control" name="userId" id="userId" placeholder="鲲语帐号" autocomplete="off">
    </div>

    <div class="form-group">
		<i class="fa fa-key"></i>
        <input type="password" class="form-control" name="password" id="password" placeholder="密码" autocomplete="off">
    </div>
   
    <div class="form-group">
        <button type="button" class="btn btn-primary btn-block btn-login" onclick="bindwx()">绑定</button>
    </div>

</form>
<div class="form_footer">@2018 鲲语物联 粤ICP备16111138号-1</div>
</div>
</body>
</html>
