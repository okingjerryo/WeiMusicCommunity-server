<%@page language="java" contentType="text/html; UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html class="signin no-js" lang="">

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
                    petok: "8f7b41b79fe810b6951f62425931df1cfa3f78a5-1419101773-1800",
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
                        "ape": "2cb0bbaf515762861a9ada12b07fbc38"
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


    <link rel="stylesheet" href="../assat/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assat/css/font-awesome.min.css">
    <link rel="stylesheet" href="../assat/css/animate.min.css">


    <link rel="stylesheet" href="../assat/css/skins/palette.1.css" id="skin">
    <link rel="stylesheet" href="../assat/css/fonts/style.1.css" id="font">
    <link rel="stylesheet" href="../assat/css/main.css">


    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script src="vendor/modernizr.js"></script>
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


        // 使用 jQuery 异步跨域提交表单
        $('#login').submit(function () {
            alert("试试看11");
            $.getJSON("ta.aspx " + $('#login').serialize() + "&jsoncallback= ",
                function (data) {
                    alert(data);
                });
            return false;
        });


        function onSubmit() {

            var finalRes = $('#login').serializeArray().reduce(function (result, item) {
                result[item.name] = item.value;
                return result;
            }, {})
        }

    </script>
</head>

<body class="bg-color center-wrapper">
<div class="center-content">
    <div class="row">
        <div class="col-xs-10 col-xs-offset-1 col-sm-6 col-sm-offset-3 col-md-4 col-md-offset-4">
            <section class="panel panel-default">
                <header class="panel-heading">登录</header>
                <div class="bg-white user pd-md">
                    <h6><strong>欢迎.</strong>登录音乐微协作</h6>
                    <form role="form" id="login" action="http://localhost:8080/api/loginPage" method="POST">
                        <input type="text" name="UAName" class="form-control mg-b-sm" placeholder="Username" autofocus>
                        <input type="password" name="UPwd" class="form-control" placeholder="Password">
                        <label class="checkbox pull-left">
                            <input type="checkbox" value="remember-me">记住密码
                        </label>
                        <div class="text-right mg-b-sm mg-t-sm">
                            <a href="javascript:">忘记密码?</a>
                        </div>
                        <button class="btn btn-info btn-block" type="submit">登 录</button>
                    </form>
                    <p class="center-block mg-t mg-b text-right">还没有账号?
                        <a href="signup.html">注册一个吧</a>
                    </p>
                </div>
            </section>
        </div>
    </div>
</div>
</body>

</html>