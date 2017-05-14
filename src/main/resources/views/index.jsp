<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE>
<html class="no-js" lang="">

<head>

    <meta charset="utf-8">
    <meta name="description" content="Flat, Clean, Responsive, admin template built with bootstrap 3">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1">

    <title>Cameo | Responsive Admin Dashboard</title>

    <script type="text/javascript">
        //<![CDATA[
        try {
            if (!window.CloudFlare) {
                var CloudFlare = [{
                    verbose: 0,
                    p: 0,
                    byc: 0,
                    owlid: "cf",
                    bag2: 1,
                    mirage2: 0,
                    oracle: 0,
                    paths: {
                        cloudflare: "/cdn-cgi/nexp/dok2v=1613a3a185/"
                    },
                    atok: "1668c19642567e08c574f5d9458345a2",
                    petok: "3285f1db20753df31d6fcdae6015a30e5a60a785-1419101748-1800",
                    zone: "nyasha.me",
                    rocket: "0",
                    apps: {
                        "ga_key": {
                            "ua": "UA-50530436-1",
                            "ga_bs": "2"
                        }
                    }
                }];
                CloudFlare.push({
                    "apps": {
                        "ape": "86d42e0b8c0027bb178361fce707fc15"
                    }
                });
                !function (a, b) {
                    a = document.createElement("script"), b = document.getElementsByTagName("script")[0], a.async = !0, a.src = "//ajax.cloudflare.com/cdn-cgi/nexp/dok2v=919620257c/cloudflare.min.js", b.parentNode.insertBefore(a, b)
                }()
            }
        } catch (e) {
        }
        //]]>
    </script>
    <link rel="stylesheet" href="../assat/vendor/offline/theme.css">
    <link rel="stylesheet" href="../assat/vendor/pace/theme.css">
    <link rel="stylesheet" href="../assat/vendor/jvectormap/jquery-jvectormap-1.2.2.css">
    <link rel="stylesheet" href="../assat/vendor/bxslider/jquery.bxslider.css">


    <link rel="stylesheet" href="../assat/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assat/css/font-awesome.min.css">
    <link rel="stylesheet" href="../assat/css/animate.min.css">

    <link rel="stylesheet" href="../assat/css/panel.css">

    <link rel="stylesheet" href="../assat/css/skins/palette.1.css" id="skin">
    <link rel="stylesheet" href="../assat/css/fonts/style.1.css" id="font">
    <link rel="stylesheet" href="../assat/css/main.css">


    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="../assat/vendor/modernizr.js"></script>
    <script type="text/javascript">
        /* <![CDATA[ */
        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-50530436-1']);
        _gaq.push(['_trackPageview']);

        (function () {
            var ga = document.createElement('script');
            ga.type = 'text/javascript';
            ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0];
            s.parentNode.insertBefore(ga, s);
        })();

        (function (b) {
            (function (a) {
                "__CF" in b && "DJS" in b.__CF ? b.__CF.DJS.push(a) : "addEventListener" in b ? b.addEventListener("load", a, !1) : b.attachEvent("onload", a)
            })(function () {
                "FB" in b && "Event" in FB && "subscribe" in FB.Event && (FB.Event.subscribe("edge.create", function (a) {
                    _gaq.push(["_trackSocial", "facebook", "like", a])
                }), FB.Event.subscribe("edge.remove", function (a) {
                    _gaq.push(["_trackSocial", "facebook", "unlike", a])
                }), FB.Event.subscribe("message.send", function (a) {
                    _gaq.push(["_trackSocial", "facebook", "send", a])
                }));
                "twttr" in b && "events" in twttr && "bind" in twttr.events && twttr.events.bind("tweet", function (a) {
                    if (a) {
                        var b;
                        if (a.target && a.target.nodeName == "IFRAME") a: {
                            if (a = a.target.src) {
                                a = a.split("#")[0].match(/[^?=&]+=([^&]*)?/g);
                                b = 0;
                                for (var c; c = a[b]; ++b)
                                    if (c.indexOf("url") === 0) {
                                        b = unescape(c.split("=")[1]);
                                        break a
                                    }
                            }
                            b = void 0
                        }
                        _gaq.push(["_trackSocial", "twitter", "tweet", b])
                    }
                })
            })
        })(window);
        /* ]]> */
    </script>
</head>

<body>
<div class="app" data-sidebar="locked">
    <header class="header header-fixed navbar">
        <div class="brand">
            <a href="javascript:" class="fa fa-bars off-left visible-xs" data-toggle="off-canvas" data-move="ltr"></a>
            <a href="index.html" class="navbar-brand">
                <i class="fa fa-stop mg-r-sm"></i>
                <span class="heading-font">
                    音乐微协作<b>后台管理</b>
                    </span>
            </a>
        </div>
        <li class="quickmenu" style="float: right">
            <a href="javascript:" data-toggle="dropdown">
                <img src="../assat/img/avatar.jpg" class="avatar pull-left img-circle" alt="user" title="user">
                <i class="caret mg-l-xs hidden-xs no-margin"></i>
            </a>
            <ul class="dropdown-menu dropdown-menu-right mg-r-xs">
                <li>
                    <a href="javascript:">
                        <div class="pd-t-sm">
                            gerald@morris.com
                            <br>
                            <small class="text-muted">4.2 MB of 51.25 GB used</small>
                        </div>
                        <div class="progress progress-xs no-radius no-margin mg-b-sm">
                            <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="40"
                                 aria-valuemin="0" aria-valuemax="100" style="width: 40%">
                            </div>
                        </div>
                    </a>
                </li>

                <li class="divider"></li>
                <li>
                    <a href="signin.html">Logout</a>
                </li>
            </ul>
        </li>
    </header>


    <section class="layout">
        <aside class="sidebar canvas-left">
            <nav class="main-navigation" style="bottom:50px">
                <ul>
                    <li class="active">
                        <a href="index.html" data-toggle="dropdown">
                            <i class="fa fa-coffee"></i>
                            <span>图片上传</span>
                        </a>
                    </li>
                    <li class="dropdown show-on-hover">
                        <a href="#" data-toggle="dropdown">
                            <i class="fa fa-ellipsis"></i>
                            <span>文件上传</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="buttons.html">
                                    <span>Buttons</span>
                                </a>
                            </li>
                            <li>
                                <a href="forms.html">
                                    <span>Forms</span>
                                </a>
                            </li>
                            <li>
                                <a href="tables.html">
                                    <span>Tables</span>
                                </a>
                            </li>
                            <li>
                                <a href="calendar.html">
                                    <span>Calendar</span>
                                </a>
                            </li>
                            <li>
                                <a href="components.html">
                                    <span>Components</span>
                                </a>
                            </li>
                            <li>
                                <a href="sortable.html">
                                    <span>Sortable</span>
                                </a>
                            </li>
                            <li>
                                <a href="chart.html">
                                    <span>Charts</span>
                                </a>
                            </li>
                            <li>
                                <a href="editor.html">
                                    <span>Editor</span>
                                </a>
                            </li>

                        </ul>
                    </li>
                    <li class="dropdown show-on-hover">
                        <a href="#" data-toggle="dropdown">
                            <i class="fa fa-ellipsis"></i>
                            <span>文件管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="buttons.html">
                                    <span>Buttons</span>
                                </a>
                            </li>
                            <li>
                                <a href="forms.html">
                                    <span>Forms</span>
                                </a>
                            </li>
                            <li>
                                <a href="tables.html">
                                    <span>Tables</span>
                                </a>
                            </li>
                            <li>
                                <a href="calendar.html">
                                    <span>Calendar</span>
                                </a>
                            </li>
                            <li>
                                <a href="components.html">
                                    <span>Components</span>
                                </a>
                            </li>
                            <li>
                                <a href="sortable.html">
                                    <span>Sortable</span>
                                </a>
                            </li>
                            <li>
                                <a href="chart.html">
                                    <span>Charts</span>
                                </a>
                            </li>
                            <li>
                                <a href="editor.html">
                                    <span>Editor</span>
                                </a>
                            </li>

                        </ul>
                    </li>
                </ul>
            </nav>
        </aside>
        <aside class="chat-sidebar canvas-right pull-right">

        </aside>
        <section class="main-conten">
            <div class="site-overlay"></div>

            <div class="show">
                <c:forEach items="${list}" var="item">
                    ${item.UAName}<br>
                </c:forEach>
            </div>

        </section>
    </section>
</div>


<script src="../assat/vendor/jquery-1.11.1.min.js"></script>
<script src="../assat/bootstrap/js/bootstrap.js"></script>
<script src="../assat/vendor/jquery.easing.min.js"></script>
<script src="../assat/vendor/jquery.placeholder.js"></script>
<script src="../assat/vendor/fastclick.js"></script>
<script src="../assat/vendor/moment.js"></script>
<script src="../assat/vendor/skycons.js"></script>
<script src="../assat/vendor/jquery.blockUI.js"></script>
<script src="../assat/vendor/raphael.min.js"></script>
<script src="../assat/vendor/morris/morris.js"></script>
<script src="../assat/vendor/switchery/switchery.js"></script>
<script src="../assat/vendor/jquery.slimscroll.js"></script>
<script src="../assat/vendor/bxslider/jquery.bxslider.min.js"></script>
<script src="../assat/vendor/offline/offline.min.js"></script>
<script src="../assat/vendor/pace/pace.min.js"></script>


<script src="../assat/js/off-canvas.js"></script>
<script src="../assat/js/main.js"></script>

<script src="../../assat/js/panel.js"></script>
<script src="../../assat/js/dashboard.js"></script>
</body>

</html>