//javascript.js
$(function(){

    /* progress slide */
    if($.fn.slider){
        $( "#slider-range-min" ).slider({
                    range: "min",
                    value: 35,
                    min: 0,
                    max: 100,
                    slide: function( event, ui ) {
                        $("#amount").html( ui.value + "%" );
                        var len = $("#amount").width();
                        $("#amount").css({"left":$(".ui-slider-range").width()-len/2});
                    },
                    change:function(event,ui){
                        $("#amount").html( ui.value + "%" );
                        var len = $("#amount").width();
                        $("#amount").css({"left":$(".ui-slider-range").width()-len/2});
                    }
        });
    }

    /* create amount*/
    if($(".ui-slider-range").length>0){
        $(".ui-slider-range").after('<div id="amount"></div>');;
        $("#amount").html( $( "#slider-range-min" ).slider( "value" ) + "%");
        $("#amount").css({"position":"absolute","z-index":99999,top:25,"left":$(".ui-slider").width()/2.9-10});
    }
    /* bind tabs */
    $("ul.sub-tab").find("li a").each(function(index){
        $(this).click(function(){
            $("ul.sub-tab").find("li").removeClass("current");
            $(this).parent("li").addClass("current");
            $("div.main-content").find("div.contents").hide();
            $("div.main-content").find("div.contents").eq(index).show();
        });
    });
    /* default tab*/
    $("ul.sub-tab").find("li a").eq(0).trigger("click");
    /* table line toggle color */
    $("table.table-data").find("tr").each(function(index){
        if(index>0){
            if(index%2==0){$(this).addClass("white");}else{$(this).addClass("gray");}
        }
    });
    /* list detail toggle */
    $("table.data-container").find("tr").each(function(index){
        $(this).find("td").eq(1).find("span a").bind('click',function(){
            var table = $(this).parent("span").parent("td").parent("tr").parent().parent().parent();
            var tablecls = table.attr("class");
            if(tablecls.indexOf("correlation-data")>-1){
                $("div.contents").eq(3).find(".sub-tab-content").hide();
                var searchbox='<div class="refine-search-box"><div class="positions"><a href="advance-search.html" class="search-btn"><span class="left-button"><span class="right-button"><span class="itext">Refine<em></em>Search</span></span></span></a></div></div>';
                $("div.contents").eq(3).prepend(searchbox);
            }else{
                $("div.contents").eq(3).find(".sub-tab-content").show();
                $("div.contents").eq(3).find(".refine-search-box").remove();
            }
            var contents=$(this).parent("span").parent("td").parent("tr").parent().parent().parent().parent().parent().parent().parent().parent().parent();
            if(contents.length>0){
                contents.hide();
                $("div.contents").eq(3).show();
                var handle = $("div.contents").eq(3).find("ul.data-bar").find("li").last().find("span a");
                handle.unbind().bind('click',function(){
                    $("div.contents").eq(3).hide();
                    contents.show();
                    return false;
                });
            }
            return false;
        });
    });

    /* text shadow */

    $(".add-more-btn .right-button .itext").css({"text-shadow":"0px -1px 0px #587002"});
    $(".filter-btn .right-button .itext").css({"text-shadow":"0px -1px 0px #AFBD00"});
    $(".tab li a .right-button .itext").css({"text-shadow":"0px -1px 0px #307394"});
    $(".tab li.current a .right-button .itext").css({"text-shadow":"0px -1px 0px #585858"});
    $(".main-content div.contents a.search-btn .right-button .itext").css({"text-shadow":"0px -1px 0px #A0AC00"});
    $(".search-btn-box a.search-link .right-button .itext").css({"text-shadow":"0px -1px 0px #708C13"});


    /* new window */
    $(".filter-btn").click(function(){
        NewWindow("filter-pop-window.html#other=" + encodeURI(" ((Wireframe Notes:))  Existing table is successfully filtered "), "", "directories=0, height=300, location=0, menubar=0, resizable=1, scrollbars=1, status=1, toolbar=0, width=300", true, 300, 300);
    });
    /* new window */
    $(".pop-window").click(function(){
        NewWindow("pop-window.html#other=" + encodeURI(" Will Open/Download the Document "), "", "directories=0, height=300, location=0, menubar=0, resizable=1, scrollbars=1, status=1, toolbar=0, width=300", true, 300, 300);
    });
    /* add-more-btn bind click event to add more search input */
    if($(".add-more-btn").length>0){
        $(".add-more-btn").bind("click",function(){
            addMore();
        });
    }
/*
    $("input.datepicker").datePicker({startDate:'01/01/2001'});
    //$("input.datepicker").eq(0).trigger("click");
    $("input#fromDate").keyup(function(){
        if($(this).val()=="")$(this).val("From:");
    });
    $("input#toDate").keyup(function(){
        if($(this).val()=="")$(this).val("To:");
    });
    */
});

/* add more search input*/
function addMore(){
/*
    var table = $(".add-more-btn").parent("td").parent("tr").parent().parent("table");
    var html = table.find("tr").eq(0).html();
    var last = table.find("tr").last();
    if(last.attr("class")=="gray"){
        cls = "white";
    }else{
        cls = "gray";
    }
    last.find("td").last().html("&nbsp;");
    table.append('<tr class="'+cls+'"><td><input name="" type="checkbox" value="" /></td><td><select name="" class="parameter"><option selected="selected" value="Target">Target</option><option value="Target Type">Target Type</option><option value="Missons">Missons</option><option value="Instruments">Instruments</option><option value="Instrument Type">Instrument Type</option><option value="Start Date">Start Date</option><option value="End Date">End   Date</option><option value="Data Set Name">Data Set Name</option><option value="Data Set ID">Data Set ID</option><option value="Data Type">Data Type</option><option value="Instrument Host">Instrument Host</option><option value="Instrument Host Type">Instrument Host Type</option></select>                          </td><td><input name="" type="text" class="blue-reset values" /></td><td><select name="" class="condition"><option selected="selected" value="and ">and</option><option value="or">or</option></select>                          </td><td class="right-bg-none"><a href="javascript:;" class="add-more-btn right-spacing"><span class="left-button"><span class="right-button"><span class="itext">Add more</span></span></span></a></td></tr>');
    $(".add-more-btn").bind("click",function(){
            addMore();
    });
*/
}

/* create a new window*/
function NewWindow(hyperlink, name, features, center, width, height)
{
    if(center)
    {
        var winl = (screen.width - width) / 2;
        var wint = (screen.height - height) / 2;
        features = features + ', left=' + winl + ', top=' + wint;
    }
    window.open(hyperlink, name, features);
}