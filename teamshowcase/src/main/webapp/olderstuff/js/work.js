var workJson = (typeof paramWorkJson === "undefined")? paramWorkJson: "work.json";

var casesJson = (paramCasesJson)? paramCasesJson: "cases.json";

var app = {

	init : function(){
		app.jsonData();
		app.jsonCaseStudyData();
	},
	jsonData : function(){

		if((navigator.userAgent.match(/iPhone/i)) || (navigator.userAgent.match(/iPod/i)) || (navigator.userAgent.match(/iPad/i))) {
			
			//Load JSON
			$.getJSON(workJson, function(data) {

				//First for to each person
				for (var i = 0; i < data.work.length; i++) {
					$( "#work" ).addClass('border').append('<div class="imageContent border"><img src="work/jpg/'+(i+1)+'.jpg" width="100%" alt="Example Image"/></div>');
				}

				$('.imageContent').delay(400).fadeIn();
				$("#footer").fadeIn(400);

			});
		}else{
			for (var i = 0; i < 12; i++) {
				$( "#work" ).append('<div class="flashContent"><object width="300" height="250"><param name="testing" value="work/testing.swf" /><param name="FlashVars" value="var1=work/'+(i+1)+'" /><embed bgcolor="#000" wmode="transparent" src="work/testing.swf" width="300" height="250" FlashVars="var1=work/'+(i+1)+'"/></object></div>');
			}

			$('.flashContent').delay(500).fadeIn();
			$("#footer").fadeIn(400);
		}

		$('#work').delay(1000).fadeIn();
	},
	jsonCaseStudyData : function(){
		//Load JSON
		$.getJSON(casesJson, function(data) {
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

			$('#cases').delay(1500).fadeIn();
		});
	}
};

$(document).ready(function(){
	app.init();
});