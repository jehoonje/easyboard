<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <%@ include file="../include/static-head.jsp" %>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 8px 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #f5f5f5;
        }

        a {
            text-decoration: none;
            color: #007bff;
        }

        a:hover {
            text-decoration: underline;
        }
        #wrap {
            width: 70%;
            
            }
        #wrap .main-title-wrapper {
            width: 100%;
        }
        #wrap .table-container {
            width: 100%;
            margin-left: 20%;
            
        }
    </style>
</head>

<body>
    <%@ include file="../include/header.jsp" %>

    <div id="wrap">
        <div class="main-title-wrapper">
            <h1 class="main-title">Discover</h1>
        </div>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>순위</th>
                        <th>아이디</th>
                        <th>회원 이름</th>
                        <th>이메일</th>
                    </tr>
                </thead>
                <tbody>
                    <c:if test="${users.size() > 0}">
                        <c:forEach var="user" items="${users}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${user.account}</td>
                                <td>
                                    <a href="/profile/${user.account}">${user.nickName}</a>
                                </td>
                                <td>${user.email}</td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
    <!-- end div.wrap -->

</body>

</html>