var casesJson = (paramCasesJson)? paramCasesJson: "cases.json";

var app = {
	init : function(){
		app.jsonData();
		app.chartOnFocus();
		$("#footer").fadeIn(400);
		app.jsonCaseStudyData();
	},
	config : {
		caseExist : false,
		caseCount : 0,
		caseRequested : 0
	},
	jsonData : function(){
		//Load JSON
		$.getJSON(casesJson, function(data) {

			app.config.caseCount = data.cases.length;
			app.getParam('cs');
			
			$('h2.client').text(data.cases[app.config.caseRequested].brand);
			$('h3.job-description').text(data.cases[app.config.caseRequested].description);

			$('#input .content-paragraph').text(data.cases[app.config.caseRequested].inputText)

			for (var a=0; a<data.cases[app.config.caseRequested].challengeText.length; a++) {
				$('#challenge .content-paragraphs').append('<p class="content-paragraph">'+data.cases[app.config.caseRequested].challengeText[a].text+'</p>');
			};

			for (var i=0; i<data.cases[app.config.caseRequested].whatWeDoText.length; i++) {
				$('#context .content-paragraphs').append('<p class="content-paragraph">'+data.cases[app.config.caseRequested].whatWeDoText[i].text+'</p>');
			};

			for (var c=0; c<data.cases[app.config.caseRequested].whatWeDoList.length; c++) {
				$('#context ul').append('<li>'+data.cases[app.config.caseRequested].whatWeDoList[c].text+'</li>');
			};

			for (var d=0; d<data.cases[app.config.caseRequested].results.length; d++) {
				$('.swiper-wrapper').append('<div class="swiper-slide"><div class=""><img class="job" src="'+data.cases[app.config.caseRequested].results[d].url+'"/></div></div>');
			};

			$('#execution-time .q-hours').text(data.cases[app.config.caseRequested].excecutionTime);

			for (var d=0; d<data.cases[app.config.caseRequested].charts.length; d++) {
				$('#charts').append('<div class="skill-charts"><span class="chart-b" data-percent="'+data.cases[app.config.caseRequested].charts[d].porcentage+'"><span class="percent"></span></span><span class="skill-used-name">'+data.cases[app.config.caseRequested].charts[d].skill+'</span></div>');
			};

			for (var d=0; d<data.cases[app.config.caseRequested].keytools.length; d++) {
				$('.skillsused ul').append('<li>'+data.cases[app.config.caseRequested].keytools[d].name+'</li>');
			};

			$('.case-study').fadeIn(400);

			$('#cases').delay(1500).fadeIn();

			var mySwiper = new Swiper('.swiper-container',{
				pagination: '.pagination',
				paginationClickable: true,
				loop: true,
				calculateHeight: true
			});

			$('.arrow-left').on('click', function(e){
				e.preventDefault();
				mySwiper.swipePrev();
			})
			$('.arrow-right').on('click', function(e){
				e.preventDefault();
				mySwiper.swipeNext();
			})
		});
	},
	charts : function(){
  		$('.chart-b').easyPieChart({
			barColor: '#8BC53F',
			trackColor: false,
			scaleColor: false,
			lineWidth: 5,
			lineCap: 'square',
			size: 60,
			onStep: function(from, to, percent) {
				$(this.el).find('.percent').text(Math.round(percent));
			}
		});
	},
	chartOnFocus : function(){
    	$('#charts').on('scrollin', function ( e, ui) {
    		app.charts();
        }).scrollable();

		setTimeout(function(){	
	    	if(app.onViewport($('#charts')) === true){
	    		app.charts();
	    	}
    	}, 100);
	},
	onViewport : function(el){
		//special bonus for those using jQuery
	    if (el instanceof jQuery) {
	        el = el[0];
	    }
	    var rect = el.getBoundingClientRect();
	    return (
	        rect.top >= 0 &&
	        rect.left >= 0 &&
	        rect.bottom <= (window.innerHeight || document.documentElement.clientHeight) && /*or $(window).height() */
	        rect.right <= (window.innerWidth || document.documentElement.clientWidth) /*or $(window).width() */
	    );
	},
	getParam : function(name){
		var value =  decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search)||[,""])[1].replace(/\+/g, '%20'))||null;
		
		var parsedValue = parseInt(value);
		if(!isNaN(parsedValue) && parsedValue <= app.config.caseCount && parsedValue > 0){
			app.config.caseRequested = parsedValue-1;
		}else{
			app.config.caseRequested = 0;
		}
	},
	jsonCaseStudyData : function(){
		//Load JSON
		$.getJSON("cases.json", function(data) {
			var isRetina = (('devicePixelRatio' in window && devicePixelRatio > 1) || matchMedia('-webkit-min-device-pixel-ratio: 1.5').matches || matchMedia('min-device-pixel-ratio: 1.5').matches);

			for (var a=0; a<data.cases.length; a++) {
				if ('matchMedia' in window) {				
					if (isRetina) {
						$('#cases').append('<a class="case-study-box" href="cases.html?cs='+(a+1)+'"><img width="100%" src="images/cases-splash/retina/'+data.cases[a].image+'"/></a></p>');
					}else{
						$('#cases').append('<a class="case-study-box" href="cases.html?cs='+(a+1)+'"><img src="images/cases-splash/'+data.cases[a].image+'"/></a></p>');
					}
				}
			};
		});
	}
};

$(document).ready(function(){
	app.init();
});