/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function (config) {
    config.extraPlugins += 'lineheight, upload';

    config.font_names +='宋体/宋体;黑体/黑体;仿宋/仿宋;仿宋_GB2312/仿宋_GB2312;楷体/楷体;楷体_GB2312/楷体_GB2312;隶书/隶书;幼圆/幼圆;微软雅黑/微软雅黑;';
    // 设置宽高
    config.height = 300;
    //改变大小的最小高度
    config.resize_minHeight = 250;
    //改变大小的最小宽度
    config.resize_minWidth = 750;
    // 背景颜色
    config.uiColor = '#EEEEEE';
    // 工具栏
    config.toolbar = [
        { name: 'document', items: [ 'Source'] },
        { name: 'tools', items: [ 'Maximize'] },
        { name: 'clipboard', items: ['Undo', 'Redo' ] },
        { name: 'basicstyles', items: ['CopyFormatting', 'RemoveFormat', '-', 'Bold', 'Italic', 'Underline', 'Strike'] },
        { name: 'styles', items: ['Font', 'FontSize','lineheight'] },
        { name: 'colors', items: [ 'TextColor', 'BGColor' ] },
        { name: 'paragraph', items: [ 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'] },
        { name: 'links', items: [ 'Link', 'Unlink', 'Anchor' ] },
        { name: 'insert', items: [ 'upload', 'Table', 'PageBreak'] }
    ];
    CKEDITOR.config.allowedContent = true;
};
