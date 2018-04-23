/**
 * Created by Administrator on 2017/8/24 0024.
 * 依赖jQuery.js
 */
function checkBoxFun(check){
    var icon = $(check).parent().find('i').attr('class');
    check.checked ? (icon='icon-check-sign'):
        icon='icon-check-empty';
    $(check).parent().find('i').attr('class',icon);
}


function checkChidenFun(check){
    var a_btn =$(check).parent();
    if(check.checked){
        //选中所有子集
        $($(check).parents('li')[0]).find('input[type="checkbox"]').each(function () {
            this.checked = true;
            checkBoxFun(this);
        });
        //如果点击的是全部按钮
        if(a_btn.hasClass('checkAll')){
           $('.treeList').find('input[type="checkbox"]').each(function () {
               this.checked = true;
               checkBoxFun(this);
           });
        }
        //选中所有上级
        $(check).parents('li').each(function () {
            var a = $($(this).find('a')[0]);
            a.find('input[type="checkbox"]')[0].checked = true;
            checkBoxFun(a.find('input[type="checkbox"]')[0]);
        });
    }else{
        //如果点击的是全部按钮
        if(a_btn.hasClass('checkAll')){
            $('.treeList').find('input[type="checkbox"]').each(function () {
                this.checked = false;
                checkBoxFun(this);
            });
        }

        //取消所有子集
        $($(check).parents('li')[0]).find('input[type="checkbox"]').each(function () {
            this.checked = false;
            checkBoxFun(this);
        });

        //如果同级都没选中 则父级不选：
        $(check).parents('ul').each(function () {
            var parLi = $(this).parent();
            var chidUl = parLi.find('ul');
            if(! $(chidUl).find('input[type="checkbox"]').is(':checked') ){
                var checkthat = parLi.find('input[type="checkbox"]')[0];
                checkthat.checked = false;
                checkBoxFun(checkthat);
            }
        });
    }

}

$(function () {
   $('.treeList').on('change','input[type="checkbox"]',function () {
       checkChidenFun(this);
   });



});