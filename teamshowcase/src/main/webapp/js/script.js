var app = {
	init : function(){
		app.jsonData();
	},
	jsonData : function(){
		//Distance between skillname and bar
		var spaceSkillNames = 10;

		//Load JSON
        console.log(paramTeamJson);
        var teamJson = (paramTeamJson)? paramTeamJson: "team.json";
        console.log(teamJson);

		$.getJSON(teamJson, function(data) {

			var skillsForTag = [],
				skillsNamesForTag = [],
				skillString, skillStingTag;

			//First for to each person
			for (var i = 0; i < data.person.length; i++) {
				//console.log(data.person[i].name);
				$( "#team" ).append('<div class="person" data-person="'+i+'"> <div class="head"> <div class="photo"><span class="image-border"></span> <img src="images/crew/'+data.person[i].photo+'"/> </div> <div class="info"> <p class="person-name">'+data.person[i].name+'</p> <p class="person-position">'+data.person[i].position+'</p> <a class="person-email" href="mailto:'+data.person[i].email+'">'+data.person[i].email+'</a> </div> </div> <div class="subtitles"> <p class="section-title-skill">Knowledge</p> <p class="section-subtitle">experience &amp; academics</p> </div> <div class="chart chart-'+i+'"><div class="line line'+i+'"></div></div></div>');

				//Second for to each category
				for (var k = 0; k < data.person[i].categories.length; k++) {
					//console.log(data.person[i].categories[k].categoryName)
					$('.chart-'+i).append('<div class="skillset category-'+k+'"><span class="categoryTitle">'+data.person[i].categories[k].categoryName+'</span></div>');

					//Third for to each skill per category
					for (var j = 0; j < data.person[i].categories[k].categoryOps.length; j++) {
						//console.log(data.person[i].categories[k].categoryOps[j].name);

						if(data.person[i].categories[k].categoryOps[j].tag){
							skillString = data.person[i].categories[k].categoryOps[j].tag;
						}else{
							skillString = data.person[i].categories[k].categoryOps[j].name;
						}
							skillStingTag = skillString.toString();

							var tagSkillName = data.person[i].categories[k].categoryOps[j].name;

						$('.chart-'+i+' > .category-'+k).append('<span class="skill '+skillStingTag.split(' ').join('-').toLowerCase()+'" data-awidth="'+Math.round(parseInt(data.person[i].categories[k].categoryOps[j].value) *2.43)+'"><span class="skillnames" style="left:'+((parseInt(data.person[i].categories[k].categoryOps[j].value) *2.43) + spaceSkillNames)+'px">'+data.person[i].categories[k].categoryOps[j].name+'</span></span>');
						
						$('.chart-'+i).parent().addClass(skillStingTag.split(' ').join('-').toLowerCase());

						if(skillsForTag.indexOf(skillStingTag)==-1){
						  skillsForTag.push(skillStingTag);
						}

						if(skillsNamesForTag.indexOf(tagSkillName)==-1){
						  skillsNamesForTag.push(tagSkillName);
						}
					}

					//Change line height
					var heightOne = $(".category-0").height();
					var heightTwo = $(".category-1").height();
					var heightThree = $(".category-2").height();
					var height = (heightOne + heightTwo + heightThree);
				}
			}

			app.beginAnimations();

			for (var i = 0; i < skillsForTag.length; i++) {
				$('#tags ul').append('<li class="skill-tag" data-filter="'+skillsForTag[i].split(' ').join('-').toLowerCase()+'">'+skillsNamesForTag[i]+'</li>');
			};

			$(".skill").each(function( index ) {
				var dataNum = $(this).data('awidth');
				$(this).animate({
					width : dataNum
				}, 1000);
			});

			$("#tags").fadeIn(600);
			$(".subtitles").fadeIn(1000);
			$(".chart").fadeIn(1000);
			$('.skillnames').delay(1000).fadeIn(1000);
			
			$(".secondtitle").fadeIn(400);
			$("#second-chart").fadeIn(600);
			$("#footer").fadeIn(400);

			app.intermedialFunctions();
			app.events();
			app.stickybar();
		});
	},
	intermedialFunctions : function(){
		app.heightAdjustments();
	},
	heightAdjustments : function(){
		$(".person").each(function( index ) {
			var heightPerson = $(this).height();
			$(".line"+index).height(heightPerson-180);
			//console.log(heightPerson);
		});
	},
	beginAnimations : function(){
		$(".photo").fadeIn(1000);
		$(".person-name").fadeIn(1000);
		$(".person-position").fadeIn(1000);
		$(".person-email").fadeIn(1000);
	},
	resetValues : function(){
		$(".skill").animate({
			width : 1
		});
	},
	events : function(){
		$('.skill-tag').click(function(){
			var dataTag = $(this).data('filter'),
				dataSkillTag = $(this).data('skilltag');

			//Reset active
			$('.skill-tag').removeClass('active');
			$(this).addClass('active');
			$('.skill').removeClass('skillSelected');

			if(dataTag == 'all'){
				$('.person').show();
			}else{
				$('.person').show();
				$('.'+dataTag).addClass('skillSelected');
				$('.person').not('.'+dataTag).hide();
			}
		});
	},
	stickybar : function(){
		$(document).scroll(function() {
			var scrollData = $(document).scrollTop();

			if(scrollData > 294){
				$('#tags').css({
					'position' : 'fixed',
					'top' : 0,
					'left' : '5%',
					'padding-top' : 20,
					'padding-bottom' : 20
				});

				$('#team').css({
					'margin-top': 150
				});
			}else{
				$('#tags').css({
					'position':'static',
					'padding-top' : 0,
					'padding-bottom' : 0
				});

				$('#team').css({
					'margin-top': 40
				});
			}
		});
	},
	calculator : function(){
		var calculatorHeight = $('#calculator > .type').outerHeight();
		$('#calculator > .graph').height(calculatorHeight);

		$('input').on('focus', function(){

			if($(this).val() === '0'){
				$(this).val('');
				$(this).addClass('active-number');
			}

			$(this).siblings().addClass('selected');
		});

		$('input').on('blur', function() {
			if($(this).val() == '' || $(this).val() == '0'){
				$(this).siblings().removeClass('selected');
				$(this).val('0');
				$(this).removeClass('active-number');
			}
		});

		$('.icon').click(function(){
			if($(this).hasClass('selected')){
				if($(this).siblings('input').val() == '0'){
					$(this).removeClass('selected');
					$(this).siblings('input').removeClass('active-number');
					app.doCalculation();
				}else{
					$(this).removeClass('selected');
					$(this).siblings('input').removeClass('active-number').addClass('gray-number').prop('disabled', true);
					app.doCalculation();
				}
			}else{
				$(this).addClass('selected');
				$(this).siblings('input').removeClass('gray-number').addClass('active-number').prop('disabled', false);
				app.doCalculation();
			}
		});

		$('input').on('keyup', function(){
			app.doCalculation();
		});
	},
	doCalculation : function(){
		importe_total = 0;

		$('input.active-number').each(
			function(index, value) {
					if (isNaN($(this).val())){
						alert('Please enter only numbers!');
					}else{
					var number2 = $(this).attr('data-hour');
					importe_total = importe_total + eval($(this).val() * number2);
				}
			}
		);

		//console.log(importe_total);

		$('.result-number').text(importe_total+' hrs');
		$('.result-number-days').text((importe_total/8)+' days');
	}
};

$(document).ready(function(){
	app.init();
	app.calculator();
});