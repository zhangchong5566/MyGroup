; (function ($) {
    $.extend({
        tabspanel: function (arg) {
            var tabobj = $(arg.tabobj);//tab标签父容器的选择名称
            var panelobj = $(arg.panelobj);//div的父容器的选择名称
            var tabselclassname = arg.tabselclassname == undefined ? 'act' : arg.tabselclassname;//选中标签的样式
            var eventtype = arg.eventtype == undefined ? 'click' : arg.eventtype;//相应类型，鼠标划过选中还是点击选中 [click|mouseover]          
            var muiltpanel = arg.muiltpanel == undefined ? true : arg.muiltpanel;//多容器切换/容器内容加载
            var activefun = arg.activefun == undefined ? null : arg.activefun; //选中后的自定义处理函数，为null为 div切换显示
            var moreindex = arg.moreindex == undefined ? -1 : arg.moreindex;//更多链接所在位置索引
            var selindex = 0;
            tabobj.on(eventtype, "a", function () {
                if ($(this).parent().children().length == 1)
                    selindex = $(this).parents().index();
                else {
                    selindex = $(this).index();
                    if (moreindex == selindex)
                        return;
                    if (moreindex == 0)
                        selindex -= 1;
                }
                tabobj.find("a").each(function () {
                    $(this).removeClass(tabselclassname);                   
                });
                $(this).addClass(tabselclassname);
                if (muiltpanel) {
                    panelobj.children().each(function () {
                        if ($(this).index() == selindex) {
                            $(this).show();
                        }
                        else {
                            $(this).hide();
                        }
                    });
                }
                if (activefun != null) {
                    if (activefun != "undefined" && activefun != null) {
                        activefun(selindex);
                    }
                }
            });
        },
        imgscreenmove: function (arg) {
            var parentobj = $(arg.parentobj);//总容器选择名称         
            var leftobj = arg.leftobj == undefined ? parentobj.find("a:first") : parentobj.find(arg.leftobj);//左侧按钮选择名称
            var rightobj = arg.rightobj == undefined ? parentobj.find("a:last") : parentobj.find(arg.rightobj);//右侧按钮选择名称
            var spanelname = arg.spanelname == undefined ? "li" : arg.spanelname;//滚动单元名;
            var panelobj = arg.panelobj == undefined ? leftobj.next().children().eq(0) : parentobj.find(arg.panelobj);//图片容器的选择名称
            var snum = arg.snum;//单屏图片数量           
            var autotime = arg.autotime == undefined ? 0 : arg.autotime;//自动滚动时间 0为不滚动，大于0为滚动时间，单位秒
            var panelwidth = panelobj.parents().width();
            var zcount = 0;            
            var page_count = 0;
            var page = 1;
            var timerun;
            leftobj.click(function () {
                run(-1);
            });
            rightobj.click(function () {
                run(1);
            });
            if (arg.autotime > 0) {
                setauto();
                parentobj.hover(function () { clearInterval(timerun); }, function () { setauto() });
            };
            function setauto() {
                timerun = setInterval(function () { run(0); }, autotime * 1000);
            }
            function run(mark) {
                if (zcount == 0)
                    zcount = panelobj.find(spanelname).length;
                if (page_count == 0)
                    page_count = Math.ceil(zcount / snum);
                if (!panelobj.is(":animated")) {
                    if (mark >= 0) {
                        if (page == page_count) {
                            if (mark == 0) {
                                panelobj.css("left", "0px");
                                page = 1;
                            }
                            else
                                return;
                        } else {
                            panelobj.animate({ left: '-=' + panelwidth }, "slow");
                            page++;
                        }
                    }
                    else {
                        if (page == 1) {
                            return;
                        } else {
                            panelobj.animate({ left: '+=' + panelwidth }, "slow");
                            page--;
                        }
                    }
                }
            }
        }
    });
})(jQuery);

