<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>重置密码通知</title>
</head>
<body>
<h3>尊敬的  ${loginName}, 您好!</h3>

<br>感谢您智领在线开发云, 请您尽快点击下面的密码重置链接, 重置密码。
<br>
<#--<a href="#" th:href="@{ ${url} }" target="_blank">${url}</a>-->
<a href="${resetPwdUrl}" target="_blank">${resetPwdUrl}</a>
<br>24小时后，链接将会自动失效.
<br>如果无法点击该链接, 可以将链接复制并粘帖到浏览器的地址输入框, 然后单击回车即可.
<br>如果您不需要重置密码了, 请忽略这封邮件。
<br>该邮件为系统自动发出, 请勿回复！
<br>谢谢合作！

<br>tc.com
<br>${dateTime}
<br>Sep 12, 2018 22:05:58 PM
</body>
</html>