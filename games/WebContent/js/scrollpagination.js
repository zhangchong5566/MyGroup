(function($) {

	$.fn.scrollPagination = function(options) {

		var opts = $.extend($.fn.scrollPagination.defaults, options);
		var target = opts.scrollTarget;
		if (target == null) {
			target = obj;
		}
		opts.scrollTarget = target;

		return this.each(function() {
			$.fn.scrollPagination.init($(this), opts);
		});

	};

	$.fn.stopScrollPagination = function() {
		return this.each(function() {
			$(this).attr('scrollPagination', 'disabled');
		});

	};
	var loading = false;
	$.fn.scrollPagination.loadContent = function(obj, opts) {
		if(loading)return;//here
		var target = opts.scrollTarget;
		var mayLoadContent = $(target).scrollTop() + opts.heightOffset >= $(
				document).height()
				- $(target).height();
		if (mayLoadContent&& opts.lock) {
			if (opts.beforeLoad != null) {
				opts.beforeLoad();
			}
			opts.lock = false;
			$(obj).children().attr('rel', 'loaded');
			$.ajax({
				type : 'POST',
				url : opts.contentPage,
				data : opts.contentData,
				success : function(data) {
					opts.lock = true;
					opts.contentData.currentPage=opts.contentData.currentPage+1;
					$(obj).append(data);
					var objectsRendered = $(obj).children('[rel!=loaded]');

					if (opts.afterLoad != null) {
						opts.afterLoad(objectsRendered,data);
					}
				},
				dataType : 'html'
			});
		}

	};

	$.fn.scrollPagination.init = function(obj, opts) {
		var target = opts.scrollTarget;
		$(obj).attr('scrollPagination', 'enabled');

		$(target).scroll(function(event) {
			if ($(obj).attr('scrollPagination') == 'enabled') {
				$.fn.scrollPagination.loadContent(obj, opts);
			} else {
				event.stopPropagation();
			}
		});

		$.fn.scrollPagination.loadContent(obj, opts);

	};

	$.fn.scrollPagination.defaults = {
		'contentPage' : null,
		'contentData' : {},
		'beforeLoad' : null,
		'afterLoad' : null,
		'scrollTarget' : null,
		'heightOffset' : 0,
		'lock':true
	};
})(jQuery);