/**
 * Created by lihuiyang on 2017/9/5 0005.
 * table 手风琴折叠 （依赖jQuery.js）
 * 使用：
 *     <table class="tableAcc">
 *     <tbody>
 *     <tr class="table_level_01"> <td></td> <tr>
 *     <tr class="table_level_02"> <td></td> <tr>
 *     <tr class="table_level_03"> <td></td> <tr>
 *     <tbody>
 *     </table>
 *     说明：
 *     class='table_level_01'表示一级菜单
 *     class='table_level_02'表示二级菜单
 *     class='table_level_03'表示三级菜单
 *
 */

//隐藏子级

function nexthide(parLevelCLass,chidLevelClass){
    $('.tableAcc').find('.'+parLevelCLass).each(function () {
        var next = $(this).nextAll();

        for(var i = 0;i<next.length;i++){
            if($(next[i]).hasClass(chidLevelClass)){
                $(next[i]).hide();

                if($(next[i]).hasClass(parLevelCLass)){
                    return false;
                }
            }
        }
        $($(this).find('td')[0]).find('i').attr('class',' icon-plus-sign-alt');
    });
}
//点击显示隐藏子级的方法
function clickShowHideFun(that,parLevelCLass,chidLevelClass){
    var next = $(that).nextAll();
    var bgColor = $(that).find('td').css('backgroundColor');
    if(!$(that).hasClass('act')){//显示下级
        for(var i = 0;i<next.length;i++){
            if(!$(next[i]).hasClass(chidLevelClass)){
                return false;
            }else{
                var act_bgColor = $(next[i]).find('td').css('backgroundColor');
                $(that).find('td').css('backgroundColor',act_bgColor);
                if($(that).hasClass('table_level_01')){
                    $(that).find('td').css('backgroundColor','rgba(0, 0, 0, 0.05)');
                }
                if( $(next[i]).hasClass('table_level_02')){
                    $(next[i]).find('td').css('backgroundColor','rgba(0, 0, 0, 0.05)');
                }
                $(that).addClass('act');
                $($(that).find('td')[0]).find('i').attr('class','icon-minus-sign-alt');
                $(next[i]).show();
            }
        }
    }else{////隐藏下级
        for(var i = 0;i<next.length;i++){
            if($(next[i]).hasClass(chidLevelClass)){
                $(next[i]).hide();
                $($(that).find('td')[0]).find('i').attr('class','icon-plus-sign-alt');
                $(that).find('td').css('backgroundColor','transparent');
                if($(that).hasClass('table_level_02')){
                    $(that).find('td').css('backgroundColor','rgba(0, 0, 0, 0.05)');
                }
                if($(that).hasClass('table_level_01')){
                    nexthide(chidLevelClass,'table_level_03');
                }
                $(that).removeClass('act');
                if($(next[i]).hasClass(parLevelCLass)){
                    return false;
                }
            }
        }
    }
}



$(function () {
    //页面加载完 隐藏子级
    nexthide('table_level_02','table_level_03');
    nexthide('table_level_01','table_level_02');

    //点击显示隐藏子级
    $('.tableAcc').on('click','tr', function () {

        var cla = $(this).attr('class');
        //点击的是一级菜单
        if(cla.indexOf('table_level_01') !=-1){
            clickShowHideFun(this,'table_level_01','table_level_02');
        }
        //点击的是二级菜单
        if(cla.indexOf('table_level_02') !=-1){
            clickShowHideFun(this,'table_level_02','table_level_03');
        }

    });
});