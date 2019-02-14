$(function ($) {
    'use strict';
    var revapi = $('.tp-banner').revolution(
        {
            delay: 5000,
            startwidth: 1140,
            startheight: 600,
            hideThumbs: 10,
            fullWidth: "on",
            forceFullWidth: "on",
            navigationType: "none" // bullet, thumb, none
        });

    $('.numbers-counter').waypoint(function () {
            $('.numbers').data('countToOptions', {
                formatter: function (value, options) {
                    return value.toFixed(options.decimals).replace(/\B(?=(?:\d{3})+(?!\d))/g, ',');
                }
            });
            $('.timer').each(count);

            function count(options) {
                var $this = $(this);
                options = $.extend({}, options || {}, $this.data('countToOptions') || {});
                $this.countTo(options);
            }
        },
        {
            offset: '70%',
            triggerOnce: true
        }
    );

});