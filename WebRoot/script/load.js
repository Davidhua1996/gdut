function mask(msg){
	$("<div class=\"datagrid-mask\"></div>")
		.css({display:"block",'z-index':9998,width:"100%",height:($(window).height()-5)})
		.appendTo("body");     
        $("<div class=\"datagrid-mask-msg\"></div>").html(msg)
        	.appendTo("body").css({display:"block",'z-index':9999,
 //                        left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2
                         left:($(document.body).outerWidth(true) - 190) / 2,top:($(window).height() - 45) / 2
              });    
};
function unmask(){
	$('.datagrid-mask-msg').remove();
    $('.datagrid-mask').remove();

}
