<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hwh
  Date: 2020/12/3
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<head>
    <title>日历</title>
    <meta charset="UTF-8">
    <meta name="viewpoint" content="width=device-width,initial-scale=1.0">
    <title>Event Calendar</title>
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fullcalendar@5.4.0/main.css">
    <script src="https://cdn.jsdelivr.net/npm/fullcalendar@5.4.0/main.js"></script>
</head>

<body>
<<<<<<< HEAD
<div class="container-fluid">
    <div class="row-fluid">
        <div class="col-md-2 column">
            <div class="page-header">
                <img src="${pageContext.request.contextPath}/img/logo.png" class="img-responsive" alt="加载失败">
            </div>
            <ul class="nav nav-list">
                <li class="nav-header">
                    <h4>
                        页面导航
                    </h4>
                </li>
                <li class="active">
                    <a href="${pageContext.request.contextPath}"><span class="glyphicon glyphicon-home"
                                                                       aria-hidden="true"></span>首页</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/team/myTeam/${userTel}"><span
                            class="glyphicon glyphicon-grain" aria-hidden="true"></span> 我的团队</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/task/myTask/${userId}"><span
                            class="glyphicon glyphicon-th-list" aria-hidden="true"></span> 我的任务</a>
                </li>
                <li>
                    <a href="#"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span> 日历</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/log/mylog/${userId}"><span
                            class="glyphicon glyphicon-file" aria-hidden="true"></span> 日志</a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/post/AllPost/1">
                        <span class="glyphicon glyphicon-fire" aria-hidden="true"></span>
                        论坛
                    </a>
                </li>
                <li class="divider">
                </li>
                <li>
                    <a href="#"><span class="glyphicon glyphicon-question-sign" aria-hidden="true"></span> 帮助</a>
                </li>
            </ul>
        </div>
        <div class="col-md-10 column">
            <div class="page-header">
                <h1>todoList</h1>
            </div>
            <div class="todo-grid-parent">
                <div>
                    <div class="todo-input todo-block">
                        <span>代办:</span>
                        <input type="text" placeholder="输入待办事项">
                        <span>类别:</span>
                        <input type="text" placeholder="输入类别" list="categoryList">
                        <datalist id="categoryList">
                            <option value="个人"></option>
                            <option value="工作"></option>
                        </datalist>
                        <span>日期</span>
                        <input type="date" id="dateInput">
                        <span>时间</span>
                        <input type="time" id="timeInput">
                        <span></span>
                        <button id="addBtn">添加</button>
                        <span></span>
                        <button id="sortBtn">按日期排序</button>
                        <span></span>
                        <label><input type="checkbox" id="shortlistBtn">优先显示未完成</label>
                    </div>

                    <table id="todoTable" class="todo-block">
                        <tr>
                            <td>✔</td>
                            <td>日期</td>
                            <td>时间</td>
                            <td>待办事项</td>
                            <td>
                                <select id="categoryFilter">
                                </select>
                            </td>
                            <td>编辑</td>
                            <td>删除</td>
                        </tr>
                    </table>
                </div>

                <div class="todo-calendar todo-block">
                    <div id='calendar'></div>
                </div>
            </div><!--class="todo-grid-parent"-->
        </div>
    </div>
</div>


<div class="todo-overlay" id="todo-overlay">
    <div class="todo-modal" id="todo-modal">
        <div class="todo-input todo-block">
            <span>代办:</span>
            <input type="text" placeholder="输入待办事项" id="todo-edit-todo">
            <span>类别:</span>
            <input type="text" placeholder="输入类别" list="categoryList" id="todo-edit-category">
            <datalist id="todo-edit-categoryList">
                <option value="个人"></option>
                <option value="工作"></option>
            </datalist>
            <span>日期</span>
            <input type="date" id="todo-edit-date">
            <span>时间</span>
            <input type="time" id="todo-edit-time">
            <span></span>
            <button id="changeBtn">保存修改</button>
        </div>
    </div>
    <div class="todo-modal-close-btn" id="todo-modal-close-btn">X</div>
</div>

</body>
<script src="${pageContext.request.contextPath}/js/calendar.js"></script>
</html>

<style>
    .strike {
        text-decoration: line-through;
    }

    td {
        border: 1px solid black;
        padding: 1px;
    }

    .todo-input {
        display: grid;
        grid-template-columns: 1fr 1fr;
        row-gap: 1rem;
    }

    #todoTable {

        margin-top: 1rem;
    }

    .material-icons {
        cursor: pointer;
    }

    ::-ms-input-placeholder {
        color: #ed6a5e;
    }

    ::-webkit-input-placeholder {
        color: #ed6a5e;
    }

    ::placeholder {
        color: #ed6a5e;
    }

    input {
        color: #a11e12;
    }

    h3 {
        color: #a11e12;
    }

    body {

    }

    .todo-block {
        border: 1px solid #ff8e72;
        padding: 1rem;
        border-radius: 20px;
        color: #a11e12;
    }

    @media screen and (max-width: 767px) {
        /*for mobile or small screen device*/
        #todoTable, .todo-calendar {
            margin-top: 1rem;
        }
    }

    @media screen and (min-width: 768px) {
        /*for tablets or laptops or desktops*/
        .todo-grid-parent {
            display: grid;
            grid-template-columns: 2fr 3fr;
            column-gap: 1rem;
        }

        #calendar {
            position: sticky;
            top: 0;
        }
    }

    .todo-overlay {
        width: 100vw;
        height: 100vh;
        background-color: #ddd;
        position: fixed;
        top: 0;
        left: 0;
        opacity: 0.9;
        display: flex;
        justify-content: center;
        align-items: center;
        transform: translateX(-100vw);
        transition: transform 250ms;
        z-index: 2;
    }

    .todo-modal {
        width: 50vw;
        height: 50vh;
        background-color: #ffd6cc;
    }

    .todo-modal-close-btn {
        background-color: red;
        position: absolute;
        top: 2rem;
        right: 2rem;
        width: 2rem;
        height: 2rem;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 50%;
        color: white;
        font-weight: bold;
    }

    .slidedIntoView {
        transform: translateX(0);
        transition: transform 650ms;
    }

    #todoTable tr:nth-child(even) {
        background-color: #ffd6cc;
    }

    button {
        background-color: #ffd6cc;
        color: #a11e12;
        border: 1px outset #ed6a5e;
    }

    button:active {
        border: 1px inset #ed6a5e;
    }

    button:hover {
        background-color: #ffad99;
    }

    .chevron {
        transform: translateY(0.4rem);
    }

    .itemsPerPage {
        text-align: right;
        margin-bottom: 0.5rem;
    }
</style>
