<!DOCTYPE html>
<html>

<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">



<!-- Bootstrap -->
<link href="${request.getContextPath()}css/bootstrap.min.css"
	rel="stylesheet" media="screen">
<link href="${request.getContextPath()}css/font-awesome.css"
	rel="stylesheet" media="screen">
<link href="${request.getContextPath()}css/main.css" rel="stylesheet"
	media="screen">
</head>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="./dashboard"> Application -
				Computer Database </a>
		</div>
		<a href="?lang=en"><spring:message code="lang.english" /></a> | <a href="?lang=fr"><spring:message code="lang.french" /></a>
	</header>